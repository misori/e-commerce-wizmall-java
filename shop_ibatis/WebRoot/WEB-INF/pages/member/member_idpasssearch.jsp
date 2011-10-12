<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>회원 ID, Pass Search</title>
<script language=javascript src="${pageContext.request.contextPath}/js/jquery.plugins/jquery.validator-1.0.1.js"></script>
<script>
$(function(){
	$(".btn_find_id").click(function(){
		if($('#idsearch_form').formvalidate()){
			$("#idsearch_form").submit();
		}
	});

	$(".btn_find_pass").click(function(){
		if($('#passsearch_form').formvalidate()){
			$("#passsearch_form").submit();
		}
	});
});
</script>
</head>
<body>

<div class="navy">Home &gt; 아이디 및 비밀번호 찾기</div>
<div class="space15"></div>
- 회원님!! 아이디 또는 비밀번호를 
            잊으셨나요? 입력하신 후 [확인]단추를 누르세요
<br />
<c:choose> 
    <c:when test="${msg_idsearch == null}"> 
	
	ID(아이디)를 잊으셨나요?<br />
	<font color='#FF9900'>이름과 <font color='#FF9900'>주민등록번호를 입력하신 후 <font color='#FF9900'>&quot;찾기&quot;버튼을 눌러주세요
    </c:when> 
    <c:otherwise> 
    ${msg_idsearch}
    </c:otherwise> 
</c:choose> 
<form action='' method=post name="idsearch_form" id="idsearch_form">
	<input type="hidden" name="smode" value="idsearch">
	<input type="hidden" name="output" value="print">
	<dl class="dl_gen">
		<dt>
			<table class="table_main" style="width:400px">
			<col width="120px" />
			<col width="*" />
				<tr>
					<th>이름</th>
					<td><input name="name" type="text" tabindex="1" class="required" msg="이름을 입력하세요" />
					</td>
				</tr>
				<tr>
					<th>주민등록번호</th>
					<td><input name="juminno1" type="text" tabindex="2" maxlength="6" onkeyup="moveFocus(6,this,document.idsearch_form.juminno2)" class="w50 required" msg="주민번호를 입력하세요">
						-
						<input name="juminno2" type="text" tabindex="3" maxlength="7" class="w50 required" msg="주민번호를 입력하세요"></td>
				</tr>
			</table>
		</dt>
		<dd style="padding-left:20px">
			<span class="button bull btn_find_id"><a>아이디 찾기</a></span>
		</dd>
	</dl>
</form>
<div class="space20"></div>
<c:choose> 
    <c:when test="${msg_passsearch == null}"> 
		회원님의 비밀번호를 잊으셨나요?<br />
		<font color='#FF9900'>아이디와 <font color='#FF9900'>주민등록번호를 입력하신 후 <font color='#FF9900'>&quot;찾기&quot;버튼을 눌러주세요
    </c:when> 
    <c:otherwise> 
    ${msg_passsearch}
    </c:otherwise> 
</c:choose> 
<form action='' method=post name="passsearch_form" id="passsearch_form">
	<input type="hidden" name="smode" value="passsearch">
	<input type="hidden" name="output" value="mail">
	<dl class="dl_gen">
		<dt>
			<table class="table_main" style="width:400px">
						<col width="120px" />
			<col width="*" />
				<tr>
					<th>아이디</th>
					<td><input name="id" type="text" tabindex="5" class="required" msg="아이디를 입력하세요"></td>
				</tr>
				<tr>
					<th>주민등록번호</th>
					<td><input name="juminno1" type="text"tabindex="2" maxlength="6" onkeyup="moveFocus(6,this,document.idsearch_form.juminno2)" class="w50 required" msg="주민번호를 입력하세요" >
						-
						<input name="juminno2" type="text"tabindex="3" maxlength="7" class="w50 required" msg="주민번호를 입력하세요" ></td>
				</tr>
			</table>
		</dt>
		<dd style="padding-left:20px">
			<span class="button bull btn_find_pass"><a>패스워드 찾기</a></span>
		</dd>
	</dl>
</form>
</body>
</html>