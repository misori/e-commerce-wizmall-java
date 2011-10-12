package com.web.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.DelivererDAO;
import com.domain.Buyers;
import com.domain.Point;
import com.service.BuyerService;
import com.service.PointService;
import com.util.Constants;
import com.util.PageNavigation;
import com.util.StringUtil;

@Controller
public class AdminOrderController {



	@Autowired
	private PointService pointService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private DelivererDAO delivererDAO;

	/**
	 * 주문정보리스트 폼
	 * @return
	 */
	@RequestMapping("/admin/order/orderList")
	public ModelAndView payInfo(HttpServletRequest request) {
		ModelAndView mav		= new ModelAndView();
		StringUtil stringUtil	= new StringUtil();

		Integer cp			= stringUtil.strToint(request.getParameter("cp"));
		String s_title		= stringUtil.emptyToNull(request.getParameter("s_title"));
		String s_keyword	= stringUtil.emptyToNull(request.getParameter("s_keyword"));
		String s_date		= stringUtil.emptyToNull(request.getParameter("s_date"));
		String e_date		= stringUtil.emptyToNull(request.getParameter("e_date"));

	    if(cp < 1) cp = 1;
		Integer blockList	= 10;
		Integer blockPage	= 10;

	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("s_title", s_title);
	    if(s_keyword != null)	params.put("s_keyword", "%"+s_keyword+"%");
		params.put("s_date", s_date);
		params.put("e_date", e_date);
		params.put("s_order", request.getParameter("s_order"));
	    //현재 총게시물수를 구해옮
	    int	tc	= buyerService.countAllBuyerList(params);


	 // 객체를 생성한다 (현재페이지, 전체글수, 페이지당표시할 글의수, 한번에 표시할 페이징블록수)
		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);
		mav.addObject("pageNav", pageNav.getParams());




		params.put("StartRow",Integer.toString(pageNav.getStartRow()));//database limit 절
		params.put("blockList",Integer.toString(blockList));
		mav.addObject("info", buyerService.getAllBuyerList(params));

		mav.addObject("orderStatus", Constants.OrderStatus());//orderStatus에 대한 인자값 전달

		params.put("s_keyword", request.getParameter("s_keyword"));
		mav.addObject("params", params);

		mav.setViewName("admin/order/order_list.jsp");

