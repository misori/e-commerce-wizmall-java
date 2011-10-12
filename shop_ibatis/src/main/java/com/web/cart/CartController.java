package com.web.cart;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.Cart;
import com.service.CartService;
import com.util.StringUtil;

@Controller
public class CartController {
	//private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;

	/**
	 * 장바구니 저장
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cart/cartSave")
	public String saveCart(HttpServletRequest request) throws Exception{
		Cart cart				= new Cart();
		StringUtil stringUtil	= new StringUtil();
		HttpSession session		= request.getSession();
		String orderId;

		if(session.getAttribute("session-orderid") == null){
			orderId = Long.toString(System.currentTimeMillis());
			session.setAttribute("session-orderid", orderId);//세션 설정
		}else{
			orderId	= session.getAttribute("session-orderid").toString();
		}


		int tid 			= Integer.parseInt(request.getParameter("tid"));
		int qty				= stringUtil.strToint(request.getParameter("qty"));
		int price			= stringUtil.strToint(request.getParameter("price"));
		int point			= stringUtil.strToint(request.getParameter("point"));

		cart.setOid(orderId);
		cart.setPid(tid);
		cart.setQty(qty);
		cart.setPrice(price);
		cart.setTprice(price*qty);
		cart.setPoint(point);
		cart.setTpoint(point*qty);

		Date date = new Date();
		cart.setWdate(date);
		cartService.saveCart(cart);

		//return "forward:/cart/cartView";
		return "redirect:/cart/cartView.do";
	}

	/**
	 * 장바구니(실제적인 장바구니 항목은 아래의 cartList에서 처리)
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/cartView")
	public ModelAndView cartView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart/cartView.jsp");
		return mav;

	}

	/**
	 * 장바구니 리스트 보이기
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/cartList")
	public ModelAndView cartList(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String orderId;
		String type	= request.getParameter("type");// type=1 수정가능폼 , cart main 에서 넘어옮, 2: 수정불가, 3:관리자용
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("session-orderid") != null){
			orderId	= session.getAttribute("session-orderid").toString();
			mav.addObject("info", cartService.getCartList(orderId));
			mav.addObject("total", cartService.getPriceByOrderId(orderId));//배송비및 기타를 제외한 장바구니에 담긴 상품 총액만을 계산한다.
		}else if(request.getParameter("orderid") != null){//관리자단이나 주문 상세보기에서 넘어온경우
			orderId	= request.getParameter("orderid");
			mav.addObject("info", cartService.getCartList(orderId));
			mav.addObject("total", cartService.getPriceByOrderId(orderId));//배송비및 기타를 제외한 장바구니에 담긴 상품 총액만을 계산한다.
		}

		System.out.println("type:"+type);
		mav.addObject("type", type);
		mav.setViewName("cart/cartList.jsp");
		return mav;

	}

	/**
	 * 상품 구매 갯수 수정
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/setQty")
	public String  setQty(HttpServletRequest request) {
		int tid	= Integer.parseInt(request.getParameter("tid"));
		int buynum	= Integer.parseInt(request.getParameter("buynum"));
		Cart cart	= cartService.getCartItem(tid);

		cart.setQty(buynum);
		cart.setTprice(cart.getPrice()*buynum);
		cart.setTpoint(cart.getPoint()*buynum);

		cartService.saveCart(cart);
		 return "redirect:/cart/cartView.do";

	}

	/**
	 * 장바구니 아이템 삭제
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/deleteItem")
	public String  deleteItem(HttpServletRequest request) {
		int tid	= Integer.parseInt(request.getParameter("tid"));
		//Cart cart	= cartService.getCartItem(tid);
		//cartService.delCartItem(cart);
		cartService.deleteCart(tid);
		return "redirect:/cart/cartView.do";
	}


}
