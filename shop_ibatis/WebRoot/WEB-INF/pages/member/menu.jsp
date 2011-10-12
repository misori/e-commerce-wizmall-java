<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.member-resources"/>
<a class="button" href="${pageContext.request.contextPath}/indexMember"><span><img src="images/icons/view.gif" /><fmt:message key="navigation.view"/> <fmt:message key="member.title"/>s</span></a>
