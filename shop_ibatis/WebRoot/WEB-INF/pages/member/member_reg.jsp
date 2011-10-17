<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.member-resources"/>
<html>
<head>
<title>회원가입</title>
</head>
<body>
<script language=javascript src="<c:url value="/js/jquery.plugins/jquery.validator.js"/>"></script>

<script language="javascript">
<!--
$(function(){
	$(".btn_cancel").click(function(){
		history.go(-1);
	});

	$(".btn_confirm").click(function(){
		if($('#memberForm').formvalidate()){
			$("#memberForm").submit();
		}
	});

	$(".btn_id_check").click(function(){
		winobject = wizwindow("${pageContext.request.contextPath}/member/idCheck.blank","","scrollbars=no,resizable=yes,width=300,height=200");
		winobject.focus();
	});
	$("#btn_find_zipcode").click(function(){
		wizwindow("${pageContext.request.contextPath}/util/FindZip.blank?form=memberForm&zip1=zip1_1&zip2=zip1_2&firstaddress=address1&secondaddress=address2","ZipWin","width=490,height=250,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no");
	});

	$("#sel_email").change(function(){
		switch($(this).val()){
			case "etc":
				$("#email_2").val("");
				break;
			default:
				$("#email_2").val($(this).val());
				break;

		}
	})
});

////////////////////////////////////////////////////////////////////////////////
function CompanyCheckField()
{
var f=document.FrmUserInfo;
// 사업자등록증 책크 시작
	if(!chkWorkNum(f.companynum1.value, f.companynum2.value, f.companynum3.value)){
			f.companynum1.focus();
			return false;
			}
	return true;
}




function FillBirthDay()
{
var f=document.FrmUserInfo;

	if ( ! TypeCheck(f.jumin1.value, NUM)) {
	alert("주민등록 번호에 잘못된 문자가 있습니다. ");
	f.jumin1.focus();
	return false;
	}

	num = f.jumin1.value;

	mm = parseInt(num.substring(2,4), 10);
	dd = parseInt(num.substring(4,6), 10);

	if ((mm < 1) || (mm > 12)) {
	alert ("주민등록 번호 앞자리가 잘못되었습니다.");
	return false;
	}

	if ((dd < 1) || (dd > 31)) {
	alert ("주민등록 번호 앞자리가 잘못되었습니다.");
	return false;
	}

	f.birthyy.value = "19" + num.substring(0,2);
	f.birthmm.value = num.substring(2,4);
	f.birthdd.value = num.substring(4,6);


	f.jumin2.focus();
}



function OpenZipcode1(){
	wizwindow("./util/zipcode/zipcode.php?form=memberForm&zip1=zip2_1&zip2=zip2_2&firstaddress=address3&secondaddress=address4","ZipWin","width=490,height=250,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no");
}

function IDcheck()
{
	var f=document.FrmUserInfo;
	winobject = window.open("","","scrollbars=no,resizable=yes,width=300,height=200");
	winobject.document.location = "./wizmember/MemberSkin/ID_EXISTS.php?id=" + f.id.value;
	winobject.focus();
}

function nickNamecheck()
{
	var f=document.FrmUserInfo;
	winobject = window.open("","","scrollbars=no,resizable=yes,width=380,height=300");
	winobject.document.location = "./wizmember/MemberSkin/NICKNAME_EXISTS.php?nickname=" + f.nickname.value;
	winobject.focus();
}

function Jumincheck()
{
	var f=document.FrmUserInfo;
		if(!IsJuminChk(f.jumin1.value, f.jumin2.value)){
		f.jumin1.focus();
		return false;
		}
	winobject = window.open("","","scrollbars=no,resizable=yes,width=1,height=1");
	winobject.document.location = "./wizmember/MemberSkin/Jumin_EXISTS.php?jumin1=" + f.jumin1.value+"&jumin2=" + f.jumin2.value;
	winobject.focus();
}
function Reqercheck()
{
var f=document.FrmUserInfo;
if (f.recid.value == "") {
alert("추천인 ID를 입력해 주세요. ");
return false;
}

winobject = window.open("","","scrollbars=no,resizable=yes,width=100,height=100");
winobject.document.location = "./wizmember/MemberSkin/REQER_EXISTS.php?id=" + f.recid.value;
winobject.focus();
}
//-->
</script>
회원가입 페이지 입니다.

