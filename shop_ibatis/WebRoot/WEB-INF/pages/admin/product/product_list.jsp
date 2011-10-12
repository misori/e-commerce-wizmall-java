<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List product</title>
<script  language="javascript" src="<c:url value="/js/jquery.plugins/jquery.selectboxes.min.js"/>"></script>
<script type="text/javascript">
$(function(){
	$(".btn_reg").click(function(){
		location.href="${pageContext.request.contextPath}/admin/product/productWrite.do"
	})

	//검색 버튼 클릭시
	$(".btn_search").click(function(){
		$("#search_box").submit();
	});
	
	//정렬실렉트 박스 변경시
	$("#s_order").change(function(){
		$("#search_box").submit();
	});

	//분류카테고리 변경에 따른 값 변경
	$("#input_category1").change(function(){
		
		var val	= $("#input_category1 > option:selected").val();
		getCategory(val, 1)
	});

	$("#input_category2").change(function(){
		var val	= $("#input_category2 > option:selected").val();
		getCategory(val, 2)
	});

	$("#input_category3").change(function(){
		var val	= $("#input_category3 > option:selected").val();
		getCategory(val, 2)
	});

})

function getCategory(v, step) {
	
	if(step == 1) {
		$("#input_category2").removeOption(/./);
		$("#input_category3").removeOption(/./);
		$("#s_category").val(v);
	}else if(step == 2){
		$("#input_category3").removeOption(/./);
		$("#s_category").val(v);
	}
	else $("#s_category").val(v);

	$("#search_box").submit();
}

//페이지 넘김
function gotopage(page){
	$("#search_box").attr("action", $("#search_box").attr("action")+"?cp="+page);
	$("#search_box").submit();
}
</script>
</head>
<body>
<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>제품수정</legend>
			<div class="notice">[note]</div>
			<div class="comment"> 제품을 수정 삭제 하실 수 있습니다.<br />
				수정방법은 수정을 원하시는 제품을 선택 후 우측
				&quot;Modify&quot;아이콘을 클릭하시면 수정 페이지로 화면이 전환됩니다.<br />
				삭제방법은
				삭제를 원하시는 제품의 좌측 실렉트박스를 책크 후 하단 상품삭제하기를 클릭하시면 상품이 삭제됩니다.</div>
			</fieldset>
			<div class="space20"></div>

			<br />

<!--  아래는 변경해야할 원본 -->
검색 상품수 :

<fmt:formatNumber value="${pageNav.rowTotal}" />
개 
<form action="${pageContext.request.contextPath}/admin/product/productList.do" method="post" name="search_box" id="search_box" >
<input type="hidden" name="cp" id="cp" value="${pageNav.nowPage}" />
<input type="hidden" name="s_category" id="s_category" value="${params.s_category}">
<select name="s_title">
	<option value=""<c:if test = "${params.s_title == null || params.s_title == ''}" > selected="selected"</c:if>>전체</option>
	<option value="NAME"<c:if test = "${params.s_title == 'NAME'}" > selected="selected"</c:if>>제품명</option>
	<option value="MODEL"<c:if test = "${params.s_title == 'MODEL'}" > selected="selected"</c:if>>모델명</option>
	<option value="BRAND"<c:if test = "${params.s_title == 'BRAND'}" > selected="selected"</c:if>>브랜드</option>
</select>


<input type="text" name="s_keyword" value="${params.s_keyword}">
<span class="button bull btn_search"><a>검색</a></span>
		
				
				
<select name="input_category1" id="input_category1">
								<option value="" selected>대분류 </option>
								<option value="">----------------</option>
<c:forEach items="${categoryList.depth1}" var="current" varStatus="i">
<option value="${current.cat_no}"<c:if test = "${current.cat_no == fn:substring(params.s_category, 0, 3)}" > selected="selected"</c:if>>${current.cat_name}</option>
</c:forEach>
							</select>
							<select name="input_category2" id="input_category2">
								<option value="" selected>중분류</option>
								<option value="">----------------</option>
<c:forEach items="${categoryList.depth2}" var="current" varStatus="i">
	<c:if test="${fn:substring(current.cat_no,0,3) == fn:substring(params.s_category,0,3)}">
		<option value="${current.cat_no}"<c:if test = "${current.cat_no == fn:substring(params.s_category, 0, 6)}" > selected="selected"</c:if>>${current.cat_name}</option>
	</c:if>
</c:forEach>
							</select>
							<select name="input_category3" id="input_category3">
								<option value="" selected>소분류</option>
								<option value="">----------------</option>
<c:forEach items="${categoryList.depth3}" var="current" varStatus="i">
	<c:if test="${fn:substring(current.cat_no,0,6) == fn:substring(params.s_category,0,6)}">
		<option value="${current.cat_no}"<c:if test = "${current.cat_no == fn:substring(params.s_category, 0, 9)}" > selected="selected"</c:if>>${current.cat_name}</option>
	</c:if>
</c:forEach>
							</select>		
							
							<br />
							정렬방식 : 
							<select name="s_order" id="s_order">
							<option value="TID@desc"<c:if test = "${params.s_order	== 'TID@desc'}"> selected="selected"</c:if>>등록순</option>
							<option value="NAME@asc"<c:if test = "${params.s_order	== 'NAME@asc'}"> selected="selected"</c:if>>제품명</option>
							<option value="PRICE@desc"<c:if test = "${params.s_order	== 'PRICE@desc'}"> selected="selected"</c:if>>가격순(고)</option>
							<option value="PRICE@asc"<c:if test = "${params.s_order	== 'PRICE@asc'}"> selected="selected"</c:if>>가격순(저)</option>
							<option value="POINT@desc"<c:if test = "${params.s_order	== 'POINT@desc'}"> selected="selected"</c:if>>포인트순(고)</option>
							<option value="POINT@asc"<c:if test = "${params.s_order	== 'POINT@asc'}"> selected="selected"</c:if>>포인트순(저)</option>
							</select>
</form>



				<table class="table_main list">
					<col width="60" />
					<col width="70" />
					<col width="*" />
					<col width="70" />
					<col width="100" />
					<col width="100" />
					<col width="80" />
					<thead>
						<tr class="altern">
							<th>No</th>
							<th>제품이미지</th>
							<th>제품명</th>
							<th>모델명</th>
							<th>브랜드</th>
							<th>가격/포인트</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
<c:forEach items="${info}" var="current" varStatus="i">
						<tr>
							<td>${pageNav.startNum-i.index}</td>
							<td><img src="${pageContext.request.contextPath}/data/product/${current.attached}" width="50" height="50" /></td>
							<td>${current.name}</td>
							<td>${current.model}</td>
							<td>${current.brand}
							</td>
							<td>${current.price}
								원<br />
								${current.point}
								포인트 </td>
							<td><div ><span class="button bull"><a title="" href="${pageContext.request.contextPath}/admin/product/productEdit.do?tid=${current.tid}&">수정</a></span>
						<span class="button bull"><a title="" href="${pageContext.request.contextPath}/admin/product/productDelete_x.do?tid=${current.tid}&">삭제</a></span>
					</div></td>
						</tr>
</c:forEach>
					</tbody>
				</table>
				<div class="w_default">
				<div>
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
        		</div></div>
				</div>
		</td>
	</tr>
</table>






</body>
</html>