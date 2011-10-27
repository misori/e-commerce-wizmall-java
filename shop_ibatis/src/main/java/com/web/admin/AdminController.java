package com.web.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.BoardMain;
import com.service.BoardMainService;
import com.util.ParseURL;
import com.util.StringUtil;
import com.web.board.BoardUtil;

@Controller
public class AdminController {


	@Autowired
	private BoardMainService boardMainService;
	/**
	 *
	 * @throws IOException
	 * @d
	 */

	@RequestMapping("/admin")
	public ModelAndView viewBoard(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/main.jsp");
		return mav;
	}

	@RequestMapping("/admin/leftMenu")
	public ModelAndView leftMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ParseURL parseURL		= new ParseURL();
		StringUtil sutil		= new StringUtil();
		ModelAndView mav		= new ModelAndView();


		parseURL.parseURL(request.getRequestURL().toString());
		String urlpath	= parseURL.getPath();

		String setLeftMenu = "menu0";
		if(sutil.strstr(urlpath, "/admin/basic") != -1){
			setLeftMenu = "menu1";
		}else if(sutil.strstr(urlpath, "/admin/product") != -1){
			setLeftMenu = "menu2";
		}else if(sutil.strstr(urlpath, "/admin/category") != -1){
			setLeftMenu = "menu3";
		}else if(sutil.strstr(urlpath, "/admin/order") != -1){
			setLeftMenu = "menu4";
		}else if(sutil.strstr(urlpath, "/admin/member") != -1){
			setLeftMenu = "menu6";
		}else if(sutil.strstr(urlpath, "/admin/board") != -1){
			List<BoardMain>	mainList	= boardMainService.getBoardMainJoinBoardGroupList();
			Iterator<BoardMain> itr = mainList.iterator();

			HashMap<String, String> op = new HashMap<String, String>();
			op.put("owner", "admin");
			BoardUtil boardUtil	= new BoardUtil();
			String opFlag = boardUtil.toJsonOpFlag(op);
			mav.addObject("opFlag", opFlag);

			HashMap<String, String> gnameList = new HashMap<String, String>();

	        while(itr.hasNext() == true){
	        	BoardMain boardmain	= itr.next();
	        	gnameList.put(boardmain.getG_name(), boardmain.getG_name());//한번의 query로 unique한 값을 얻기위해 약간 편법 사용

	        }
	        mav.addObject("mainList", mainList);
	        mav.addObject("gnameList", gnameList);
			setLeftMenu = "menu7";
		}else if(sutil.strstr(urlpath, "/admin/visit") != -1){
			setLeftMenu = "menu8";
		}


		mav.addObject("LeftMenu", setLeftMenu);
		mav.setViewName("admin/admin_left_menu.jsp");
		return mav;
	}

	/**
	 * 관리자 로그인 페이지 출력
	 * @return
	 */
	@RequestMapping("/admin/AdminLogin")
	public ModelAndView AdminLogin() {
		ModelAndView mav		= new ModelAndView();
		mav.setViewName("admin/admin_login.jsp");
		return mav;
	}
}
