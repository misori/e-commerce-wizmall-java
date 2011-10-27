package com.web;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.Banner;
import com.domain.Category;
import com.domain.Product;
import com.domain.VisitMain;
import com.domain.VisitReferer;
import com.service.BannerService;
import com.service.CategoryService;
import com.service.ProductService;
import com.service.VisitMainService;
import com.service.VisitRefererService;
import com.util.Constants;
import com.util.Serialization;
import com.web.admin.AdminBasicController;

@Controller
public class DefaultController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private BannerService bannerService;

	@Autowired
	private VisitMainService visitMainService;

	@Autowired
	private VisitRefererService visitRefererService;

	@RequestMapping("/blankOutput")
	public ModelAndView listMembers() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("blank.jsp");
		return mav;
	}

	/**
	 * 메인페이지
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView mainView(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		/*
		//System.out.println("referer:"+request.getHeader("referer"));
		System.out.println("SERVER_NAME         :"+request.getServerName());
		System.out.println("SERVER_SOFTWARE     :"+request.getScheme());
		System.out.println("SERVER_PROTOCOL     :"+request.getProtocol());
		System.out.println("SERVER_PORT         :"+request.getServerPort());
		System.out.println("REQUEST_METHOD      :"+request.getMethod());
		System.out.println("PATH_INFO           :"+request.getPathInfo());
		System.out.println("PATH_TRANSLATED     :"+request.getPathTranslated());
		System.out.println("SCRIPT_NAME         :"+request.getServletPath());
		System.out.println("QUERY_STRING        :"+request.getQueryString());
		System.out.println("REMOTE_HOST         :"+request.getRemoteHost());
		System.out.println("REMOTE_ADDR         :"+request.getRemoteAddr());
		System.out.println("AUTH_TYPE           :"+request.getAuthType());
		System.out.println("REMOTE_USER         :"+request.getRemoteUser());
		System.out.println("CONTENT_TYPE        :"+request.getContentType());
		System.out.println("CONTENT_LENGTH      :"+request.getContentLength());
		System.out.println("HTTP_ACCEPT         :"+request.getHeader("Accept"));
		System.out.println("Proxy-Client-IP     :"+request.getHeader("Proxy-Client-IP"));
		System.out.println("HTTP_USER_AGENT     :"+request.getHeader("User-Agent"));
		System.out.println("HTTP_REFERER        :"+request.getHeader("Referer"));

		System.out.println("REMOTEADDR        :"+request.getRemoteAddr().toString());
		//IPv6형식 이클립스 > Run > Run Configuration > Arguments > -Djava.net.preferIPv4Stack=true
		//localhost 대신에 다른 경로를 잡아주면 정상적으로 출력된다.
		String clientIP = request.getHeader("Proxy-Client-IP");
		if(clientIP == null){
			clientIP = request.getHeader("WL-Proxy-Client-IP");
			if(clientIP == null){
				clientIP = request.getHeader("X-Forwared-For");
				if(clientIP == null){
					//clientIP = request.getCl
				}
			}
		}

		System.out.println("clientIP        :"+clientIP);

*/

		//로그 카운트를 남김
		saveVistCount(request);

		//hit 상품가져오기
		List<Product> product_hit	= productService.getProductByOption("40");
		mav.addObject("product_hit", product_hit);

		//신규 상품가져오기
		List<Product> product_new	= productService.getProductByOption("20");
		mav.addObject("product_new", product_new);


		//각종 베너를 가져온다.
		//현재 베너를 불러온다.
		AdminBasicController ctrlAdminBasic	= new AdminBasicController();
		List<Banner> banner	= bannerService.getAllBannerList();
		mav.addObject("banner", ctrlAdminBasic.bannerSetList(banner));


		mav.setViewName("main.jsp");
		return mav;
	}

	private void saveVistCount(HttpServletRequest request){

		//오늘날짜로 입력된 정보출력

		Calendar cal = Calendar.getInstance();
		VisitMain visitMain	= visitMainService.getVistMainByDate(cal.getTime());
		//new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

		VisitReferer visitReferer	= new VisitReferer();
		//visitReferer.setHit(1);
		visitReferer.setW_date(cal.getTime());
		visitReferer.setIp(request.getRemoteAddr());
		visitReferer.setReferer(request.getHeader("referer"));

		//오늘 방문자수가 없으면 오늘 데이타를 입력해준다.
		if(visitMain == null){
			visitMain	= new VisitMain();
			visitMain.setPageview(1);
			visitMain.setUnique_counter(1);
			//visitMain.setW_date(date);
			visitMainService.saveVisitMain(visitMain);
		}else{
			visitMain.setPageview(visitMain.getPageview()+1);

			//Referer에서 오늘날짜 및 IP를 비교하여 Unique_counter 여부를 체크 한다.

			VisitReferer is_visitReferer	= visitRefererService.getVisitReferer(visitReferer);

			if(visitReferer != null) visitMain.setUnique_counter(visitMain.getUnique_counter()+1);
			visitMainService.updateVisitMain(visitMain);
		}


		visitRefererService.saveVisitReferer(visitReferer);

	}

	/**
	 * 전체페이지의 Top Menu
	 */
	@RequestMapping("/topMenu")
	public ModelAndView topMenu() {
		ModelAndView mav = new ModelAndView();

		//각종 베너를 가져온다.
		//현재 베너를 불러온다.
		AdminBasicController ctrlAdminBasic	= new AdminBasicController();
		List<Banner> banner	= bannerService.getAllBannerList();
		mav.addObject("banner", ctrlAdminBasic.bannerSetList(banner));

		mav.setViewName("common/topmenu.jsp");
		return mav;
	}

	/**
	 * 전체페이지의 Left Menu
	 */
	@RequestMapping("/leftMenu")
	public ModelAndView leftMenu() {

		ModelAndView mav = new ModelAndView();

		// String now = (new java.util.Date()).toString();
	       // logger.info("returning hello view with " + now);

		//Map<String, Object> myModel = new HashMap<String, Object>();
		//List<Category> categoryList	= categoryService.getAllCategoryList();
		List<Category> categoryList	= categoryService.getCategoryPerLength(3);
		mav.addObject("category", categoryList);

		//각종 베너를 가져온다.
		//현재 베너를 불러온다.
		AdminBasicController ctrlAdminBasic	= new AdminBasicController();
		List<Banner> banner	= bannerService.getAllBannerList();
		mav.addObject("banner", ctrlAdminBasic.bannerSetList(banner));

		mav.setViewName("common/leftmenu.jsp");
		return mav;
	      // return new ModelAndView("common/leftmenu.jsp", "info", myModel);

	}

	/**
	 * 전체페이지의 Bottom Menu
	 */
	@RequestMapping("/bottomMenu")
	public ModelAndView bottomMenu() {
		ModelAndView mav = new ModelAndView();

		//각종 베너를 가져온다.
		//현재 베너를 불러온다.
		AdminBasicController ctrlAdminBasic	= new AdminBasicController();
		List<Banner> banner	= bannerService.getAllBannerList();
		mav.addObject("banner", ctrlAdminBasic.bannerSetList(banner));

		//홈페이지 기본 정보값을 불러온다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/basicInfo";
		HashMap<String, String> basicinfo = serialization.unSerialize(outPath, "hashMap");
		mav.addObject("basicinfo", basicinfo);

		mav.setViewName("common/bottommenu.jsp");
		return mav;
	}


	/**
	 * 회사소개 페이지
	 * @return
	 */
	@RequestMapping("/html/company")
	public ModelAndView company() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("html/company.jsp");
		return mav;
	}
	/**
	 * 개인정보보호정책
	 * @return
	 */
	@RequestMapping("/html/privacy")
	public ModelAndView privacy() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("html/privacy.jsp");
		return mav;
	}

	/**
	 * 이용약관
	 * @return
	 */
	@RequestMapping("/html/agreement")
	public ModelAndView agreement() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("html/agreement.jsp");
		return mav;
	}

}
