<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.member-resources"/>
<html>
<head>
<title>마이페이지</title>
<script language=javascript src="<c:url value="/js/jquery.plugins/jquery.validator.js"/>"></script>

<script language="javascript">
<!--
$(function(){

});

//-->
</script>
</head>
<body>

<div class="navy">Home &gt; My Page</div>
<fieldset class="desc">
<legend>[안내]</legend>
<div class="notice">마이페이지</div>
<div class="comment">
회원님과 관련된 정보를 보시거나 수정하실 수 있습니다.</div>
</fieldset>
<ul class="mypage_list">
<li><ul><li><a href="${pageContext.request.contextPath}/member/member_update.do"><img src="${pageContext.request.contextPath}/images/default/mypage_icon1.gif" width="155"></a></li>
<li>회원님과 관련된 정보를 변경하실 수 있습니다. <br />
                        개인정보 누출 방지를 위해 주기적으로 패스워드를 변경해 주시기 바랍니다.</li>
</ul>
<li><ul><li><a href="${pageContext.request.contextPath}/member/order_list.do"><img src="${pageContext.request.contextPath}/images/default/mypage_icon2.gif" width="155"></a></li>
<li>회원님의 주문내역을 보실 수 있습니다.</li>
</ul></li>


<li><ul><li><a href="./wizmember.php?query=out"><img src="${pageContext.request.contextPath}/images/default/mypage_icon4.gif" width="155"></a></li>
<li>회원탈퇴를 하실 수 있습니다.<br />
                        회원탈퇴시 아이디 및 주민번호등만이 추후 이중 등록을 방지를 위해 남고 나머지 데이타는<br /> 완전히
                        삭제됩니다.</li>
</ul></li>
<li><ul><li><a href="./wizmember.php?query=point"><img src="${pageContext.request.contextPath}/images/default/mypage_icon5.gif" width="155"></a></li>
<li>회원님의 현 적릭금 상태를 보실 수있습니다.<br />
                        적립금과 함께 다양한 혜택을 누리시기 바랍니다.</li>
</ul></li>
<li><ul><li><a href="wizmember.php?query=wish"><img src="${pageContext.request.contextPath}/images/default/mypage_icon6.gif" width="155"></a></li>
<li>회원님이 찜하신 관심상품을 보실 수 있습니다.</li>
</ul></li>
</ul>
</body>
</html>