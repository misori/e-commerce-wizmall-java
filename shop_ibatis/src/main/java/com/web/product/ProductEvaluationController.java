package com.web.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.ProductEvaluation;
import com.service.ProductEvaluationService;
import com.util.StringUtil;

@Controller
public class ProductEvaluationController {

	@Autowired
	private ProductEvaluationService productEvaluationService;

	private StringUtil stringUtil;

	public ProductEvaluationController(){
		stringUtil	= new StringUtil();
	}


	/**
	 * 평가저장하기
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/product/saveEvaluation")
	public void saveEvaluation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contents		= request.getParameter("contents");
		int grade			= stringUtil.strToint(request.getParameter("grade"));
		int pid				= stringUtil.strToint(request.getParameter("pid"));
		JSONObject object=new JSONObject();
		if(SecurityContextHolder.getContext().getAuthentication() == null){
			object.put("result","1");//로그인 요청
			response.setContentType("application/x-json; charset=UTF-8");//이부분이 없을경우 일부브라우저에서 에러가 출력된다.
			response.getWriter().print(object);
		}else{
			final String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
			ProductEvaluation productEvaluation	= new ProductEvaluation();
			productEvaluation.setContents(contents);
			productEvaluation.setGrade(grade);
			productEvaluation.setUser_id(user_id);
			productEvaluation.setPid(pid);
	
			productEvaluationService.saveProductEvaluation(productEvaluation);
	
			
			object.put("result","0");
			response.setContentType("application/x-json; charset=UTF-8");//이부분이 없을경우 일부브라우저에서 에러가 출력된다.
			response.getWriter().print(object);
		}
	}

	/**
	 * 평가하기 리스트 가져오기
	 * @param request
	 * @return
	 */
	@RequestMapping("product/evaluationList")
	public ModelAndView evaluationList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		int pid				= stringUtil.strToint(request.getParameter("pid"));
		System.out.println("evaluationList:");
		List<ProductEvaluation> list	= productEvaluationService.getProductEvaluationListByPid(pid);

		//로그인 정보 가져오기
		HashMap<String, String> params = new HashMap<String, String>();
		//로그인 정보 가져오기
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && (!AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass()))) {
			params.put("user_id", auth.getName());
		}
		mav.addObject("params", params);


		mav.addObject("list", list);
		mav.setViewName("product/evaluationList.jsp");
		return mav;
	}

	@RequestMapping("/product/deleteEvaluation")
	public void deleteEvaluation(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int tid				= stringUtil.strToint(request.getParameter("tid"));


		productEvaluationService.deleteProductEvaluation(tid);

		JSONObject object=new JSONObject();
		object.put("result","0");
		response.setContentType("application/x-json; charset=UTF-8");//이부분이 없을경우 일부브라우저에서 에러가 출력된다.
		response.getWriter().print(object);
	}

}
