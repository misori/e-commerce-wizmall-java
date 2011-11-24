package com.web.member;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.DelivererDAO;
import com.domain.Buyers;
import com.domain.Members;
import com.security.CustomUser;
import com.service.BuyerService;
import com.util.PageNavigation;
import com.util.StringUtil;
import com.util.Constants;


@Controller
public class MemberShopController {
	@Autowired
	private BuyerService buyerService;

	@Autowired
	private DelivererDAO delivererDAO;
	/**
	 * 아이디 존재여부 체크 팝업 띄우기
	 * 현재 이페이지는 관리자단과 동일하게 사용하고 있음. 추후에 분리 예정
	 * 관리자단에서 넘어올 경우 mid 가 넘어옮
	 * @return
	 *
	 */
	@RequestMapping("/member/order_list")
	public ModelAndView order_list(HttpServletRequest request, Authentication auth) {
		ModelAndView mav	= new ModelAndView();
		StringUtil stringUtil	= new StringUtil();
		HashMap<String, String> params = new HashMap<String, String>();
		Integer cp			= stringUtil.strToint(request.getParameter("cp"));
		if(cp < 1) cp = 1;
		Integer blockList	= 10;
		Integer blockPage	= 10;
		//String login_id	= null;
		//if(SecurityContextHolder.getContext().getAuthentication() != null) user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		if(auth != null){
			Members member	= ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMembersInfo();
			//login_id = auth.getName();
			//login_grade	= member.getUser_grade();
			params.put("login_name", member.getUser_name());
		}

		//params.put("memberid", auth.getName());
		params.put("memberid", SecurityContextHolder.getContext().getAuthentication().getName());
		int tc	= buyerService.countBuyerList(params);
		//페이지 네비 게이션 생성
		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);
		mav.addObject("pageNav", pageNav.getParams());

		//리스트 내용을 가져온다.
		params.put("StartRow",Integer.toString(pageNav.getStartRow()));//database limit 절
		params.put("blockList",Integer.toString(blockList));
		mav.addObject("info", buyerService.getBuyerList(params));

		mav.addObject("params", params);
		mav.setViewName("member/shop/order_list.jsp");
		return mav;
	}

	/**
	 * /admin/order/orderView 참조
	 * @param request
	 * @param auth
	 * @return
	 */
	@RequestMapping("/member/order_view")
	public ModelAndView order_view(HttpServletRequest request, Authentication auth) {
		/*
		ModelAndView mav	= new ModelAndView();
		StringUtil stringUtil	= new StringUtil();
		HashMap<String, String> params = new HashMap<String, String>();
		Integer cp			= stringUtil.strToint(request.getParameter("cp"));
		if(cp < 1) cp = 1;
		Integer blockList	= 10;
		Integer blockPage	= 10;
		//String login_id	= null;
		if(auth != null){
			Members member	= ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMembersInfo();
			//login_id = auth.getName();
			//login_grade	= member.getUser_grade();
			params.put("login_name", member.getUser_name());
		}

		params.put("memberid", SecurityContextHolder.getContext().getAuthentication().getName());
		int tc	= buyerService.countBuyerList(params);
		//페이지 네비 게이션 생성
		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);
		mav.addObject("pageNav", pageNav.getParams());

		//리스트 내용을 가져온다.
		params.put("StartRow",Integer.toString(pageNav.getStartRow()));//database limit 절
		params.put("blockList",Integer.toString(blockList));
		mav.addObject("info", buyerService.getBuyerList(params));

		mav.addObject("params", params);

		*/

		ModelAndView mav = new ModelAndView();
		String oid	= request.getParameter("oid");

		Buyers buyers	= buyerService.getBuyerByOrderId(oid);
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


		mav.setViewName("member/shop/order_view.jsp");
		return mav;
	}


}
