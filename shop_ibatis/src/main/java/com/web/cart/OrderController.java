package com.web.cart;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.Buyers;
import com.domain.Members;
import com.service.AccountService;
import com.service.BuyerService;
import com.service.CartService;
import com.service.MembersService;
import com.util.Constants;
import com.util.Serialization;

@Controller
public class OrderController {


	@Autowired
	private CartService cartService;

	@Autowired
	private MembersService membersService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private AccountService accountService;
	/*
	 * 주문서 작성 폼
	 */
	@RequestMapping("/cart/orderForm")
	public ModelAndView orderForm(HttpServletRequest request, Principal principal) {
		//HttpSession session = request.getSession();
		//String orderId; //이것이 없을 경우 튕겨내는 프로그램 필요

		HttpSession session = request.getSession();
		String orderId;//각각 세션에 입력된 정보 가져오기
		String user_id = null;
		int point	= 0;
		//System.out.println("principal.getName():"+principal.getName());
		if(principal != null) user_id = principal.getName();
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("session-orderid") == null){
			//잘못된 경로 접근
		}else{

			orderId	= session.getAttribute("session-orderid").toString();
			//배송비및 기타를 제외한 장바구니에 담긴 상품 총액만을 계산한다.
			int total	= cartService.getPriceByOrderId(orderId);
			mav.addObject("total", total);

			//현재 회원의 포인트를 구한다.
			if(user_id != null){
				point	= membersService.getMemberPointByUserid(user_id);
				mav.addObject("point", point);
			}
			//배송비를 구한다.
			int deleverFee	= SetDeliveryFee(total);
			mav.addObject("deleverFee", deleverFee);

			//총결제금액(상품총액 + 배송비)
			int totalSum	= total + deleverFee;
			mav.addObject("totalSum", totalSum);

			//결제관련 총정보
			Serialization serialization	= new Serialization();
			String outPath				= Constants.AbsolutePath+"config/payinfo";
			HashMap<String, String> payinfo = serialization.unSerialize(outPath, "hashMap");
			mav.addObject("payinfo", payinfo);

			//입금 계좌 정보
			mav.addObject("accountInfo", accountService.getAllAccountList());



			//System.out.println("total:"+cartDAO.getPriceByOrderId(orderId));
			Buyers buyers =new Buyers();
			buyers	= buyerService.getBuyerByOrderId(orderId);
			//System.out.println("buyers:"+buyers);
			if(buyers == null){//현재 등록된 정보가 없는 경우(수정이 아닌경우) 기본 회원 정보를 불러와서 입력한다.
				//로그인 회원인지 체크
				//if(session.getAttribute("session-userid") != null){
				if(user_id != null){
					//userId	= session.getAttribute("session-userid").toString();
					Members mem =new Members();
					mem	= membersService.getMemberByUserid(user_id);
				//	System.out.println("user_id :"+user_id);
				//	System.out.println("mem.getUser_name :"+mem.getUser_name());

					buyers =new Buyers();
					buyers.setSname(mem.getUser_name());
					buyers.setSaddress1(mem.getUser_address1());
					buyers.setSaddress2(mem.getUser_address2());
					buyers.setSemail(mem.getUser_email());

					//우편번호
					String[] user_zip1	= mem.getUser_zip1().split("-");
					mav.addObject("user_zip1", user_zip1);

					//전화번호1
					String[] user_tel1	= mem.getUser_tel1().split("-");
					mav.addObject("user_tel1", user_tel1);

					//전화번호2
					String[] user_tel2	= mem.getUser_tel2().split("-");
					mav.addObject("user_tel2", user_tel2);
				}
			}else{
				//주문 고객 정보
				//우편번호
				String[] user_zip1	= buyers.getSzip().split("-");
				mav.addObject("user_zip1", user_zip1);

				//전화번호1
				String[] user_tel1	= buyers.getStel1().split("-");
				mav.addObject("user_tel1", user_tel1);

				//전화번호2
				String[] user_tel2	= buyers.getStel2().split("-");
				mav.addObject("user_tel2", user_tel2);

				//배송지 정보
				//우편번호
				String[] r_zip1	= buyers.getRzip().split("-");
				mav.addObject("r_zip1", r_zip1);

				//전화번호1
				String[] r_tel1	= buyers.getRtel1().split("-");
				mav.addObject("r_tel1", r_tel1);

				//전화번호2
				String[] r_tel2	= buyers.getRtel2().split("-");
				mav.addObject("r_tel2", r_tel2);
			}
			mav.addObject("buyers", buyers);

		}


