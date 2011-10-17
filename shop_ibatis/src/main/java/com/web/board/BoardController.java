package com.web.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.Board;
import com.domain.BoardFile;
import com.domain.BoardReply;
import com.domain.Members;
import com.security.CustomUser;
import com.service.BoardCategoryService;
import com.service.BoardFileService;
import com.service.BoardReplyService;
import com.service.BoardService;
import com.util.Constants;
import com.util.PageNavigation;
import com.util.Serialization;
import com.util.StringUtil;
import com.util.file.FileManage;
import com.util.file.FileUpload;
import com.util.file.FileUploadUtil;
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardCategoryService boardCategoryService;

	@Autowired
	private BoardReplyService boardReplyService;

	@Autowired
	private BoardFileService boardFileService;


	private StringUtil stringUtil;
	private BoardUtil boardutil;

	public BoardController(){
		stringUtil	= new StringUtil();
		boardutil	= new BoardUtil();
	}

	//@PersistenceContext(unitName = "mySQLConnection")
	//@RequestMapping( value="/board/boardList", method=RequestMethod.GET)
	//@RequestParam(value="cp", required=false) Integer cp,
	//@RequestParam(value="s_title", required=false) String s_title,
	//@RequestParam(value="s_keyword", required=false) String s_keyword

	/**
	 * 게시물 리스트 보기
	 */
	@RequestMapping("/board/boardList")
	public ModelAndView boardList(HttpServletRequest request){

		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		Integer cp			= stringUtil.strToint(request.getParameter("cp"));
		String s_title		= stringUtil.emptyToNull(request.getParameter("s_title"));
		String s_keyword	= stringUtil.emptyToNull(request.getParameter("s_keyword"));
		String category		= stringUtil.emptyToNull(request.getParameter("category"));
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));
		category			= category == null ? "0" : category;

		//기본 정보를 가져온다.
		HashMap<String, String> config = getConfig(gid, bid);



		ModelAndView mav = new ModelAndView();

		if(cp < 1) cp = 1;
		Integer blockList	= Integer.parseInt(config.get("ListNo"));
		Integer blockPage	= Integer.parseInt(config.get("PageNo"));


		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("category",category);

		params.put("s_title",s_title);
		if(s_keyword != null) params.put("s_keyword","%"+s_keyword+"%");
		int tc	= boardService.getBoardCount(params);




		//페이지 네비 게이션 생성
		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);
		mav.addObject("pageNav", pageNav.getParams());

		//리스트 내용을 가져온다.
		params.put("StartRow",Integer.toString(pageNav.getStartRow()));//database limit 절
		params.put("blockList",Integer.toString(blockList));
		mav.addObject("info", boardService.getBoardList(params));

		//공지게시물을 가져온다.
		params.put("notice","Y");

		//키워드를 원래 값으로 돌려둔다.
		params.put("s_keyword",request.getParameter("s_keyword"));

		//옵션 flag를 가져온다.
		HashMap<String, String> opFlag	= boardutil.formJsonOpFlag(opflag);
		//mav.addObject("opFlag", opFlag);//hashmap
		//확장자 변경(관리자단에서 볼경우는 blank, 일반적으로 do)
		params.put("opflag",opflag);//string
		String svl_ext	= opFlag.get("owner") != null && opFlag.get("owner").equals("admin") ? "blank":"do";
		params.put("svl_ext",svl_ext);



		mav.addObject("params", params);
		mav.setViewName("board/skins/"+config.get("BOARD_SKIN_TYPE")+"/boardList.jsp");
		return mav;
	}

	//@RequestParam Integer tid
	/**
	 * 게시물 상세보기
	 * @param tid
	 * @return
	 */
	@RequestMapping("/board/boardView")
	public ModelAndView viewBoard(HttpServletRequest request){

		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String tid			= request.getParameter("tid");
		Integer cp			= stringUtil.strToint(request.getParameter("cp"));
		String s_title		= stringUtil.emptyToNull(request.getParameter("s_title"));
		String s_keyword	= stringUtil.emptyToNull(request.getParameter("s_keyword"));
		String category		= stringUtil.emptyToNull(request.getParameter("category"));
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));
		category			= category == null ? "0" : category;


		//기본 정보를 가져온다.
		HashMap<String, String> config = getConfig(gid, bid);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);
		params.put("cp",Integer.toString(cp));
		params.put("s_title",s_title);
		params.put("s_keyword",s_keyword);
		params.put("category",category);

		//조회수를 올린다.
		boardService.updateBoardViewCount(params);

		//첨부화일을 가져온다.
		params.put("pid",tid);//boardFile 용
		List<BoardFile> attachedInfo = boardFileService.getBoardFileByPid(params);


		//첨부화일중 출력가능한 화일을 별도로 처리한다.
		Iterator<BoardFile> iterBoardFile		= attachedInfo.iterator();
		String allowExt[]	={"png","jpg","gif","jpeg"};
		List<String> displayImg	= new ArrayList<String>();
		while(iterBoardFile.hasNext()){
			BoardFile boardFile	= iterBoardFile.next();
			int pos = boardFile.getFilename().lastIndexOf(".");
			String ext = boardFile.getFilename().substring( pos + 1 ).toLowerCase();
			for(int i=0; i < allowExt.length; i++){//출력가능한 확장자이면 화면에 디스플레이 할수 있게 처리한다.
				if(ext.equals(allowExt[i])){
					displayImg.add("/data/board/table/"+gid+"/"+bid+"/updir/"+tid+"/"+boardFile.getFilename());
				}
			}
		}

		//옵션 flag를 가져온다.
		HashMap<String, String> opFlag	= boardutil.formJsonOpFlag(opflag);
		String svl_ext	= opFlag.get("owner") != null && opFlag.get("owner").equals("admin") ? "blank":"do";//확장자 변경(관리자단에서 볼경우는 blank, 일반적으로 do)
		params.put("opflag",opflag);//string
		params.put("svl_ext",svl_ext);


		//모든 내용을 가져온다.
		Board board = boardService.getBoardByPrimaryKey(params);

		ModelAndView mav = new ModelAndView();
		mav.addObject("params", params);
		mav.addObject("board", board);
		mav.addObject("attachedInfo", attachedInfo);//첨부화일 : 다운로드용
		mav.addObject("displayImg", displayImg);//첨부화일: 디스플레이용

		mav.setViewName("board/skins/"+config.get("BOARD_SKIN_TYPE")+"/boardView.jsp");

		return mav;
	}

	/**
	 * 쓰기폼 출력
	 * @param request
	 * @return
	 */
	@RequestMapping("/board/boardWrite")
	public ModelAndView boardWrite(HttpServletRequest request, Authentication auth) {
		ModelAndView mav = new ModelAndView();

		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String tid			= request.getParameter("tid");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));

		//기본 정보를 가져온다.
		HashMap<String, String> config = getConfig(gid, bid);
		mav.addObject("config", config);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);

		//옵션 flag를 가져온다.
		HashMap<String, String> opFlag	= boardutil.formJsonOpFlag(opflag);
		String svl_ext	= opFlag.get("owner") != null && opFlag.get("owner").equals("admin") ? "blank":"do";//확장자 변경(관리자단에서 볼경우는 blank, 일반적으로 do)
		params.put("opflag",opflag);//string
		params.put("svl_ext",svl_ext);


		//카테고리 정보를 가져온다.
		mav.addObject("category", boardCategoryService.getBoardCategoryList(params));

		mav.addObject("params", params);
		mav.setViewName("board/skins/"+config.get("BOARD_SKIN_TYPE")+"/boardWrite.jsp");
		return mav;
	}

	/**
	 * 수정폼(기존 Write폼과 동일 폼 사용)
	 * @param request
	 * @return
	 */
	@RequestMapping("/board/boardEdit")
	public ModelAndView boardEdit(HttpServletRequest request, Authentication auth) {

		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String tid			= request.getParameter("tid");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));

		//현재 인증된 권한인지 체크한다.
		HttpSession session = request.getSession();
		Boolean accessResult = false;
		if(session.getAttribute("boardAccess") != null){
			HashMap<String, String> boardAccess	= (HashMap<String, String>)session.getAttribute("boardAccess");
			if(boardAccess.get("bid").equals(bid) && boardAccess.get("gid").equals(gid) && boardAccess.get("tid").equals(tid)) accessResult = true;
		}

		ModelAndView mav = new ModelAndView();
		if(accessResult == true){
			//기본 정보를 가져온다.
			HashMap<String, String> config = getConfig(gid, bid);

			HashMap<String, String> params = new HashMap<String, String>();
			params.put("bid",bid);
			params.put("gid",gid);
			params.put("tid",tid);
			params.put("smode","edit");


			//카테고리 정보를 가져온다.
			mav.addObject("category", boardCategoryService.getBoardCategoryList(params));


			Board board	= boardService.getBoardByPrimaryKey(params);


			//옵션 flag를 가져온다.
			HashMap<String, String> opFlag	= boardutil.formJsonOpFlag(opflag);
			String svl_ext	= opFlag.get("owner") != null && opFlag.get("owner").equals("admin") ? "blank":"do";//확장자 변경(관리자단에서 볼경우는 blank, 일반적으로 do)
			params.put("opflag",opflag);//string
			params.put("svl_ext",svl_ext);


			//옵션정보를 분리하여 가져온다.
			mav.addObject("params_op", resolvOp(board.getOp_val()));
			mav.addObject("board", board);
			mav.addObject("params", params);
			mav.addObject("config", config);
			mav.setViewName("board/skins/"+config.get("BOARD_SKIN_TYPE")+"/boardWrite.jsp");
		}else{
			mav.setViewName("board/accessDeny.jsp");
		}
		return mav;
	}

	/**
	 * 리플라이 폼 출력(기존 Write폼과 동일 폼 사용)
	 * @param request
	 * @return
	 */
	@RequestMapping("/board/boardReply")
	public ModelAndView boardReply(HttpServletRequest request, Authentication auth) {

		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String tid			= request.getParameter("tid");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));

		//기본 정보를 가져온다.
		HashMap<String, String> config = getConfig(gid, bid);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);
		params.put("smode","reply");

		ModelAndView mav = new ModelAndView();
		//카테고리 정보를 가져온다.
		mav.addObject("category", boardCategoryService.getBoardCategoryList(params));

		//옵션 flag를 가져온다.
		HashMap<String, String> opFlag	= boardutil.formJsonOpFlag(opflag);
		String svl_ext	= opFlag.get("owner") != null && opFlag.get("owner").equals("admin") ? "blank":"do";//확장자 변경(관리자단에서 볼경우는 blank, 일반적으로 do)
		params.put("opflag",opflag);//string
		params.put("svl_ext",svl_ext);


		Board board = boardService.getBoardByPrimaryKey(params);
		//board.setTid(null);
		board.setUser_name(null);
		board.setUser_passwd(null);
		board.setSubject("RE: "+board.getSubject());
		board.setContents("\n\n\n>>>>>>> "+board.getContents());
		mav.addObject("params", params);
		mav.addObject("config", config);
		mav.addObject("board", board);

		mav.setViewName("board/skins/"+config.get("BOARD_SKIN_TYPE")+"/boardWrite.jsp");
		return mav;
	}


	@RequestMapping("/board/boardReg_x")
	public String saveBoard(
			@ModelAttribute Board board,
			HttpServletRequest request) throws Exception {

		FileManage filemanage	= new FileManage();//파일매니저 set

		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String tid			= request.getParameter("tid");
		String smode		= request.getParameter("smode");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));
		//Integer category	= Integer.parseInt(request.getParameter("category"));
		//category	= category == null ? 0 :category;

		//System.out.println(board);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);


		Integer maxTid	= null;
		if(smode.equals("edit")){//현재 게시물의 max TID 값을 구해 온다.
			maxTid	= Integer.parseInt(tid);
		}else{
			maxTid	= boardService.getMaxTid(params) + 1;
		}

		String updirpath	= "board/table/"+gid+"/"+bid+"/updir/"+maxTid+"/";
		String AbsolutePath	= Constants.AbsolutePath+updirpath;
		filemanage.makeDir(AbsolutePath);

		//boardFile에서 기존 정보를 담아둔후 테이블에 있는 정보는 날린다.
		params.put("pid",Integer.toString(maxTid));//boardFile 용
		List<BoardFile> attachedInfo = boardFileService.getBoardFileByPid(params);
		//삭제하기
		boardFileService.deleteBoardFileByPid(params);

		//첨부화일을 새로이 저장하고 그 값들을 List에 넣어둔다.
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		List<FileUpload> FileList = fileUploadUtil.getFileList(request, updirpath);

		//새로이 들어간 첨부화일과 기존 파일을 비교하여 삽입/수정/삭제를 진행한다.
		List<FileUpload> newFileList = new ArrayList<FileUpload>();//최종적으로 입력될 배열 생성
		Iterator<FileUpload> iterFileList		= FileList.iterator();
		Iterator<BoardFile> iterBoardFile		= null;


		while(iterFileList.hasNext()){
			FileUpload loadedFile	= iterFileList.next();
			if(loadedFile.getFileSize() !=0){//현재업로된 파일이있으면 기존파일은 삭제하고 현재 파일로 대처한다.
				//System.out.println("loadedFile:"+loadedFile);
				newFileList.add(loadedFile);//입력
				//기존이미지는 삭제
				iterBoardFile		= attachedInfo.iterator();
				while(iterBoardFile.hasNext()){
					BoardFile presentBoardFile	= iterBoardFile.next();
					//System.out.println(presentBoardFile.getSeq()+","+loadedFile.getFileNo());
					if(presentBoardFile.getSeq() == loadedFile.getFileNo()){
						//System.out.println(Constants.AbsolutePath+updirpath+presentBoardFile.getFilename());
						int result = filemanage.DeleteFile(AbsolutePath+presentBoardFile.getFilename());
						//System.out.println("result:"+result);
					}
				}
			}else{//현재 업로드된 파일이 없으면 기존 db에서 정보를 찾아 현재에 넣는다.
				iterBoardFile		= attachedInfo.iterator();
				while(iterBoardFile.hasNext()){
					BoardFile presentBoardFile	= iterBoardFile.next();
					if(presentBoardFile.getSeq() == loadedFile.getFileNo()){
						FileUpload tmp	= new FileUpload();
						tmp.setFileName(presentBoardFile.getFilename());
						tmp.setFileNo(presentBoardFile.getSeq());
						newFileList.add(tmp);
					}
				}
			}
		}

		// 새로이 들어간 데이타를 기준으로 현재 BoardFile 및 Board를 업데이트 시킨다.
		Iterator<FileUpload> iterNewFileList	= newFileList.iterator();
		while(iterNewFileList.hasNext()){
			FileUpload newBoardFile				= iterNewFileList.next();
			if(newBoardFile.getFileNo() == 0){//첫번째 인자값은 현재 Board Table에 입력한다.
				board.setAttached(newBoardFile.getFileName());
			}

			//모든 데이타를 BoardFile에 입력한다.
			BoardFile boardfile	= new BoardFile();
			boardfile.setBid(bid);
			boardfile.setGid(gid);
			boardfile.setPid(maxTid);
			boardfile.setFilename(newBoardFile.getFileName());
			boardfile.setSeq(newBoardFile.getFileNo());

			boardFileService.saveBoardFile(boardfile);


		}


		//아이피 세팅
		InetAddress ip =InetAddress.getLocalHost();
		String user_ip	= ip.getHostAddress();
		board.setUser_ip(user_ip);

		//옵션값 세팅
		board.setOp_val(createOp(request));



		//System.out.println("smode:"+smode);
		if(smode.equals("reply")){
			System.out.println("repl");
			//기존데이타에서 정보 가져오기
			Board tmp = boardService.getBoardByPrimaryKey(params);
			Integer c_fid		= tmp.getFid();
			String c_thread		= tmp.getThread();
			params.put("c_fid",Integer.toString(c_fid));
			params.put("c_thread",c_thread);
			String tmpthread	= boardService.getBoardThread(params);
			//System.out.println("tmpthread:"+tmpthread);
			String thread;
			if(tmpthread != null) {
				int ords = tmpthread.charAt(0) + 1;
				//System.out.println("ords:"+ords);

				//String chars	= StringUtil.fromCharCode(ords);
				String chars	= stringUtil.fromCharCode(ords);
				//System.out.println("chars:"+chars);
				//thread	= c_thread+chr(ord(tmpthread.charAt(0)+1);
				thread	= c_thread+chars;
				//thread	= "A";
				//$FUTURE_THREAD	= $CURRENT_THREAD.chr(ord($MORE_THREAD)+1);

			} else {
				thread = c_thread+"A";
				//$FUTURE_THREAD	= $CURRENT_THREAD."A";
			}

			board.setThread(thread);
			board.setFid(c_fid);
			board.setTid(null);//이렇게 해야 수정으로 전환되지 않는다.
		}else{
			board.setThread("A");
			board.setFid(boardService.getMaxFid(params) + 1);
		}

		//옵션 flag를 가져온다.
		HashMap<String, String> opFlag	= boardutil.formJsonOpFlag(opflag);
		String svl_ext	= opFlag.get("owner") != null && opFlag.get("owner").equals("admin") ? "blank":"do";//확장자 변경(관리자단에서 볼경우는 blank, 일반적으로 do)


		boardService.saveBoard(board);
		return "forward:/board/boardList."+svl_ext;
		//return new ModelAndView(getSuccessView());
	}


	@RequestMapping("/board/boardDelete_x")
	public ModelAndView boardDelete_x(HttpServletRequest request, Authentication auth) {
		String bid				= request.getParameter("bid");
		String gid				= request.getParameter("gid");
		String tid				= request.getParameter("tid");
		String insert_passwd	= request.getParameter("insert_passwd");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);
		Board board	=	boardService.getBoardByPrimaryKey(params);

		JSONObject object=new JSONObject();
		if(hasAuthority(board, auth).equals("true")){//삭제권한이 있으면 삭제한다.
			boardService.deleteBoard(params);
			object.put("result","0");
		}else if(board.getUser_passwd().equals(insert_passwd)){
			boardService.deleteBoard(params);
			object.put("result","0");
		}else{//권한이 없으므로 삭제 경고 메시지를 날린다.
			object.put("result","1");
			object.put("msg","삭제할 권한이 없습니다.");
		}

		//object.put("url","0");

		ModelAndView mav = new ModelAndView();
		mav.addObject("json", object);
		mav.setViewName("common/blank.jsp");
		return mav;
		//return "forward:/board/boardList.do";
	}


	/**
	 * 게시물 삭제폼 출력
	 * @param request
	 * @return
	 */
	@RequestMapping("/board/boardDeleteForm")
	public ModelAndView boardDeleteForm(HttpServletRequest request, Authentication auth) {
		String bid				= request.getParameter("bid");
		String gid				= request.getParameter("gid");
		String tid				= request.getParameter("tid");
		String opflag			= stringUtil.emptyToNull(request.getParameter("opflag"));

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);
		Board board	=	boardService.getBoardByPrimaryKey(params);

        params.put("accessResult",hasAuthority(board, auth) );//권한 체크(권한에 따라 패스워드 입력란을 출력할 것인지 확인창을 출력할 것인지 체크)

		HashMap<String, String> params_hidden = new HashMap<String, String>();
		params_hidden.put("bid",bid);
		params_hidden.put("gid",gid);
		params_hidden.put("tid",tid);


		ModelAndView mav = new ModelAndView();
		mav.addObject("params", params);
		mav.addObject("params_hidden", params_hidden);//hidden으로 자동 출력
		mav.setViewName("board/accessFormDelete.jsp");
		return mav;
	}

	/**
	 * 게시판 수정시 확인 폼
	 * @param request
	 * @param auth
	 * @return
	 */
	@RequestMapping("/board/boardEditAccessAuthorityForm")
	public ModelAndView boardEditAccessAuthorityForm(HttpServletRequest request, Authentication auth) {
		String bid				= request.getParameter("bid");
		String gid				= request.getParameter("gid");
		String tid				= request.getParameter("tid");
		String insert_passwd	= request.getParameter("insert_passwd");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));


		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);
		Board board	=	boardService.getBoardByPrimaryKey(params);

        String accessResult = hasAuthority(board, auth);//권한 체크(권한에 따라 패스워드 입력란을 출력할 것인지 확인창을 출력할 것인지 체크)

        if(accessResult.equals("false") && insert_passwd != null && board.getUser_passwd().equals(insert_passwd))
        	accessResult = "true";//비밀번호가 있을 경우 현재 비밀번호와 비교


		HashMap<String, String> params_hidden = new HashMap<String, String>();
		params_hidden.put("bid",bid);
		params_hidden.put("gid",gid);
		params_hidden.put("tid",tid);


		ModelAndView mav = new ModelAndView();
		mav.addObject("params", params);
		mav.addObject("params_hidden", params_hidden);//hidden으로 자동 출력
		mav.setViewName("board/accessFormModify.jsp");
		return mav;
	}

	/**
	 * 권한이 있는지의 여부 만을 출력
	 * @param request
	 * @param auth
	 * @return
	 */
	@RequestMapping("/board/hasAuthorityForModify")
	public ModelAndView hasAuthorityForModify(HttpServletRequest request, Authentication auth) {
		String bid				= request.getParameter("bid");
		String gid				= request.getParameter("gid");
		String tid				= request.getParameter("tid");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));

        String accessResult = hasAuthority(request, auth);//권한 체크(권한에 따라 패스워드 입력란을 출력할 것인지 확인창을 출력할 것인지 체크)

		//현재 인증된 권한인지 체크한다.
		HttpSession session = request.getSession();

		if(session.getAttribute("boardAccess") != null){
			HashMap<String, String> boardAccess	= (HashMap<String, String>)session.getAttribute("boardAccess");
			if(boardAccess.get("bid").equals(bid) && boardAccess.get("gid").equals(gid) && boardAccess.get("tid").equals(tid)) accessResult = "true";
		}

        if(accessResult.equals("true")){
        	//권한 세션을 생성한다.
    		HashMap<String, String> params = new HashMap<String, String>();
    		params.put("bid",bid);
    		params.put("gid",gid);
    		params.put("tid",tid);

        	session.setAttribute("boardAccess", params);//세션 설정
        }
        JSONObject object=new JSONObject();
        object.put("result",accessResult);

		ModelAndView mav = new ModelAndView();
		mav.addObject("json", object);

		mav.setViewName("common/blank.jsp");
		return mav;
	}



	/**
	 * 리플등록 리스트 및 폼
	 * @param request
	 * @return
	 */
	@RequestMapping("/board/reple")
	public ModelAndView reple(HttpServletRequest request) {

		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String tid			= request.getParameter("tid");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));

		//기본 정보를 가져온다.
		HashMap<String, String> config = getConfig(gid, bid);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);

		ModelAndView mav = new ModelAndView();
		List<BoardReply> replyList	= boardReplyService.getBoardReplyList(params);

		mav.addObject("params", params);
		mav.addObject("config", config);
		mav.addObject("replyList", replyList);


		mav.setViewName("board/skins/"+config.get("BOARD_SKIN_TYPE")+"/boardReple.jsp");
		return mav;
	}

	/**
	 * 리플 등록실행
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/board/boardReplyReg_x")
	public ModelAndView boardReplyReg_x(HttpServletRequest request) throws Exception {
		//System.out.println("boardReplyReg_x");
		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String tid			= request.getParameter("tid");
		String user_name	= request.getParameter("user_name");
		String contents		= request.getParameter("contents");
		String user_passwd	= request.getParameter("user_passwd");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));

		BoardReply	boardReply	= new BoardReply();
		boardReply.setBid(bid);
		boardReply.setGid(gid);
		boardReply.setMid(Integer.parseInt(tid));
		boardReply.setUser_name(user_name);
		boardReply.setContents(contents);
		boardReply.setUser_passwd(user_passwd);

		boardReplyService.saveBoardReply(boardReply);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/blank.jsp");
		return mav;
	}

	/**
	 * 리플삭제 실행
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/board/boardReplyDelete_x")
	public ModelAndView boardReplyDelete_x(HttpServletRequest request) throws Exception {
		System.out.println("boardReplyReg_x");
		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String tid			= request.getParameter("reple_tid");
		String opflag		= stringUtil.emptyToNull(request.getParameter("opflag"));

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);

		System.out.println(params);
		boardReplyService.deleteBoardReply(params);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/blank.jsp");
		return mav;
	}

	/**
	 * 삭제/수정시 권한체크
	 * @param request
	 * @param auth
	 * @return
	 */
	public String hasAuthority(Board board, Authentication auth) {
		String accessResult		= "false";
		String login_id			= null;
		Integer login_grade		= null;

		if(auth != null){
			Members member	= ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMembersInfo();
			login_id = auth.getName();
			login_grade	= member.getUser_grade();
		}

        if(login_id != null && board.getUser_id() == login_id){//글쓴이와 동일한지 체크
			accessResult = "true";
		}else if(login_grade != null && login_grade == 10){//관리자 권한인지 체크
			accessResult = "true";
			System.out.println("admin logined");
		}

		return accessResult;
	}

	public String hasAuthority(HttpServletRequest request, Authentication auth) {//request로 받을 경우 Board에 값을 세팅해 준다.
		String bid				= request.getParameter("bid");
		String gid				= request.getParameter("gid");
		String tid				= request.getParameter("tid");
		String insert_passwd	= request.getParameter("insert_passwd");

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bid",bid);
		params.put("gid",gid);
		params.put("tid",tid);
		Board board	=	boardService.getBoardByPrimaryKey(params);

        String accessResult = hasAuthority(board, auth);//권한 체크(권한에 따라 패스워드 입력란을 출력할 것인지 확인창을 출력할 것인지 체크)
        if(accessResult.equals("false") && insert_passwd != null && board.getUser_passwd().equals(insert_passwd))
        	accessResult = "true";

		return accessResult;
	}
	/**
	 * 첨부화일 다운로드
	 */
	@RequestMapping("/board/fileDownLoad")
	public void fileDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String bid			= request.getParameter("bid");
		String gid			= request.getParameter("gid");
		String tid			= request.getParameter("tid");

		String filename	= request.getParameter("filename");
		String path = Constants.AbsolutePath+"board/table/"+gid+"/"+bid+"/updir/"+tid+"/";
		response.reset();

		if(request.getHeader("User-Agent").indexOf("MSIE5.0") > -1){
			//IE 가 아닐 경우
			response.setHeader("content-Type", "doesn/matter;");
		}else{
			//ID 일 경우
			response.setHeader("content-Type", "application/unknown");
		}
		response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");

		//본격적인 파일 다운로드 Start
		File fp		= new File(path + filename);
		int read	= 0;
		byte[] b	= new byte[(int)fp.length()];

		if(fp.isFile()){
			BufferedInputStream fin		= new BufferedInputStream(new FileInputStream(fp));
			BufferedOutputStream outs	= new BufferedOutputStream(response.getOutputStream());

			//파일을 읽어서 브라우저에 출력
			try{
				while((read=fin.read(b)) != -1){
					outs.write(b, 0, read);
				}
			} catch (Exception e){
				e.printStackTrace();
			} finally{
				if(outs	!= null) outs.close();
				if(fin	!= null){fin.close();}
			}
		}
	}

	public HashMap<String, String> getConfig(String gid, String bid){
		//기본 정보를 가져온다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"board/table/"+gid+"/"+bid+"/config";
		HashMap<String, String> config = serialization.unSerialize(outPath, "hashMap");
		return config;
	}

	/**
	 * 옵션값을 문자열로 만들어 준다.
	 * @param request
	 * @return
	 */
	public String createOp(HttpServletRequest request){
		String TxtType		= request.getParameter("TxtType");
		TxtType	= TxtType == null ? "0":TxtType;
		String Notice		= request.getParameter("Notice");
		Notice	= Notice == null ? "0":Notice;
		String Secret		= request.getParameter("Secret");
		Secret	= Secret == null ? "0":Secret;
		String RepleMail	= request.getParameter("RepleMail");
		RepleMail	= RepleMail == null ? "0":RepleMail;
		String checkReple	= request.getParameter("checkReple");
		checkReple	= checkReple == null ? "0":checkReple;
		String checkReply	= request.getParameter("checkReply");
		checkReply	= checkReply == null ? "0":checkReply;
		//TxtType=0;//텍스트 타입 0: text, 1:html
		//RepleMail=0;//리플달릴경우 메일 수신여부
		//Secret=0;//비밀 게시판 여부
		//Notice=0;//공지글 여부
		//checkReple=0;//댓글가능 이기능과 아래 답글기능은 관리자단에서 허용될 경우 실지 적용된다.
		//checkReple=0;//답글가능

		return TxtType+"|"+Notice+"|"+Secret+"|"+RepleMail+"|"+checkReple+"|"+checkReply;
	}

	/**
	 *
	 * @param opval
	 * @return
	 */
	public HashMap<String, String> resolvOp(String opval){
		String[] opArr	= opval.split("\\|");
		//System.out.println("opval:"+opval);
		//System.out.println("opArr:"+opArr);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("TxtType", opArr[0]);
		params.put("Notice", opArr[1]);
		params.put("Secret", opArr[2]);
		params.put("RepleMail", opArr[3]);
		params.put("checkReple", opArr[4]);
		params.put("checkReply", opArr[5]);
		//System.out.println(params);
		return params;
	}



}
