<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>개인정보보호정책</title>
<script src="${pageContext.request.contextPath}/js/mall.js"></script>
<script>
$(function(){


});
</script>

  </head>

  <body>
<div class="navy">Home > 개인정보보호정책
</div>






  </body>
</html>
