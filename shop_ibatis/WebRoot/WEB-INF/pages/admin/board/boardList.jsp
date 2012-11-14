<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.plugins/jquery.validator.js" ></script>
<script language="javascript">
<!--
$(function(){

	//보드 생성
	$("#btn_insert").click(function(){
		/*$('#b_reg_form').attr("action", "${pageContext.request.contextPath}/admin/board/createBoard.ajax");
		$('#b_reg_form').submit();
		*/
		if($('#b_reg_form').formvalidate()){
			$.post("${pageContext.request.contextPath}/admin/board/createBoard.ajax", $('#b_reg_form').serialize(), function(data){
				eval("var obj =" + data);

				if(obj.result == "0"){
					location.reload();
				}else jAlert(obj.msg);
			});
		}
	});
	
	//보드 삭제
	$(".btn_board_del").click(function(){
		var tid = $(this).attr("tid");
		//alert(tid);
		jConfirm("삭제하시겠습니까? <br> 삭제된 데이타는 복구되지 않습니다.","",function(r){
			if(r == true){
				$.post("/board/admin/del_board", {tid:tid}, function(data){
					//jAlert(data);
					location.reload();
				});
			}
		});
	});
	
	//보드옵션관리
	$(".btn_board_manage").click(function(){
		var gid = $(this).attr("gid");
		var bid = $(this).attr("bid");
		window.open("${pageContext.request.contextPath}/admin/board/boardManage.popup?bid="+bid+"&gid="+gid, "", "");
	});	
	
	
});

function gotopage(cp){
	$("#cp").val(cp);
	$("#s_form").submit();
}

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

		<form name="b_reg_form" id="b_reg_form" action="" method="post">
			<table  class="table_main">
				<thead>
					<tr>
						<th>그룹</th>
						<td><select name="f_gid" class="required" msg="그룹을 선택해주세요">
								<option value="root" selected="selected">root</option>
							</select></td>
						<th>보드아이디</th>
						<td><input name="f_bid" type="text" class="required alphanum inputBox w100" title="보드아이디" msg="보드아이디를 입력해주세요" /></td>
						<th>테이블설명</th>
						<td><input name="f_title" type="text" class="required inputBox w100" title="테이블" msg="테이블설명을 입력해주세요" /></td>
						<td><span class="button bull"><a id="btn_insert">등록</a></span> </td>
					</tr>
			</table>
		</form>
		<table class="table_main list">
			<col width="50" /><col width="*" /><col width="100" /><col width="80" /><col width="80" /><col width="150" />
			<thead>
				<tr>
					<th>번호</th>
					<th>테이블제목</th>
					<th>그룹아이디</th>
					<th>보드아이디</th>
					<th>생성일</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
			
			<c:forEach var="current" varStatus="i" items="${tblList}"> 
			<tr>
				<!-- division -->
				<td class="Agn_c">${pageNav.startNum-i.index}</td>
				<td class="Pad_l10"><a href="${pageContext.request.contextPath}/board/boardList.do?bid=${current.bid}&gid=${current.gid}" target="_blank">${current.title}</a></td>
				<td class="Agn_c">${current.gid}</td>
				<td class="Agn_c">${current.bid}</td>
				<td class="Agn_c"><fmt:formatDate value="${current.w_date}" pattern="yyyy.MM.dd" /></td>
				<td class="Agn_c"><span class="button bull"><a class="btn_board_del" tid="${current.tid}">삭제</a></span> <span class="button bull"><a class="btn_board_manage" gid="${current.gid}" bid="${current.bid}">옵션관리</a></span></td>
			</tr>
			</c:forEach>
			<c:if test="${pageNav.rowTotal == '0'}">
			<tr>
				<!-- division -->
				<td colspan="6" class="Agn_c">리스팅할 내용이 없습니다.</td>
			</tr>
			</c:if>
			</tbody>
			
		</table>
		<p class="Agn_r"> <span class='button bull'><a href="${pageContext.request.contextPath}/admin/board/boardList.do">목록보기</a></span> </p>
		<!-- 페이지구분 -->
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
		<!-- //페이지구분 -->
		<div class="agn_c">
			<form id="s_form" method="post" action="${pageContext.request.contextPath}/admin/board/boardList.do">
				<select id="s_title" name="s_title">
					<option value="TITLE" selected="selected">제목</option>
				</select>
				<input type="text" class="inputBox" id="s_keyword" name="s_keyword" value="${params.s_keyword}"/>
				<span class="button bull btn_search"><a>검색</a></span>
			</form>
		</div>
		</td>
	</tr>
</table>
			




</body>
</html>