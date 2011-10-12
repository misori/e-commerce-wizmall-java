package com.web.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.MembersDAO;
import com.domain.Members;
import com.service.MembersService;
import com.util.Constants;
import com.util.PageNavigation;
import com.util.StringUtil;
@Controller
public class AdminMemberController {

	@Autowired
	private MembersService membersService;

	private StringUtil stringUtil;
	public AdminMemberController(){
		stringUtil	= new StringUtil();
	}


	/**
	 * 회원리스트 출력
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/member/memberList")
	public ModelAndView memberList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//페이지에 대한 정의
		Integer cp			= stringUtil.strToint(request.getParameter("cp"));
		String s_title		= stringUtil.emptyToNull(request.getParameter("s_title"));
		String s_keyword	= stringUtil.emptyToNull(request.getParameter("s_keyword"));
		String s_date		= stringUtil.emptyToNull(request.getParameter("s_date"));
		String e_date		= stringUtil.emptyToNull(request.getParameter("e_date"));


	    if(cp < 1) cp = 1;
		Integer blockList	= 10;
		Integer blockPage	= 10;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("s_date",s_date);
		params.put("e_date",e_date);
		params.put("s_title",s_title);
		if(s_keyword != null) params.put("s_keyword","%"+s_keyword+"%");
		int tc	= membersService.getMembersCount(params);


		// 객체를 생성한다 (현재페이지, 전체글수, 페이지당표시할 글의수, 한번에 표시할 페이징블록수)
		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);
		mav.addObject("pageNav", pageNav.getParams());



		//회원정보를 가져온다.
		params.put("StartRow",Integer.toString(pageNav.getStartRow()));//database limit 절
		params.put("blockList",Integer.toString(blockList));
		mav.addObject("info", membersService.getMembersList(params));

		params.put("s_keyword",request.getParameter("s_keyword"));
		mav.addObject("params", params);
		mav.setViewName("admin/member/member_list.jsp");
		//System.out.println("mav"+mav);
		//assertViewName(mav, "redirect:member/member_list.jsp");
		return mav;
	}


	/**
	 * 회원리스트 엑셀 다운로드
	 * @param request
	 * @return
	 * @throws WriteException
	 * @throws IOException
	 */
	@RequestMapping("/admin/member/memberExcelWrite")
	public void memberExcelWrite(HttpServletRequest request, HttpServletResponse response) throws WriteException, IOException {

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=memberList.xls");
		WritableWorkbook myWorkbook = Workbook.createWorkbook(response.getOutputStream());

		HashMap<String, String> params = new HashMap<String, String>();
		List<Members> members	= membersService.getMembersList(params);
		Iterator<Members> iter = members.iterator();

		WritableSheet mySheet = myWorkbook.createSheet("MemberList", 0); // WritableSheet는 인터페이스

		// 라벨을 이용하여 해당 셀에 정보 넣기 시작
		mySheet.addCell(new Label(0, 0, "이름")); // 쉬트의 addCell 메소드를 사용하여 삽입
		mySheet.addCell(new Label(1, 0, "아이디")); //
		mySheet.addCell(new Label(2, 0, "등급")); //

		int no = 1;
		while(iter.hasNext()) {
			Members m = iter.next();
			mySheet.addCell(new Label(0, no, m.getUser_name())); // 이름
			mySheet.addCell(new Label(1, no, m.getUser_id())); // 아이디
			mySheet.addCell(new Label(2, no, Integer.toString(m.getUser_grade()))); // 등급
			no++;
			// iter.remove();
		}
		// 라벨을 이용하여 해당 셀에 정보 넣기 끝
		myWorkbook.write(); // 준비된 정보를 엑셀 포멧에 맞게 작성
		myWorkbook.close(); // 처리 후 메모리에서 해제 처리
	}


	/**
	 * 회원상세보기창 출력
	 * @param id
	 * @return
	 */
	 @RequestMapping("/admin/member/memberView")
		public ModelAndView viewMember(@RequestParam String id) {
			//logger.info("Welcome /member/viewMember");
			ModelAndView mav = new ModelAndView();

			//상수값 가져오기(메일어드레스)
			mav.addObject("MailAddress", Constants.MailAddress());

			//상수값 가져오기(회원등급)
			mav.addObject("MemberGrade", Constants.MemberGrade());

			Members mem =new Members();
			mem	= membersService.getMemberByUserid(id);
			//mav.addObject("member", membersService.getMemberByUserid(userId));
			mav.addObject("info", mem);


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


			mav.setViewName("admin/member/member_view.jsp");
			return mav;
	}


	/**
	 * 멤버 주문 내역 리스트
	 * @param id
	 * @return
	 */
	@RequestMapping("/admin/member/OrderHistory")
	public ModelAndView OrderHistory(@RequestParam String id) {
		//logger.info("Welcome /member/viewMember");
		ModelAndView mav = new ModelAndView();

		//상수값 가져오기(메일어드레스)
		mav.addObject("MailAddress", Constants.MailAddress());

		//상수값 가져오기(회원등급)
		mav.addObject("MemberGrade", Constants.MemberGrade());

		Members mem =new Members();
		mem	= membersService.getMemberByUserid(id);
		//mav.addObject("member", membersService.getMemberByUserid(userId));
		mav.addObject("info", mem);


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


		mav.setViewName("admin/member/member_view.jsp");
		return mav;
	}

}
