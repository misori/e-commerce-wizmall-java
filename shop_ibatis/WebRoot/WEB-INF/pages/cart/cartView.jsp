<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Cart View</title>
<script type="text/javascript">
$(function(){
	$(".btn_order").click(function(){
		location.href="${pageContext.request.contextPath}/cart/orderForm.do"
	});
})

function gotopage(page){
	$("#search_box").attr("action", $("#search_box").attr("action")+"&cp="+page);
	$("#search_box").submit();
}

function gotoview(tid){
	var url	= $("#vform").attr("action")+"&tid="+tid;
	$("#vform").attr("action", url);
	$("#vform").submit();
}
</script>
</head>
<body>


<!-- cart List Start -->
<page:applyDecorator page="/cart/cartList.ajax?type=1" name="planepanel"/>
<!-- cart List End -->
<form action="${pageContext.request.contextPath}/product/productLis.dot?code=${code}" method="post" id="search_box">
</form>
		<div class="agn_c"><span class="bull button btn_order"><a>주문하기</a></span></div>
</body>
</html>