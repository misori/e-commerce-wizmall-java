<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Order Form</title>
</head>
<body>

<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jquery.validator.js"/>"></script>
<script language=javascript>
<!--
$(function(){
	$("#btn_find_zipcode1").click(function(){
		wizwindow("${pageContext.request.contextPath}/util/FindZip.blank?form=orderForm&zip1=SZip1&zip2=SZip2&firstaddress=SAddress1&secondaddress=SAddress2","ZipWin","width=490,height=250,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no");
	});
	
	$("#btn_find_zipcode2").click(function(){
		wizwindow("${pageContext.request.contextPath}/util/FindZip.blank?form=orderForm&zip1=RZip1&zip2=RZip2&firstaddress=RAddress1&secondaddress=RAddress2","ZipWin","width=490,height=250,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no");
	});
	
	
	$(".copybtn").click(function(){
		var v = $('.copybtn:checked').val();
		switch(v){
			case "0"://정보 불일치
				$("#RName").val("");
				$("#RTel1_1").val("");
				$("#RTel1_2").val("");
				$("#RTel1_3").val("");
				$("#RTel2_1").val("");
				$("#RTel2_2").val("");
				$("#RTel2_3").val("");
				$("#RZip1").val("");
				$("#RZip2").val("");
				$("#RAddress1").val("");
				$("#RAddress2").val("");
			break;
			case "1"://정보일치
				$("#RName").val($("#SName").val());
				$("#RTel1_1").val($("#STel1_1").val());
				$("#RTel1_2").val($("#STel1_2").val());
				$("#RTel1_3").val($("#STel1_3").val());
				$("#RTel2_1").val($("#STel2_1").val());
				$("#RTel2_2").val($("#STel2_2").val());
				$("#RTel2_3").val($("#STel2_3").val());
				$("#RZip1").val($("#SZip1").val());
				$("#RZip2").val($("#SZip2").val());
				$("#RAddress1").val($("#SAddress1").val());
				$("#RAddress2").val($("#SAddress2").val());			
			break;	
		}
	});
	
	$(".btn_order").click(function(){
		$("#orderForm").submit();
	});
});



function FilluserName(){
	var f=document.FrmUserInfo;
	if (!f.SName.value) {
		alert("주문자 성명을 정확히 적어주십시오.");
		f.SName.focus();
		return false;
	}
	num = f.SName.value;
	f.RName.value = num;
	f.RName.focus();
}

function FilluserTel(){
	var f=document.FrmUserInfo;
	if ((!f.UserTel1.value) || (!f.UserTel1.value)) {
		alert("전화번호를 정확히 입력해 주세요. ");
		return false;
	}
	num = f.UserTel1.value;
	f.UserTel3.value = num;
	f.UserTel3.focus();
}


function CheckField(f){
	if(f.paytype != "undefined") var paytype = getRadiovalue(f.paytype)

	if (!f.SName.value) {
		alert("주문자 성명을 정확히 적어주십시오.");
		f.SName.focus();
		return false;
	}else if (f.SEmail.value.length < 3) {
		alert("E-mail 주소가 부정확합니다. 확인해 주십시오");
		f.SEmail.focus();
		return false;
	}else if(f.RAddress2.value.length < 2) {
		alert("번지수/통/반을 정확히 입력해 주세요.[예:123번지] ");
		f.RAddress2.focus();
		return false;
	}else if (f.RTel1_1.value.length < 1 && f.RTel2_1.value.length < 1) {
		alert("전화번호는 반드시 1개 이상 입력되어야 합니다.");
		f.RTel1_1.focus();
		return false;
	}else if (paytype == "online" && f.BankInfo.value == "") {
		alert("입금은행을 선택해 주세요");
		f.BankInfo.focus();
		return false;
	}else if (paytype == "online" && f.Inputer.value == "") {
		alert("입금인 성명을 정확히 적어주십시오.");
		f.Inputer.focus();
		return false;
	}else if(f.pointamount){
		f.pointamount.value = RemoveComma(f.pointamount.value)
	}else return true;

}


function SelectPayType(v){

	var f = eval("document."+v.form.name);
	totalpay = RemoveComma(f.total_check.value);
	if(v.value == "online"){
		$(".onlinepaytype").show();
	}else if(v.value == "card"){
		if(totalpay < 1000 ){
			window.alert('신용카드구매는 구매액이 1000원 이상만 가능합니다.');
			v.checked = false;
			return;
		}
		$(".onlinepaytype").hide();
	}else if(v.value == "hand"){
		if(totalpay < 1000 ){
			window.alert('신용카드구매는 구매액이1000원 이상만 가능합니다.');
			v.checked = false;
			return;
		}
		$(".onlinepaytype").hide();
	}
}

