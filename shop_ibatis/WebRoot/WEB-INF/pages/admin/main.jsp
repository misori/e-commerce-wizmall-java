<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List</title>
<script>
$(function(){
	$(".btn_reg").click(function(){
		location.href = "${pageContext.request.contextPath}/board/boardWrite";
	});

	$(".btn_search").click(function(){
		$("#search_box").submit();
	});

	$(".btn_list").click(function(){
		location.href = "${pageContext.request.contextPath}/board/boardList";
	});
});

function gotopage(page){
	$("#search_box").attr("action", $("#search_box").attr("action")+"?cp="+page);
	$("#search_box").submit();
}
</script>
</head>
<body>

이곳은 Admin page입니다.
</body>
</html>