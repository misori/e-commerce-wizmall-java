<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.util.ParseURL" %>

<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.application-resources"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
    <head>
        <title><decorator:title default="My ShoppingMall Project" /></title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jquery-ui-1.7.2/js/jquery-ui-1.7.2.custom.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/admin.js"/>"></script>

		<link rel="stylesheet" href="<c:url value="/css/base.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/css/admin.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/js/jquery.plugins/jquery-ui-1.7.2/css/ui-lightness/jquery-ui-1.7.2.custom.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.css"/>" type="text/css" />

		<script language="javascript" type="text/javascript">
        <!--
		contextPath = "${pageContext.request.contextPath}";//자바스크립트에 사용한 contextPath를 전역으로 설정
        $(function(){
            $(".altern th:odd").addClass("bg3"); //basic이라는 클래스네임을 가진 요소의 tr 요소 중 홀수번째에 bg1클래스 부여
            $(".altern th:even").addClass("bg4");
            $(".list tr:odd").addClass("bg1");
            $(".list tr:even").addClass("bg2");

        });
        //-->
        </script>
		<decorator:head />
    </head>
	<body>

<!--// 각종 팝업용 Start //-->
<div id="dynamicPop"></div>
<!--// 각종 팝업용 End //-->
<div id="head">
	<div id="logo">
		<a href="${pageContext.request.contextPath}">오늘</a>
		<div id="logo_word"></div>
	</div>

	<!--onMouseOver="this.style.background='#70AF41'" onMouseOut="this.style.background='#5A9D28'"-->
	<div id="top">
    	<a href="${pageContext.request.contextPath}/admin/basic/basicInfo.do" class="topnavigation">기본환경설정</a>
        <a href="${pageContext.request.contextPath}/admin/member/memberList.do" class="topnavigation">회원리스트</a>
        <a href="${pageContext.request.contextPath}/admin/product/productList.do" class="topnavigation">상품관리</a>
		<a href="${pageContext.request.contextPath}/admin/order/orderList.do" class="topnavigation">주문배송관리</a>
		<a href="${pageContext.request.contextPath}/admin/board/boardManageList.do" class="topnavigation">보드관리</a>
        <a href="${pageContext.request.contextPath}/admin/category.do" class="topnavigation">카테고리관리</a>
	</div>
</div>

<div class="clear"></div>


<!-- body Start -->
<div id="body_wrapper">
	<!-- lefet menu -->
	<div id="sub">
        <page:applyDecorator page="/admin/leftMenu.do" name="blankpanel"/>
	</div>
	<!-- main contents -->
	<div id="mainbody">
		<div  id="mainbody_top"><img src="${pageContext.request.contextPath}/images/admin/admintop_img01.gif" width="762" height="75" /></div>
		<decorator:body />
	</div>
</div>

<!-- bottom -->
<div class="space30"></div>
<div id="foot"><jsp:include page="/WEB-INF/pages/admin/admin_bottom_menu.jsp" /></div>

	</body>
</html>



