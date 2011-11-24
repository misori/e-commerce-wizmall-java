<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.member-resources"/>
<html>
<head>
<title>주문내역 조회</title>
<script language=javascript src="<c:url value="/resources/js/jquery.plugins/jquery.validator.js"/>"></script>

<script language="javascript">
<!--
$(function(){

});

function gotopage(page){
	$("#cp").val(page);
	$("#search_box").submit();
}


function gotoOrderInfo(oid){
	location.href = "${pageContext.request.contextPath}/member/order_view.do?oid="+oid;
}

function Repay(oid){
	location.href = "wizbag.php?query=step3&RepayOrderID="+oid;
}

function getDeliveryStatus(targeturl, arg, argvalue, method){//리스트에서 주문조회용
	var url = "./skinwiz/common/delivery.php?targeturl="+targeturl+"&arg="+arg+"&argvalue="+argvalue+"&method="+method;
	wizwindow(url,'DeliveryCheckWindow','');
	//사용법 <a href="getDeliveryStatus('<?=$d_inquire_url?>','<?=$d_code?>', '<?=$InvoiceNo?>', '<?=$d_method?>')">배송조회</a>
}
//-->
</script>
</head>
<body>

<div class="navy">Home &gt; 주문 조회</div>

<fieldset class="desc">
<legend>[안내]</legend>
<div class="notice">주문 조회</div>
<div class="comment">
고객님께서 주문하신 내역입니다.<br />
회원가입후 현재까지 ${params.login_name}(${params.memberid}) 의 주문내역 입니다.<br />
주문번호를 클릭하면 자세한 사항을 보실 수 있습니다. <br /></div>
</fieldset>
<form id="search_box" action="${pageContext.request.contextPath}/member/order_list.do">
</form>
<table class="table_main w100p">
		<col width="50" title="" />
		<col width="80" title="주문번호" />
		<col width="*" title="상품명" />
		<col width="70" title="구매금액" />
		<col width="70" title="결제방식" />
		<col width="70" title="거래상태" />
		<col width="100" title="주문일시" />
		<col width="70" title="상세내역" />
		<col width="50" title="재결제" />
		<thead>
	<tr>
		<th class="agn_c">&nbsp;</th>
		<th class="agn_c">주문번호</th>
		<th class="agn_c">상품명</th>
		<th class="agn_c">구매금액</th>
		<th class="agn_c"> 결제방식</th>
		<th class="agn_c"> 거래상태</th>
		<th class="agn_c">주문일시</th>
		<th class="agn_c">상세내역</th>
		<th class="agn_c">재결재</th>
	</tr>
	</thead>
	<tbody>
<c:forEach items="${info}" var="current" varStatus="i">
	<tr>
		<td>${pageNav.startNum-i.index}</td>
		<td><a href="javascript:gotoOrderInfo('${current.orderid}')">${current.orderid}</a></td>
		<td>상품외 건</td>
		<td class="agn_r">${current.totalamount}
			원</td>
		<td class="agn_c">${current.paymethod}</td>
		<td class="agn_c">${current.orderstatus}</td>
		<td class="agn_c"><fmt:formatDate value="${current.buydate}" pattern="yyyy.MM.dd" /></td>
		<td class="agn_c"><input type="button" name="button" id="button" value="상세내역" style="cursor:pointer" onclick="gotoOrderInfo('${current.orderid}')"></td>
		<td class="agn_c"><input type="button" name="button2" id="button2" value="결제" style="cursor:pointer" onclick="Repay('${current.orderid}')">
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
<div>현재페이지 합계금액 : <font color="E37509">
	
	원| 총 주문금액 : <font color="703EAE">
	
	원 </div>

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