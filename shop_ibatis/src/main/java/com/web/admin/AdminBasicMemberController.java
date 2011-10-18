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

	@RequestMapping("/admin/basic/memberInfo_x")
	public String memberInfo_x(HttpServletRequest request) {

		//기타 정보는 파일로 남긴다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/memberInfo";
		HashMap<String, String> basicinfo = new HashMap<String, String>();
		basicinfo.put("LoginLimitCnt", request.getParameter("LoginLimitCnt"));//관리자단 로그인 실패 제한 횟수
		basicinfo.put("admin_title", request.getParameter("admin_title"));//쇼핑몰명(한글)
		basicinfo.put("admin_title_e", request.getParameter("admin_title_e"));//쇼핑몰명(영문)
		basicinfo.put("company_domain", request.getParameter("company_domain"));//쇼핑몰명
		basicinfo.put("home_url", request.getParameter("home_url"));//홈페이지 주소
		basicinfo.put("admin_email", request.getParameter("admin_email"));//대표이메일
		basicinfo.put("admin_tel", request.getParameter("admin_tel"));//대표전화번호
		basicinfo.put("company_name", request.getParameter("company_name"));//상호명
		basicinfo.put("president", request.getParameter("president"));//대표자번호
		basicinfo.put("company_num", request.getParameter("company_num"));//사업자등록번호
		basicinfo.put("company_licence_num", request.getParameter("company_licence_num"));//통신판매업신고
		basicinfo.put("customer_service_tel", request.getParameter("customer_service_tel"));//고객상담전화
		basicinfo.put("customer_service_fax", request.getParameter("customer_service_fax"));//팩스번호
		basicinfo.put("company_address", request.getParameter("company_address"));//사업장주소
		basicinfo.put("company_domain", request.getParameter("company_domain"));//
		basicinfo.put("company_domain", request.getParameter("company_domain"));//
		basicinfo.put("company_domain", request.getParameter("company_domain"));//
		basicinfo.put("company_domain", request.getParameter("company_domain"));//

		serialization.Serialize(outPath, basicinfo);

		return "redirect:/admin/basic/memberInfo.do";
	}
}
