<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List product</title>
</head>
<body>
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
<form action="${pageContext.request.contextPath}/product/productLis.dot?code=${code}" method="post" id="search_box">
</form>
<form action="${pageContext.request.contextPath}/product/productView.do?code=${code}" method="post" id="vform">
<input type="hidden" name="cp" value="${cp}"/>
</form>
		<table class="table_main w_default">
			<tbody>
				<tr>
					<th>no</th>
					<th>이미지</th>
					<th>상품명</th>
					<th>모델명</th>
					<th>가격</th>
				</tr>

				<c:forEach items="${info}" var="current" varStatus="i">

				<tr>

					<td>${i.index}</td>
					<td><img src="${pageContext.request.contextPath}/data/product/${current.product.attached}" width="80" height="80" /></td>
					<td><a href="javascript:gotoview(${current.pid})">${current.product.name}</a></td>
					<td>${current.product.model}</td>
					<td>${current.price}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="agn_c"><span class="bull button btn_order"><a>주문하기</a></span></div>

</body>
</html>