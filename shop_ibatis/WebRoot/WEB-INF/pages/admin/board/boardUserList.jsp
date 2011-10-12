<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.plugins/jquery.validator.js" ></script>
<script language="javascript" type="text/javascript">
<!--
$(function(){

});
function reSize() {
    try {
        var objBody = auto_iframe.document.body;
        var objFrame = document.all["auto_iframe"];
        ifrmHeight = objBody.scrollHeight + (objBody.offsetHeight - objBody.clientHeight);
        objFrame.style.height = ifrmHeight;
    }
        catch(e) {}
}

function init_iframe() {
    reSize();
    setTimeout('init_iframe()',1)
}

init_iframe();
//-->
</script>
</head>
<body>
<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>보드관리</legend>
			<div class="notice">[note]</div>
			<div class="comment"> 게시판을 생성/수정/삭제하실 수 있습니다.<br />
			</div>
			</fieldset>
			<div class="space20"></div>

		<iframe src="../../board/boardList.blank?bid=${params.bid}&gid=${params.gid}&opflag=${params.opflag}" height="800" frameborder="0" framespacing="0" name="auto_iframe" id="auto_iframe" scrolling="no" class="w100p"></iframe>
			<div class="space20"></div>
		</td>
	</tr>
</table>





</body>
</html>