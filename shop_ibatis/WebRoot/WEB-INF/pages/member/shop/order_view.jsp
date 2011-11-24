<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<html>
<head>
<title>주문내역 조회</title>
<script language=javascript src="<c:url value="/resources/js/jquery.plugins/jquery.validator.js"/>"></script>

<script language="javascript">
<!--
	function check_bil(v){
		if(v.value == "2"){//현금영수증 신청
			check_comp.style.display = "none";
			check_ind.style.display = "block";
		}else{
			check_comp.style.display = "block";
			check_ind.style.display = "none";
		}
	}

	function getDeliveryStatus(targeturl, arg, argvalue, method){//리스트에서 주문조회용
		var url = "./skinwiz/common/delivery.php?targeturl="+targeturl+"&arg="+arg+"&argvalue="+argvalue+"&method="+method;
		wizwindow(url,'DeliveryCheckWindow','');
		//사용법 <a href="getDeliveryStatus('<?=$d_inquire_url?>','<?=$d_code?>', '<?=$InvoiceNo?>', '<?=$d_method?>')">배송조회</a>
	}

	function checkField(f){//세금계산서 신청관련
		if(f.ptype[0].checked){
			if(f.cnum.value == ""){
				alert('사업자 번호를 입력해 주세요');
				f.cnum.focus();
				//return false;
			}else if(f.cname.value == ""){
				alert('회사명을 입력해 주세요');
				f.cname.focus();
				//return false;
			}else if(f.ceoname.value == ""){
				alert('대표자명을 입력해 주세요');
				f.ceoname.focus();
				//return false;
			}else if(f.cuptae.value == ""){
				alert('업태를 입력해 주세요');
				f.cuptae.focus();
				//return false;
			}else if(f.cupjong.value == ""){
				alert('업종을 입력해 주세요');
				f.cupjong.focus();
				//return false;
			}else if(f.caddress1.value == ""){
				alert('사업장주소를 입력해 주세요');
				f.caddress1.focus();
			//	return false;
			}else f.submit();
		}else if(f.ptype[1].checked){//현금영수증 발행신청
			if(f.cname1.value == ""){
				alert('요청자명을 입력해 주세요');
				f.cname1.focus();
				//return false;
			}else if(f.cachreceipt.value == ""){
				alert('현금영수증 번호를 입력해 주세요');
				f.cachreceipt.focus();
				//return false;
			}else f.submit();
		}else{
			alert('발행방식을 선택해 주세요');
			//return false;
		}


	}
//-->
</script>
</head>
<body>

<div class="navy">Home &gt; 주문 조회</div>
- 고객님께서 주문하신 상품의 상세 내역입니다. <img src="<?=$mem_skin_path?>/images/point_cart_01.gif" height="11">고객님께서
                  선택하신 상품내역입니다.
<!-- cart List Start -->
<page:applyDecorator page="/cart/cartList.ajax?type=3&orderid=${info.orderid}" name="planepanel"/>
<!-- cart List End -->
<div class="space15"></div>
배송지 정보
<table class="table_main w100p">
	<col width="130" />
	<col width="*" />
	<tbody>
	<tr>
		<th>입금인</th>
		<td><? ECHO "$List[Inputer]";?></td>
	</tr>
	<tr>
		<th>보내는
			분</th>
		<td>${info.sname}</td>
	</tr>
	<tr>
		<th>보내는
			분 E-mail</th>
		<td><a href='mailto:${info.semail}'>${info.semail}</a></td>
	</tr>
	<tr>
		<th>보내는
			분 전화번호</th>
		<td>${info.stel1}</td>
	</tr>
	<tr>
		<th>보내는
			분 휴대폰번호</th>
		<td>${info.stel2}</td>
	</tr>
	<tr>
		<th>수령인</th>
		<td>${info.rname}</td>
	</tr>
	<tr>
		<th>수령인주소</th>
		<td>(${r_zip1[0]}-${r_zip1[2]}) ${info.raddress1} ${info.raddress2}</td>
	</tr>
	<tr>
		<th>수령인 전화번호</th>
		<td>${info.rtel1}</td>
	</tr>
	<tr>
		<th>수령인 휴대폰번호</th>
		<td>${info.rtel2}</td>
	</tr>
	<tr>
		<th>희망배송일</th>
		<td><? ECHO number_format($List[ExpectDate]);?></td>
	</tr>
	<tr>
		<th>배송안내글</th>
		<td>${info.message}</td>
	</tr>
	</tbody>
