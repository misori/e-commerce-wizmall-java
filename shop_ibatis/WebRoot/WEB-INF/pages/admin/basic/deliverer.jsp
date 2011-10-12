<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Banner List</title>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	$("#btn_deliverer_save").click(function(){
		$.post("${pageContext.request.contextPath}/admin/basic/deliverer_x.do", { d_name:$("#d_name").val()
			, d_code:$("#d_code").val()
			, d_url:$("#d_url").val()
			, d_inquire_url:$("#d_inquire_url").val()
			, d_method:$("#d_method").val()
			},
			function(data){
				//alert(data);
				location.reload();
		});
	});



	$(".btn_deliverer_up").click(function(){
		var tid = $(this).attr("tid");
		var index = $(".btn_deliverer_up").index(this);
		var d_name		= $(".d_name").eq(index).val();
		var d_code		= $(".d_code").eq(index).val();
		var d_url		= $(".d_url").eq(index).val();
		var d_inquire_url	= $(".d_inquire_url").eq(index).val();
		var d_method	= $(".d_method").eq(index).val();

		$.post("${pageContext.request.contextPath}/admin/basic/deliverer_x.do", {tid:tid
			, d_name:d_name
			, d_code:d_code
			, d_url:d_url
			, d_inquire_url:d_inquire_url
			, d_method:d_method
			},
			function(data){
				location.reload();
		});
	});

	$(".btn_deliverer_del").click(function(){
		var tid = $(this).attr("tid");

		$.post("${pageContext.request.contextPath}/admin/basic/deliverer_del_x.do", {tid:tid},
			function(data){
				location.reload();
		});
	});
});


//-->
</script>

</head>
<body>

<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>택배사관리</legend>
			<div class="notice">[note]</div>
			<div class="comment"> 택배사 리스트(택배사고유키는 기존 주문과연동되므로 가급적  삭제를  피해 주시기 바랍니다.)</div>
			</fieldset>
			<div class="space20"></div>
<table class="table_main">
	<tr>
		<th>택배사명</th>
		<th> 부가코드</th>
		<th>택배사URL</th>
		<th>조회페이지</th>
		<th>전송방식</th>
		<th>&nbsp;</th>
	</tr>
<c:forEach items="${info}" var="current" varStatus="i">
		<tr>
			<td><input type="text" value="${current.d_name}" class="w100 d_name"></td>
			<td><input type="text" value="${current.d_code}" class="w100 d_code"></td>
			<td><input type="text" value="${current.d_url}" class="w100 d_url"></td>
			<td><input type="text" value="${current.d_inquire_url}" class="w100 d_inquire_url"></td>
			<td><select class="d_method">
					<option value="GET"<c:if test="${current.d_method == 'GET'}"> selected</c:if>>GET</option>
					<option value="POST"<c:if test="${current.d_method == 'POST'}"> selected</c:if>>POST</option>
				</select>
			</td>
			<td><span class="btn_deliverer_up button bull" tid="${current.tid}"><a>수정</a></span>
		  <span class="btn_deliverer_del button bull" tid="${current.tid}"><a>삭제</a></span>
			</td>
		</tr>
</c:forEach>

		<tr>
			<td><input name="d_name" type="text" id="d_name" class="w100">
			</td>
			<td><input name="d_code" type="text" id="d_code" class="w100"></td>
			<td><input name="d_url" type="text" id="d_url" class="w100"></td>
			<td><input name="d_inquire_url" type="text" id="d_inquire_url" class="w100"></td>
			<td><select name="d_method" id="d_method">
					<option value="GET">GET</option>
					<option value="POST">POST</option>
				</select></td>
			<td><span class="button bull" id="btn_deliverer_save"><a>등록</a></span></td>
		</tr>
	</form>
</table>
</td>
	</tr>
</table>


</body>
</html>