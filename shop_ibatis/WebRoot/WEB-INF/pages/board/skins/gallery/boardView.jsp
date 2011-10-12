<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("newLineChar", "\n");
%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<base href="<%=basePath%>">
	<title>${board.subject}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script>
		$(function(){
			$(".btn_list").click(function(){
				$("#sform_view").attr("action", "${pageContext.request.contextPath}/board/boardList.do?bid=${param.bid}&gid=${param.gid}&cp=${param.cp}");
				$("#sform_view").submit();
			});

			$(".btn_modify").click(function(){
				var i = $(".btn_modify").index(this);
				var tid	= $(".btn_modify").eq(i).attr("tid");
				$("#sform_view").attr("action", "${pageContext.request.contextPath}/board/boardEdit.do?bid=${param.bid}&gid=${param.gid}&cp=${param.cp}&tid="+tid);
				$("#sform_view").submit();
			});

			$(".btn_delete").click(function(){
				var i = $(".btn_modify").index(this);
				var tid	= $(".btn_modify").eq(i).attr("tid");
				$("#sform_view").attr("action", "${pageContext.request.contextPath}/board/boardDelete_x.do?bid=${param.bid}&gid=${param.gid}&cp=${param.cp}&tid="+tid);
				$("#sform_view").submit();
			});

			$(".btn_reply").click(function(){
				var i = $(".btn_modify").index(this);
				var tid	= $(".btn_modify").eq(i).attr("tid");
				$("#sform_view").attr("action", "${pageContext.request.contextPath}/board/boardReply.do?bid=${param.bid}&gid=${param.gid}&cp=${param.cp}&tid="+tid);
				$("#sform_view").submit();
			});

			$(".btn_dowonload").click(function(){
				var i = $(".btn_dowonload").index(this);
				var filename	= $(".btn_dowonload").eq(i).attr("attached");
				location.href="${pageContext.request.contextPath}/board/fileDownLoad.do?bid=${param.bid}&gid=${param.gid}&tid=${board.tid}&filename="+filename;
			});
		});
	</script>
	</head>

	<body>
<form id="sform_view" method="post" action="">
<form>
<table class="table_main comTbl boardTbl_view">
      <col width="100px"/>
      <col width="400px"/>
      <tr>
    <th>제목</th>
    <td>${board.subject}</td>
  </tr>
      <tr>
    <th>이름</th>
    <td>${board.user_name}(count:${board.count} 작성일: <fmt:formatDate value="${board.w_date}" pattern="yyyy.MM.dd" />)</td>
  </tr>
    <th>첨부화일</th>
        <td>다운로드:
			<c:forEach var="current" items="${attachedInfo}">
				<a class="btn_dowonload" attached="${current.filename}"> ${current.filename}</a> 
			</c:forEach>
    </td>
  </tr>
  <tr>
    <td colspan="2">내용</td>
  </tr>
      <tr>
    <td colspan="2">
	
	<c:forEach var="current" items="${displayImg}">
				<img src="${pageContext.request.contextPath}/${current}" /><br />
			</c:forEach>

          ${fn:replace(board.contents, newLineChar, "<br />")}</td>
  </tr>
    </table>
<span class="button bull btn_modify" tid="${board.tid}"><a>수정</a></span> 
<span class="button bull btn_delete" tid="${board.tid}"><a>삭제</a></span> 
<span class="button bull btn_list"><a>리스트</a></span> 
<span class="button bull btn_reply" tid="${board.tid}"><a>답변</a></span>
</body>
</html>