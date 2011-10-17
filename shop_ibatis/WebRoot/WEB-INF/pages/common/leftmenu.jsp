<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title></title>
    </head>
	<body>
<script>
$(function(){
	$("#submit_sub_login_btn").click(function(){
		if($("#sub_user_id").val() == ""){
			jAlert("아이디를 입력하세요");
		}else if($("#sub_user_passwd").val() == ""){
			jAlert("패스워드를 입력하세요");
		}else{
			$.post("${pageContext.request.contextPath}/j_spring_security_check", $("#sub_login_form").serialize(), function(data){
				//alert(data);
				//eval("var obj="+data);
				//if(obj.result.result == "0"){
					location.reload();
				//}else{
				//	alert(obj.result.message);
				//}

			});
		}
		//$("#sform").submit();
	});
});
</script>
	<div id="left_member_box">
<%

Authentication auth = SecurityContextHolder.getContext().getAuthentication();
if (auth != null && (!AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass()))) {
%>
		<p class="agn_l"> <span class="orange">
			<sec:authentication property="principal.username"/>
			</span> 님이 로그인 중입니다. </p>
		<p class="agn_l">포인트 :
			<?=number_format($cfg["member"]["mpoint"])?>
			<br />
			최근방문일 :
			<? if($cfg["member"]["mlogindate"]) echo date("Y.m.d", $cfg["member"]["mlogindate"]); else echo 0; ?>
		</p>
		<span class="button bull"><a href="${pageContext.request.contextPath}/logout">로그아웃</a></span> <span class="button bull"><a href="${pageContext.request.contextPath}/member/member_update.do">정보변경</a></span>
<%}else	{%>
		<form action='${pageContext.request.contextPath}/j_spring_security_check' method="post" id="sub_login_form">
			<input type="hidden" name="action" value="login_check" />
			<input type="hidden" name="log_from" value="" />
			<dl class="left_login">
				<dt> 아이디</dt>
				<dd>
					<input type="text" name="j_username" id="sub_user_id" tabindex="1" autocomplete="off" class="w100" />
				</dd>
				<dt> 패스워드</dt>
				<dd>
					<input type="password" name="j_password" id="sub_user_passwd" tabindex="2"  autocomplete="off" class="w100" />
				</dd>
			</dl>
			<span class="button bull" id="submit_sub_login_btn"><a>로그인</a></span>
			<div class="space10"></div>
			<span class="button bull"><a href="${pageContext.request.contextPath}/member/memberReg.do">회원가입</a></span>
			<span class="button bull"><a href="${pageContext.request.contextPath}/member/searchIdPass.do">id/pwd 찾기</a></span>
			<div class="space20"></div>
		</form>
<%}%>
</div>
	<div class="space20"></div>
	카테고리
	<div id="left_category_list">

<!-- 대분류 리스트 Start  -->

<ul>
<c:forEach items="${category}" var="current" varStatus="i">
<li><a href="${pageContext.request.contextPath}/product/productList.do?code=${current.cat_no}" class='left_menu_click' category="${current.cat_no}">${current.cat_name}</a></li>
</c:forEach>
</ul>
<!-- 대분류 리스트 End  -->

</div>

	<c:forEach var="j" items="${banner}"> 
		<c:if test="${j.key == 50}">
			<c:forEach var="k" items="${j.value}"> 
		<p><img src="${pageContext.request.contextPath}/data/banner/${k.attached}"></p>
			</c:forEach>
		</c:if>
	</c:forEach>


	</body>
</html>