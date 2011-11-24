<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>회원상세정보</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jquery-ui-1.7.2/js/jquery-ui-1.7.2.custom.min.js"/>"></script>

		<link rel="stylesheet" href="<c:url value="/css/base.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/css/admin.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.css"/>" type="text/css" />

		<script language=javascript src="<c:url value="/js/jquery.plugins/jquery.validator.js"/>"></script>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	$(".btn_save").click(function(){
		$("#s_form").submit();
	});
});

function insertpoint(type){
//id, point, 41, $content
	var userid = '<?=$list[mid]?>';
	if(type == "exp"){
		var point = document.getElementById("exp_value").value;
		var content = document.getElementById("exp_cont").value;
	}else if(type == "point"){
		var point = document.getElementById("point_value").value;
		var content = document.getElementById("point_cont").value;
	}

	$.post("../../lib/ajax.admin.php", {smode:"in_point",userid:userid,point:point,content:content,type:type}, function (data){
		//alert(data);
		var result=data.split("|");
		//echo $type."|".$point;
		if(result[0] == "exp"){
			document.getElementById("exp_str").innerHTML = SetComma1(parseInt(RemoveComma(document.getElementById("exp_str").innerHTML)) + parseInt(result[1]));
		}else if(result[0] == "point"){
			document.getElementById("point_str").innerHTML = SetComma1(parseInt(RemoveComma(document.getElementById("point_str").innerHTML)) + parseInt(result[1]));
		}
	});
}
//-->
</script>
		</head>
<body>
<form action='' method="post" id="s_form">
	<input type="hidden" name="query" value="update">
	<input type="hidden" name='oldid' value='<?=$list[mid]?>'>
	회원가입 상세정보입니다.
	<table class="table_popmain">
		<col width="120" />
		<col width="*" />
		<col width="120" />
		<col width="*" />
		<tr>
			<th>* 아이디</th>
			<td colspan="3"><input name="id" type="text"  id="id" value="${info.user_id}" readonly></td>
		</tr>
		<tr>
			<th>* 패스워드</th>
			<td colspan="3"><input name="passwd" type="text" id="passwd" value="${info.user_passwd}">
				<input name="ini_pwd" type="checkbox" id="ini_pwd" value="1">
				패스워드 초기화</td>
		</tr>
		<tr>
			<th>* 이 름</th>
			<td><input name="name" type="text" id="name" value="${info.user_name}"></td>
			<th>* 메일수신여부</th>
			<td><input type="radio" name="mailreceive" value="1">
				받음
				<input type="radio" name="mailreceive" value="0">
				받지않음 </td>
		</tr>
		<tr>
			<th>* 주민등록번호</th>
			<td colspan="3"><input name="jumin1" id="jumin1" value="${info.user_jumin1}" class="w50">
				-
				<input maxLength="7" name="jumin2" value="${info.user_jumin2}" class="w50">
				<input name="ini_jumin" type="checkbox" id="ini_jumin" value="1">
				주빈번호 초기화</td>
		</tr>
		<tr>
			<th>* 생년월일</th>
			<td><input type="text" name="birthy" value="${user_birthdate[0]}" class="w30">
				년
				<input type="text" name="birthm" value="${user_birthdate[1]}" class="w30">
				월
				<input type="text" name="birthd" value="${user_birthdate[2]}" class="w30">
				일
				<select name="birthtype" size="1">
					<option value="0"<c:if test="${info.user_birthtype != '1'}"> checked</c:if>>양력</option>
					<option value="1"<c:if test="${info.user_birthtype == '1'}"> checked</c:if>>음력</option>
				</select>
			</td>
			<th>* 성별구분</th>
			<td><input type="radio" name='gender' value='M'<c:if test="${info.user_gender != 'W'}"> checked</c:if>>
				남자
				<input type="radio" name='gender' value='W'<c:if test="${info.user_gender == 'W'}"> checked</c:if>>
				여자 </td>
		</tr>
		<tr>
			<th>* E-mail</th>
			<td colspan="3"><input name="email" type="text" id="email" value='${info.user_email}'>
				<span class="button bull"><a href="mailto:${info.user_email}">메일보내기</a></span></td>
		</tr>
		<tr>
			<th>홈페이지</th>
			<td colspan="3"><input name="url" type="text" value=''></td>
		</tr>
		<tr>
			<th>* 자택 전화번호</th>
			<td colspan="3"><input name="tel1" type="text" value='${info.user_tel1}'>
				&quot;-&quot;포함 입력 </td>
		</tr>
		<tr>
			<th>핸드폰</th>
			<td colspan="3"><input name="tel2" type="text" value='${info.user_tel2}'>
				&quot;-&quot;포함 입력 </td>
		</tr>
		<tr>
			<th>* 자택
				우편번호</th>
			<td colspan="3"><input name="zip1_1" value='${user_zip1[0]}' class="w30">
				-
				<input name="zip1_2" value='${user_zip1[1]}' class="w30">
			</td>
		</tr>
		<tr>
			<th>* 동(면)이상주소</th>
			<td colspan="3"><input name="address1" type="text" id="address1" value='${info.user_address1}' class="w300"></td>
		</tr>
		<tr>
			<th>* 나머지
				주소</th>
			<td colspan="3"><input name="address2" type="text" id="address2" value='${info.user_address1}' class="w300"></td>
		</tr>
		<tr>
			<th>(직장)학교명</th>
			<td colspan="3"><input type="text" name="company" value="">
			</td>
		</tr>
		<tr>
			<th>사업자등록번호</th>
			<td colspan="3"><input name="companynum" type="text" id="companynum" value="">
			</td>
		</tr>
		<tr>
			<th>* 회원등급</th>
			<td><select name='mgrade'>
<c:forEach var="i" items="${MemberGrade}">
					<option value='${i.key}'<c:if test="${info.user_grade == i.key}"> selected</c:if>>${i.value}</option>
</c:forEach>
				</select></td>
			<th>회원레벨</th>
			<td></td>
		</tr>
		<tr>
			<th>로그인</th>
			<td colspan="3"> ${info.user_login_num} 회 </td>
		</tr>
	</table>
	<table class="table_popmain">
		<col width="120" />
		<col width="*" />
		<col width="120" />
		<col width="*" />
		<tr>
			<th>경험치</th>
			<td><a href="javascript:getpointInfo('${info.user_id}', 1)"></a></td>
			<th>머니</th>
			<td><a href="javascript:getpointInfo('${info.user_id}', 1)"><span id="point_str"></span> POINT</a></td>
		</tr>
		<tr>
			<th>사유</th>
			<td><input type="text" name="exp_cont" id="exp_cont"></td>
			<th>사유</th>
			<td><input type="text" name="point_cont" id="point_cont"></td>
		</tr>
		<tr>
			<th>경험치추가</th>
			<td><input name="exp_value" type="text" id="exp_value" class="w30 agn_r">
				<input type="button" name="button" id="button" value="확인" onClick="insertpoint('exp')"></td>
			<th>머니추가</th>
			<td><input name="point_value" type="text" id="point_value" class="w30 agn_r">
				<input type="button" name="button" id="button" value="확인" onClick="insertpoint('point')"></td>
		</tr>
	</table>
	<br />
	<div class="btn_box">
	<span class="btn_save button bull"><a>수정</a></span>
	<span class="button bull"><a href="javascript:window.print()">출력</a></span>
	<span class="button bull"><a href="javascript:top.close();">창닫기</a></span>
</form>
</body>
</html>