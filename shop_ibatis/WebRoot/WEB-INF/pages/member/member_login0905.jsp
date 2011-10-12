<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>로그인</title>
<script>
$(function(){
	$(".submit_btn").click(function(){
		$.post("${pageContext.request.contextPath}/member/memberLogin_x.do", $("#sform").serialize(), function(data){
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
회원로그인 페이지 입니다. (${sessionScope['session-userid']})
<form action="${pageContext.request.contextPath}/member/memberLogin_x.do"  name="sform" id="sform" method="POST">
아이디 : <input type="text" name="user_id" value="" /><br />
패스워드 : <input type="password" name="user_passwd" value="" /><br />

<!-- <input type="button" value="전송" class="submit_btn" />-->
<span class="inputbutton"><input class="button" name="submit" type="submit" value="Submit"/>
</form>
</body>
</html>