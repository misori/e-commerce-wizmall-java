package com.web.product;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.ProductService;
import com.util.PageNavigation;
import com.util.StringUtil;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 제품리스트
	 *
	 */
	@RequestMapping("/product/productList")
	public ModelAndView userProductList(HttpServletRequest request) throws UnsupportedEncodingException {
		StringUtil stringUtil	= new StringUtil();
		ModelAndView mav 		= new ModelAndView();

		int cp	= stringUtil.strToint(request.getParameter("cp"));
		System.out.println("s_keyword1:"+request.getParameter("s_keyword"));
		if(cp < 1) cp = 1;
		String code			= stringUtil.emptyToNull(request.getParameter("code"));
		String s_title		= stringUtil.emptyToNull(request.getParameter("s_title"));
		String s_keyword	= stringUtil.emptyToNull(request.getParameter("s_keyword"));

		//s_keyword = "제품";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("cp",Integer.toString(cp));
		params.put("s_title",s_title);
		if(s_keyword != null)  params.put("s_keyword","%" + s_keyword + "%");
		if(code != null) params.put("s_category",code+"%");

	    //현재 총게시물수를 구해옮
	    int tc	= productService.countAllProduct(params);
		//int tc = 10;

	    int blockList	= 10;
	    int blockPage	= 5;


	    // 페인징 객체를 생성한다 (현재페이지, 전체글수, 페이지당표시할 글의수, 한번에 표시할 페이징블록수)
		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);
		//pageNav.Debug();
		mav.addObject("pageNav", pageNav.getParams());

		params.put("blockList",Integer.toString(blockList));
		params.put("StartRow",Integer.toString(pageNav.getStartRow()));

		//상품을 불러온다
		//System.out.println(params);
		mav.addObject("info", productService.getAllProductList(params));

		//keyword를 원래 값으로 치환한다.
		params.put("s_keyword",request.getParameter("s_keyword"));
		params.put("s_category",code);
		//if(s_keyword != null) params.put("s_keyword",URLDecoder.decode(request.getParameter("s_keyword"), "utf-8"));
		mav.addObject("params", params);
		mav.setViewName("product/productList.jsp");

		return mav;
	}

	/**
	 * 제품리스트(옵션보기)
	 */
	@RequestMapping("/product/searchList")
	public ModelAndView searchList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		int cp;
		String op			= request.getParameter("op");
		String s_title		= request.getParameter("s_title");
		String s_keyword	= request.getParameter("s_keyword");

		//페이지에 대한 정의
		if(request.getParameter("cp") == null) cp = 1;
		else cp = Integer.parseInt(request.getParameter("cp"));
	    if(cp < 1) cp = 1;




	    //카테고리에 대한 정의



	    //현재 총게시물수를 구해옮
	    int	tc	= productService.countProductByOption(op);


	    int blockList	= 10;
	    int blockPage	= 5;
		// 객체를 생성한다 (현재페이지, 전체글수, 페이지당표시할 글의수, 한번에 표시할 페이징블록수)
		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);

		// 디버깅이 필요할시 사용한다. 안써도 됨
		pageNav.Debug();

		Integer list_no	= tc - (blockList *( cp-1));//리스트될 경우 최상위 숫자

		// 뷰에게 넘길 값을 지정한다
		request.setAttribute("pageIsPrev",	pageNav.isPrevPage());		// 이전페이지 블록의 존재유무
		request.setAttribute("pageIsNext",	pageNav.isNextPage());		// 다음페이지 블록의 존재유무
		request.setAttribute("pageStart",	pageNav.getStartPage());	// 시작페이지 번호
		request.setAttribute("pageEnd",		pageNav.getEndPage());		// 종료페이지 번호

		//기타 뷰에 넘길 값 설정
		request.setAttribute("cp",			cp);		// 현재 페이지
		request.setAttribute("op",			op);		// 카테고리
		request.setAttribute("list_no",		list_no);	// 페이지 초기값
		request.setAttribute("s_title",		s_title);	// 검색항목
		request.setAttribute("s_keyword",	s_keyword); // 검색어

	    mav.addObject("info", productService.getProductByOption(op));


		//mav.addObject("info", productService.getProduct(pageNav.getStartRow(), blockList));
		mav.setViewName("product/productList.jsp");

		return mav;
	}

	/**
	 * 제품상세보기
	 */
	@RequestMapping("/product/productView")
	//public ModelAndView userProductView(@RequestParam Integer tid, HttpServletRequest request) {
	public ModelAndView userProductView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//HashMap<String, Category> depth1 = categoryList();
		//mav.addObject("categoryList", depth1);
		//System.out.println("tid:"+tid);
		//int cp;



		int tid = Integer.parseInt(request.getParameter("tid"));

		String code			= request.getParameter("code");
		String cp			= request.getParameter("cp");
		String s_title		= request.getParameter("s_title");
		String s_keyword	= request.getParameter("s_keyword");


		//System.out.println("tid:"+tid);
		//if(request.getParameter("cp") == null) cp = 1;
		//else cp = Integer.parseInt(request.getParameter("cp"));
		request.setAttribute("tid",			tid);
	    request.setAttribute("cp",			cp);
	    request.setAttribute("code",		code);
	    request.setAttribute("s_title",		s_title);
	    request.setAttribute("s_keyword",	s_keyword);

		mav.addObject("product", productService.getProductByPrimaryKey(tid));
		mav.setViewName("product/productView.jsp");
		return mav;
	}

	/**
	 * 상세검색 폼 출력
	 * @return
	 */
	@RequestMapping("/product/searchForm")
	public ModelAndView searchForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/searchForm.jsp");
		return mav;
	}

}