function paycalculate() {

	num1 = parseInt(filterNum($("#pointamount").val()));
	num2 = parseInt(filterNum($("#TOTAL_MONEY").val()));
	user_point	= parseInt(${point});
	POINT_ENABLE_MONEY	= parseInt(${payinfo["POINT_ENABLE_MONEY"]});


	if (!TypeCheck($("#pointamount").val(), NUM+COMMA)) {
		resetPaymoney();
		alert('숫자와 콤마만 입력가능합니다.');
		return;
	}
	if (num1 > user_point) {
		resetPaymoney();
		alert('고객님께서 사용가능한 '+user_point+'포인트 이내에서만 구매가능합니다.');	
		return;
	}

	if (user_point < POINT_ENABLE_MONEY) {
		resetPaymoney();
		alert('고객님께서 '+POINT_ENABLE_MONEY+'포인트 이상부터 포인트 구매가능합니다.');
		return;
	}

	if (num1 > num2) {
			alert('포인트 결제금액이 제품 구매액보다 많게 입력되었습니다');
	}
	
	//alert(num1+","+num2);
	$("#pointamount").val(SetComma1($("#pointamount").val()));
	$("#total_check").val(SetComma1(num2 - num1));

}

function resetPaymoney(f){
	$("#pointamount").val(0);
	$("total_check").val(SetComma1($("#TOTAL_MONEY").val()));

	$("#pointamount").focus();
	//event.returnValue=false;
}

//-->
</script>
<form action="${pageContext.request.contextPath}/product/productList.do?code=${code}" method="post" id="search_box">
</form>
<!-- <form action='${pageContext.request.contextPath}/cart/orderInsert' name='orderForm' id="orderForm"  method=post OnSubmit='javascript:return CheckField(this)'> -->

<!-- cart List Start -->
<page:applyDecorator page="/cart/cartList.ajax?type=2" name="planepanel"/>
<!-- cart List End -->

