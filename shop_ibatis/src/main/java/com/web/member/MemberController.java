package com.web.member;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.MembersDAO;
import com.domain.Members;
import com.domain.MembersGen;
import com.service.MembersGenService;
import com.service.MembersService;
import com.util.Constants;
import com.util.DateTimeUtil;
import com.util.Serialization;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	/*
	 * 세션 생성용
	 */
	//private Map<String, String> session;

	/**
	 * DAO injected by Spring that manages Member entities
	 *
	 */
	@Autowired
	private MembersDAO membersDAO;

	@Autowired
	private MembersService membersService;

	//@Autowired
	//private UserDetailsService userDetailsService

	/*
	 * @Autowired
	 * private UserDetailsService userDetailsService;
	 */


	@Autowired
	private MembersGenService membersgenService;


	/**
	 * 아이디 존재여부 체크 팝업 띄우기
	 * @return
	 */
	@RequestMapping("/member/idCheck")
	public ModelAndView idCheck(HttpServletRequest request) {
		ModelAndView mav	= new ModelAndView();
		String user_id		= request.getParameter("user_id");
		String result		= "true";
		if(user_id != null){
			Members mem =new Members();
			mem	= membersDAO.getMemberByUserid(user_id);
			if(mem == null){
				result		= "false";
			}
			request.setAttribute("user_id",			user_id);
			//System.out.println("mav"+mav);
		}
		request.setAttribute("result",			result);
		mav.setViewName("member/member_idcheck.jsp");
		return mav;
	}

	/**
	 * 등록폼 출력하기
	 */
	@RequestMapping("/member/memberReg")
	public ModelAndView memberReg(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		//DateTimeUtil dateTime = new DateTimeUtil();
		String curYear	= DateTimeUtil.getDateTimeByPattern("yyyy");
		//System.out.println("curYear:"+curYear);
		request.setAttribute("curYear",			curYear);
		request.setAttribute("MobileNum", Constants.MobileNum);
		request.setAttribute("PhoneNum", Constants.PhoneNum);
		request.setAttribute("MailAddress", Constants.MailAddress());
		//mav.addObject("members", new Members());
		//mav.addObject("newFlag", true);
		mav.setViewName("member/member_reg.jsp");

		return mav;
	}

	/**
	 * 회원 등록 실행
	 */
	@RequestMapping("/member/memberReg_x")
	//@RequestMapping(value = "/dopost.html", method = RequestMethod.POST)
	//public String saveMember(@ModelAttribute Members members) {
	public String saveMember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id	= null;
		if(session.getAttribute("session-userid") != null){
			user_id	= session.getAttribute("session-userid").toString();;
		}

		Members mem =new Members();
		mem.setUser_id(request.getParameter("user_id"));
		mem.setUser_passwd(request.getParameter("user_passwd"));
		mem.setUser_name(request.getParameter("user_name"));
		mem.setUser_grantsta("03");
		mem.setUser_point(0);
		mem.setUser_grade(100);
		mem.setUser_email(request.getParameter("email_1")+"@"+request.getParameter("email_2"));

		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		date = cal.getTime();
		mem.setUser_regdate(cal.getTime());

		//기타 회원 가입 정보 입력

		MembersGen memgen = new MembersGen();
		memgen.setUser_id(request.getParameter("user_id"));
		memgen.setUser_gender(request.getParameter("user_gender"));
		memgen.setUser_jumin1(Integer.parseInt(request.getParameter("user_jumin1")));
		memgen.setUser_jumin2(Integer.parseInt(request.getParameter("user_jumin2")));
		memgen.setUser_birthdate(request.getParameter("user_birthyy")+"/"+request.getParameter("user_birthmm")+"/"+request.getParameter("user_birthdd"));
		memgen.setUser_birthtype(Integer.parseInt(request.getParameter("user_birtytype")));
		memgen.setUser_zip1(request.getParameter("zip1_1")+"-"+request.getParameter("zip1_2"));
		memgen.setUser_address1(request.getParameter("address1"));
		memgen.setUser_address2(request.getParameter("address2"));
		memgen.setUser_tel1(request.getParameter("tel1_1")+"-"+request.getParameter("tel1_2")+"-"+request.getParameter("tel1_3"));
		memgen.setUser_tel2(request.getParameter("tel2_1")+"-"+request.getParameter("tel2_2")+"-"+request.getParameter("tel2_3"));

		 if(user_id == null){//입력
			 membersService.saveMember(mem);
			 membersgenService.saveMemberGen(memgen);
			 return "redirect:/";

		 }else{//수정모드
			 membersService.saveMember(mem);
			 membersgenService.saveMemberGen(memgen);
			 return "forward:/member/memberReg";
		 }
	}


	 /**
	  * 로그인 폼 출력
	  */
	 @RequestMapping("/member/memberLogin")
		public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav = new ModelAndView();

			String msg = request.getParameter("msg");
			//((Exception)request.getSession().getAttribute(SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() :
			if (msg != null) {
				 String failureReason = request.getSession().getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null ?
				          ((Exception)request.getSession().getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() :
				           "Invalid login attempt, check your authentication credentials.";
				          mav.addObject("failureReason", failureReason);
				          System.out.println("failureReason:"+failureReason);
				/*if (request.getSession().getAttribute(SPRING_SECURITY_LAST_EXCEPTION_KEY) != null)
					mav.addObject("error", ((AuthenticationException) request.getSession()
							.getAttribute(SPRING_SECURITY_LAST_EXCEPTION_KEY))
							.getMessage());
				mav.addObject("user", request.getSession()
						.getAttribute(SPRING_SECURITY_LAST_USERNAME_KEY));
				*/
			}


			mav.addObject("loginForm", new Members());
			mav.setViewName("member/member_login.jsp");
			return mav;
		}


	 /**
	  * 로그인 실행
	  */
	 @RequestMapping("/member/memberLogin_x")
	 public void memberLogin_x(@ModelAttribute Members loginForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
	 	//System.out.println("memberLogin_x");
		Members mem =new Members();
		String user_id	= loginForm.getUser_id();
		mem	= membersDAO.getMemberByUserid(user_id);

		Map<String, String> hm = new HashMap<String, String>();

		if(mem == null){
			hm.put("result", "1");
			hm.put("message", "등록된 아이디가 없습니다.");
		}else{
			if(!loginForm.getUser_passwd().equals(mem.getUser_passwd())){
				hm.put("result", "1");
				hm.put("message", "패스워드가 일치하지 않습니다.");
			}else{
				HttpSession session = request.getSession();
				session.setAttribute("session-userid", user_id);

				Serialization serialization	= new Serialization();
				String outPath				= Constants.AbsolutePath+"user/"+session.getId();
				String[] userinfo			= new String[3];
				userinfo[0]					= mem.getUser_id();//아이디
				userinfo[1]					= mem.getUser_name();//이름
				userinfo[2]					= Integer.toString(mem.getUser_grade());//등급
				serialization.Serialize(outPath, userinfo);

				hm.put("result", "0");
			}
		}

		//JSONObject jsonObject = new JSONObject();
		//jsonObject.put("result", hm);
		//String json = jsonObject.toString();
		//System.out.println(json);
		//response.getWriter().print(json);
	 }

	 /**
	  *  회원로그아웃 실행
	  * @param request
	  * @return
	  */
	@RequestMapping("/member/memberLogout_x")
	public String memberLogout_x(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("session-userid", null);
		return "redirect:/";//+request.getContextPath()
		//return "forward:/member/memberList";
	}

	/**
	 *  Member Modify Form Display
	 */
	 @RequestMapping("/member/member_update")
		public ModelAndView member_update(HttpServletResponse response, HttpServletRequest request) {//, Principal principal
		 	ModelAndView mav = new ModelAndView();
		 	//String userId;
		 	String user_id = null;
		 	if(SecurityContextHolder.getContext().getAuthentication() != null) user_id = SecurityContextHolder.getContext().getAuthentication().getName();


		 	//final String user_id	= principal.
		 	//System.out.println("user_id:"+user_id);

			//기본 인자값 가져오기
		 	String curYear	= DateTimeUtil.getDateTimeByPattern("yyyy");
			//System.out.println("curYear:"+curYear);
			request.setAttribute("curYear",			curYear);
			request.setAttribute("MobileNum", Constants.MobileNum);
			request.setAttribute("PhoneNum", Constants.PhoneNum);
			request.setAttribute("MailAddress", Constants.MailAddress());


			//if(session.getAttribute("session-user_id") != null){
			if(user_id != null){
				//user_id	= session.getAttribute("session-user_id").toString();

				Members mem =new Members();
				mem	= membersDAO.getMemberByUserid(user_id);
				//mav.addObject("member", membersDAO.getMemberByUserid(user_id));
				mav.addObject("member", mem);

				//우편번호
				String[] user_zip1	= mem.getUser_zip1().split("-");
				mav.addObject("user_zip1", user_zip1);

				//생일
				String[] user_birthdate	= mem.getUser_birthdate().split("/");
				mav.addObject("user_birthdate", user_birthdate);

				//전화번호1
				String[] user_tel1	= mem.getUser_tel1().split("-");
				mav.addObject("user_tel1", user_tel1);

				//전화번호2
				String[] user_tel2	= mem.getUser_tel2().split("-");
				mav.addObject("user_tel2", user_tel2);

				//전자우편
				String[] user_email	= mem.getUser_email().split("@");
				mav.addObject("user_email", user_email);
			}

			mav.setViewName("member/member_reg.jsp");

			return mav;
		}

	/**
	 * 회원페이지 메인
	 */
	 @RequestMapping("/member/member_page")
		public ModelAndView viewMember() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("member/member_page.jsp");

			return mav;
	}

	/**
	 * 아이디 패스워드 찾기 출력 폼
	 * @param response
	 * @return
	 */
	@RequestMapping("/member/searchIdPass")
	public ModelAndView searchIdPass(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String smode	= request.getParameter("smode");//idsearch or passsearch or null
		String output	= request.getParameter("output");//mail or print
		String name	= request.getParameter("name");
		String juminno1	= request.getParameter("juminno1");
		String juminno2	= request.getParameter("juminno2");
		String id	= request.getParameter("id");
		String rtn_msg_idsearch	= null;
		String rtn_msg_passsearch	= null;
		String rtn_jumin1, rtn_jumin2;

		if(smode.equals("idsearch")){//아이디 찾기//이렇게 하면 동일 이름이 있을 경우는 에러가 발생한다.(일단 skip)
			//System.out.println("idsearch start");
			Members m	= membersDAO.getMemberByUsername(name.trim());
			//System.out.println("m:"+m);

			if(m == null){
				rtn_msg_idsearch = "회원정보를 찾을 수 없습니다.";
			}else{
				rtn_jumin1 = Integer.toString(m.getUser_jumin1());
				rtn_jumin2 = Integer.toString(m.getUser_jumin2());
				//System.out.println("rtn_jumin1:"+rtn_jumin1);
				//System.out.println("rtn_jumin2:"+rtn_jumin2);
				//System.out.println("juminno1.trim():"+juminno1.trim());
				//System.out.println("juminno2.trim():"+juminno2.trim());

				if(rtn_jumin1.equals(juminno1) && rtn_jumin2.equals(juminno2)){
					rtn_msg_idsearch = "회원님의 ID는 "+m.getUser_id()+"입니다.";

				}else{
					//true;
					rtn_msg_idsearch = "회원정보를 찾을 수 없습니다.";
				}
			}
		}else if(smode.equals("passsearch")){//패스워드 찾기
			Members m	= membersDAO.getMemberByUserid(id.trim());
			rtn_jumin1 = Integer.toString(m.getUser_jumin1());
			rtn_jumin2 = Integer.toString(m.getUser_jumin2());
			if(m == null){
				rtn_msg_passsearch = "회원정보를 찾을 수 없습니다.";
			}else{
				if(rtn_jumin1.equals(juminno1) && rtn_jumin2.equals(juminno2)){
					rtn_msg_passsearch = "회원님의 패스워드는 "+m.getUser_passwd()+"입니다.";
				}else{
					rtn_msg_passsearch = "회원정보를 찾을 수 없습니다.";
				}
			}
		}
		//System.out.println("smode:"+smode);
		//System.out.println("msg_idsearch:"+rtn_msg_idsearch);
		mav.addObject("msg_idsearch", rtn_msg_idsearch);
		mav.addObject("msg_passsearch", rtn_msg_passsearch);
		mav.setViewName("member/member_idpasssearch.jsp");
		return mav;
	}

}
