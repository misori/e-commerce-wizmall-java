<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List product</title>
<script type="text/javascript" src="<c:url value="/js/mall.js"/>"></script>
<script type="text/javascript">
$(function(){
	$(".btn_product_search").click(function(){
		$("#cp").val("1");
		//$("#s_keyword").val(eval($("#s_keyword").val()));
		$("#search_box").submit();
	});
})

function gotopage(page){
	//$("#search_box").attr("action", $("#search_box").attr("action")+"&cp="+page);
	$("#cp").val(page);
	$("#search_box").submit();
}


function orderbyfnc(stritem,strorder){
	var f = document.OrderForm;
	f.orderby.value = stritem;
	f.submit();

}

function listAllfnc(v){
	f = document.OrderForm;
	if(v.checked){
		f.ShopListNo.value = "all";
		f.submit();
	}

}

</script>
</head>
<body>


<div class="navy">Home path </div>	
<dl class="m_cat">
<dt>title</dt>
<dd><ul>
      
          <li> </li>
 </ul></dd>
</dl>

<div class="space15"></div>
<form action="${pageContext.request.contextPath}/product/productList.do" method="get" id="search_box">
	<input type="hidden" id="pd_code" name="code" value="${params.s_category}">
	<input type="hidden" id="cp" name="cp" value="${params.cp}"/>
	<div class="agn_r">

		<input type="text" name="s_keyword" id="s_keyword" value="${params.s_keyword}" />
		<span class="button bull btn_product_search"><a>검색</a></span>

	</div>
</form>

<div class="space15"></div>
<ul class="sub_product_list">

<c:forEach items="${info}" var="current" varStatus="i">

	<li><a class="btn_product_view hand" product_data="${current.tid}|카테고리정보|재고여부"><img src="${pageContext.request.contextPath}/data/product/${current.attached}" width="80" height="80" /></a>
		<div class="desc_pd_list"> <span class="p_name">
		
			${current.name}
			</span>
			<br />
			${current.model}
		<br />
			<span class="p_price">
			<fmt:formatNumber value="${current.price}" />
			</span> 원 </div>
	</li>
</c:forEach>

</ul>
<div class="paging_box">
        <c:if test="${pageNav.isPrevPage=='true'}">
            <a href="javascript:gotopage(${pageNav.startPage - 1})">prev</a>
        </c:if>
        <c:forEach var="page" begin="${pageNav.startPage}" end="${pageNav.endPage}">
            <a href="javascript:gotopage(${page})">[${page}] </a>
        </c:forEach>
        <c:if test="${pageNav.isNextPage == 'true'}">
            <a href="javascript:gotopage(${pageNav.endPage + 1})">next</a>
        </c:if>
</div>

</body>
</html>