		mav.setViewName("cart/orderForm.jsp");
		return mav;
	}

	/**
	 * 배송비를 구한다.
	 * @param total
	 * @return
	 */
	private Integer SetDeliveryFee(int total){
		//기본 정보를 가져온다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/payinfo";
		HashMap<String, String> payinfo = serialization.unSerialize(outPath, "hashMap");
		int DeliveryFee				= 0;
		if(payinfo.get("TACKBAE_ALL").equals("DISABLE")){//배송비 적용안함
			DeliveryFee				= 0;
		}else if(payinfo.get("TACKBAE_ALL").equals("ENABLE")){//배송비 적용
			int deliveryFeeLimit	= Integer.parseInt(payinfo.get("TACKBAE_CUTLINE"));
			if(total >= deliveryFeeLimit) DeliveryFee = 0;
			else DeliveryFee		= deliveryFeeLimit;
		}else if(payinfo.get("TACKBAE_ALL").equals("ALL")){//모두 배송비 적용
			DeliveryFee				= Integer.parseInt(payinfo.get("TACKBAE_MONEY"));
		}else if(payinfo.get("TACKBAE_ALL").equals("PER")){//제품당 배송비 적용

		}

		return DeliveryFee;
	}

	/**
	 * 주문 데이타입력 프로세서
	 */
	@RequestMapping("/cart/orderInsert")
	public String orderInsert(HttpServletRequest request, Principal principal) {
		System.out.println("orderInsert Start");
		HttpSession session = request.getSession();

		String user_id = null;
		if(principal != null) user_id = principal.getName();
		String orderId	= session.getAttribute("session-orderid").toString();

		//Date date = new Date();
		//Calendar cal = Calendar.getInstance();
		//cal.setTime(date);
		//date = cal.getTime();


		//logger.info(members);
		Buyers buyer =new Buyers();

		//구매자 정보 시작
		buyer.setSname(request.getParameter("SName"));
		buyer.setSemail(request.getParameter("SEmail"));
		buyer.setStel1(request.getParameter("STel1_1")+"-"+request.getParameter("STel1_2")+"-"+request.getParameter("STel1_3"));
		buyer.setStel2(request.getParameter("STel2_1")+"-"+request.getParameter("STel2_2")+"-"+request.getParameter("STel2_3"));
		buyer.setSzip(request.getParameter("SZip1")+"-"+request.getParameter("SZip2"));
		buyer.setSaddress1(request.getParameter("SAddress1"));
		buyer.setSaddress2(request.getParameter("SAddress2"));


		//도착지 정보 시작
		buyer.setRname(request.getParameter("RName"));
		buyer.setRtel1(request.getParameter("RTel1_1")+"-"+request.getParameter("RTel1_2")+"-"+request.getParameter("RTel1_3"));
		buyer.setRtel2(request.getParameter("RTel2_1")+"-"+request.getParameter("RTel2_2")+"-"+request.getParameter("RTel2_3"));
		buyer.setRzip(request.getParameter("RZip1")+"-"+request.getParameter("RZip2"));
		buyer.setRaddress1(request.getParameter("RAddress1"));
		buyer.setRaddress2(request.getParameter("RAddress2"));
		buyer.setMessage(request.getParameter("Message"));//addslashes 입력


		//결제 방식
		buyer.setPaymethod(request.getParameter("paytype"));//online, card, mobile, autobank
		buyer.setBankinfo(request.getParameter("BankInfo"));
		buyer.setInputer(request.getParameter("Inputer"));


		//결제 금액
		String pointamount = request.getParameter("pointamount");
		if(pointamount == "" || pointamount == null) pointamount = "0";

		String total_check = request.getParameter("total_check");
		if(total_check == "" || total_check == null) total_check = "0";

		String amount_online, amount_pg;
		if(request.getParameter("paytype") == "online"){
			amount_online	= total_check;
			amount_pg		= "0";
		}else{
			amount_online	= "0";
			amount_pg		= total_check;
		}

		String TOTAL_MONEY = request.getParameter("TOTAL_MONEY");
		if(TOTAL_MONEY == "" || total_check == null) TOTAL_MONEY = "0";

		buyer.setAmountpoint(Integer.parseInt(pointamount));
		buyer.setAmountonline(Integer.parseInt(amount_online));
		buyer.setAmountpg(Integer.parseInt(amount_pg));
		buyer.setTotalamount(Integer.parseInt(TOTAL_MONEY));


		buyer.setOrderid(orderId);
		buyer.setOrderstatus(0);//초기값 입력
		buyer.setMemberid(user_id);
		//buyer.setPaydate(request.getParameter("SName"));
		//buyer.setBuydate(cal.getTime());

		System.out.println(buyer);
		buyerService.saveBuyers(buyer);

		return "redirect:/cart/orderDone.do";
	}

	/**
	 * 주문 완료 페이지
	 */
	@RequestMapping("/cart/orderDone")
	public ModelAndView orderDone(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("session-orderid", null);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart/orderDone.jsp");
		return mav;
	}

}
