package com.web.admin;

import java.io.File;
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

		//스킨폴더정보를 입력한다.
		String Path				= Constants.AbsolutePath+"../WEB-INF/pages/module/realnameModule";
		File f = new File(Path);
		String[] modulefolder = f.list();
		mav.addObject("modulefolder", modulefolder);

		//기본 정보를 가져온다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/memberInfo";
		HashMap<String, String> basicinfo = serialization.unSerialize(outPath, "hashMap");
		mav.addObject("memberinfo", basicinfo);

		mav.setViewName("admin/basic/memberinfo.jsp");
		//System.out.println(mav);
		return mav;
	}

	@RequestMapping("/admin/basic/memberInfo_x")
	public String memberInfo_x(HttpServletRequest request) {

		//기타 정보는 파일로 남긴다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/memberInfo";
		HashMap<String, String> memberinfo = new HashMap<String, String>();
		memberinfo.put("EGender",			request.getParameter("EGender"));//성별 출력
		memberinfo.put("CGender",			request.getParameter("CGender"));//성별 필수
		memberinfo.put("ECompany",			request.getParameter("ECompany"));//회사명 출력
		memberinfo.put("CCompany",			request.getParameter("CCompany"));//회사명 필수
		memberinfo.put("ECompnum",			request.getParameter("ECompnum"));//사업자등록번호 출력
		memberinfo.put("CCompnum",			request.getParameter("CCompnum"));//사업자등록번호 필수
		memberinfo.put("ETel2",				request.getParameter("ETel2"));//이동전화 출력
		memberinfo.put("CTel2",				request.getParameter("CTel2"));//이동전화 필수
		memberinfo.put("EMailReceive",		request.getParameter("EMailReceive"));//메일수신 출력
		memberinfo.put("CMailReceive",		request.getParameter("CMailReceive"));//메일수신 필수
		memberinfo.put("EBirthDay",			request.getParameter("EBirthDay"));//생년월일 출력
		memberinfo.put("CBirthDay",			request.getParameter("CBirthDay"));//생년월일 필수
		memberinfo.put("EMarrStatus",		request.getParameter("EMarrStatus"));//결혼여부 출력
		memberinfo.put("CMarrStatus",		request.getParameter("CMarrStatus"));//결혼여부 필수
		memberinfo.put("EJob",				request.getParameter("EJob"));//직업 출력
		memberinfo.put("CJob",				request.getParameter("CJob"));//직업 필수
		memberinfo.put("EScholarship",		request.getParameter("EScholarship"));//학력 출력
		memberinfo.put("CScholarship",		request.getParameter("CScholarship"));//학력 필수
		memberinfo.put("EAddress3",			request.getParameter("EAddress3"));//직장주소 출력
		memberinfo.put("CAddress3",			request.getParameter("CAddress3"));//직장주소 필수
		memberinfo.put("ERecID",			request.getParameter("ERecID"));//추천인 출력
		memberinfo.put("CRecID",			request.getParameter("CRecID"));//추천인 필수
		memberinfo.put("realnameModule",	request.getParameter("realnameModule"));//실명인증서비스
		memberinfo.put("realnameID",		request.getParameter("realnameID"));//인증아이디
		memberinfo.put("realnamePWD",		request.getParameter("realnamePWD"));//인증패스워드
		memberinfo.put("EGrantSta",			request.getParameter("EGrantSta"));//회원가입시 인증: 03, 회원가입후 인증 : 04
		memberinfo.put("INCLUDE_MALL_SKIN",	request.getParameter("INCLUDE_MALL_SKIN"));//몰인클루드(yes or no)
		memberinfo.put("SendMail",			request.getParameter("SendMail"));//회원가입메일발송(yes or no)
		memberinfo.put("EPoint",			request.getParameter("EPoint"));//회원가입시 포인트
		memberinfo.put("RPoint",			request.getParameter("RPoint"));//회원추천시 포인트
		memberinfo.put("LPoint",			request.getParameter("LPoint"));//로그인시 포인트
		serialization.Serialize(outPath, memberinfo);

		return "redirect:/admin/basic/memberInfo.do";
	}
}
