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

	$("#btn_del_x").click(function(){
		
		if($("#insert_passwd").val() == ""){
			jAlert('패스워드를 입력해주세요');
			$("#insert_passwd").focus();
		}else{
			var actionurl = $("#delete_form").attr("action");
			$.post(actionurl, $("#delete_form").serialize(), function (data){
				//alert(data);
				eval("var obj=" + data);
				var result = obj.result;
				if(result == "0"){
					gotoList();
					//location.href = obj.url;
				}else{
					alert(obj.msg);
				}
			});

		}

	});
});
//-->
</script>
<form name="delete_form" id="delete_form" action="${pageContext.request.contextPath}/board/boardDelete_x.ajax" method="post">
<!-- 이곳에서 id 를 제어할 경우 메인에서와 충돌이 우려된다. -->
<c:forEach var="i" items="${params_hidden}"> 
  <input type="hidden" name="${i.key}"  value="${i.value}">
 </c:forEach>
<c:choose>
       <c:when test="${params.accessResult == 'true'}">

	<div class="boardPop">
		<h3>삭제하기</h3>
		<div class="popBody"> 삭제된 글은 <span class="txt_red">복구가 불가능</span>합니다.<br />
			삭제하시겠습니까? </div>
		<p class="Agn_c Pad_t15"> 
		<span class="button bull"><a id="btn_del_x">확인</a></span> 
		<span class="button bull"><a id="btn_close_del_login_form">취소</a></span></p>
	</div>

</c:when>
    <c:otherwise>
<script language="JavaScript" type="text/javascript">
<!--
$(function(){
	$("#insert_passwd").focus();
});
//-->
</script>

	<div class="boardPop">
		<h3>삭제하기</h3>
		<div class="popBody"> 글 작성시의 <strong class="txt_org01">비밀번호</strong>를 입력해 주세요.<br />
			삭제된 글은 <span class="txt_red">복구가 불가능</span>합니다.
			<p> 비밀번호 :
				<input type="password" name="insert_passwd" id="insert_passwd" class="inputBox" />
			</p>
		</div>
		<p class="btn_box"> 
		<span class="button bull"><a id="btn_del_x">확인</a></span> 
		<span class="button bull"><a id="btn_close_del_login_form">취소</a></span></p>
	</div>
<!-- //레이어팝업 //-->
</c:otherwise>
</c:choose>
</form>