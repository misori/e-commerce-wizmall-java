<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("newLineChar", "\n");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>상품 상세 검색 페이지</title>
<script src="${pageContext.request.contextPath}/js/mall.js"></script>
<script>
<!--
$(function(){

});
function naturalcheck()
{
	if (document.natural.keyword.value.length < 1) {
	window.alert('\n다중검색에 필요한 단어들을 입력해 주세요.\n');
	document.natural.keyword.focus();
	return false;
	}

}
//-->
</script>

  </head>

  <body>
<div class="navy">Home &gt; 상세검색</div>
<div class="space20"></div>

찾고 싶은 상품의 상품명이나 가격대, 제조사 등을 입력하셔도 검색됩니다.
<form action="./wizsearch.php" name="SearchForm">
	<input type="hidden" name=query value='search'>
	<table class="table_main noside w100p list">
		<col width="120px" />
		<col width="*" />
		<tr>
			<th>검 색</th>
			<td><input name="Target" type="radio" value="all" checked>
				상품명(상품설명 포함)검색
				<input type="radio" name="Target" value="Name">
				상품명 검색
				<input type="radio" name="Target" value="Model">
				모델명 검색 </td>
		</tr>
		<tr>
			<th>키 워 드</th>
			<td><input name="keyword" type="text" class="formline" id="keyword" size="40"></td>
		</tr>
		<tr>
			<th>가  격 대</th>
			<td><select name="price1" class="form">
					<option value="">선택하세요
					<option value="1000">1,000원 이상
					<option value="10000">10,000원 이상
					<option value="30000">30,000원 이상
					<option value="50000">50,000원 이상
					<option value="100000">100,000원 이상
					<option value="200000">200,000원 이상
					<option value="300000">300,000원 이상
					<option value="500000">500,000원 이상
					<option value="1000000">1,000,000원 이상
					<option value="2000000">2,000,000원 이상
					<option value="3000000">3,000,000원 이상
					<option value="5000000">5,000,000원 이상
					<option value="10000000">10,000,000원 이상
				</select>
				-
				<select name="price2">
					<option value="" >선택하세요
					<option value="1000">1,000원 이하
					<option value="10000">10,000원 이하
					<option value="30000">30,000원 이하
					<option value="50000" >50,000원 이하
					<option value="100000">100,000원 이하
					<option value="200000" >200,000원 이하
					<option value="300000" >300,000원 이하
					<option value="500000" >500,000원 이하
					<option value="1000000">1,000,000원 이하
					<option value="2000000">2,000,000원 이하
					<option value="3000000">3,000,000원 이하
					<option value="5000000">5,000,000원 이하
					<option value="10000000">10,000,000원 이하
				</select>			</td>
		</tr>
		<tr>
			<th>제 조 사</th>
			<td><input name="Brand" type="text" class="formline" id="Brand" size="30"></td>
		</tr>
		<tr>
			<th>정렬 방식 </th>
			<td><input name="sort" type="radio" value="m2.Price" checked>
				가격순으로 정렬
				<input type="radio" name="sort" value="m2.Output">
				상품인기순으로 정렬
				<input type="radio" name="sort" value="m2.Point">
				포인트순으로 정렬 </td>
		</tr>
	</table>
	<div class="btn_box">
		<span class="button bull"><a>검색</a></span>
	</div>
</form>
<div class="space20"></div>
여러개의 검색어로 상세한 검색을 원하실때는 다중검색을 이용하세요.
<form action="./wizsearch.php" onsubmit='return naturalcheck();' name=natural>
	<input type="hidden" name="query" value="natural">
	<table class="table_main noside w100p list">
			<col width="120px" />
		<col width="*" />
		<tr>
			<th>다중검색어</th>
			<td><input name="Target" type="radio" value="m2.Description1" checked>
				상품설명 검색
				<input type="radio" name="Target" value="m2.Name">
				상품명 검색
				<input type="radio" name="Target" value="m2.Model">
				모델명 검색 <br />
				<input name="keyword" type="text" class="formline" id="keyword" size="30">
				<select name="andor" class="form" id="andor">
					<option>and</option>
					<option>or</option>
				</select></td>
		</tr>
	</table>
	<div class="btn_box">
		<span class="button bull"><a>검색</a></span>
	</div>
</form>

  </body>
</html>
