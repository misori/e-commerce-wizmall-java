<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>
<decorator:title default="Shop" />
</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script language="javascript" type="text/javascript">
<!--
contextPath = "${pageContext.request.contextPath}";//
//-->
</script> 
<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script> 
<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jquery-ui-1.7.2/js/jquery-ui-1.7.2.custom.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/base.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/layout.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/homepage.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/board.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.css"/>" type="text/css" />
<decorator:head />
</head>
<body>
<div>
  <page:applyDecorator page="/topMenu.do" name="blankpanel"/>
  <!-- left menu start -->
  <div id="sub">
    <page:applyDecorator page="/leftMenu.do" name="blankpanel"/>
  </div>
  <!-- left menu end --> 
  <!-- main content start -->
  <div id="body">
    <decorator:body />
  </div>
  <!-- main content End-->
  
  <page:applyDecorator page="/bottomMenu.do" name="blankpanel"/>
</div>
</body>
</html>
