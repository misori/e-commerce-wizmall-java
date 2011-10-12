<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List members</title>
</head>
<body>List members 입니다.
<a href="${pageContext.request.contextPath}/">홈으로가기</a>
<a href="${pageContext.request.contextPath}/member/memberReg.do">회원가입</a>
		<table id="listTable" cellpadding="0" cellspacing="0">
			<tbody>
				<c:forEach items="${info}" var="current" varStatus="i">
					<c:choose>
						<c:when test="${(i.count) % 2 == 0}">
		    				<c:set var="rowclass" value="rowtwo"/>
						</c:when>
						<c:otherwise>
		    				<c:set var="rowclass" value="rowone"/>
						</c:otherwise>
					</c:choose>	
				<tr>
					<td>
						<a title="" href="${pageContext.request.contextPath}/member/viewMember.do?tid=${current.tid}&">보기</a>
						<a title="" href="${pageContext.request.contextPath}/member/editMember.do?tid=${current.tid}&">수정</a>
						<a title="" href="${pageContext.request.contextPath}/member/deleteMember.do?tid=${current.tid}&">삭제</a>
					</td>
					<td nowrap="nowrap" class="tabletd">
							${current.tid}
					</td>
					<td nowrap="nowrap" class="tabletd">
							${current.user_id}
					</td>	
					<td nowrap="nowrap" class="tabletd">
							${current.user_name}
					</td>
					<td nowrap="nowrap" class="tabletd">
							${current.user_passwd}
					</td>					
				</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>