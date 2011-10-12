<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<head>
<script language = javascript>
<!--
$(function(){
	$(".btn_reple_write").click(function(){
		$.post($("#replyForm").attr("action"), $("#replyForm").serialize(), function(data){
			loadRepleForm();
		});
	})

	$(".btn_reple_delete").click(function(){
		var i	= $(".btn_reple_delete").index(this);
		var tid	= $(".btn_reple_delete").eq(i).attr("tid");
		//alert(tid);
		//alert(i);
		$("#reple_tid").val(tid);
		//alert($("#reple_tid").val());
		$.post("${pageContext.request.contextPath}/board/boardReplyDelete_x.ajax", $("#replyForm").serialize(), function(data){
			loadRepleForm();
		});
	});
});

function DELETE_REPLE(UID,cp,BID,GID,BUID,adminmode){
	window.open("/delete.php?UID="+UID+"&cp="+cp+"&BID="+BID+"&GID="+GID+"&BUID="+BUID+"&adminmode="+adminmode,"","scrollbars=no, toolbar=no, width=320, height=220, top=220, left=350")
}

function showrepleList(flag){
	repleList1.style.display = "none";
	repleList2.style.display = "none";
	switch(flag){
		case 1:repleList1.style.display = "block";break;
		case 2:repleList2.style.display = "block";break;
		break;
	}
}
//-->
</script>
</head>
<table width="100%" border="0" cellspacing="0" cellpadding="3" class="board1" id="repleList1">
  <col width="100px" title="등록자" />
  <col width="*" title="내용" />
  <col width="100px" title="등록일" />
  <c:forEach items="${replyList}" var="current" varStatus="i">
    <tr>
      <td valign="top"> ${current.user_name} </td>
      <td valign="top">${current.contents}</td>
      <td><fmt:formatDate value="${current.w_date}" pattern="yyyy.MM.dd" />
        <span class="button bull btn_reple_delete" tid="${current.tid}"><a>삭제</a></span></td>
    </tr>
  </c:forEach>
  <c:if test="${tc == '0'}">
    <tr>
      <td colspan="5">검색된 내용이 없습니다.</td>
    </tr>
  </c:if>
</table>
<!--  action="${pageContext.request.contextPath}/board/boardReplyReg_x.do" -->
<form name="replyForm" id="replyForm" method="post" action="${pageContext.request.contextPath}/board/boardReplyReg_x.ajax">
  <input type="hidden" name="tid" value="${params.tid}" />
  <input type="hidden" name="bid" value="${params.bid}" />
  <input type="hidden" name="gid" value="${params.gid}" />
  <input type="hidden" name="reple_tid" id="reple_tid" value="" />

  <input type="hidden" name="REPLE_MODE" value="WRITE" />
  <input type="hidden" name="mode" value="" />
  <input type="hidden" name="adminmode" value="" />
  <input type="hidden" name="cp" value="" />
  <input type="hidden" name="BOARD_NO" value="" />
  <input type="hidden" name="ID" value="" />
  <input type="hidden" name="RUID" value="" />
  <input type="hidden" name="ismember" value="false" />
  <!-- 자바스크립트 제어를 위해 회원전용:true, 일반 : false 로서 플래그 값변경-->
  <input type="hidden" name="spamfree" value="" />
  <table width="100%">
    <col width="100px" title="등록자" />
    <col width="*" title="내용" />
    <col width="100px" title="비밀번호" />
    <tr>
      <td> 이름
        <input type="text" name="user_name" value="" class="w50" msg="이름을 입력하세요" /></td>
      <td><textarea name="contents" wrap="VIRTUAL" rows="3" class="w50" msg="내용을 입력하세요" ></textarea></td>
      <td> 비밀번호
        <input type="password" name="user_passwd" value="" class="w50" msg="비밀번호를 입력하세요" />
        <br />
        <span class="button bull btn_reple_write"><a>등록</a></span></td>
    </tr>
  </table>
</form>