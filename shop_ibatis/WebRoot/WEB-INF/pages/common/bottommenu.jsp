<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Bottom Menu</title>
</head>

<body>
<div id="foot">
  <div id="foot_menu"> <a href="${pageContext.request.contextPath}/html/company.do">회사소개</a> | <a href="${pageContext.request.contextPath}/html/privacy.do">개인정보보호정책</a> | <a href="${pageContext.request.contextPath}/html/agreement.do">이용약관</a> </div>
  <div id="foot_main">
    <div id="foot_logo"> <c:forEach var="j" items="${banner}"> 
	<c:if test="${j.key == 20}">
		<c:forEach var="k" items="${j.value}"> 
			<img src="${pageContext.request.contextPath}/data/banner/${k.attached}">
		</c:forEach>
	</c:if>
</c:forEach> </div>
    <div id="foot_desc">상호 : ${basicinfo["company_name"]} | 대표 :${basicinfo["president"]}  | 사업자등록번호 : ${basicinfo["company_num"]} | 통신판매업신고 : ${basicinfo["company_licence_num"]}<br />
      주소 : ${basicinfo["company_address"]} <br />
      고객상담전화 : ${basicinfo["customer_service_tel"]}| 팩스 : ${basicinfo["customer_service_fax"]}| E-mail : ${basicinfo["admin_email"]}<br />
      Copyright ⓒ 2011 oneul.com All Rights Reserved </div>
  </div>
</div>
</body>
</html>