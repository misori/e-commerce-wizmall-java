<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Banner Write</title>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.plugins/jquery.validator-1.0.1.js"></script>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	$("#btn_save").click(function(){
		$("#s_form").submit();
	});
})
//-->
</script>

</head>
<body>

<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>베너관리</legend>
			<div class="notice">[note]</div>
			<div class="comment"> order가 작은 순서가 상위에 위치합니다. </div>
			</fieldset>
			<div class="space20"></div>
			<form action="${pageContext.request.contextPath}/admin/basic/banner_x.do" enctype="multipart/form-data" id="s_form" method="post">
				<input type="hidden" name="tid" value="${info.tid}">
				<input type="hidden" name="banner_pos" value="${banner_pos}">
				<table class="table_main">
					<tr>
						<th>우선순위</th>
						<td><input name="ordernum" type="text" class="w30" value="${info.ordernum}"></td>
					</tr>
					<tr>
						<th>URL </th>
						<td><input name="url" type="text" class="w100p" value="${info.url}">
						</td>
					</tr>
					<tr>
						<th>target </th>
						<td><select name="target" id="target">
								<option value="_self"<c:if test="${info.target == '_self'}"> selected</c:if>>_self</option>
								<option value="_blank"<c:if test="${info.target == '_blank'}"> selected</c:if>>_blank</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>첨부화일</th>
						<td><input type="file" name="file[0]"></td>
					</tr>
				</table>
				<div class="btn_box" id="btn_save"><span class="button bull"><a>등록</a></span></div>
			</form></td>
	</tr>
</table>
</body>
</html>