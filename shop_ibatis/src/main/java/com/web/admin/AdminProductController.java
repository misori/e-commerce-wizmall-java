package com.web.admin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.ProductDAO;
import com.dao.ProductImageDAO;
import com.domain.Category;
import com.domain.Product;
import com.domain.ProductImage;
import com.service.CategoryService;
import com.service.ProductImageService;
import com.service.ProductService;
import com.util.ArrayUtil;
import com.util.Constants;
import com.util.PageNavigation;
import com.util.StringUtil;
import com.util.file.FileUpload;
import com.util.file.FileUploadUtil;
import com.web.admin.AdminCategoryController;

@Controller
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductImageDAO productImageDAO;

	/**
	 * 제품리스트 출력폼
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/product/productList")
	public ModelAndView productList(HttpServletRequest request) throws Exception{
		StringUtil stringUtil	= new StringUtil();
		ModelAndView mav		= new ModelAndView();
		AdminCategoryController	adminCategoryController	= new AdminCategoryController();

		//String code			= stringUtil.emptyToNull(request.getParameter("code"));
		String s_title		= stringUtil.emptyToNull(request.getParameter("s_title"));
		String s_keyword	= stringUtil.emptyToNull(request.getParameter("s_keyword"));
		String s_category	= stringUtil.emptyToNull(request.getParameter("s_category"));
		String s_order		= stringUtil.emptyToNull(request.getParameter("s_order"));

		Integer cp			= stringUtil.strToint(request.getParameter("cp"));
		if(cp < 1) cp = 1;

		HashMap<String, String> params = new HashMap<String, String>();
		//params.put("code",code);
		params.put("s_title",s_title);
		if(s_keyword != null) params.put("s_keyword","%"+s_keyword+"%");
		if(s_category != null) params.put("s_category",s_category+"%");


	    //현재 총게시물수를 구해옮
	    int tc	= productService.countAllProduct(params);


	    int blockList	= 10;
	    int blockPage	= 5;
		// 객체를 생성한다 (현재페이지, 전체글수, 페이지당표시할 글의수, 한번에 표시할 페이징블록수)
		PageNavigation pageNav = new PageNavigation(cp, tc, blockList, blockPage);
		mav.addObject("pageNav", pageNav.getParams());


		//전체카테고리 가져오기(depth별 정리되어져 있음)
		List<Category> categoryAllList	= categoryService.getAllCategoryList();
		HashMap<String, List<Category>> categoryList = adminCategoryController.getAllCategory(categoryAllList);
		mav.addObject("categoryList", categoryList);

		//상품리스트를 가져옮
		params.put("blockList",Integer.toString(blockList));
		params.put("StartRow",Integer.toString(pageNav.getStartRow()));
		params.put("s_order",s_order);
		mav.addObject("info", productService.getAllProductList(params));

		//키워드를 원래 값으로 돌려둔다.
		params.put("s_keyword",request.getParameter("s_keyword"));
		params.put("s_category",request.getParameter("s_category"));
		mav.addObject("params", params);
		mav.setViewName("admin/product/product_list.jsp");
		return mav;
	}

	/**
	 * 제품 등록폼 출력
	 * @throws Exception
	 */
	@RequestMapping("/admin/product/productWrite")
	public ModelAndView productWrite() throws Exception {
		ModelAndView mav = new ModelAndView();
		AdminCategoryController	adminCategoryController	= new AdminCategoryController();
		//전체카테고리 가져오기(depth별 정리되어져 있음)
		List<Category> categoryAllList	= categoryService.getAllCategoryList();
		HashMap<String, List<Category>> categoryList = adminCategoryController.getAllCategory(categoryAllList);
		mav.addObject("categoryList", categoryList);

		//옵션코드 출력
		mav.addObject("regOption", Constants.ProdRegOption());

		mav.setViewName("admin/product/product_write.jsp");

		return mav;
	}


	/**
	 * 제품 수정폼 출력
	 * @throws Exception
	 */
	@RequestMapping("/admin/product/productEdit")
	public ModelAndView productEdit(@RequestParam Integer tid) throws Exception {
		ModelAndView mav = new ModelAndView();
		AdminCategoryController	adminCategoryController	= new AdminCategoryController();

		Product product	= productDAO.getProductByPrimaryKey(tid);
		mav.addObject("product", product);

		//전체카테고리 가져오기(depth별 정리되어져 있음)
		List<Category> categoryAllList	= categoryService.getAllCategoryList();
		HashMap<String, List<Category>> categoryList = adminCategoryController.getAllCategory(categoryAllList);
		mav.addObject("categoryList", categoryList);

		//모든 이미지 정보 가져오기
		mav.addObject("attachedInfo", productImageDAO.getProductImageByProduct(tid));

		//regoption 처리
		String[] splitRegoption	= product.getRegoption().split("\\|");
		mav.addObject("regoption", splitRegoption);
		ArrayUtil.dumpArray(splitRegoption);
		//옵션코드 출력
		mav.addObject("regOption", Constants.ProdRegOption());


		mav.setViewName("admin/product/product_write.jsp");
		return mav;
	}


	/**
	 * 카테고리 리스트를 hashMap으로 출력
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Category> getCategoryListTOHashMap() throws Exception{
		List<Category> categoryList	= categoryService.getCategoryList(null);
		Iterator<Category> iterator		= categoryList.iterator();

		//현재에 대한 각각의 분류를 담을 HashSet을 만든다.
		HashMap<String, Category>list = new HashMap<String, Category>();
		//<List>
		while(iterator.hasNext())
		  {
			Category category = iterator.next();
			list.put(category.getCat_no(), category);
		  }
		return list;
	}

	public HashMap<String, Category> getCategoryListTOHashMap(String code) throws Exception{
		List<Category> categoryList	= categoryService.getCategoryList(null);
		Iterator<Category> iterator		= categoryList.iterator();

		//현재에 대한 각각의 분류를 담을 HashSet을 만든다.
		HashMap<String, Category>list = new HashMap<String, Category>();
		while(iterator.hasNext())
		  {
			Category category = iterator.next();
			list.put(category.getCat_no(), category);
		  }
		return list;
	}


	/**
	 * 제품저장
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/product/productReg_x")
	public String productReg_x(HttpServletRequest request) throws Exception {

		StringUtil stringUtil = new StringUtil();
		//FileManage filemanage	= new FileManage();//파일매니저 set
		FileUploadUtil fileUploadUtil = new FileUploadUtil();

		Product product	= new Product();
		String p_category	= "";
		if(!request.getParameter("input_category3").equals("")) p_category = request.getParameter("input_category3");
		else if(!request.getParameter("input_category2").equals("")) p_category = request.getParameter("input_category2");
		else p_category = request.getParameter("input_category1");

		String[] check_regoption	= request.getParameterValues("regoption");
		String regoption = "";
		try{
			for(int i=0;i<check_regoption.length;i++){
				regoption += check_regoption[i]+"|";
			}
		}
		catch(Exception e){}

		String tid	= request.getParameter("tid").trim();

		//if(tid.equals("")) tid = null;
		//product.setAttached();
		if(stringUtil.isInt(tid)) product.setTid(Integer.parseInt(tid));

		product.setBrand(request.getParameter("brand"));
		//System.out.println("setCategory:"+request.getParameter("input_category1")+"/"+request.getParameter("input_category2")+"/"+request.getParameter("input_category3"));
		product.setCategory(p_category);
		product.setCompname(request.getParameter("compname"));
		product.setDescription1(request.getParameter("description1"));
		product.setDescription2(request.getParameter("description2"));
		product.setDescription3(request.getParameter("description3"));
		//product.setHit(request.getParameter(""));
		product.setModel(request.getParameter("model"));
		product.setName(request.getParameter("name"));
		product.setPoint(stringUtil.strToint(request.getParameter("point")));
		product.setPrice(stringUtil.strToint(request.getParameter("price")));
		product.setPrice1(stringUtil.strToint(request.getParameter("price1")));
		product.setRegoption(regoption);
		product.setTexttype(request.getParameter("texttype"));
		product.setVendor(request.getParameter("vendor"));
		product.setNone( stringUtil.strToint(request.getParameter("none")));


		List<ProductImage> attachedInfo	= null;
		if(stringUtil.isInt(tid)){//수정모드일경우 기존 이미지 정보를 받아온후 모두 날린다.
			//기존 Image정보 불러오기
			attachedInfo = productImageDAO.getProductImageByProduct(Integer.parseInt(tid));
			//삭제하기
			productImageService.deleteProductImageByProduct(Integer.parseInt(tid));
		}


		//이미지를 새로이 저장하고 그 값들을 List에 넣어둔다.
		//List<FileUpload> FileList = getBoardFileList(request);
		String realUploadPath = Constants.AbsolutePath+"product/";
		List<FileUpload> FileList = fileUploadUtil.getFileList(request, realUploadPath);
		for ( int i=0; i<FileList.size(); i++ ){
			//System.out.println(FileList.get(i).getFileName());

			if(FileList.get(i).getFileSize() != 0){
				if(i == 1){//현재 파일이 작은이미지이면 상품 테이블에도 입력한다.(0: 큰이미지, 2:중간이미지)
					product.setAttached(FileList.get(i).getFileName());
				}

				//그리고 모든 이미지를 이미지 테이블에 입력한다.
				ProductImage productImage = new ProductImage();
				productImage.setFilename(FileList.get(i).getFileName());
				productImage.setOrderid(i);
				productImage.setOpflag("M");

				if(stringUtil.isInt(tid)){//수정모드이면
					productImage.setPid(Integer.parseInt(tid));
				}else{//신규이면
					int maxTid = productDAO.getProductMaxTid();
					productImage.setPid(maxTid);
					//현재 ProductTable의 Max값을 가져온다.(약간은 위험한 방식)
				}

				productImageService.saveProductImage(productImage);
			}else{//0일경우는 기존 데이타와 비교하여 기존 데이타가 있으면 그 데이타를 그대로 넣어준다.
				if(stringUtil.isInt(tid)){//수정모드인 경우
					Iterator<ProductImage> iterImages		= attachedInfo.iterator();
					while(iterImages.hasNext())
					  {

						//System.out.println("this must be printed");
						ProductImage pimg = iterImages.next();
						if(pimg.getOrderid() == i){
							if(i == 1){//현재 파일이 작은이미지이면 상품 테이블에도 입력한다.(0: 큰이미지, 2:중간이미지)
								product.setAttached(pimg.getFilename());
							}

							//그리고 모든 이미지를 이미지 테이블에 입력한다.
							ProductImage productImage = new ProductImage();
							productImage.setFilename(pimg.getFilename());
							productImage.setOrderid(i);
							productImage.setOpflag("M");
							productImage.setPid(Integer.parseInt(tid));

							productImageService.saveProductImage(productImage);
						}
					  }
				}
			}
		}

		//System.out.println("productReg_x:"+product);
		productService.saveProduct(product);
		return "forward:/admin/product/productList.do";
	}


	/**
	 * 제품 삭제 : 이미지삭제, 다중카테고리에 등록된 제품 삭제, 카테고리에서 등록된 갯수 카운트, 관련 리플 삭제
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/product/productDelete_x")
	public String productDelete_x(HttpServletRequest request) throws Exception {

		Integer tid	= Integer.parseInt(request.getParameter("tid"));

		// Integer tid	= Integer.parseInt(request.getParameter("prerequest").getParameter("tid"));
		productService.deleteProduct(tid);
		return "forward:/admin/product/productList.do";
		//return "redirect:/admin/product/productList.do;
	}

}
