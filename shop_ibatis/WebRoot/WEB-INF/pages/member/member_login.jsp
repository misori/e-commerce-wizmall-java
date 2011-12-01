<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>로그인</title>
<script>
$(function(){
	$(".submit_btn").click(function(){
		$.post("${pageContext.request.contextPath}/auth/login", $("#sform").serialize(), function(data){
			//alert(data);
			eval("var obj="+data);
			if(obj.result.result == "0"){
				location.href = "${pageContext.request.contextPath}";
			}else{
				alert(obj.result.message);
			}

		});
		//$("#sform").submit();
	});
});
</script>
</head>
<body>
<c:if test="${failureReason == 'Bad credentials'}">

</c:if>
 <c:choose>
    <c:when test="${failureReason == 'Bad credentials'}">
         아이디가 일치하지 않습니다.
    </c:when>
    <c:when test="${failureReason == 'Invalid Password'}">
	패스워드가 일치하지 않습니다.
    </c:when>
    <c:otherwise>
	아이디와 패스워드를 입력해 주세요
    </c:otherwise>
</c:choose>
<form action="${pageContext.request.contextPath}/j_spring_security_check"  name="sform" id="sform" method="POST">
아이디 : <input type="text" name="j_username" value="" /><br />
패스워드 : <input type="password" name="j_password" value="" /><br />

<!-- <input type="button" value="전송" class="submit_btn" />-->
<span class="inputbutton"><input class="button" name="submit" type="submit" value="Submit"/>
</form>
</body>
</html>