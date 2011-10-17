<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List</title>
<script language="javascript" type="text/javascript">
<!--
$(function() {
	//검색날짜 선택
	$( ".s_date" ).datepicker( {dateFormat:"yy-mm-dd"} );
	
	//엑셀출력
	$("#btn_excel").click(function(){
			location.href="${pageContext.request.contextPath}/admin/order/orderExcelWrite.ajax";
	});

	//검색
	$(".btn_search").click(function(){
		var url = "${pageContext.request.contextPath}/admin/order/orderList.do";
		$("#search_box").submit();
	});

	//리스트
	$(".btn_list").click(function(){
		location.href="${pageContext.request.contextPath}/admin/order/orderList.do";
	});

	//정렬
	$("#s_order").change(function(){
		var url = "${pageContext.request.contextPath}/admin/order/orderList.do";
		$("#search_box").submit();
	});

});

function gotopage(page){
	$("#search_box").attr("action", $("#search_box").attr("action")+"?cp="+page);
	$("#search_box").submit();
}
//-->
</script>
</head>
<body>

<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>
			주문상품보기
			</legend>
			<div class="notice">[note]</div>
			<div class="comment">주문번호를 클릭시 현재 주문상품 및 배송지에 대한 자세한 내용을 보실 수 있으며 더불어 현재 거래상태를
				변경하실 수 있습니다.<br />
				거래상태를 변경하시면 고객이 주문번호 혹은 로그인만으로도 주문상태를 확인할 수있습니다.<br />
				주문자를 클릭하시면 주문자의 상세정보를 보실 수 있습니다.<br />
				* 주의: 카드결제 성공시 거래상태가 "입금확인됨" 단계,
				실패시는 "결제실패"로 표시되지만 인터페이스상의 문제로 반드시
				카드결제시는 카드사 관리자 모드에서 반드시 한 번더 확인 바랍니다. </div>
			</fieldset>
			<div class="space20"></div>
			<table class="table_main">
				<form action='${pageContext.request.contextPath}/admin/order/orderList.do' method="post" name="search_box" id="search_box" >
					<tr>
						<td>			
						<select name='s_title'>
							<option value="ORDERID"<c:if test="${params.s_title == 'ORDERID'}" var="result"> selected</c:if>>주문번호</option>
							<option value="SNAME"<c:if test="${params.s_title == 'SNAME'}" var="result"> selected</c:if>>주문자</option>
							<option value="STEL1"<c:if test="${params.s_title == 'STEL1'}" var="result"> selected</c:if>>전화번호</option>
						</select>

						<input size=10 name="s_keyword" value='${params.s_keyword}'>
						
						<span class="button bull btn_search"><a>검색</a></span>
						<span class="button bull btn_list"><a>리스트</a></span>
						
						
						정렬 : 
						<select name='s_order' id="s_order">
							<option value="BUYDATE@desc"<c:if test="${params.s_order == 'BUYDATE@desc'}"> selected</c:if>>주문일시</option>
							<option value="SNAME@asc"<c:if test="${params.s_order == 'SNAME@asc'}"> selected</c:if>>주문자명</option>
							<option value="STEL1@asc"<c:if test="${params.s_order == 'STEL1@asc'}"> selected</c:if>>구매금액</option>
							<option value="ORDERSTATUS@asc"<c:if test="${params.s_order == 'ORDERSTATUS@asc'}"> selected</c:if>>주문상태</option>
						</select></td>
					</tr>
					<tr>
						<td>
							<input type="text" class="w100 s_date" name="s_date" value="${params.s_date}" />
							~
							<input type="text" class="w100 s_date" name="e_date" value="${params.e_date}" />
										
						</td>
					</tr>
				</form>
			</table>
			<br />
			* 주의 : 카드결제 성공시 "입금확인됨" 단계, 실패시는
			"결제실패"단계. 카드결제시는 카드사 관리자 모드에서 한 번더 확인 바람 <br />
			<span class="button bull" id="btn_excel"><a>엑셀출력</a></span>
			<table class="table_main list">
				<col width="30" /><!-- NO -->
				<col width="100" /><!-- 주문번호 -->
				<col width="90" /><!-- 구매금액 -->
				<col width="70" /><!-- 결제방식 -->
				<col width="70" /><!-- 거래상태 -->
				<col width="*" /><!-- 주문자 -->
				<col width="100" /><!-- 전화 -->
				<col width="100" /><!--주문일시  -->
				<thead>
					<tr class="altern">
						<th class="agn_c">NO </th>
						<th class="agn_c">주문번호</th>
						<th class="agn_c">구매금액</th>
						<th class="agn_c">결제방식</th>
						<th class="agn_c">거래상태</th>
						<th class="agn_c">주문자</th>
						<th class="agn_c">전화</th>
						<th class="agn_c">주문일시</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="sum_price" value="0"/>
				<c:forEach items="${info}" var="current" varStatus="i">
				<c:set var="sum_price" value="${current.totalamount + sum_price }"/>
					<tr>
						<td>${pageNav.startNum-i.index}</td>
						<td><a  href="javascript:openorderwindow(${current.tid})"> ${current.orderid} </a></td>
						<td class="agn_r"><fmt:formatNumber value="${current.totalamount}" /> 원 </td>
						<td>${current.paymethod}</td>
						<td>${OrderStatus[current.orderstatus]}</td>
						<td><a  href="javascript:getuserInfo('${current.memberid}')">${current.sname}(${current.memberid})</a> </td>
						<td>${current.stel1}</td>
						<td><fmt:formatDate value="${current.buydate}" pattern="yyyy.MM.dd" /></td>
					</tr>
				</c:forEach>
				<c:if test="${pageNav.rowTotal == '0'}">
					<tr>
						<td colspan="8" class="Agn_c">리스팅할 내용이 없습니다.</td>
					</tr>
				</c:if>
				</tbody>
				<tr>
					<td colspan="8">현재페이지 합계금액 :
						<fmt:formatNumber value="${sum_price}" />
						원 |
						총금액 :
						<?=number_format($TOTAL_SMONEY);?>
						원</td>
				</tr>
			</table>
			<br />
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
        		</div></td>
	</tr>
</table>
</body>
</html>