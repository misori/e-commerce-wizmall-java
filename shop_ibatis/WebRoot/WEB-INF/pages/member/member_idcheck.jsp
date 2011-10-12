<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>아이디 찾기</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jquery-ui-1.7.2/js/jquery-ui-1.7.2.custom.min.js"/>"></script>

		<link rel="stylesheet" href="<c:url value="/css/base.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/css/homepage.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.css"/>" type="text/css" />

		<script language=javascript src="<c:url value="/js/jquery.plugins/jquery.validator.js"/>"></script>
		<script language="javascript">
			$(function(){
				$("#btn_search").click(function(){
					if($("#user_id").val() == ""){
						alert("아이디를 입력해주세요");
						$("#user_id").focus();
					}else{
						$("#s_form").submit();
					}
				});

				$("#user_id").click(function(){
					$("#user_id").val("");
					$(".btn_apply").hide();
				})
				$(".btn_apply").click(function(){
					$("#user_id",opener.document).val($("#user_id").val())
					$("#idchk_result",opener.document).val(1)
					self.close();
				});
			});
		//-->
		</script>
		</head>
<body>
<form name="s_form" id="s_form">
	<input type="hidden" name="action" value="user_idcheck">
	<div class="agn_l b white b_black">아이디 체크</div>
	<div class="space15"></div>
	아이디는 영/숫자 혼합으로 6~15자가 가능합니다.
	<div class="space15"></div>
	아이디
	<input type="text" name="user_id" id="user_id" value="${user_id}" size=15>
<c:if test="${result == 'false'}">
	<span class="button bull btn_apply"><a>적용</a></span>
</c:if>
	<span class="button bull" id="btn_search"><a>검색</a></span>
	<div class="space15"></div>
	<div class="msg">
		<?=$message?>
	</div>
</form>
</body>
</html>