<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List</title>
<script>
$(function(){
	$(".btn_reg").click(function(){
		location.href = "${pageContext.request.contextPath}/board/boardWrite.do?bid=${bid}&gid=${gid}";
	});

	$(".btn_search").click(function(){
		$("#search_box").submit();
	});

	$(".btn_list").click(function(){
		location.href = "${pageContext.request.contextPath}/board/boardList.do?bid=${bid}&gid=${gid}";
	});

	$(".btn_view").click(function(){
		var i	= $(".btn_view").index(this);
		var tid	= $(".btn_view").eq(i).attr("tid");
		$("#search_box").attr("action", "${pageContext.request.contextPath}/board/boardView.do?bid=${bid}&gid=${gid}&tid="+tid);
		$("#search_box").submit();
	});

	
});

function gotopage(page){
	$("#search_box").attr("action", $("#search_box").attr("action")+"&cp="+page);
	$("#search_box").submit();
}
</script>
</head>
<body>
겔러리
		<input type="button" value="등록" class="btn_reg"/> <input type="button" value="리스트" class="btn_list"/>
</body>
</html>