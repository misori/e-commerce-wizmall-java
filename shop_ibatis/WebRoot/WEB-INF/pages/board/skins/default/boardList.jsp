<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List</title>
<script>
$(function(){
	$(".btn_reg").click(function(){
		var url = "${pageContext.request.contextPath}/board/boardWrite.${params.svl_ext}?bid=${params.bid}&gid=${params.gid}";
		$("#search_box").attr("action", url);
		$("#search_box").submit();
	});

	$(".btn_search").click(function(){
		var url = "${pageContext.request.contextPath}/board/boardList.${params.svl_ext}?bid=${params.bid}&gid=${params.gid}";
		$("#search_box").attr("action", url);
		$("#search_box").submit();
	});

	$(".btn_list").click(function(){
		var url = "${pageContext.request.contextPath}/board/boardList.${params.svl_ext}?bid=${params.bid}&gid=${params.gid}";
		$("#search_box").attr("action", url);
		$("#search_box").submit();
	});

	$(".btn_view").click(function(){
		var i	= $(".btn_view").index(this);
		var tid	= $(".btn_view").eq(i).attr("tid");
		var url = "${pageContext.request.contextPath}/board/boardView.${params.svl_ext}?bid=${params.bid}&gid=${params.gid}&tid="+tid;
		$("#search_box").attr("action", url);
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
<form action="" method="post" id="search_box">
<input type="hidden" name="cp" id="cp" value="${pageNav.nowPage}" />
<input type="hidden" name="opflag" value="${params.opflag}" />
<select name="s_title">
	<option value=""<c:if test = "${params.s_title == null || params.s_title == ''}" > selected="selected"</c:if>>전체</option>
	<option value="subject"<c:if test = "${params.s_title == 'subject'}" > selected="selected"</c:if>>제 목</option>
	<option value="user_name"<c:if test = "${params.s_title == 'user_name'}" > selected="selected"</c:if>>글쓴이</option>
	<option value="contents"<c:if test = "${params.s_title == 'contents'}" > selected="selected"</c:if>>내 용</option>
</select>

<input type="text" name="s_keyword" value="${params.s_keyword}">
<input type="button"  class="btn_search" value="검색" />
</form>
검색된 총게시물 수 : ${pageNav.rowTotal} <br/>


	<table id="listTable" cellpadding="0" cellspacing="0" class="comTbl boardTbl_list">
		<col width="50px" title="번호" />
		<col width="200px" title="제목" />
		<col width="70px" title="작성자" />
		<col width="50px" title="view" />
		<col width="70px" title="등록일" />
		<thead>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>view</th>
			<th>등록일</th>
		</thead>
		<tbody>

			<c:forEach items="${notice}" var="current" varStatus="i">
			<tr>
				<td>[공지]</td>
				<td class="agn_l btn_view" tid="${current.tid}"><a>${fn:substring(current.subject, 0, 17)}</a></td>
				<td>${current.user_name}</td>
				<td>${current.count}</td>
				<td><fmt:formatDate value="${current.w_date}" pattern="yyyy.MM.dd" /></td>
			</tr>
			</c:forEach>

			<c:forEach items="${info}" var="current" varStatus="i">
			<tr>
				<td>${pageNav.startNum - i.index}</td>
				<td class="agn_l btn_view" tid="${current.tid}"><a>${fn:substring(current.subject, 0, 17)}</a></td>
				<td>${current.user_name}</td>
				<td>${current.count}</td>
				<td><fmt:formatDate value="${current.w_date}" pattern="yyyy.MM.dd" /></td>
			</tr>
			</c:forEach>
			<c:if test="${pageNav['rowTotal'] == '0'}">
			<tr>
				<td colspan="5">검색된 내용이 없습니다.</td>
			</tr>
			</c:if>
		</tbody>
	</table>

    <div class="t_default agn_c">
        <c:if test="${pageNav.isPrevPage=='true'}">
            <a href="javascript:gotopage(${pageNav.startPage - 1})">prev</a>
        </c:if>
        <c:forEach var="page" begin="${pageNav.startPage}" end="${pageNav.endPage}">
            <a href="javascript:gotopage(${page})">[${page}] </a>
        </c:forEach>
        <c:if test="${pageNav.isNextPage == 'true'}">
            <a href="javascript:gotopage(${pageNav.endPage + 1})">next</a>
        </c:if>
    </div>

		<input type="button" value="등록" class="btn_reg"/> <input type="button" value="리스트" class="btn_list"/>
</body>
</html>