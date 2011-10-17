<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<script>
	$(function(){
		$(".btn_delete_evaluation").click(function(){
			var tid = $(this).attr("tid");
			$.post("${pageContext.request.contextPath}/product/deleteEvaluation.ajax", {tid:tid}, function(data){
				getList_Evaluation();
			});
		});
	});
</script>
<table class="table_main w100p">
<c:set var="listcnt" value="0"/>
<c:forEach items="${list}" var="current" varStatus="i">
<c:set var="listcnt" value="${i.count}"/>
	<tr>
		<th>글쓴이</th>
		<td>${current.user_id}
			<c:if test="${current.user_id == params.user_id}">
			 <span class="button bull btn_delete_evaluation" tid="${current.tid}"><a>x</a></span>
			</c:if></td>
		<th>고객선호도 </th>
		<td>${current.grade}</td>
	</tr>
	<tr>
		<td colspan="4" style="word-break:break-all;">${current.contents}
		</td>
	</tr>
</c:forEach>
<c:if test="${listcnt == '0'}">
	<tr>
		<td colspan="4" style="word-break:break-all;">등록된 평가가 없습니다.</td>
	</tr>
</c:if>
</table>

≡ 상품평은 개인의 체험을 바탕으로 한 주관적인 의견으로 사실과 다르거나,보는 사람에 따라 차이가 있을 수 있습니다.
<!-- 상품 평가 끝 -->