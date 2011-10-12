<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List</title>
<script  language=JavaScript>
<!--
$(function(){
	//검색날짜
	$( ".s_date" ).datepicker( {dateFormat:"yy-mm-dd"} );
	//엑셀출력
	$("#btn_excel").click(function(){
			location.href="${pageContext.request.contextPath}/admin/member/memberExcelWrite.ajax";
	});

	$("#SelListNo").change(function(){
		$("#ListNo").val($(this).val());
		$("#submitForm").submit();
	});

	//검색 버튼 클릭시
	$(".btn_search").click(function(){
		$("#search_box").submit();
	});
});

function gotopage(cp){
	$("#cp").val(cp);
	$("#submitForm").submit();
}

function really(){
	var f = document.forms.memberlist;
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
			alert('삭제하고자 하는 회원을 체크해주세요.');
			return false;
	}
	if (confirm('\n정말로 삭제하시겠습니까? 삭제는 복구가 불가능합니다.   \n')) return true;
	return false;
}

function changegrant(sta, id){
	var str = "";
	if(sta == "03"){//승인상태
		str = "보류상태로 변경하시겠습니까?";
	}else if(sta == "00"){//탈퇴상태
		str = "보류상태로 변경하시겠습니까?";
	}else{//보류상태 : 04
		str = "승인상태로 변경하시겠습니까?";
	}
	if(confirm(str)){
		location.href = "<?=$PHP_SELF?>?menushow=<?=$menushow?>&theme=<?=$theme?>&grade=<?=$grade?>&grantsta="+sta+"&action=update&id="+id+"&cp=<?=$cp?>";
	}else{
		return false;
	}
}

function gotoHistory(id){
	var url = "${pageContext.request.contextPath}/member/order_list.popup?&mid="+id;
	wizwindow(url,'buyhistory','width=670,height=700,statusbar=no,scrollbars=yes,toolbar=no');
}
//-->
</script>
</head>
<body>
<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>
			회원정보보기
			</legend>
			<div class="notice">[note]</div>
			<div class="comment">
				가입회원 리스트 입니다.</div>
			</fieldset>
			<div class="space20"></div>

<form id="submitForm">
<input type="hidden" name="cp" id="cp" value="${pageNav.nowPage}" />
<input type="hidden" name="ListNo" id="ListNo" value="15" />
</form>
			<table>

					<tr>
						<td>검색된 회원수:
							<fmt:formatNumber value="${pageNav.rowTotal}" /></td>
						<td><select name="SelListNo" id="SelListNo">
								<option>리스트갯수</option>
								<option value="15">15</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="50">50</option>
								<option value="100">100</option>
							</select>
						</td>
						<td><span class="button bull" id="btn_excel"><a>엑셀출력</a></span></td>
					</tr>
			</table>
			<form action='' name='memberlist'>

				<table class="table_main list">
					<col width="50" /><!-- 번호 -->
					<col width="*" /><!-- 회원이름 -->
					<col width="70" /><!-- 아이디 -->
					<col width="70" /><!-- 지역 -->
					<col width="70" /><!-- 등급 -->
					<col width="70" /><!-- 포인트 -->
					<col width="70" /><!-- 구매내역 -->
					<col width="70" /><!-- 가입일 -->
					<col width="70" /><!-- 로긴수 -->
					<col width="50" /><!-- 승인 -->
					<col width="70" /><!-- 선택삭제 -->
					<thead>
						<tr class="altern">
							<th>번호</th>
							<th>회원이름</th>
							<th>아이디</th>
							<th>지역</th>
							<th>등급</th>
							<th>포인트</th>
							<th>구매내역</th>
							<th>가입일</th>
							<th>로긴수</th>
							<th>승인</th>
							<th> <span class="button bull"><a>선택삭제</a></span></th>
						</tr>
					</thead>
					<tbody>
<c:forEach items="${info}" var="current" varStatus="i">
						<tr>
							<td>${pageNav.startNum-i.index}</td>
							<td><a href="javascript:getuserInfo('${current.user_id}');">${current.user_name}</a></td>
							<td><a href="javascript:getuserInfo('${current.user_id}');">${current.user_id}</a></td>
							<td>&nbsp;</td>
							<td><a href="javascript:getuserInfo('${current.user_id}');">${current.user_grade}</a></td>
							<td>&nbsp;<a href="javascript:getuserInfo('${current.user_id}');">${current.user_point}</a></td>
							<td class="agn_c"><span class="button bull"><a href="javascript:gotoHistory('${current.user_id}')">보기</a></span></td>
							<td><fmt:formatDate value="${current.user_regdate}" pattern="yyyy.MM.dd" /></td>
							<td><fmt:formatNumber value="${current.user_login_num}" /></td>
							<td><a href="javascript:changegrant('${current.user_grantsta}', '${current.user_id}');">${current.user_grantsta}</a></td>
							<td class="agn_c"><input type="checkbox" name='DeleteMember[${current.tid}]' value='${current.tid}'>
							</td>
						</tr>
</c:forEach>
<c:if test="${pageNav.rowTotal == '0'}">
						<tr>
							<td colspan="11">검색된 회원이 없습니다.</td>
						</tr>
</c:if>
				</table>
			</form>
			<form action='' method="post" name="search_box" id="search_box">
				<table class="table_main">
					<tr>
						<td colspan="4"><div>
								
								&nbsp;
								<input type="text" class="w100 s_date" name="s_date" value="${params.s_date}" />
							~
							<input type="text" class="w100 s_date" name="e_date" value="${params.e_date}" />
								<input type="checkbox" name="DataEnable" value="1">
								Enable   &nbsp;&nbsp;&nbsp;
							
								<input type="text" name="s_keyword" value="${params.s_keyword}">
								<span class="button bull btn_search"><a>검색</a></span>
							</div></td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
</table>
			<div class="paging_box">

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
</body>
</html>