</table>
주문정보
<table class="table_main w100p">
	<col width="130" />
	<col width="*" />
	<tbody>
	<tr>
		<th>주문번호</th>
		<td>${info.orderid}</td>
	</tr>
	<tr>
		<th>주문일자</th>
		<td><?=date("Y.m.d",$List[BuyDate])?></td>
	</tr>
	<tr>
		<th>거래상태</th>
		<td><?=$DeliveryStatusArr[$OrderStatus];?>
		</td>
	</tr>
	<tr>
		<th>택배사</th>
		<td><a href="javascript:getDeliveryStatus('<?=$d_inquire_url?>','<?=$d_code?>', '<?=$InvoiceNo?>', '<?=$d_method?>')">
			<?=$Deliverer?>
			</a> </td>
	</tr>
	<tr>
		<th>송장번호</th>
		<td><a href="javascript:getDeliveryStatus('<?=$d_inquire_url?>','<?=$d_code?>', '<?=$InvoiceNo?>', '<?=$d_method?>')">
			<?=$List[InvoiceNo]?>
			</a> </td>
	</tr>
	</tbody>
</table>
결제 정보
<table class="table_main w100p">
	<col width="130" />
	<col width="*" />
	<tbody>
	<tr>
		<th>결제방식</th>
		<td>${info.paymethod}</td>
	</tr>
<c:choose>
	<c:when test="${info.paymethod == 'all'}">
	<tr>
		<th>온라인입금</th>
		<td><fmt:formatNumber value="${info.amountonline}" />원</td>
	</tr>
	<tr>
		<th>입금계좌</th>
		<td>${info.bankinfo}
		</td>
	</tr>
	<tr>
		<th>신용카드</th>
		<td><fmt:formatNumber value="${info.amountpg}" />원</td>
	</tr>
	<tr>
		<th>포인트</th>
		<td><fmt:formatNumber value="${info.amountpoint}" />원</td>
	</tr>
	</c:when>
	<c:when test="${info.paymethod == 'online'}">
	<tr>
		<th>임금계좌</th>
		<td>${info.bankinfo}
		</td>
	</tr>
	</c:when>
