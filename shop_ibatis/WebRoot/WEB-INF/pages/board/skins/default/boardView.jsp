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
				gotoList();
			});

			$(".btn_modify").click(function(){
				gotoModify();
			});

			$(".btn_delete").click(function(){
				$.post("${pageContext.request.contextPath}/board/boardDeleteForm.ajax", $("#sform_view").serialize(), function (data){
					
					if(	$("body #dynamicPop").length == 0){
						$("body").append('<div id="dynamicPop" class="none"></div>');
						
					}
					$('#dynamicPop').html(data);
					$("#dynamicPop").locationcenter().show();
				});
			});

			$(".btn_reply").click(function(){
				var url = "${pageContext.request.contextPath}/board/boardReply.${params.svl_ext}?bid=${param.bid}&gid=${param.gid}&cp=${param.cp}&tid="+${board.tid};
				$("#sform_view").attr("action",url);
				$("#sform_view").submit();
			});

			$(".btn_dowonload").click(function(){
				var i = $(".btn_dowonload").index(this);
				var filename	= $(".btn_dowonload").eq(i).attr("attached");
				var url	= "${pageContext.request.contextPath}/board/fileDownLoad.ajax?bid=${param.bid}&gid=${param.gid}&tid=${board.tid}&filename="+filename;
				location.href=url;
			});

			loadRepleForm();
		});

		function gotoList(){//삭제후에도 이 플로우를 타게 됨
			var url = "${pageContext.request.contextPath}/board/boardList.${params.svl_ext}?bid=${param.bid}&gid=${param.gid}&cp=${param.cp}";
			$("#sform_view").attr("action", url);
			$("#sform_view").submit();
		}
		


		function loadRepleForm(){
			$("#RepleFormHtml").load("${pageContext.request.contextPath}/board/reple.ajax?tid=${param.tid}&bid=${param.bid}&gid=${param.gid}", function(){

			});
		}

		function gotoModify(){
			$.post("${pageContext.request.contextPath}/board/hasAuthorityForModify.ajax", $("#sform_view").serialize(), function (data){
				//true일경우 session 생성됨
				eval("var obj=" + data);
				if(obj.result == "true"){//수정권한이 있으면
					$("#sform_view").attr("action", "${pageContext.request.contextPath}/board/boardEdit.${params.svl_ext}?bid=${param.bid}&gid=${param.gid}&cp=${param.cp}&tid="+${board.tid});
					$("#sform_view").submit();
				}else{
					$.post("${pageContext.request.contextPath}/board/boardEditAccessAuthorityForm.ajax", $("#sform_view").serialize(), function (data){
						
						if(	$("body #dynamicPop").length == 0){
							$("body").append('<div id="dynamicPop" class="none"></div>');
						}
		
						$('#dynamicPop').html(data);
						$("#dynamicPop").locationcenter().show();
					});
				}
			});
		}

		function myscript(str){
			alert(str);
		}
	</script>
	</head>

	<body>
<form id="sform_view" method="post" action="">
<input type="hidden" name="bid" id="bid" value="${param.bid}" />
<input type="hidden" name="gid" id="gid" value="${param.gid}" />
<input type="hidden" name="cp" id="cp" value="${param.cp}" />
<input type="hidden" name="s_title" value="${param.s_title}" />
<input type="hidden" name="s_keyword" value="${s_keyword.cp}" />
<input type="hidden" name="category" value="${category.cp}" />
<input type="hidden" name="tid" id="tid" value="${board.tid}" />
<input type="hidden" name="opflag" value="${params.opflag}" />
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
<!-- Reple Start -->
<div id="RepleFormHtml"></div>
<!-- Reple End -->
<span class="button bull btn_modify" tid="${board.tid}"><a>수정</a></span>
<span class="button bull btn_delete" tid="${board.tid}"><a>삭제</a></span>
<span class="button bull btn_list"><a>리스트</a></span>
<span class="button bull btn_reply" tid="${board.tid}"><a>답변</a></span>
</body>
</html>