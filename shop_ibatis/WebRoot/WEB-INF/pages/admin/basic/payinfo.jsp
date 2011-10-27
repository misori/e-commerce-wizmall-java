<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Pay Info</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tree.css"/>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	loadbankList();

	$("#btn_save").click(function(){
		$("#s_form").submit();
	});
});

function loadbankList(){
	$("#bankListHtml").load("${pageContext.request.contextPath}/admin/basic/payinfo_bank.ajax", function(){

	});
}
//-->
</script>

</head>
<body>

<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>몰
			결제환경 설정</legend>
			<div class="notice">[note]</div>
			<div class="comment">몰의 결제 환경설정을 하실 수 있습니다. 결제방법을 원하시면 각각의 결제방법 앞에 책크해 주시면됩니다.<br />
				결제종류 : 온라인무통장, 신용카드, 포인트(적립금), 다중결제(온라인+신용카드+포인트)<br />
				배송비선택 및 VAT. 설정 그리고 카테고리별 카드가 차등적용등이 가능합니다.<br />
				기본제공외의 결제모듈에 관해서는 업체와 상담하시기 바랍니다. </div>
			</fieldset>
			<div class="space20"></div>
			<form action='${pageContext.request.contextPath}/admin/basic/payinfo_x.do' method="post" id="s_form">

				다음의 결제관련 설정을 정확히 지정하여
							주십시오.
				<table class="table_main">
					<tr>
						<td width="96" bgColor="#f2f2f2"><br />
							<br />
							결제형식 설정</td>
						<td><input type="checkbox" value="true" name="ONLINE_ENABLE"<c:if test="${payinfo['ONLINE_ENABLE'] == 'true'}"> checked</c:if>  />
							온라인무통장 결제(기본)
							<div id="bankListHtml"></div>
							<br />
							<input type="checkbox" value="true" name="CARD_ENABLE"<c:if test="${payinfo['CARD_ENABLE'] == 'true'}"> checked</c:if> />
							신용카드 결제<br />
							* 신용카드 결제를 사용할 시
							<table class="table_sub">
								<tr>
									<th>결제시스템 업체</th>
									<td><select name="CARD_PACK">
<?
$skskin_dir = opendir("../skinwiz/cardmodule");
        while($skskdir = readdir($skskin_dir)) {
                if(($skskdir != ".") && ($skskdir != "..")) {
                        if($cfg["pay"]["CARD_PACK"] == "$skskdir") {
                                echo "<option value=\"$skskdir\" selected>$skskdir</option>\n";
                        }
                        else {
                                echo "<option value=\"$skskdir\">$skskdir</option>\n";
                        }
                }
        }
closedir($skskin_dir);
?>
										</select>
									</td>
								</tr>
								<tr>
									<th>상점아이디</th>
									<td><input value='${payinfo["CARD_ID"]}' name="CARD_ID"></td>
								</tr>
								<tr>
									<th>상점패스워드</th>
									<td><input value='${payinfo["CARD_PASS"]}' name="CARD_PASS"></td>
								</tr>
								<tr>
									<th>신용카드
										최소 제품구매액</th>
									<td><input value='${payinfo["CARD_ENABLE_MONEY"]}' name="CARD_ENABLE_MONEY" class="w50 agn_r">
										원 이상</td>
								</tr>
							</table>
							<br />
							<table class="table_sub">
								<tr>
									<th> <input type="checkbox" value="true" name="PHONE_ENABLE"<c:if test="${payinfo['PHONE_ENABLE'] == 'true'}"> checked</c:if> />
										핸드폰결제</th>
								</tr>

								<tr>
									<th> <input type="checkbox" value="true" name="AUTOBANKING_ENABLE"<c:if test="${payinfo['AUTOBANKING_ENABLE'] == 'true'}"> checked</c:if> />
										실시간자동이체</th>
								</tr>
								<tr>
									<th> <input type="checkbox" value="true" name="POINT_ENABLE"<c:if test="${payinfo['POINT_ENABLE'] == 'true'}"> checked</c:if> />
										포인트 결제 </th>
								</tr>
								<tr>
									<td height=59>&nbsp;*
										최소 구매 허용 포인트 &nbsp;
										<input name="POINT_ENABLE_MONEY" value='${payinfo["POINT_ENABLE_MONEY"]}' class="w50 agn_r">
										포인트 이상<br />
										&nbsp;* , (컴마) 표시 없이 숫자로만 입력해 주시기 바랍니다. </td>
								</tr>
							</table>
							<br />
							배송비
							<input type="text" name="TACKBAE_MONEY" value='${payinfo["TACKBAE_MONEY"]}' class="w50 agn_r" />
							원(,(컴마)없이 숫자로만 입력)<br />
							<input type="radio" name="TACKBAE_ALL" value="DISABLE"<c:if test="${payinfo['TACKBAE_ALL'] == 'DISABLE'}"> checked</c:if> />
							배송비 미 적용.<br />
							<input type="radio" name="TACKBAE_ALL" value="ENABLE"<c:if test="${payinfo['TACKBAE_ALL'] == 'ENABLE'}"> checked</c:if> />
							상품구매액이
							<input type="text" name="TACKBAE_CUTLINE" value='${payinfo["TACKBAE_CUTLINE"]}'  class="w50 agn_r" />
							미만일 경우 배송비 적용.<br />
							<input type="radio" name="TACKBAE_ALL" value="ALL"<c:if test="${payinfo['TACKBAE_ALL'] == 'ALL'}"> checked</c:if> />
							구매액에 관계없이 배송비적용 <br />
							<input type="radio" name="TACKBAE_ALL" value="PER"<c:if test="${payinfo['TACKBAE_ALL'] == 'PER'}"> checked</c:if> />
							제품(수)당 배송비적용 <br />



							<div class="agn_c">
							<span class="button bull" id="btn_save"><a>변경</a></span>
							</div></td>
					</tr>
				</table>
			</form></td>
	</tr>
</table>
</body>
</html>