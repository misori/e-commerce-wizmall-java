package com.web.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.Category;
import com.service.CategoryService;
@Controller
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;


	/**
	 * 카테고리 전체 출력폼
	 * @return : 페이지 출력
	 */
	@RequestMapping("/admin/category")
	public ModelAndView category() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/category/category.jsp");
		return mav;
	}

	/**
	 * 전체카테고리 리스트 출력
	 * @return : 페이지 출력
	 */
	@RequestMapping("/admin/category/categoryForm")
	public ModelAndView categoryForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoryList", getAllCategory());
		mav.setViewName("admin/category/categoryForm.jsp");
		return mav;
	}

	/**
	 * 전체카테고리 리스트를 구해와 depth별로 담아둔다.
	 * @return
	 */
	public HashMap<String, List<Category>> getAllCategory(){
		List<Category> categoryAllList	= categoryService.getAllCategoryList();
		HashMap<String, List<Category>> categoryList = getAllCategory(categoryAllList);
		return categoryList;
	}

	public HashMap<String, List<Category>> getAllCategory(List<Category> categoryAllList){

		Iterator<Category> iterator	= categoryAllList.iterator();

		//현재에 대한 각각의 분류를 담을 HashSet을 만든다.
		List<Category> depth1 = new ArrayList<Category>();
		List<Category> depth2 = new ArrayList<Category>();
		List<Category> depth3 = new ArrayList<Category>();


		while(iterator.hasNext())
		  {
			Category category = iterator.next();
			switch(category.getCat_no().length()){
				case 3://대분류
					depth1.add(category);
					break;
				case 6://중분류
					depth2.add(category);
					break;
				case 9://소분류
					depth3.add(category);
					break;
			}
		  }
		HashMap<String, List<Category>>categoryList = new HashMap<String, List<Category>>();
		categoryList.put("depth1", depth1);
		categoryList.put("depth2", depth2);
		categoryList.put("depth3", depth3);

		return categoryList;
	}

	/**
	 * 상위카테고리 리스트 가져오기
	 * @param step : 단계(1단계는 1차카테고리에서 넘어온 정보)
	 * @param value : 현재 넘어온 카테고리값
	 * @return
	 *

	public List<Category> getCategoryList() throws Exception{
		List<Category> categoryList	= categoryService.getCatList();
		return categoryList;
	}
	*/
	/**
	 * 특정 카테고리의 한단계 하위 리스트 가져오기
	 * @param step : 단계(1단계는 1차카테고리에서 넘어온 정보)
	 * @param value : 현재 넘어온 카테고리값
	 * @return
	 */
	public List<Category> getCategoryList(HashMap<String, String> params) throws Exception{
		//HashMap<String, String> params = new HashMap<String, String>();
		//params.put("length", Integer.toString(length));
		//params.put("cat_no", code);

		List<Category> categoryList	= categoryService.getCategoryList(params);
		return categoryList;
	}

	/*
	 * 카테고리 리스트 가져오기

	public HashMap<String, Category> getCategoryListTOHashMap() throws Exception{
		System.out.println("getCategoryListTOHashMap()");
		List<Category> categoryList		= this.getCategoryList();
		Iterator<Category> iterator		= categoryList.iterator();

		//현재에 대한 각각의 분류를 담을 HashSet을 만든다.
		while(iterator.hasNext())
		  {
			Category category = iterator.next();
			list.put(category.getCat_no(), category);
		  }
		return list;


	}

*/

	@RequestMapping("/admin/category/getCategoryListToJson")
	public void getCategoryListToJson(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		int step		= (Integer.parseInt(request.getParameter("step")) * 3 + 3);
		String cat_no	= request.getParameter("cat_no");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("c_length", Integer.toString(step));
		params.put("cat_no", cat_no+"%");
		List<Category> categoryList	= getCategoryList(params);

		Iterator<Category> iterator	= categoryList.iterator();
		Category category	= new Category();
		List<String[]> list = new ArrayList<String[]>();

		while(iterator.hasNext())
		  {
			String[] inJson	= new String[2];
			category = iterator.next();
			inJson[0]	= category.getCat_no();
			inJson[1]	= category.getCat_name();
			//System.out.println("cat_no:"+inJson[0]+", cat_name:"+inJson[1]);
			list.add( inJson );
		  }
		//System.out.println("inJson:"+list);
		JSONArray jsonArray = JSONArray.fromObject( list );
		//System.out.println( jsonArray );
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jsonArray);
	}


	/**
	 * 카테고리 등록
	 * @return
	 */
	@RequestMapping("/admin/category/addCategory")
	//@ModelAttribute Category category 를 사용하기 위해서는 상기 com.doamin.Category가 import 되어 있어야 한다.
	//public void addCategory(@ModelAttribute Category category, HttpServletResponse response) throws Exception{
	public void addCategory(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Category category	= new Category();


		String cat_name		= request.getParameter("cat_name");
		String c_code		= request.getParameter("c_code");// 자동으로 카테고리 no 및 카테고리 정렬순서 구하기

		if (c_code == null) c_code = "";
		Integer lv			= c_code.length() / 3 + 1;
		//System.out.println("lv:"+lv+", c_code:"+c_code+", cat_name:"+cat_name);
		String cat_no		= c_code+getcatno(lv, c_code);

		//String cat_no		= "";

		Integer cat_order	= getcatorder(lv, c_code);
		category.setCat_order(cat_order);
		category.setCat_name(cat_name);
		category.setCat_no(cat_no);
		categoryService.saveCategory(category);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "0");
		//String json = jsonObject.toString();
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}

	/**
	 * 카테고리 수정
	 * @return
	 */
	@RequestMapping("/admin/category/chCategory")
	public void chCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{


		Category category	= new Category();

		String cat_name		= request.getParameter("cat_name");
		String c_code		= request.getParameter("c_code");
		int	tid				= Integer.parseInt(request.getParameter("tid"));

		category.setCat_name(cat_name);
		category.setCat_no(c_code);
		category.setTid(tid);
		categoryService.saveCategory(category);

		//실제적인 수정은 상기에서 끝남, 하기는 json 출력 관련.

		Map<String, String> hm = new HashMap<String, String>();

		hm.put("result", "0");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", hm);
		String json = jsonObject.toString();
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(json);
	}

	/**
	 * 카테고리 삭제
	 * @return
	 */
	@RequestMapping("/admin/category/delCategory")
	public void delCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		/*
		현재는 간단하게 처리되나 추후 카테고리에 상품이 등록(카테고리 등록) 있는지 확인등등을 거쳐 삭제한다.
		*/
		String c_code		= request.getParameter("c_code");
		int tid			= Integer.parseInt(request.getParameter("tid"));
		Category category	= categoryService.getCategoryByPrimaryKey(tid);

		categoryService.deleteCategory(category);

		//실제적인 수정은 상기에서 끝남, 하기는 json 출력 관련.
		JSONObject object=new JSONObject();
		object.put("result", "0");
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(object);
	}

	/**
	 * 레벨에 따른 카테고리 순서 뽑아오기
	 * @param lv : 현재 카테고리 레벨
	 * @param ccode : 현재카테고리 코드
	 * @return
	 */
	public Integer getcatorder(Integer lv, String ccode){
		Integer strlen = lv * 3;//카테고리당 3자리씩 자른다.(뒤부터 1차, 2차...  카테고리
		Integer comlen = strlen - 3;
		Integer maxcnt	= categoryService.getMaxCatOrder(comlen, ccode, strlen);
		//$sqlstr = "select max(cat_order) from ".$this->tables["category"]." where LENGTH(CAT_NO) = $strlen and LEFT(cat_no, $comlen) = '$ccode' and CAT_FLAG='wizmall'";
		//$maxcnt = $this->db->get_one($sqlstr);
		//return maxcnt+1;
		return maxcnt + 1;
	}

	/**
	 * 카테고리 번호 가져오기
	 * @param lv : 현재 카테고리 레벨
	 * @param ccode : 현재카테고리 코드
	 * @return
	 */
	public String getcatno(Integer lv, String ccode){
		Integer strlen = lv * 3;//카테고리당 3자리씩 자른다.(뒤부터 1차, 2차...  카테고리
		Integer comlen = strlen - 3;
		System.out.println("comlen:"+comlen+", ccode:"+ccode+", strlen:"+strlen);
		String maxCatno	= categoryService.getMaxCatno(comlen, ccode, strlen);
		maxCatno = maxCatno == null ? "001":maxCatno;
		//.toString();
		//System.out.println("maxCatno:"+maxCatno);
		String returnStr;
		if(maxCatno != null && maxCatno != ""){
			//System.out.println(maxCatno);
			Integer	inputInteger = 	Integer.valueOf(maxCatno)+1;
			returnStr = String.format("%1$3s", inputInteger.toString());
			//System.out.println(returnStr);
			returnStr	= returnStr.replace(" ", "0");
			//System.out.println(returnStr);
		}else{
			returnStr = "001";
		}

		//System.out.println("returnStr:"+returnStr);
		return returnStr;
	}

}

/**
 * 카테고리리스트에 대한 객체배열 생성
 * @author YoungHyeongRyu
 *
 */
/*
 * class CategoryList {
	private String sn;
	private String name;
	public CategoryList(String sn, String name){
		this.sn=sn;
		this.name=name;
	}
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

*/