</c:choose>
</tbody>
</table>
<?
if($OrderStatus >= 30 && $OrderStatus < 60 && $PayMethod == "online"){
//신청여부 확인
	$substr = "select * from wizBillcheck where oid = '$OrderID'";
	$subqry = $dbcon->_query($substr);
	$sublist = $dbcon->_fetch_array($subqry);
	if($sublist[0]){//이미 신청이 된 상태이면
	$uid		= $sublist["uid"];
	$mid		= $sublist["mid"];
	$oid		= $sublist["oid"];
	$ptype		= $sublist["ptype"];
	$cnum		= $sublist["cnum"];
	$cname		= $sublist["cname"];
	$ceoname	= $sublist["ceoname"];
	$cuptae		= $sublist["cuptae"];
	$cupjong	= $sublist["cupjong"];
	$cachreceipt= $sublist["cachreceipt"];
	$caddress1	= $sublist["caddress1"];
	$presult	= $sublist["presult"];
	$rdate		= $sublist["rdate"];
	$pdate		= $sublist["pdate"];
	switch($ptype){
		case "1":$ptypestr = "세금계산서";break;
		case "2":$ptypestr = "현금영수증";break;
	}
?>
세금계산서/현금영수증발행<br />
<img src="<?=$mem_skin_path?>/images/point_list_01.gif" width="21" height="9" />발행방식 :
<?=$ptypestr?>
<?
if($ptype == "1"){
?>
현재 발행이 신청되었습니다. 수정사항이 있을 경우 고객센터로 요청 바랍니다.
<table class="table_main w100p">
	<col width="130" />
	<col width="*" />
	<col width="130" />
	<col width="*" />
	<tbody>
	<tr>
		<th>요청일</th>
		<td colspan="3"><?=date("Y.m.d", $rdate)?>
		</td>
	</tr>
	<tr>
		<th>사업자번호</th>
		<td colspan="3"><?=$cnum?>
		</td>
	</tr>
	<tr>
		<th>회사명</th>
		<td><?=$cname?></td>
		<th><img src="<?=$mem_skin_path?>/images/point_list_01.gif" width="21" height="9" />대표자명</th>
		<td><?=$ceoname?></td>
	</tr>
	<tr>
		<th>업태</th>
		<td><?=$cuptae?></td>
		<th><img src="<?=$mem_skin_path?>/images/point_list_01.gif" width="21" height="9" />종목</th>
		<td><?=$cupjong?></td>
	</tr>
	<tr>
		<th>사업장주소</th>
		<td colspan="3"><?=$caddress1?></td>
	</tr>
	</tbody>
</table>
<?
}else if($ptype == "2"){
?>
현재 발행이 신청되었습니다. 수정사항이 있을 경우 고객센터로 요청 바랍니다.
<table style="display:none" id="check_ind" class="table_main w100p">
	<col width="130" />
	<col width="*" />
	<col width="130" />
	<col width="*" />
	<tbody>
	<tr>
		<th>요청일</th>
		<td colspan="3"><?=date("Y.m.d", $rdate)?></td>
	</tr>
	<tr>
		<th>요청자명</th>
		<td><?=$cname?></td>
		<th><img src="<?=$mem_skin_path?>/images/point_list_01.gif" width="21" height="9" />현금영수증번호</th>
		<td><?=$cachreceipt?></td>
	</tr>
	</tbody>
</table>
<?
}
?>
<?
	}else{//if($sublist){ 신청이 안된 상태이면
?>
세금계산서/현금영수증발행
<form id="form1" name="form1" method="post" action="" onsubmit="return checkField(this)">
	<input type="hidden" name="query" value="<?=$query?>" />
	<input type="hidden" name="OrderID" value="<?=$OrderID?>" />
	<input type="hidden" name="d_mode" value="receiptUpdate" />
	<img src="<?=$mem_skin_path?>/images/point_list_01.gif" width="21" height="9" />발행방식 :
	<input name="ptype" type="radio" id="radio" value="1" checked="checked" onclick="check_bil(this)">
	세금계산서
	<input type="radio" name="ptype" id="radio2" value="2" onclick="check_bil(this)">
	현금영수증
	<table style="display:block" id="check_comp" class="table_main w100p">
		<col width="130" />
		<col width="*" />
		<col width="130" />
		<col width="*" />
		<tbody>
		<tr>
			<th>사업자번호</th>
			<td colspan="3"><input type="text" name="cnum" id="cnum" />
			</td>
		</tr>
		<tr>
			<th>회사명</th>
			<td><input type="text" name="cname" id="cname" /></td>
			<th><img src="<?=$mem_skin_path?>/images/point_list_01.gif" width="21" height="9" />대표자명</th>
			<td><input type="text" name="ceoname" id="ceoname" /></td>
		</tr>
		<tr>
			<th>업태</th>
			<td><input type="text" name="cuptae" id="cuptae" /></td>
			<th><img src="<?=$mem_skin_path?>/images/point_list_01.gif" width="21" height="9" />종목</th>
			<td><input type="text" name="cupjong" id="cupjong" /></td>
		</tr>
		<tr>
			<th>사업장주소</th>
			<td colspan="3"><input name="caddress1" type="text" id="caddress1" /></td>
		</tr>
		</tbody>
	</table>
	<table style="display:none" id="check_ind" class="table_main w100p">
		<col width="130" />
		<col width="*" />
		<col width="130" />
		<col width="*" />
		<tbody>
		<tr>
			<th>요청자명</th>
			<td><input type="text" name="cname1" id="cname1" /></td>
			<th><img src="<?=$mem_skin_path?>/images/point_list_01.gif" width="21" height="9" />현금영수증번호</th>
			<td><input type="text" name="cachreceipt" id="cachreceipt" /></td>
		</tr>
		</tbody>
	</table>
	<div class="btn_box">
		<input type="submit" name="button" id="button" value="요청" />
	</div>
</form>
<?
	}//if($sublist){//이미 신청이 된 상태이면
}
?>
<div class="btn_box">
	<!-- <a href='#' onclick='jvascript:self.close()'><img src="<?=$mem_skin_path?>/images/but_close.gif"></a> -->
	<a href='#' onclick='jvascript:self.print()'><img src="<?=$mem_skin_path?>/images/but_print.gif"></a></div>
<br />
</body>
</html>