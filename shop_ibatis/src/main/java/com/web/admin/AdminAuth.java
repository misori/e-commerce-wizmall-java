package com.web.admin;

public class AdminAuth {
	public AdminAuth() {
		//System.out.println("AdminController Start");
		//로그인 및 관리자 등급 체크
		/*
		 * HttpSession session = request.getSession();
		//Enumeration e = session.getAttributeNames();
		if(session.getAttribute("session-userid") == null){
			System.out.println("로그인이 되어있지 않습니다.");
			response.setContentType("text/html;charset:UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print("<script>alert('등록된 아이디가 없습니다.')</script>");
			response.sendRedirect(response.encodeRedirectURL("./admin/AdminLogin.blank") );
			// return "forward:/member/memberReg";

		}else{
			String value = session.getAttribute("session-userid").toString();
			System.out.println("session.getAttribute('session-userid').toString():"+value);
		}
		*/
	}
}
