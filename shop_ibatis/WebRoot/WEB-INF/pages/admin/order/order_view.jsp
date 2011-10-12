<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>주문상세보기</title>
		<script language=javascript src="<c:url value="/js/jquery.plugins/jquery.validator.js"/>"></script>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	//메시지 관리
	$(".btn_manage_message").click(function(){
		window.open("./order1_2.php?what=sms", "SMSMessageManagerWindow","width=470,height=500,statusbar=no,scrollbars=yes,toolbar=no");
	});

	//배송지 주소 찾기
	$("#btn_find_zipcode2").click(function(){
		wizwindow("${pageContext.request.contextPath}/util/FindZip.blank?form=orderForm&zip1=RZip1&zip2=RZip2&firstaddress=RAddress1&secondaddress=RAddress2","ZipWin","width=490,height=250,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no");
	});

	//배송지 주소 변경
	$("#btn_ch_address").click(function(){
		var tid			= $("#tid").val();
		var RZip1		= $("#RZip1").val();
		var RZip2		= $("#RZip2").val();
		var RAddress1	= $("#RAddress1").val();
		var RAddress2	= $("#RAddress2").val();
		$.post("${pageContext.request.contextPath}/admin/order/order_ch_address.ajax", {tid:tid, RZip1:RZip1, RZip2:RZip2, RAddress1:RAddress1,  RAddress2:RAddress2},
			function(data){
				alert('주소가 변경되었습니다.');
		});
	});

	//송장업데이트
	$("#btn_ch_delever").click(function(){
		var tid			= $("#tid").val();
		var Deliverer	= $("#Deliverer > option:selected").val()
		var InvoiceNo	= $("#InvoiceNo").val();

		$.post("${pageContext.request.contextPath}/admin/order/order_ch_delever.ajax", {tid:tid, Deliverer:Deliverer, InvoiceNo:InvoiceNo},
			function(data){
				alert('송장이 업데이트 되었습니다.');
		});
	});

	//거래상태 변경 
	$("#btn_ch_orderstatus").click(function(){
		var tid			= $("#tid").val();
		var OrderStatus	= $("#OrderStatus > option:selected").val()

		$.post("${pageContext.request.contextPath}/admin/order/order_ch_orderstatus.ajax", {tid:tid, OrderStatus:OrderStatus},
			function(data){
				alert('거래상태가 변경되었습니다.');
		});
	});
});

function down_excel(id){
location.href = './order1_3.php?DownForExel=yes&uid='+id;
}

function really() {
		if (confirm('\n\n이미 거래가 완료된 상태입니다.  \n\n거래가 완료된 상태에서 변경할 경우 \n\n회원에게 부여되었던 포인트 및 \n\n제품판매 정보가 거래완료이전 상태로 되돌려 집니다.\n\n정말로 변경하시겠습니까?  \n\n')) return true;
		return false;
}

function change_flag(flag){
	if(flag == "reorder"){ //재주문처리
	}else if(flag == "2"){
	}else if(flag == "reset"){ //초기상태로 변경
	}

}

function openwindow(uid){
	window.open('./order1_1_1.php?uid='+uid,'AddressChangeForm','width=600,0');
}

function changeDeliveryInfo(flag){
	var f = document.OrderinfoForm;
	if(flag == "chg_deliveryStatus"){//거래상태변경

	}else if(flag == "delete"){//주문삭제
		if (!confirm('\n\n삭제된 주문데이터는 복구가 불가능합니다.  \n\n정말로 삭제하시겠습니까?  \n\n'))return false;
		else {
			f.mode.value = flag;
			f.submit();
		}
	}
}


//-->
</script>
		</head>
<body>
<form action='${pageContext.request.contextPath}/admin/order/order_x' name="s_form" method="post">
<input type="hidden" name="tid" id="tid" value='${info.tid}'>
<input type="hidden" name="mode" value=''>
<input type="hidden" name="c_orderstatus" value='${info.orderstatus}'>
<input type="hidden" name="oid" value='${info.orderid}'>

<div class="orange b">배송상세정보</div>
<!-- cart List Start -->
<page:applyDecorator page="/cart/cartList.ajax?type=3&orderid=${info.orderid}" name="planepanel"/>
<!-- cart List End -->
<div class="orange b">[ 주문자 정보 ]</div>
<table class="table_popmain">
		<col width="120" />
		<col width="*" />
				<col width="120" />
		<col width="*" />
<tr>
		<th>주문자</th>
		<td><a  href='mailto:${info.semail}'>${info.sname}</a> (${info.memberid})</td>
		<th>상호명</th>
		<td></td>
	</tr>
	<tr>
		<th>E-mail</th>
		<td><a  href='mailto:${info.semail}'>
			${info.semail}
			</a> </td>
		<th>전화번호</th>
		<td>${info.stel1}</td>
	</tr>
	<tr>
		<th>휴대폰</th>
		<td>${info.stel2}</td>
		<th>수령인</th>
		<td>${info.rname}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td colspan="3"><input name="RZip1" type="text" id="RZip1" value="${r_zip1[0]}" class="w30">
						-
						<input name="RZip2" type="text" id="RZip2"  value="${r_zip1[1]}" class="w30">
						<span class="button bull hand"><a id="btn_find_zipcode2" >우편번호찾기</a></span> 
						<span class="button bull hand"><a id="btn_ch_address" >주소변경</a></span> 
				</td>
	</tr>
	<tr>
		<th>배송지주소</th>
		<td colspan="3"><input name="RAddress1" type="text" id="RAddress1" value="${info.raddress1}"  class="w300"></td>
	</tr>
	<tr>
		<th>상세주소</th>
		<td colspan="3"><input name="RAddress2" type="text" id="RAddress2" value="${info.raddress2}"  class="w300"></td>
	</tr>
	<tr>
		<th>배송지전화 </th>
		<td>${info.rtel1}
		</td>
		<th>희망배송일</th>
		<td>
		</td>
	</tr>
	<tr>
		<th>배송안내글</th>
		<td colspan="3">${info.message}</td>
	</tr>
