package com.web.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.Members;

@Controller
public class AuthController {


	/**
	 * Permission denied Alert Page
	 * @return
	 */
	@RequestMapping("/auth/denied")
	public ModelAndView idCheck(HttpServletRequest request) {
		ModelAndView mav	= new ModelAndView();
		mav.setViewName("auth/permisiondenied.jsp");
		return mav;
	}
}
