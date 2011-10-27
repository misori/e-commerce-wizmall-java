package com.web.admin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.VisitMainService;
import com.service.VisitRefererService;

@Controller
public class AdminVisitController {
	@Autowired
	private VisitMainService visitMainService;

	@Autowired
	private VisitRefererService visitRefererService;

	@RequestMapping("/admin/visit/visit")
	public ModelAndView productWrite(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> params = new HashMap<String, String>();

		String no	= request.getParameter("no");
		if(no == null) no	= "1";


		params.put("no", no);

		mav.addObject("params", params);
		mav.setViewName("admin/visit/visit.jsp");

		return mav;
	}
}
