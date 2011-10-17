package com.web.member;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.Members;
import com.security.CustomUser;
import com.service.BuyerService;
import com.util.PageNavigation;
import com.util.StringUtil;


@Controller
public class MemberShopController {
	@Autowired
	private BuyerService buyerService;
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
		if(auth != null){
			Members member	= ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMembersInfo();
			//login_id = auth.getName();
			//login_grade	= member.getUser_grade();
			params.put("login_name", member.getUser_name());
		}

		params.put("memberid", auth.getName());
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

	@RequestMapping("/member/order_view")
	public ModelAndView order_view(HttpServletRequest request, Authentication auth) {
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

		params.put("memberid", auth.getName());
		int tc	= buyerService.countBuyerList(params);
		//페이지 네비 게이션 생성
		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);
		mav.addObject("pageNav", pageNav.getParams());

		//리스트 내용을 가져온다.
		params.put("StartRow",Integer.toString(pageNav.getStartRow()));//database limit 절
		params.put("blockList",Integer.toString(blockList));
		mav.addObject("info", buyerService.getBuyerList(params));

		mav.addObject("params", params);
		mav.setViewName("member/shop/order_view.jsp");
		return mav;
	}


}
