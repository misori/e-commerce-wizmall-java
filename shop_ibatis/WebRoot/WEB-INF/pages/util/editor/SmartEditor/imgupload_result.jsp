<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>이미지삽입</title>
<script language="javascript">
parent.parent.insertIMG('${id}','${pageContext.request.contextPath}/data/editor/${filename}');
parent.parent.oEditors.getById["${id}"].exec("SE_TOGGLE_IMAGEUPLOAD_LAYER");
</script>

</head>
<body>
</body>
</html>
