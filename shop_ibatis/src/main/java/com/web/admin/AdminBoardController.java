package com.web.admin;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.service.BoardService;

import net.sf.json.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.BoardCategory;
import com.domain.BoardMain;
import com.service.BoardCategoryService;
import com.service.BoardMainService;
import com.util.Constants;
import com.util.PageNavigation;
import com.util.Serialization;
import com.util.StringUtil;
import com.util.file.FileManage;
@Controller
public class AdminBoardController {

	@Autowired
	private BoardMainService boardMainService;

	@Autowired
	private BoardCategoryService boardCategoryService;

	@Autowired
	private BoardService boardService;

	//기본 생성자 생성
	private StringUtil stringUtil;
	public AdminBoardController(){
		stringUtil	= new StringUtil();
	}


	@RequestMapping("/admin/board/boardList")
	public ModelAndView boardList(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",request.getParameter("bid"));
		params.put("gid",request.getParameter("gid"));
		params.put("opflag",request.getParameter("opflag"));



		mav.addObject("params", params);

		mav.setViewName("admin/board/boardUserList.jsp");
		return mav;
	}

	@RequestMapping("/admin/board/boardManageList")
	public ModelAndView boardManageList(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		HashMap<String, String> params = new HashMap<String, String>();

		Integer cp			= stringUtil.strToint(request.getParameter("cp"));
		String s_title		= stringUtil.emptyToNull(request.getParameter("s_title"));
		String s_keyword	= stringUtil.emptyToNull(request.getParameter("s_keyword"));

		if(cp < 1) cp = 1;
		Integer blockList	= 10;
		Integer blockPage	= 10;

		if(s_title != null) params.put("s_title",s_title);
		if(s_keyword != null) params.put("s_keyword","%"+s_keyword+"%");
		int tc	= boardMainService.getBoardMainCount(params);

		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);
		mav.addObject("pageNav", pageNav.getParams());



		//리스트 내용을 가져온다.
		params.put("StartRow",Integer.toString(pageNav.getStartRow()));//database limit 절
		params.put("blockList",Integer.toString(blockList));
		List<BoardMain>	tblList	= boardMainService.getBoardMainList(params);
		mav.addObject("tblList", tblList);

		mav.addObject("params", params);

