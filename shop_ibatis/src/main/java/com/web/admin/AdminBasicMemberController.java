package com.web.admin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.util.Constants;
import com.util.Serialization;

@Controller
public class AdminBasicMemberController {

	@RequestMapping("/admin/basic/memberInfo")
	public ModelAndView memberInfo(HttpServletRequest request) {//Principal principal  final String userId = principal.getName();
		ModelAndView mav = new ModelAndView();



		//기본 정보를 가져온다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/memberInfo";
		HashMap<String, String> basicinfo = serialization.unSerialize(outPath, "hashMap");
		mav.addObject("basicinfo", basicinfo);

		mav.setViewName("admin/basic/memberinfo.jsp");
		//System.out.println(mav);
		return mav;
	}
}
