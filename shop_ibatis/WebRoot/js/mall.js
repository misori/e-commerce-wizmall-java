$(function(){
	//상품상세보기
	$(".btn_product_view").click(function(){
		var product_data = $(this).attr("product_data");
		var tmp			= product_data.split('|');
		var tid			= tmp[0];
		var category	= tmp[1];
		var none		= tmp[2];
		if(none == "1"){
			jAlert('품절된 제품입니다.');
		}else{
			var url	= contextPath+"/product/productView.do?code="+$("#pd_code").val()+"&tid="+tid;
			$("#search_box").attr("method", "post");
			$("#search_box").attr("action", url);
			$("#search_box").submit();
			//location.href = "/shop/product/view/"+tid+"/"+category;
		}
	});

	//쇼핑백에 담기
	$(".btn_save_cart").click(function(){
		$("#view_form").submit();
		/*
		$.post("/shop/cart/cart_x",$("#view_form").serialize(), function(data){
			$("#view_form").attr("action", "/shop/cart");
			$("#view_form").submit();//쇼핑계속하기를 클릭했을 시 인자값 유지	//hidden_category
		});
		*/

	});
});


//장바구니 가져오기
function getcartList(){
	$.post("/shop/cart/cartList", {}, function(data){
		//alert(data);
		$("#cartHTML").html(data);
	});
}