		return mav;
	}

	/**
	 * 주문상세보기 폼
	 * @return
	 */
	@RequestMapping("/admin/order/orderView")
	public ModelAndView orderView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int tid	= Integer.parseInt(request.getParameter("tid"));

		Buyers buyers	= buyerService.getBuyerByTid(tid);
		mav.addObject("info", buyers);
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


		//주문상태
		mav.addObject("orderStatus", Constants.OrderStatus());
		System.out.println("Constants.OrderStatus():"+Constants.OrderStatus());
		//택배사 정보 가져오기
		mav.addObject("deliverer", delivererDAO.getAllDelivererList());


		System.out.println(mav);
		mav.setViewName("admin/order/order_view.jsp");
		return mav;
	}

	/**
	 * 주문리스트 엑셀 다운로드
	 * @param request
	 * @return
	 * @throws WriteException
	 * @throws IOException
	 */
	@RequestMapping("/admin/order/orderExcelWrite")
	public void orderExcelWrite(HttpServletRequest request, HttpServletResponse response) throws WriteException, IOException {

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=orderList.xls");
		WritableWorkbook myWorkbook = Workbook.createWorkbook(response.getOutputStream());

		HashMap<String, String> params = new HashMap<String, String>();
		List<Buyers> buyers	= buyerService.getAllBuyerList(params);
		Iterator<Buyers> iter = buyers.iterator();

		WritableSheet mySheet = myWorkbook.createSheet("OrderList", 0); // WritableSheet는 인터페이스

		// 라벨을 이용하여 해당 셀에 정보 넣기 시작
		mySheet.addCell(new Label(0, 0, "주문번호")); // 쉬트의 addCell 메소드를 사용하여 삽입
		mySheet.addCell(new Label(1, 0, "구매금액")); //
		mySheet.addCell(new Label(2, 0, "결제방식")); //
		mySheet.addCell(new Label(3, 0, "주문자아이디"));
		mySheet.addCell(new Label(4, 0, "주문자명"));
		mySheet.addCell(new Label(5, 0, "연락처"));
		mySheet.addCell(new Label(6, 0, "주문일시"));

		int no = 1;
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		while(iter.hasNext()) {
			Buyers b = iter.next();
			mySheet.addCell(new Label(0, no, b.getOrderid())); // 주문번호
			mySheet.addCell(new Label(1, no, Integer.toString(b.getTotalamount()))); // 구매금액
			mySheet.addCell(new Label(2, no, b.getPaymethod())); // 결제방식
			mySheet.addCell(new Label(3, no, b.getMemberid())); // 주문자아이디
			mySheet.addCell(new Label(4, no, b.getSname())); // 주문자명
			mySheet.addCell(new Label(5, no, b.getRtel1())); // 연락처
			String s	= formatter.format( b.getBuydate());
			mySheet.addCell(new Label(6, no, s)); // 주문일시
			no++;
			// iter.remove();
		}
		// 라벨을 이용하여 해당 셀에 정보 넣기 끝
		myWorkbook.write(); // 준비된 정보를 엑셀 포멧에 맞게 작성
		myWorkbook.close(); // 처리 후 메모리에서 해제 처리
	}

	/**
	 *  주문정보에서 배송지 주소 변경
	 * @param request
	 * @param response
	 * @throws WriteException
	 * @throws IOException
	 */
	@RequestMapping("/admin/order/order_ch_address")
	public void order_ch_address(HttpServletRequest request, HttpServletResponse response) throws WriteException, IOException {
		int tid				= Integer.parseInt(request.getParameter("tid"));
		String RZip1		= request.getParameter("Rzip1");
		String RZip2		= request.getParameter("RZip2");
		String RAddress1	= request.getParameter("RAddress1");
		String RAddress2	= request.getParameter("RAddress2");

		Buyers buyers	= buyerService.getBuyerByTid(tid);
		buyers.setRzip(RZip1+"-"+RZip2);
		buyers.setRaddress1(RAddress1);
		buyers.setRaddress2(RAddress2);

		buyerService.saveBuyers(buyers);
	}
	/**
	 *  주문정보에서 송장 및 택배사 변경
	 * @param request
	 * @param response
	 * @throws WriteException
	 * @throws IOException
	 */
	@RequestMapping("/admin/order/order_ch_delever")
	public void order_ch_delever(HttpServletRequest request, HttpServletResponse response) throws WriteException, IOException {
		int tid				= Integer.parseInt(request.getParameter("tid"));
		int Deliverer	= Integer.parseInt(request.getParameter("Deliverer"));
		String InvoiceNo	= request.getParameter("InvoiceNo");

		Buyers buyers	= buyerService.getBuyerByTid(tid);
		buyers.setDeliverer(Deliverer);
		buyers.setInvoiceno(InvoiceNo);

		buyerService.saveBuyers(buyers);
	}

	/**
	 *  주문정보에서 주문상태 변경
	 * @param request
	 * @param response
	 * @throws WriteException
	 * @throws IOException
	 */
	@RequestMapping("/admin/order/order_ch_orderstatus")
	public void order_ch_orderstatus(HttpServletRequest request, HttpServletResponse response) throws WriteException, IOException {
		int tid			= Integer.parseInt(request.getParameter("tid"));
		int OrderStatus	= Integer.parseInt(request.getParameter("OrderStatus"));

		Buyers buyers	= buyerService.getBuyerByTid(tid);

		String user_id = buyers.getMemberid();
		Integer p_point	= buyers.getAmountpoint();
		if(user_id != null){
			Point point	= new Point();

			point.setMemo("상품구매포인트 지급");
			point.setOrder_id(buyers.getOrderid());
			point.setPtype(0);//즉시실행
			point.setUser_id(user_id);

			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			date = cal.getTime();
			point.setWdate(date);
			//현재 주문상태가 50이 아닌데에서 50으로 변경되면 포인트를 올려준다.
			//하지만 50인상태에서 다른곳으로 변경 	60: 물품반송, 70:주문삭제 인경우 혹은 다른 경우에는 포인트를 빼준다.
			int c_orderstatus = buyers.getOrderstatus();
			if(c_orderstatus == 50 && OrderStatus != 50){//포인트 삭제
				point.setFlag(22);
				point.setPoint(-p_point);
				pointService.insertPoint(point);
			}else if(OrderStatus == 50 && c_orderstatus != 50){//포인트 지급
				point.setFlag(21);
				point.setPoint(p_point);
				pointService.insertPoint(point);
			}
		}
		buyers.setOrderstatus(OrderStatus);
		buyerService.saveBuyers(buyers);

	}
}
