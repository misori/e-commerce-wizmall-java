<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Cart List</title>
<script type="text/javascript">
$(function(){
	$(".btn_order").click(function(){
		location.href="${pageContext.request.contextPath}/cart/orderForm.do"
	});
})

// 구매 갯수를 변경한다.
function num_cal(flag, i){
	var j = i-1;
	var buynum	= parseInt($(".buynum").eq(j).val());
	if(flag == "plus"){
		$(".buynum").eq(j).val(buynum+1);
	}else if(flag == "minus"){
		if(buynum > 1){
			$(".buynum").eq(j).val(buynum-1);
		}
	}
	return;
}

// 변경된 구매 갯수를 적용한다. 
function modQty(tid, i){
	var j = i-1;
	var buynum	= $(".buynum").eq(j).val();
	location.href = "${pageContext.request.contextPath}/cart/setQty.do?&tid="+tid+"&buynum="+buynum
}

// 선택된 아이템을 장바구니에서 삭제한다.
function deleachItem(tid){
	location.href = "${pageContext.request.contextPath}/cart/deleteItem.do?tid="+tid
}





//아래는 삭제 예정
function gotopage(page){
	$("#search_box").attr("action", $("#search_box").attr("action")+"&cp="+page);
	$("#search_box").submit();
}

function gotoview(tid){
	var url	= $("#vform").attr("action")+"&tid="+tid;
	$("#vform").attr("action", url);
	$("#vform").submit();
}
</script>
</head>
<body>
<c:choose>
       <c:when test="${type == '1'}">
				<table class="table_main w100p">
					<col width="*" />
					<col width="80" />
					<col width="80" />
					<col width="80" />
					<col width="50" />
					<col width="50" />
					<thead>
						<tr>
							<th>상품명</th>
							<th>가격</th>
							<th>수량</th>
							<th>소계금액</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
			<c:forEach items="${info}" var="current" varStatus="i">
						<tr>
							<td>
							<img src="${pageContext.request.contextPath}/data/product/${current.attached}" width="80" height="80" />
							<a href="javascript:gotoview(${current.pid})">${current.name}</a></td>
							<td class="agn_r"><fmt:formatNumber value="${current.price}" /> 원</td>
							<td class="agn_c"><input type="text" value='${current.qty}' onkeypress="onlyNumber()" readonly="readonly" class="w30 buynum" style="float:left">
								<div style="float:left"> 
									<a href="javascript:num_cal('plus', ${i.count})"><img src='${pageContext.request.contextPath}/images/shop/num_plus.gif'></a> <br />
									<a href="javascript:num_cal('minus', ${i.count})"><img src='${pageContext.request.contextPath}/images/shop/num_minus.gif'></a> 
								</div>
								EA</td>
							<td class="agn_r"><fmt:formatNumber value="${current.tprice}" /> 
								원<br />
							</td>
							<td class="agn_c"><a href="javascript:modQty(${current.tid}, ${i.count})"><img src='${pageContext.request.contextPath}/images/shop/but_mo.gif' align="middle"></a> </td>
							<td class="agn_c"><a href='javascript:deleachItem(${current.tid})'><img src='${pageContext.request.contextPath}/images/shop/but_cancle.gif' align="middle"></a></td>
						</tr>
			</c:forEach>
					</tbody>
				</table>

			<br />
			<div class="agn_c">

			<span class="orange">주문상품 총액:</span> <fmt:formatNumber value="${total}" /> 원

			</div>
       </c:when>
       <c:when test="${type == '2'}">
			<table class="table_main w100p">
				<thead>
					<tr>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
						<th>소계금액</th>
					</tr>
				</thead>
				<tbody>
			<c:forEach items="${info}" var="current" varStatus="i">
					<tr>
						<td>
						<img src="${pageContext.request.contextPath}/data/product/${current.attached}" width="80" height="80" />
						<a href="javascript:gotoview(${current.pid})">${current.name}</a></td>
						<td><fmt:formatNumber value="${current.price}" /> 원</td>
						<td>${current.qty} ea</td>
						<td><fmt:formatNumber value="${current.tprice}" />
							원 </td>
					</tr>
			</c:forEach>
				</tbody>
			</table>
       </c:when>
       <c:when test="${type == '3'}">
			<table class="table_main w100p">
				<thead>
					<tr>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
						<th>소계금액</th>
					</tr>
				</thead>
				<tbody>
			<c:forEach items="${info}" var="current" varStatus="i">
					<tr>
						<td>
						<img src="${pageContext.request.contextPath}/data/product/${current.attached}" width="80" height="80" />
						<a href="javascript:gotoview(${current.pid})">${current.name}</a></td>
						<td><fmt:formatNumber value="${current.price}" /> 원</td>
						<td>${current.qty} ea</td>
						<td><fmt:formatNumber value="${current.tprice}" />
							원 </td>
					</tr>
			</c:forEach>
				</tbody>
			</table>
       </c:when>
       <c:otherwise>

       </c:otherwise>
 </c:choose>
</body>
</html>