</table>
<div class="orange b">[ 주문상태 ]</div>
<table class="table_popmain">
		<col width="120" />
		<col width="*" />
				<col width="120" />
		<col width="*" />
	<tr>
		<th>주문번호 </th>
		<td>${info.orderid}</td>
		<th>주문일자</th>
		<td>${info.buydate}</td>
	</tr>
	<tr>
		<th>거래상태</th>
		<td colspan="3">
<!--
case 1. 물품반송->재주문처리
case 2. 일반
case 3. 배송완료->초기상태
case 배송완료상태에서는 주문삭제 불가 
-->
			<table>
				<tr>
					<td><select name='OrderStatus' id="OrderStatus">
						<c:forEach var="current" items="${orderStatus}"> 
							<option value='${current.key}'<c:if test="${current.key == info.orderstatus}"> selected</c:if>>${current.value}</option>
						</c:forEach>
						</select>
					</td>
					<td><span class="button bull hand"><a id="btn_ch_orderstatus" >거래상태변경</a></span></td>
				</tr>
			</table></td>
	</tr>
</table>
<div class="orange b">[ 택배사 및 송장번호]</div>
<table class="table_popmain">
		<col width="120" />
		<col width="*" />
				<col width="120" />
		<col width="*" />
	<tr>
		<th>택배사</th>
		<td><select name="Deliverer" id="Deliverer">
				<option value="">선택</option>
<c:forEach items="${deliverer}" var="current">
<option value='${current.tid}'<c:if test="${current.tid == info.deliverer}"> selected</c:if>>${current.d_name}</option>
</c:forEach>

			</select>
		</td>
		<th>송장번호</th>
		<td><input name="InvoiceNo" id="InvoiceNo" value="${info.invoiceno}">
		<span class="button bull hand"><a id="btn_ch_delever" >송장업데이트</a></span> </td>
	</tr>
</table>
<div class="orange b">[ 결제방식 선택 ]</div>
<table class="table_popmain">
		<col width="120" />
		<col width="*" />
 <c:choose>
       <c:when test="${info.paymethod == 'card'}">
	<tr>
		<th>결제방식</th>
		<td>신용카드 결제</td>
	</tr>
       </c:when>
       <c:when test="${info.paymethod == 'point'}">
	<tr>
		<th>결제방식</th>
		<td>포인트 결제</td>
	</tr>
       </c:when>
       <c:when test="${info.paymethod == 'hand'}">
	<tr>
		<th>결제방식</th>
		<td>핸드폰 결제</td>
	</tr>
       </c:when>
       <c:when test="${info.paymethod == 'all'}">
	<tr>
		<th>결제방식</th>
		<td>다중결제(온라인+신용카드+포인트)</td>
	</tr>
	<tr>
		<th>온라인입금</th>
		<td><fmt:formatNumber value="${info.amountonline}" /> 원</td>
	</tr>
	<tr>
		<th>입금계좌</th>
		<td>${info.bankinfo}</td>
	</tr>
	<!--
	<tr>
		<th>입금예정일</th>
		<td> </td>
	</tr>
	-->
	<tr>
		<th>신용카드</th>
		<td><fmt:formatNumber value="${info.amountpg}" /> 원</td>
	</tr>
	<tr>
		<th>포인트</th>
		<td><fmt:formatNumber value="${info.amountpoint}" /> 포인트</td>
	</tr>
       </c:when>

       <c:otherwise>
	<tr>
		<th>결제방식</th>
		<td>온라인입금</td>
	</tr>
	<tr>
		<th>입금계좌</th>
		<td>${info.bankinfo}</td>
	</tr>
	<tr>
		<th> 입금인 </th>
		<td>${info.inputer}</td>
	</tr>
	<tr>
		<th>입금예정일</th>
		<td></td>
	</tr>
       </c:otherwise>
   </c:choose>


</table>

<div class="btn_box">
	<!-- "\n\n거래가 완료된 상태에서 삭제가 불가능합니다. \n\n거래상태를 주문접수됨으로 변경후  \n\n삭제 처리하십시오.\n\n" -->
	<!-- "\n\n거래가 완료된 상태에서 반품처리할 경우 \n\n거래상태를 주문접수됨으로 변경후  \n\n처리하십시오.\n\n" -->
	
<c:choose>
       <c:when test="${info.orderstatus == '10'}">
		<span class="button bull"><a href='javascript:changeDeliveryInfo("delete");'>주문삭제</a></span>
       </c:when>

       <c:otherwise>
           <span class="button bull"><a href='javascript:alert("재고통계 및 포인트 적용을 위해\n거래상태를 주문접수됨으로 변경후  \n삭제 처리하십시오.")'>주문삭제</a></span>
       </c:otherwise>
   </c:choose>
	
	<span class="button bull"><a href="javascript:self.print()">프린트</a></span> <span class="button bull"><a href="javascript:self.close()">창닫기</a></span> <span class="button bull"><a href='javascript:down_excel("<?=$uid?>");'>엑셀출력</a></span> </div>
</body>
</html>