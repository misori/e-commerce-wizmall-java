<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Admin Login</title>
<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jquery.validator.js"/>"></script>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	$("#user_id").focus();

	$(".login_btn").click(function(){
		if($('#admin_login_form').formvalidate()){
			$.post("${pageContext.request.contextPath}/member/memberLogin_x.do", $("#admin_login_form").serialize(), function(data){
				//alert(data);
				eval("var obj="+data);
				if(obj.result.result == "0"){
					location.href="${pageContext.request.contextPath}/admin.do";
				}else{
					alert(obj.result.message);
				}

			});
		}
	});
});

//-->
</script>
</head>
<body>

<div id="main" class="agn_c">
	<form method='POST' name='admin_login_form' id="admin_login_form">
		<input name="AdminGrade" type="radio" value="1" checked>
		관리자
		<input type="radio" name="AdminGrade" value="2">
		일반
		<table width="552" border="0" height="254" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td><img src="./img/log01.gif" width="552" height="82"></td>
			</tr>
			<tr>
				<td><table width="552" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><img src="./img/log02.gif" width="177" height="78"></td>
							<td width="242" height="78" background="./img/logbg.gif"><table width="242" border="0" cellpadding="0" cellspacing="0" class="text-admin">
									<tr>
										<td width="48"><img src="./img/id.gif" width="28" height="18"></td>
										<td width="194"><input name="user_id" class="w100 required" id="user_id" tabindex="1" value=""  autocomplete="off" msg="아이디를 입력하세요">
											<input name="saveflag" type="checkbox" id="saveflag" value="1">
											ID 저장</td>
									</tr>
									<tr>
										<td width="48"><img src="./img/pw.gif" width="28" height="20"></td>
										<td width="194"><input name="user_passwd" type="password" class="w100 required" tabindex="2" id="user_passwd" autocomplete="off" msg="패스워드를 입력하세요"></td>
									</tr>
								</table></td>
							<td><span class="button bull login_btn"><a>로그인</a></span></td>
							<td><img src="./img/log05.gif" width="44" height="78"></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td><img src="./img/log06.gif" width="552" height="94"></td>
			</tr>
		</table>
	</form>
	<div class="agn_l">
		<ul>
			<li>* 이곳은 관리자 영역입니다.</li>
			<li>* 관리자외에 접근할 수 없도록 비밀번호관리를 잘 하여주시기 바랍니다.</li>
			<li>* <a href="./">홈으로 돌아가기</a></li>
		</ul>
	</div>
</div>
</body>
</html>