<form action="${pageContext.request.contextPath}/member/memberReg_x.do"  name="memberForm" id="memberForm" method="post">
<input type="hidden" name="tid" value="${member.tid}" />
<input type="hidden" name="idchk_result" id="idchk_result" value="0" />

	<table class="table_main w100p">
		<col width="130" />
	<col width="*" />
	<tbody>
		<tr>
			<th>* 회원 ID </th>
			<td><input type="text" name="user_id" id="user_id" value="${member.user_id}" maxlength="9" readonly="readonly" class="btn_id_check required check_engnum min6 max15" msg="영문및 숫자만 가능합니다." />
				<span class="button bull btn_id_check"><a>아이디유효성체크</a></span>영(소문자)/숫자 6~15자 ID중복검사

				</td>
		</tr>
		<tr>
			<th>* 비밀번호</th>
			<td align='left'><input type="password" name="user_passwd" value="${member.user_passwd}" class="required text_grp" group="text_grp" msg="비밀번호를 정확하게 입력해주세요" />
			</td>
		</tr>
		<tr>
			<th>* 비밀번호 확인</th>
			<td align='left'><input name="cpasswd" type="password" id="cpasswd" maxlength="30" class="text_grp">
			</td>
		</tr>
		<tr>
			<th>* 이름</th>
			<td align='left'><input type="text" name="user_name" value="${member.user_name}" class="required" msg="이름을 입력해주세요" />
			</td>
		</tr>

		<tr>
			<th>성 별</th>
			<td align='left'><input name="user_gender" type="radio" value="M"<c:if test="${member.user_gender != 'W'}"> checked</c:if>>
				남자 &nbsp;
				<input type="radio" name="user_gender" value="W"<c:if test="${member.user_gender == 'W'}"> checked</c:if>>
				여자 </td>
		</tr>

		<tr>
			<th>* 주민등록번호</th>
			<td align='left'><input name="user_jumin1" type="text" id="jumin1" onKeyup=moveFocus(6,this,this.form.jumin2); value="${member.user_jumin1}" maxlength="6" onChange="setBirthday();" class="w100 required check_jumin1" msg="주민번호를 입력해 주세요">
				-
				<input name="user_jumin2" type="password" id="jumin2" value="${member.user_jumin2}" maxlength="7" onChange="setGender();" class="w100 required check_jumin2">
			</td>
		</tr>

		<tr>
			<th>생년월일</th>
			<td align='left'>
				<select name="user_birthyy" id="birthyy">
					<option value=''>년도</option>
				<c:forEach var="birthyy" begin="1950" end="${curYear}">
					<option value='${birthyy}'<c:if test="${user_birthdate[0] == birthyy}"> selected</c:if>>${birthyy}</option>
				</c:forEach>

				</select>
				년
				<select name="user_birthmm" id="birthmm">
					<option value=''>월</option>
				<c:forEach var="birthmm" begin="1" end="12">
					<option value='${birthmm}'<c:if test="${user_birthdate[1] == birthmm}"> selected</c:if>>${birthmm}</option>
				</c:forEach>
				</select>
				월
				<select name="user_birthdd" id="birthdd">
					<option value=''>일</option>
				<c:forEach var="birthdd" begin="1" end="31">
					<option value='${birthdd}'<c:if test="${user_birthdate[2] == birthdd}"> selected</c:if>>${birthdd}</option>
				</c:forEach>
				</select>
				일&nbsp;&nbsp;&nbsp;
				<input type="radio" name="user_birtytype" value="0"<c:if test="${member.user_birthtype != '1'}"> checked</c:if>>
				양력
				<input type="radio" name="user_birtytype" value="1"<c:if test="${member.user_birthtype == '1'}"> checked</c:if>>
				음력 </td>
		</tr>



		<tr>
			<th>자택주소</th>
			<td><input type="text" name="zip1_1" id="zip1_1" maxlength="3" readonly="readonly" class="w30 required" msg="우편번호를 입력해주세요" value="${user_zip1[0]}">
				-
				<input name="zip1_2" type="text" id="zip1_2" maxlength="3" readonly="readonly" class="w30 required" msg="우편번호를 입력해주세요" value="${user_zip1[1]}">
				<span class="button bull hand"><a id="btn_find_zipcode" >우편번호찾기</a></span> <br />
				<input name=address1 type="text" id="address1" readonly="readonly" class="w300 required" msg="주소를 입력해주세요" value="${member.user_address1}">
				<br />
				<input name=address2 type="text" id="address2" class="w300 required" msg="상세주소를 입력해주세요" value="${member.user_address2}">
				(상세주소)<br />
				정확하게 기입해주세요 </td>
		</tr>

		<tr>
			<th>* 전화번호</th>
			<td align='left'>
					  <select name="tel1_1" id="telephone1" class="required" msg="전화번호를 입력해주세요">
				<c:forEach var="i" items="${PhoneNum}">
					<option value='${i}'<c:if test="${user_tel1[0] == i}"> selected</c:if>>${i}</option>
				</c:forEach>
          </select>
				-
				<input name="tel1_2" type="text" id="tel1_2" maxlength="4" class="w30 required" msg="전화번호를 입력해주세요" value="${user_tel1[1]}" />
				-
				<input name="tel1_3" type="text" id="tel1_3" maxlength="4" class="w30 required" msg="전화번호를 입력해주세요" value="${user_tel1[2]}" /></td>
		</tr>

		<tr>
			<th>이동전화</th>
			<td align='left'>
			<select name="tel2_1" id="select2">
				<c:forEach var="i" items="${MobileNum}">
					<option value='${i}'<c:if test="${user_tel2[0] == i}"> selected</c:if>>${i}</option>
				</c:forEach>

          </select>

				-
				<input name="tel2_2" type="text" id="tel2_2" maxlength=4 class="w30" value="${user_tel2[1]}">
				-
				<input name="tel2_3" type="text" id="tel2_3" maxlength=4 class="w30" value="${user_tel2[1]}">
			</td>
		</tr>

		<tr>
			<th>* 전자우편</th>
			<td align='left'><input name=email_1 type="text" id="email_1" class="w100 required check_email" groupemail="email_grp" msg="이메일을 입력해주세요"  value="${user_email[0]}" />
				@
				<input name=email_2 type="text" id="email_2" class="w100 email_grp" value="${user_email[1]}" />
				<select name="tmpmail" id="sel_email">
					<option value=''>선택해주세요</option>
				<c:forEach var="i" items="${MailAddress}">
					<option value='${i.value}'>${i.value}</option>
				</c:forEach>
					<option value='etc'>기타</option>
				</select></td>
		</tr>
		</tbody>
	</table>
	<div class="space15"></div>
	<div class="btn_box">
		<span class="btn_confirm button bull"><a>확인</a></span> <span class="btn_cancel button bull"><a>취소</a></span>
	</div>
</form>
</body>
</html>