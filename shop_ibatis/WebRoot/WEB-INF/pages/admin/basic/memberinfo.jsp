<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Member Info</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tree.css"/>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	$("#btn_save").click(function(){
		$("#s_form").submit();
	});
});


//-->
</script>

</head>
<body>

<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>회원가입 옵션 관리</legend>
			<div class="notice">[note]</div>
			<div class="comment"> 회원가입시 다양한 기능들을 적용하실 수 있습니다.<br />
				아래 스킨들은 default 스킨일 경우 100% 활용가능하며 기타 스킨은 프로그램에 따라
				적용되지 않을 수도 있습니다. </div>
			</fieldset>
			<div class="space20"></div>
			<form action='${pageContext.request.contextPath}/admin/basic/memberInfo_x.do' method="post" id="s_form">
				<table class="table_main">
					<!-------------- 메인화면 스킨 시작 ----------------------------------------------------------------------------------->
					<tr>
						<th colspan="2">회원가입옵션</th>
					</tr>
					<tr>
						<th>필수</th>
						<td><input disabled type="checkbox" CHECKED value="checkbox" name="checkbox" />
							회원아이디&nbsp;
							<input disabled type="checkbox" CHECKED value="checkbox" name="checkbox2" />
							비밀번호
							<input disabled type="checkbox" CHECKED value="checkbox" name="checkbox3" />
							이름
							<input disabled type="checkbox" CHECKED value="checkbox" name="checkbox4" />
							주민등록번호
							<input disabled type="checkbox" CHECKED value="checkbox" name="checkbox5" />
							전화번호
							<input disabled type="checkbox" CHECKED value="checkbox" name="checkbox6" />
							전자우편 </td>
					</tr>
					<tr>
						<th>선택</th>
						<td><input 
                        type="checkbox" value="true" name="EGender"<c:if test="${memberinfo['EGender'] == 'true'}"> checked</c:if> />
							성별(
							<input 
                        type="checkbox" value="true" name="CGender"<c:if test="${memberinfo['CGender'] == 'true'}"> checked</c:if> />
							)
							<input type="checkbox" value="true" name="ECompany"<c:if test="${memberinfo['ECompany'] == 'true'}"> checked</c:if> />
							회사명(
							<input 
                        type="checkbox" value="true" name="CCompany"<c:if test="${memberinfo['CCompany'] == 'true'}"> checked</c:if> />
							)
							<input 
                        type="checkbox" value="true" name="ECompnum"<c:if test="${memberinfo['ECompnum'] == 'true'}"> checked</c:if> />
							사업자등록번호(
							<input type="checkbox" value="true" name="CCompnum"<c:if test="${memberinfo['CCompnum'] == 'true'}"> checked</c:if> />
							)
							<input type="checkbox" value="true" name="ETel2"<c:if test="${memberinfo['ETel2'] == 'true'}"> checked</c:if> />
							이동전화(
							<input type="checkbox" value="true" name="CTel2"<c:if test="${memberinfo['CTel2'] == 'true'}"> checked</c:if> />
							) <br />
							<input type="checkbox" value="true" name="EMailReceive"<c:if test="${memberinfo['EMailReceive'] == 'true'}"> checked</c:if> />
							메일수신(
							<input type="checkbox" value="true" name="CMailReceive"<c:if test="${memberinfo['CMailReceive'] == 'true'}"> checked</c:if> />
							)<br />
							<input type="checkbox" value="true" name="EBirthDay"<c:if test="${memberinfo['EBirthDay'] == 'true'}"> checked</c:if> />
							생년월일(
							<input type="checkbox" value="true" name="CBirthDay"<c:if test="${memberinfo['CBirthDay'] == 'true'}"> checked</c:if> />
							)
							<input type="checkbox" value="true" name="EMarrStatus"<c:if test="${memberinfo['EMarrStatus'] == 'true'}"> checked</c:if> />
							결혼여부(
							<input type="checkbox" value="true" name="CMarrStatus"<c:if test="${memberinfo['CMarrStatus'] == 'true'}"> checked</c:if> />
							)
							<input type="checkbox" value="true" name="EJob"<c:if test="${memberinfo['EJob'] == 'true'}"> checked</c:if> />
							직업(
							<input type="checkbox" value="true" name="CJob"<c:if test="${memberinfo['CJob'] == 'true'}"> checked</c:if> />
							)
							<input type="checkbox" value="true" name="EScholarship"<c:if test="${memberinfo['EScholarship'] == 'true'}"> checked</c:if> />
							학력(
							<input type="checkbox" value="true" name="CScholarship"<c:if test="${memberinfo['CScholarship'] == 'true'}"> checked</c:if> />
							)
							<input type="checkbox" value="true" name="EAddress3"<c:if test="${memberinfo['EAddress3'] == 'true'}"> checked</c:if> />
							직장주소(
							<input type="checkbox" value="true" name="CAddress3"<c:if test="${memberinfo['CAddress3'] == 'true'}"> checked</c:if> />
							)<br />
							<input type="checkbox" value="true" name="ERecID"<c:if test="${memberinfo['ERecID'] == 'true'}"> checked</c:if> />
							추천인(
							<input type="checkbox" value="true" name="CRecID"<c:if test="${memberinfo['CRecID'] == 'true'}"> checked</c:if> />
							) <br />
							<span class="orange">* (
							<input type="checkbox" CHECKED value="true" name="checkbox7" />
							) 책크박스 책크시 필수 입력으로 전환됩니다.</span> </td>
					</tr>
					<tr>
						<th>실명인증서비스</th>
						<td>
							<select name="realnameModule">
							<c:forEach var="i" items="${modulefolder}"> 
								<option value="${i}"<c:if test="${memberinfo.realnameModule == i}"> selected="selected"</c:if>>${i}</option>
							</c:forEach>
							</select>						
						
							인증아이디 :
							<input name="realnameID" id="realnameID" value="${memberinfo['realnameID']}" class="w50 agn_r" />
							인증패스워드:
							<input name="realnamePWD" id="realnamePWD" value="${memberinfo['realnamePWD']}" class="w50 agn_r" />
						</td>
					</tr>
					<tr>
						<th>상세설정1</th>
						<td><input name="EGrantSta" type="radio" value="03"<c:if test="${memberinfo['EGrantSta'] == '03'}"> checked</c:if> />
							회원가입시 인증
							<input type="radio" name="EGrantSta" value="04"<c:if test="${memberinfo['EGrantSta'] == '04'}"> checked</c:if> />
							회원가입 후 관리자 인증 </td>
					</tr>
					<tr>
						<th>상세설정2</th>
						<td><input name="INCLUDE_MALL_SKIN" type="radio" value="yes"<c:if test="${memberinfo['INCLUDE_MALL_SKIN'] == 'yes'}"> checked</c:if> />
							몰인클루드
							<input type="radio" name="INCLUDE_MALL_SKIN" value="no"<c:if test="${memberinfo['INCLUDE_MALL_SKIN'] == 'no'}"> checked</c:if> />
							몰인클루드 하지 않음</td>
					</tr>
					<tr>
						<th>상세설정3</th>
						<td><input type="radio" name="SendMail" value="yes"<c:if test="${memberinfo['SendMail'] == 'yes'}"> checked</c:if> />
							회원가입메일발송
							<input type="radio" name="SendMail" value="no"<c:if test="${memberinfo['SendMail'] == 'no'}"> checked</c:if> />
							회원가입시 메일발송안함</td>
					</tr>
					<tr>
						<th>포인트설정1</th>
						<td>회원가입시 포인트 설정 :
							<input value="${memberinfo['EPoint']}" name="EPoint" class="w50 agn_r" />
							포인트 </td>
					</tr>
					<tr>
						<th>포인트설정2</th>
						<td>회원추천시 포인트 설정 :
							<input name="RPoint" value="${memberinfo['RPoint']}" class="w50 agn_r" />
							포인트</td>
					</tr>
					<tr>
						<th>포인트설정3</th>
						<td>로그인 포인트
							설정 :
							<input name="LPoint" value="${memberinfo['LPoint']}" class="w50 agn_r" />
							포인트(1일 1회) </td>
					</tr>
				</table>
				<div class="btn_box"><span class="button bull" id="btn_save"><a>적용</a></span> </div>
			</form></td>
	</tr>
</table>
</body>
</html>