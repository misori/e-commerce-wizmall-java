package com.web.util;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.ZipcodeService;

@Controller
public class ZipController {

	@Autowired
	private ZipcodeService zipcodeService;


	@RequestMapping("/util/FindZip")
	public ModelAndView findZip(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//form=memberForm&zip1=zip1_1&zip2=zip1_2&firstaddress=address1&secondaddress=address2
		String form				= request.getParameter("form");
		String zip1				= request.getParameter("zip1");
		String zip2				= request.getParameter("zip2");
		String firstaddress		= request.getParameter("firstaddress");
		String secondaddress	= request.getParameter("secondaddress");
		String searchStep		= request.getParameter("searchStep");
		String keyword 			= request.getParameter("keyword");

		if(searchStep == null){
			searchStep = "step1";
		}else if("step2".equals(searchStep)){
			mav.addObject("info", zipcodeService.getAddressByKeyword(keyword));
			//System.out.println("mav"+mav);
		}

		request.setAttribute("form",			form);
		request.setAttribute("zip1",			zip1);
		request.setAttribute("zip2",			zip2);
		request.setAttribute("firstaddress",	firstaddress);
		request.setAttribute("secondaddress",	secondaddress);
		request.setAttribute("searchStep",		searchStep);

		mav.setViewName("util/zip/FindZip.jsp");
		return mav;

	}
}
