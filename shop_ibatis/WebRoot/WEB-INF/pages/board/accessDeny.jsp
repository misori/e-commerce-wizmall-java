<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!-- 레이어팝업 //-->
<script language="javascript" type="text/javascript">
<!--
$(function(){
	$(".btn_goto_main").click(function(){
		location.href="${pageContext.request.contextPath}/";
	});

	$(".btn_goto_main").click(function(){
		location.href="${pageContext.request.contextPath}/";
	});
});
//-->
</script>
<form name="delete_form" id="delete_form" action="${pageContext.request.contextPath}/board/boardDelete_x.ajax" method="post">
<!-- 이곳에서 id 를 제어할 경우 메인에서와 충돌이 우려된다. -->
<c:forEach var="i" items="${params_hidden}">
  <input type="hidden" name="${i.key}"  value="${i.value}">
 </c:forEach>

	<div class="boardPop">
		<h3>접근에러</h3>
		<div class="popBody"> 잘못된 접근입니다. </div>
		<p class="Agn_c Pad_t15">
		<span class="button bull btn_goto_prev"><a>이전페이지로</a></span>
		<span class="button bull btn_goto_main"><a>메인으로</a></span></p>
	</div>
</form>