<form action='${pageContext.request.contextPath}/cart/orderInsert.do' name='orderForm' id="orderForm"  method="post">
	<input type="hidden" name="cod" value="">
	<input type="hidden" name="TOTAL_MONEY" id="TOTAL_MONEY" value='${totalSum}'>
	<input type="hidden" name="goods_name" value=''>
	주문고객정보
	<table class="table_main w100p">

	<col width="130" />
	<col width="*" />
		<tbody>
			<tr>
				<th>이름</th>
				<td><input name="SName" type="text" id="SName" value="${buyers.sname}">
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input name="SZip1" type="text" id="SZip1" value="${user_zip1[0]}" class="w30">
					-
				  <input name="SZip2" type="text" id="SZip2" value="${user_zip1[1]}" class="w30">
				  <span class="button bull hand"><a id="btn_find_zipcode1" >우편번호찾기</a></span><br />
					<input name="SAddress1" type="text" id="SAddress1" value="${buyers.saddress1}" class="w300">
				  <br />
					<input name="SAddress2" type="text" id="SAddress2" value="${buyers.saddress2}" class="w300">
				</td>
			</tr>
			<tr>
				<th>유선전화</th>
				<td><input name="STel1_1" type="text" id="STel1_1" value="${user_tel1[0]}" class="w50">
					-
					<input name="STel1_2" type="text" id="STel1_2" value="${user_tel1[1]}" class="w50">
					-
					<input name="STel1_3" type="text" id="STel1_3" value="${user_tel1[2]}" class="w50">
				</td>
			</tr>
			<tr>
				<th>휴대전화</th>
				<td><input name="STel2_1" type="text" id="STel2_1" value="${user_tel2[0]}" class="w50">
					-
				  <input name="STel2_2" type="text" id="STel2_2" value="${user_tel2[1]}" class="w50">
					-
					<input name="STel2_3" type="text" id="STel2_3" value="${user_tel2[2]}" class="w50">
				</td>
			</tr>
			<tr>
				<th>E-mail</th>
				<td><input name="SEmail" type="text" id="SEmail" value="${buyers.semail}">
				</td>
			</tr>
		</tbody>
	</table>
	<table>
		<tr>
			<td>&nbsp;</td>
			<td width="348">배송지 정보가 주문자 정보와 동일 합니까?
				<input type="radio" name="copybtn" class="copybtn" value="1">
				예
				<input type="radio" name="copybtn" class="copybtn" value="0">
				아니오</td>
		</tr>
	</table>
	<table class="table_main w100p">
	<col width="130" />
	<col width="*" />
		<tbody>
			<tr>
				<th>이름</th>
				<td><input name="RName" type="text" id="RName" value="${buyers.rname}">
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input name="RZip1" type="text" id="RZip1" value="${r_zip1[0]}" class="w30">
					-
					<input name="RZip2" type="text" id="RZip2" value="${r_zip1[1]}" class="w30">
					<span class="button bull hand"><a id="btn_find_zipcode2" >우편번호찾기</a></span> <br />
					<input name="RAddress1" type="text" id="RAddress1" value="${buyers.raddress1}" class="w300">
				  <br />
					<input name="RAddress2" type="text" id="RAddress2" value="${buyers.raddress2}" class="w300"></td>
			</tr>
			<tr>
				<th>유선전화</th>
				<td><input name="RTel1_1" type="text" id="RTel1_1" value="${r_tel1[0]}" class="w50">
					-
					<input name="RTel1_2" type="text" id="RTel1_2" value="${r_tel1[1]}" class="w50">
					-
					<input name="RTel1_3" type="text" id="RTel1_3" value="${r_tel1[2]}" class="w50">
				</td>
			</tr>
			<tr>
				<th>휴대전화</th>
				<td><input name="RTel2_1" type="text" id="RTel2_1" value="${r_tel2[0]}" class="w50">
					-
					<input name="RTel2_2" type="text" id="RTel2_2" value="${r_tel2[1]}" class="w50">
					-
					<input name="RTel2_3" type="text" id="RTel2_3" value="${r_tel2[2]}" class="w50">
				</td>
			</tr>
			<tr>
				<th>배송메세지</th>
				<td><textarea name="Message" id="Message"  class="w100p">${buyers.message}</textarea>
					<br />
					배송메세지란에는 배송시 참고할 사항이 있으면 적어주십시오.</td>
			</tr>
		</tbody>
	</table>
	결제금액
	<table class="table_main w100p">
	<col width="130" />
	<col width="*" />
	<col width="130" />
	<col width="*" />
		<tbody>
			<tr>
				<th>물품금액</th>
				<td colspan="3"><fmt:formatNumber value="${total}" /> 원</td>
			</tr>
			<tr>
				<th>배송비</th>
				<td colspan="3"><fmt:formatNumber value="${deleverFee}" /> 원</td>
			</tr>


			<c:if test="${payinfo['POINT_ENABLE'] == 'true'}">
			<!-- 포인트 결재 시작 -->
			<tr>
				<th>포인트사용금액</th>
				<td><input value='0' type="text" name='pointamount' id="pointamount" class="w50" onkeyup='paycalculate();' /></td>
				<th>가용포인트</th>
				<td><fmt:formatNumber value="${point}" />
					원</td>
			</tr>
			<!-- 포인트 결재 끝 -->
			</c:if>
			<tr>
				<th>최종결제 금액 </th>
				<td colspan="3"><input type="text" name='total_check' id="total_check" class="w50" readonly value='${totalSum}'>
					원</td>
			</tr>
		</tbody>
	</table>
	결제방식
	<table class="table_main w100p">
	<col width="130" />
	<col width="*" />
		<tbody>
			<tr>
				<th>결제방식</th>
				<td ><!-- 무통장 입금 시작 ----------->
					<input name="paytype" type="radio" value="online" checked="checked" onClick="SelectPayType(this)">
					무통장 입금
					<!-- 무통장 입금 끝 -------------->
					<c:if test="${payinfo['CARD_ENABLE'] == 'true'}">
					<!-- 신용카드 시작 --------------->
					<input name="paytype" type="radio" value="card" onClick="SelectPayType(this)">
					신용카드
					<!-- 신용카드 끝 ----------------->
					</c:if>
					
					<c:if test="${payinfo['PHONE_ENABLE'] == 'true'}">
					<!-- 핸드폰 결재 시작 --------------->
					<input name="paytype" type="radio" value="mobile" onClick="SelectPayType(this)">
					핸드폰 결제
					<!-- 핸드폰 결재 끝 ----------------->
					</c:if>
					
					<c:if test="${payinfo['AUTOBANKING_ENABLE'] == 'true'}">
					<!-- 실시간 계좌이체 시작 --------------->
					<input name="paytype" type="radio" value="autobank"  onClick="SelectPayType(this)">
					실시간 계좌 이체
					</c:if>
					<!-- 실시간 계좌이체 끝 ----------------->
				</td>
			</tr>

			<tr class="onlinepaytype">
				<th>입금은행</th>
				<td><select name='BankInfo' size='1'>
						<option value="">입금계좌선택</option>
<c:forEach items="${accountInfo}" var="current" varStatus="i">
	<option value='${current.bankname}|${current.account_no}|${current.account_owner}' > ${current.bankname} ${current.account_no} (예금주:${current.account_owner})</option>
</c:forEach>
<c:if test="${accountInfo == null}">
	<option>무통장 입금 계좌가 등록되지 않았습니다.</option>
</c:if>
					</select>
				</td>
			</tr>
			<tr class="onlinepaytype">
				<th>입금자명</th>
				<td><input name="Inputer" type="text" id="Inputer" value="${buyers.inputer}">
				</td>
			</tr>
		</tbody>
	</table>
	<div class="btn_box"></div>
		</form>
		<div class="agn_c"><span class="bull button btn_order"><a>주문하기</a></span></div>

</body>
</html>