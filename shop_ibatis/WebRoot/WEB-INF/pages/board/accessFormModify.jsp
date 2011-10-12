<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!-- 레이어팝업 //-->
<script language="javascript" type="text/javascript">
<!--
$(function(){
	$("#btn_close_del_login_form").click(function(){
		$('#dynamicPop').hide();
		//$("#layerBack").hide();
	});

	$("#btn_modify_confirm").click(function(){//$("#btn_modify_confirm").live("click", function(){
		if($("#insert_passwd").val() == ""){
			jAlert('패스워드를 입력해주세요');
			$("#insert_passwd").focus();
		}else{
			$.post("${pageContext.request.contextPath}/board/hasAuthorityForModify.ajax", $("#access_form").serialize(), function (data){
				eval("var obj=" + data);
				if(obj.result == "true"){//수정권한이 있으면
					$("#sform_view").attr("action", "${pageContext.request.contextPath}/board/boardEdit.do?bid=${param.bid}&gid=${param.gid}&cp=${param.cp}&tid="+$("#tid").val());
					$("#sform_view").submit();
				}else{
					alert("패스워드를 새로 입력해주세요");
				}
			});

		}

	});
});
//-->
</script>
<form name="access_form" id="access_form" action="${pageContext.request.contextPath}/board/hasAuthorityForModify.ajax" method="post">
<!-- 이곳에서 id 를 제어할 경우 메인에서와 충돌이 우려된다. -->
<c:forEach var="i" items="${params_hidden}"> 
  <input type="hidden" name="${i.key}"  value="${i.value}">
 </c:forEach>

<script language="JavaScript" type="text/javascript">
<!--
$(function(){
	$("#insert_passwd").focus();
});
//-->
</script>

	<div class="boardPop">
		<h3>수정하기</h3>
		<div class="popBody"> 글 작성시의 <strong class="txt_org01">비밀번호</strong>를 입력해 주세요.
			<p> 비밀번호 :
				<input type="password" name="insert_passwd" id="insert_passwd" class="inputBox" />
			</p>
		</div>
		<p class="btn_box"> 
		<span class="button bull" id="btn_modify_confirm"><a>확인</a></span> 
		<span class="button bull" id="btn_close_del_login_form"><a>취소</a></span></p>
	</div>
<!-- //레이어팝업 //-->
</form>