		mav.setViewName("admin/board/boardList.jsp");
		return mav;
	}



	@RequestMapping("/admin/board/boardManage")
	public ModelAndView boardManage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String bid	= request.getParameter("bid");
		String gid	= request.getParameter("gid");



		//기본 정보를 가져온다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"board/table/"+gid+"/"+bid+"/config";
		HashMap<String, String> info = serialization.unSerialize(outPath, "hashMap");
		mav.addObject("info", info);
		info.put("bid",bid);
		info.put("gid",gid);

		//정렬방식 설정
		HashMap<String, String> mapAdminAlign = new HashMap<String, String>();
		mapAdminAlign.put("UID@desc","등록날자순");
		mapAdminAlign.put("SDATE@desc","변경날짜순");
		mapAdminAlign.put("COUNT@desc","히트(조회)순");
		mapAdminAlign.put("DOWNCOUNT@desc","다운로드순");
		mapAdminAlign.put("RECCOUNT@desc","추천순");
		mapAdminAlign.put("NAME@asc","작성자순");
		mapAdminAlign.put("SUBJECT@asc","제목순");

		//게시물 등급(이상/이하)
		HashMap<String, String> mapGradeBoundary = new HashMap<String, String>();
		mapGradeBoundary.put("over","이상");
		mapGradeBoundary.put("less","이하");
		mapGradeBoundary.put("only","만");

		//게시물 등급(성별)
		HashMap<String, String> mapGenderBoundary = new HashMap<String, String>();
		mapGenderBoundary.put("0","성별선택");
		mapGenderBoundary.put("M","남성전용");
		mapGenderBoundary.put("W","여성전용");

		//스킨폴더정보를 입력한다.
		String Path				= Constants.AbsolutePath+"../WEB-INF/pages/board/skins";
		File f = new File(Path);
		String[] skinfolder = f.list();
		mav.addObject("skinfolder", skinfolder);

		//List<BoardMain>	tblList	= boardMainService.getBoardMainList();
		//mav.addObject("tblList", tblList);
		mav.addObject("info", info);
		mav.addObject("mapAdminAlign", mapAdminAlign);
		mav.addObject("mapGradeBoundary", mapGradeBoundary);
		mav.addObject("mapGenderBoundary", mapGenderBoundary);
		mav.addObject("MemberGrade", Constants.MemberGrade());
		mav.setViewName("admin/board/boardManager.jsp");
		return mav;
	}



	@RequestMapping("/admin/board/boardManage_x")
	public void boardManage_x(HttpServletRequest request) {

		String bid	= request.getParameter("bid");
		String gid	= request.getParameter("gid");

		//기타 정보는 파일로 남긴다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"board/table/"+gid+"/"+bid+"/config";
		HashMap<String, String> basicinfo = new HashMap<String, String>();
		basicinfo.put("BOARD_SKIN_TYPE", request.getParameter("BOARD_SKIN_TYPE"));
		basicinfo.put("TABLE_WIDTH", request.getParameter("TABLE_WIDTH"));
		basicinfo.put("TABLE_WIDTH_UNIT", request.getParameter("TABLE_WIDTH_UNIT"));
		basicinfo.put("TABLE_ALIGN", request.getParameter("TABLE_ALIGN"));
		basicinfo.put("ATTACHEDCOUNT", request.getParameter("ATTACHEDCOUNT"));
		basicinfo.put("UpLoadingOverWriteEnable", request.getParameter("UpLoadingOverWriteEnable"));
		basicinfo.put("editorenable", request.getParameter("editorenable"));
		basicinfo.put("AdminOnly", request.getParameter("AdminOnly"));
		basicinfo.put("qnaboard", request.getParameter("qnaboard"));
		basicinfo.put("INCLUDE_MALL_SKIN", request.getParameter("INCLUDE_MALL_SKIN"));
		basicinfo.put("setsecurityiframe", request.getParameter("setsecurityiframe"));
		basicinfo.put("setsecurityscript", request.getParameter("setsecurityscript"));
		basicinfo.put("SubjectLength", request.getParameter("SubjectLength"));
		basicinfo.put("NameLength", request.getParameter("NameLength"));
		basicinfo.put("ListNo", request.getParameter("ListNo"));
		basicinfo.put("PageNo", request.getParameter("PageNo"));
		basicinfo.put("NewTime", request.getParameter("NewTime"));
		basicinfo.put("VSubjectLength", request.getParameter("VSubjectLength"));
		basicinfo.put("VNameLength", request.getParameter("VNameLength"));
		basicinfo.put("ReplyBtn", request.getParameter("ReplyBtn"));
		basicinfo.put("AutoLink", request.getParameter("AutoLink"));
		basicinfo.put("CommentEnable", request.getParameter("CommentEnable"));
		basicinfo.put("ListEnable", request.getParameter("ListEnable"));
		basicinfo.put("Write_prohibition_Word", request.getParameter("Write_prohibition_Word"));
		basicinfo.put("ReadForMember", request.getParameter("ReadForMember"));
		basicinfo.put("ReadMemberGrade", request.getParameter("ReadMemberGrade"));
		basicinfo.put("ReadMemberGradeBoundary", request.getParameter("ReadMemberGradeBoundary"));
		basicinfo.put("ReadMemberGenderBoundary", request.getParameter("ReadMemberGenderBoundary"));
		basicinfo.put("WriteForMember", request.getParameter("WriteForMember"));
		basicinfo.put("WriteMemberGrade", request.getParameter("WriteMemberGrade"));
		basicinfo.put("WriteMemberGradeBoundary", request.getParameter("WriteMemberGradeBoundary"));
		basicinfo.put("WriteMemberGenderBoundary", request.getParameter("WriteMemberGenderBoundary"));
		basicinfo.put("DownForMember", request.getParameter("DownForMember"));
		basicinfo.put("DownMemberGrade", request.getParameter("DownMemberGrade"));
		basicinfo.put("DownMemberGradeBoundary", request.getParameter("DownMemberGradeBoundary"));
		basicinfo.put("DownMemberGenderBoundary", request.getParameter("DownMemberGenderBoundary"));
		basicinfo.put("ListForMember", request.getParameter("ListForMember"));
		basicinfo.put("ListMemberGrade", request.getParameter("ListMemberGrade"));
		basicinfo.put("ListMemberGradeBoundary", request.getParameter("ListMemberGradeBoundary"));
		basicinfo.put("ListMemberGenderBoundary", request.getParameter("ListMemberGenderBoundary"));
		basicinfo.put("bp_recommand", request.getParameter("bp_recommand"));
		basicinfo.put("bp_nonerecommand", request.getParameter("bp_nonerecommand"));
		basicinfo.put("bp_reple", request.getParameter("bp_reple"));
		basicinfo.put("bp_reply", request.getParameter("bp_reply"));
		basicinfo.put("np_lv10", request.getParameter("np_lv10"));
		basicinfo.put("np_lv20", request.getParameter("np_lv20"));
		basicinfo.put("np_lv30", request.getParameter("np_lv30"));
		basicinfo.put("writeExp", request.getParameter("writeExp"));
		basicinfo.put("writePoint", request.getParameter("writePoint"));
		basicinfo.put("writePer", request.getParameter("writePer"));
		basicinfo.put("commentExp", request.getParameter("commentExp"));
		basicinfo.put("commentPoint", request.getParameter("commentPoint"));
		basicinfo.put("commentPer", request.getParameter("commentPer"));
		basicinfo.put("replyExp", request.getParameter("replyExp"));
		basicinfo.put("replyPoint", request.getParameter("replyPoint"));
		basicinfo.put("replyPer", request.getParameter("replyPer"));
		basicinfo.put("rccomExp", request.getParameter("rccomExp"));
		basicinfo.put("rccomPoint", request.getParameter("rccomPoint"));
		basicinfo.put("ProhibitExtention", request.getParameter("ProhibitExtention"));
		basicinfo.put("CategoryEnable", request.getParameter("CategoryEnable"));
		basicinfo.put("CategoryType", request.getParameter("CategoryType"));
		serialization.Serialize(outPath, basicinfo);
	}

	/**
	 * 카테고리 폼 가져오기
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/board/categoryForm")
	public ModelAndView categoryForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> params = new HashMap<String, String>();
		String bid	= request.getParameter("bid");
		String gid	= request.getParameter("gid");

		params.put("bid",bid);
		params.put("gid",gid);


		//현재 카테고리 리스트 가져오기
		List<BoardCategory> category	= boardCategoryService.getBoardCategoryList(params);


		mav.addObject("params", params);
		mav.addObject("category", category);
		mav.setViewName("admin/board/categoryForm.jsp");
		return mav;
	}

	/**
	 * 포인트 폼 가져오기
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/board/pointOptionForm")
	public ModelAndView pointOptionForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> info = new HashMap<String, String>();
		String bid	= request.getParameter("bid");
		String gid	= request.getParameter("gid");

		info.put("bid",bid);
		info.put("gid",gid);


		mav.addObject("info", info);
		mav.setViewName("admin/board/pointForm.jsp");
		return mav;
	}

	/**
	 * 카테고리 저장
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/board/categorySave")
	public ModelAndView categorySave(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String bid		= request.getParameter("bid");
		String gid		= request.getParameter("gid");
		String catname	= request.getParameter("catname");

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);

		Integer ordernum	= boardCategoryService.getBoardCategoryMaxOrderNum(params) + 1;

		BoardCategory boardCategory = new BoardCategory();
		boardCategory.setBid(bid);
		boardCategory.setGid(gid);
		boardCategory.setCatname(catname);
		boardCategory.setOrdernum(ordernum);


		boardCategoryService.saveBoardCategory(boardCategory);


		mav.setViewName("common/blank.jsp");
		return mav;
	}

	/**
	 * 카테고리 삭제
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/board/categoryDel")
	public ModelAndView categoryDel(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		Integer tid	= Integer.parseInt(request.getParameter("tid"));

		boardCategoryService.deleteBoardCategory(tid);

		mav.setViewName("common/blank.jsp");
		return mav;
	}

	/**
	 * 카테고리 명 변경
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/board/categoryCh")
	public ModelAndView categoryCh(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String catname	= request.getParameter("catname");
		Integer tid	= Integer.parseInt(request.getParameter("tid"));

		BoardCategory boardCategory	= boardCategoryService.getBoardCategoryByTid(tid);
		boardCategory.setCatname(catname);

		boardCategoryService.updateBoardCategory(boardCategory);

		mav.setViewName("common/blank.jsp");
		return mav;
	}

	/**
	 * 게시물의 카테고리 변경
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/board/categoryMove")
	public ModelAndView categoryMove(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String s_category	= request.getParameter("s_category");
		String d_category	= request.getParameter("d_category");

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("s_category",s_category);
		params.put("d_category",d_category);

		boardService.updateCategoryInBoard(params);

		mav.setViewName("common/blank.jsp");
		return mav;
	}

	/**
	 * 보드를 생성한다.
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/board/createBoard")
	public ModelAndView createBoard(HttpServletRequest request) {

		String bid			= request.getParameter("f_bid");
		String gid			= request.getParameter("f_gid");
		String title	= request.getParameter("f_title");


		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("title",title);

		//board 및 reply 테이블 생성
		boardService.createBoard(params);

		//카테고리의 기본값입력
		BoardCategory boardCategory = new BoardCategory();
		boardCategory.setBid(bid);
		boardCategory.setGid(gid);
		boardCategory.setCatname("전체");
		boardCategory.setOrdernum(0);
		boardCategoryService.saveBoardCategory(boardCategory);


		//보드메인에 데이타 입력
		BoardMain boardMain= new BoardMain();
		boardMain.setBid(bid);
		boardMain.setGid(gid);
		boardMain.setTitle(title);
		boardMainService.saveBoardMain(boardMain);

		//폴더 생성 및 기본 데이타 복사
		FileManage filemanage	= new FileManage();//파일매니저 set
		String updirpath	= "board/table/"+gid+"/"+bid+"/";
		String AbsolutePath	= Constants.AbsolutePath+updirpath;
		filemanage.makeDir(AbsolutePath);

		updirpath	= "board/table/"+gid+"/"+bid+"/updir/";
		AbsolutePath	= Constants.AbsolutePath+updirpath;
		filemanage.makeDir(AbsolutePath);

		String source	= Constants.AbsolutePath+"default/board/config";
		String target	= Constants.AbsolutePath+"board/table/"+gid+"/"+bid+"/config";
		filemanage.fileCopy(source, target);


		// json으로 리턴값 생성
        JSONObject object=new JSONObject();
        object.put("result",0);

		ModelAndView mav = new ModelAndView();
		mav.addObject("json", object);

		mav.setViewName("common/blank.jsp");
		return mav;
	}

}
