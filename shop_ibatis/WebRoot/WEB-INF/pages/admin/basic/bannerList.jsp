<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Banner List</title>
<script language="javascript" type="text/javascript">
<!--
	$(function(){
		$(".btn_click").click(function(i){
			$(".submenu").hide();
			$(".submenu").eq($(".btn_click").index(this)).show(); //메서드
		});
		
		$(".btn_delete").click(function(){
			var tid = $(this).attr("tid");
			location.href="${pageContext.request.contextPath}/admin/basic/bannerDelete.do?tid="+tid
		});
	});

	function deletefnc(){
			var f = document.forms.BrdList;
			var i = 0;
			var chked = 0;
			for(i = 0; i < f.length; i++ ) {
					if(f[i].type == 'checkbox') {
							if(f[i].checked) {
									chked++;
							}
					}
			}
			if( chked < 1 ) {
					alert('삭제하고자 하는 게시물을 하나 이상 선택해 주세요.');
					return false;
			}
			if (confirm('\n\n삭제하는 게시물은 복구가 불가능합니다!!! \n\n정말로 삭제하시겠습니까?\n\n')) return true;
			return false;
	}

	function gotoWrite(banner_pos, tid){
		if (tid == undefined) tid = "";
		location.href="${pageContext.request.contextPath}/admin/basic/bannerWrite.do?&banner_pos="+banner_pos+"&tid="+tid;
	}
//-->
</script>

</head>
<body>

<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>메인베너관리</legend>
			<div class="notice">[note]</div>
			<div class="comment"> order가 작은 순서가 상위에 위치합니다.</div>
			</fieldset>
			<div class="space20"></div>
			<table class="table_main">
				<tr>
					<td>베너위치</td>
				</tr>
<c:forEach var="i" items="${position}"> 
				<tr>
					<td class="btn_click b">${i.value} (그룹코드 : ${i.key}) </td>
				</tr>
				<tr class="submenu" style="display:none">
					<td><table class="table_main">
						<col width="50px" />
						<col width="*" />
						<col width="*" />
						<col width="80px" />
						<col width="80px" />
							<tr>
								<th>order</th>
								<th>url</th>
								<th>이미지</th>
								<th>수정</th>
								<th>삭제</th>
							</tr>
							<c:forEach var="j" items="${info}"> 
								<c:if test="${j.key == i.key}">
									<c:forEach var="k" items="${j.value}"> 
							<tr>
								<td>${k.ordernum}</td>
								<td>${k.url}</td>
								<td><img src="${pageContext.request.contextPath}/data/banner/${k.attached}"> </td>
								<td><span class="button bull"><a href="javascript:gotoWrite(${i.key}, ${k.tid})">수정</a></span></td>
								<td><span class="btn_delete button bull" tid="${k.tid}"><a>삭제</a></span></td>
							</tr>
									</c:forEach>
								</c:if>
							</c:forEach><!-- position -->

					</table><div class="btn_box"><span class="button bull"><a href="javascript:gotoWrite(${i.key})">등록</a></span></div></td>
				</tr>
</c:forEach><!-- position -->
			</table></td>
	</tr>
</table>


</body>
</html>