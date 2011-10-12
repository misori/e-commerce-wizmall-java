<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>leftmenu</title>


  </head>

  <body>
<div id="head">
	<div id="logo">
<c:forEach var="j" items="${banner}"> 
	<c:if test="${j.key == 10}">
		<c:forEach var="k" items="${j.value}"> 
			<img src="${pageContext.request.contextPath}/data/banner/${k.attached}">
		</c:forEach>
	</c:if>
</c:forEach>
	</div>
	<div id="top_menu">
		<div id="topmenutext">
			<a href="${pageContext.request.contextPath}/main.do">홈</a>


<%

Authentication auth = SecurityContextHolder.getContext().getAuthentication();
if (auth != null && (!AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass()))) {
%>
 					<a href="${pageContext.request.contextPath}/member/member_page.do">마이페이지</a>
					<a href="${pageContext.request.contextPath}/member/member_update.do">정보변경</a>
					<a href="${pageContext.request.contextPath}/auth/logout">로그아웃</a>
<%}else	{%>
					<a href="${pageContext.request.contextPath}/member/memberLogin.do">로그인</a>
					<a href="${pageContext.request.contextPath}/member/memberReg.do">회원가입</a>
<%}%>





			<a href="${pageContext.request.contextPath}/board/boardList.do?bid=board01&gid=root">고객게시판</a>
			<a href="${pageContext.request.contextPath}/cart/cartView.do">장바구니</a></div>
		<div id="topmenusearch">
			<script language="JavaScript">
<!--
function SearchCheckForm(f){
	if(f.keyword.value == ''){
		alert('검색어를 입력해주세요');
		f.keyword.focus();
		return false;
	}
}
//-->
</script>
			<form action="wizsearch.php" name="SearchCheck" onsubmit='return SearchCheckForm(this);'>
				<input type="hidden" name="query" value="search">
				<input type="hidden" name="Target" value="all">

				<input type="text" name="keyword" class="input" />
				<span class="button bull"><a>검색</a></span>
				<span class="button bull"><a href="${pageContext.request.contextPath}/product/searchForm.do">상세검색</a></span>
			</form>
		</div>
	</div>
</div>
</body>
</html>