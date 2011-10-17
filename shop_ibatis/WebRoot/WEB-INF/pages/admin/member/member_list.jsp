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

	$("#selListNo").change(function(){
		$("#ListNo").val($(this).val());
		$("#submitForm").submit();
	});

	//검색 버튼 클릭시
	$(".btn_search").click(function(){
		$("#search_box").submit();
	});

	//선택삭제
	$(".btn_sel_delete").click(function(){
		jConfirm('삭제하시겠습니까?', 'Confirmation Dialog', function(r) {
			if(r == true){
				$.post("${pageContext.request.contextPath}/admin/member/deleteMemberMulti.ajax", $("#memberList").serialize(), function(data){
					location.reload();
				});
			}else{

			}
		});
	});

	//회원등급변경
	$(".btn_ch_grant").click(function(){
		var i = $(".btn_ch_grant").index(this);
		var c_grantsta	= $(this).attr("grantsta");
		var user_id		= $(this).parent().parent().attr("user_id");
		alert(c_grantsta+","+user_id);
		$.post("${pageContext.request.contextPath}/admin/member/chGrantStatus.ajax", {c_grantsta:c_grantsta, user_id:user_id}, function(data){
			location.reload();
		});
	});
});

function gotopage(cp){
	$("#cp").val(cp);
	$("#submitForm").submit();
}


function gotoHistory(id){
	var url = "${pageContext.request.contextPath}/member/OrderHistory.popup?&mid="+id;
	wizwindow(url,'buyhistory','width=670,height=700,statusbar=no,scrollbars=yes,toolbar=no');
}
//-->
</script>
</head>
<body>
<table class="table_outline">
  <tr>
    <td><fieldset class="desc">
        <legend> 회원정보보기 </legend>
        <div class="notice">[note]</div>
        <div class="comment"> 가입회원 리스트 입니다.</div>
      </fieldset>
      <div class="space20"></div>
      <form id="submitForm">
        <input type="hidden" name="cp" id="cp" value="${pageNav.nowPage}" />
        <input type="hidden" name="ListNo" id="ListNo" value="${params.ListNo}" />
      </form>
      <div> 검색된 회원수:
        <fmt:formatNumber value="${pageNav.rowTotal}" />
        <select name="selListNo" id="selListNo" class="w30">
          <option>리스트갯수</option>
          <option value="1"<c:if test="${params.ListNo==1}"> selected</c:if>>1</option>
          <option value="15"<c:if test="${params.ListNo==15}"> selected</c:if>>15</option>
          <option value="20"<c:if test="${params.ListNo==20}"> selected</c:if>>20</option>
          <option value="30"<c:if test="${params.ListNo==30}"> selected</c:if>>30</option>
          <option value="50"<c:if test="${params.ListNo==50}"> selected</c:if>>50</option>
          <option value="100"<c:if test="${params.ListNo==100}"> selected</c:if>>100</option>
        </select>
        <span class="button bull" id="btn_excel"><a>엑셀출력</a></span> </div>
      <form id='memberList'>
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
              <th> <span class="button bull btn_sel_delete"><a>선택삭제</a></span></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${info}" var="current" varStatus="i">
              <tr user_id="${current.user_id}">
                <td>${pageNav.startNum-i.index}</td>
                <td><a href="javascript:getuserInfo('${current.user_id}');">${current.user_name}</a></td>
                <td><a href="javascript:getuserInfo('${current.user_id}');">${current.user_id}</a></td>
                <td>${fn:substring(current.user_address1, 0, 2)}</td>
                <td><a href="javascript:getuserInfo('${current.user_id}');">${current.user_grade}</a></td>
                <td>&nbsp;<a href="javascript:getuserInfo('${current.user_id}');">${current.user_point}</a></td>
                <td class="agn_c"><span class="button bull"><a href="javascript:gotoHistory('${current.user_id}')">보기</a></span></td>
                <td><fmt:formatDate value="${current.user_regdate}" pattern="yyyy.MM.dd" /></td>
                <td><fmt:formatNumber value="${current.user_login_num}" /></td>
                <td class="agn_c"><span class="btn_ch_grant" rantsta="${current.user_grantsta}"><a>${MemberGrantStatus[current.user_grantsta]}</a></span></td>
                <td class="agn_c"><input type="checkbox" name='chkMember' value='${current.user_id}'></td>
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
            <td colspan="4"><div> &nbsp;
                <input type="text" class="w100 s_date" name="s_date" value="${params.s_date}" />
                ~
                <input type="text" class="w100 s_date" name="e_date" value="${params.e_date}" />
                <input type="checkbox" name="DataEnable" value="1">
                Enable   &nbsp;&nbsp;&nbsp;
                <input type="text" name="s_keyword" value="${params.s_keyword}">
                <span class="button bull btn_search"><a>검색</a></span> </div></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>
<div class="paging_box">
  <c:if test="${pageNav.isPrevPage=='true'}"> <a href="javascript:gotopage(${pageNav.startPage - 1})">prev</a> </c:if>
  <c:forEach var="page" begin="${pageNav.startPage}" end="${pageNav.endPage}"> <a href="javascript:gotopage(${page})">[${page}] </a> </c:forEach>
  <c:if test="${pageNav.isNextPage == 'true'}"> <a href="javascript:gotopage(${pageNav.endPage + 1})">next</a> </c:if>
</div>
</body>
</html>