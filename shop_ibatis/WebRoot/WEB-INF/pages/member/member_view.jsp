<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>멤버 상세보기</title>
</head>
<body>
회원가입 페이지 입니다.
<form:form action="${pageContext.request.contextPath}/member/memberReg_x"  name="sform" method="POST" modelAttribute="members">
고유키 : ${member.tid}<br />
아이디 : ${member.user_id}<br />
패스워드 : ${member.user_passwd}<br />
이름 : ${member.user_name}<br />


</form:form>
</body>
</html>