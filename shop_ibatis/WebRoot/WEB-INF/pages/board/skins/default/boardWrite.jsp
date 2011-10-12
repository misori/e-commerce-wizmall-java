<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Board Write Page</title>
<script  type="text/javascript" src="<c:url value="/js/Smart/js/HuskyEZCreator.js"/>" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="<c:url value="/js/Smart/css/default.css"/>" />
<script>
$(function(){

	$("#btn_submit").click(function(){
		//alert('전송1');
		oEditors.getById["board_contents"].exec("UPDATE_IR_FIELD", []);
		$("#sform").submit();
		//alert('전송2');
	})

	$("#btn_list").click(function(){
		history.go(-1);
	});

});
</script>
</head>

<body>
<form:form action="${pageContext.request.contextPath}/board/boardReg_x.do" id="sform"  name="sform" method="POST" modelAttribute="board" enctype="multipart/form-data">
  <input type="hidden" name="tid" value="${board.tid}" />
  <input type="hidden" name="smode" value="${params.smode}" />
  <input type="hidden" name="bid" value="${params.bid}" />
  <input type="hidden" name="gid" value="${params.gid}" />
  <input type="hidden" name="opflag" value="${params.opflag}" />

  <table class="table_main comTbl boardTbl_write">
    <col width="100px"/>
    <col width="400px"/>
    <tr>
      <th>이름</th>
      <td><input type="text" name="user_name" value="${board.user_name}" /></td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td><input type="password" name="user_passwd" value="${board.user_passwd}" /></td>
    </tr>
    <tr>
      <th>제목</th>
      <td><input type="text" name="subject" value="${board.subject}" />
<input type="hidden" name="TxtType" value="1" /> 
<input type="checkbox" name="Secret" value="1"<c:if test="${params_op.Secret == '1'}"> checked</c:if>> 비밀글
<input type="checkbox" name="Notice" value="1"<c:if test="${params_op.Notice == '1'}"> checked</c:if>> 공지글
	  </td>
    </tr>
<c:if test="${config.CategoryEnable == 'Y'}">
    <tr>
      <th>카테고리</th>
      <td><select name="category">
          <c:forEach var="i" items="${category}" >
		  <option value="${i.ordernum}" <c:if test="${i.ordernum == board.category}"> selected="selected"</c:if>>
			${i.catname}
           </option>
          </c:forEach>
        </select></td>
    </tr>
</c:if>
    <tr>
      <th>내용 </th>
      <td><textarea name="contents" id="board_contents" rows="15" style="width:100%;">${board.contents}</textarea></td>
    </tr>
<c:forEach var="i" begin="1" end="${config.ATTACHEDCOUNT}"> 
    <tr>
      <th>첨부화일</th>
      <td> <input type="file" name="fileattach[${i-1}]"/></td>
    </tr>
</c:forEach>
  </table>
  <script>

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "board_contents",
	sSkinURI: "${pageContext.request.contextPath}/js/Smart/SEditorSkin.html",
	fCreator: "createSEditorInIFrame"
});

function insertIMG(irid,fileame)
{
    var sHTML = "<img src='" + fileame + "'>";
    oEditors.getById[irid].exec("PASTE_HTML", [sHTML]);
}
</script>
  <div class="t_default agn_c">
    <input type="button" value="전송" id="btn_submit"/>
    <input type="button" value="리스트" id="btn_list" />
  </div>
</form:form>
</body>
</html>