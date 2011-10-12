<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>홈페이지메인</title>
<script>
$(function(){
	$(".btn_product_view").click(function(){
		var i = $(".btn_product_view").index(this);
		var sp	= $(this).attr("product_data").split("|");//tid|category|none
		//alert(sp);
		location.href="${pageContext.request.contextPath}/product/productView.do?code="+sp[1]+"&tid="+sp[0];
	});
});
</script>
</head>
<body>

<div class="space5"></div>
<div id="main_top_banner">
<c:forEach var="j" items="${banner}"> 
	<c:if test="${j.key == 30}">
		<c:forEach var="k" items="${j.value}"> 
			<img src="${pageContext.request.contextPath}/data/banner/${k.attached}">
		</c:forEach>
	</c:if>
</c:forEach>
</div>
<ul class="main_center_banner">
	<c:forEach var="j" items="${banner}"> 
		<c:if test="${j.key == 40}">
			<c:forEach var="k" items="${j.value}"> 
		<li><img src="${pageContext.request.contextPath}/data/banner/${k.attached}"></li>
			</c:forEach>
		</c:if>
	</c:forEach>
</ul>
<div class="clear">
<div class="main_product_title">
<a href="${pageContext.request.contextPath}/product/searchList.do?op=40"><img src="${pageContext.request.contextPath}/images/shop/title_hit.gif"></a>
</div>
<ul class="main_product_list">
<c:forEach items="${product_hit}" var="current" varStatus="i">
		<li><a class="btn_product_view" product_data="${current.tid}|${current.category}|${current.none}">
		<img src="${pageContext.request.contextPath}/data/product/${current.attached}" width="132"  height="132" >${current.attached}</a>
			<br />
			<span class="p_name">${current.name}</span>
			<br />
			<span class="p_price"><fmt:formatNumber value="${current.price}" /></span>
						원
		</li>
</c:forEach>
</ul>

<div class="clear">

<div class="main_product_title">
<a href="${pageContext.request.contextPath}/product/searchList.do?op=20"><img src="${pageContext.request.contextPath}/images/shop/title_new.gif"></a>
</div>
<ul class="main_product_list">
<c:forEach items="${product_new}" var="current" varStatus="i">
		<li><a class="btn_product_view" product_data="${current.tid}|${current.category}|${current.none}"><img src="${pageContext.request.contextPath}/data/product/${current.attached}" width="132"  height="132" ></a>
			<br />
			<span class="p_name">${current.name}</span>
			<br />
			<span class="p_price"><fmt:formatNumber value="${current.price}" /></span>
						원
		</li>
</c:forEach>
</ul>



</body>
</html>