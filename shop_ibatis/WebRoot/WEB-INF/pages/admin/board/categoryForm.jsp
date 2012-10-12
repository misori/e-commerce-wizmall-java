<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
$(function(){
//카테고리관련
	//카테고리 등록
	$("#btn_cat_in").click(function(){
		$.post("${pageContext.request.contextPath}/admin/board/categorySave.ajax",{bid:$("#bid").val(),gid:$("#gid").val(),catname:$("#input_catname").val()}, function(data){
			getCategory();
		});
	});
	//카테고리 삭제
	$(".btn_cat_del").click(function(){
		var tid = $(this).parent().parent().attr("tid");
		alert(tid);
		$.post("${pageContext.request.contextPath}/admin/board/categoryDel.ajax",{tid:tid}, function(data){
			getCategory();
		});
	});

	//카테고리 이동
	$(".btn_cat_move").click(function(){
		var i = $(".btn_cat_up").index(this);
		var s_category	= $(this).parent().parent().attr("ordernum");
		var d_category = $(".target_category").eq(i).val();
		/*
		$(".target_category").each(function (index) {
			if($(this).attr("tid") == tid){
				target_category = $(this).val();
				return;
			}
		});
		*/
		$.post("${pageContext.request.contextPath}/admin/board/categoryMove.ajax",{bid:$("#bid").val(),gid:$("#gid").val(),s_category:s_category, d_category:d_category}, function(data){
			getCategory();
		});
	});
	//카테고리명 수정
	$(".btn_cat_up").click(function(){
		var i = $(".btn_cat_up").index(this);
		var tid = $(this).parent().parent().attr("tid");
		var catname	= $(".newwcatname").eq(i).val();
		$.post("${pageContext.request.contextPath}/admin/board/categoryCh.ajax",{tid:tid, catname:catname}, function(data){
			//alert(data);
			getCategory();
		});
	});

});
</script>

<table class="table_pop_main">
  <tr>
    <td height="25" colspan="4"><div id="div">전체 <span class="red">${fn:length(category)}</span> 개
        카테고리
        카테고리명 :
        <input name="input_catname" id="input_catname" type="text" size="18" />
        <span class='button bull'><a id="btn_cat_in">등록</a></span> </div></td>
  </tr>
</table>
<table class="table_pop_main">
  <col width="50px" /> <!-- 번호 -->
  <col width="150px" /> <!-- 카테고리명 -->
  <col width="*" /> <!-- 카테고리이동 -->
  <col width="80px" />  <!-- 삭제 -->
  <col width="80px" /> <!-- 수정 -->
  <tr>
    <th >번호</th>
    <th>카테고리명</th>
    <th>카테고리이동</th>
    <th>수정</th>
    <th>삭제</th>
  </tr>
  <c:forEach var="current" items="${category}" varStatus="j" >
    <tr tid="${current.tid}" ordernum="${current.ordernum}">
      <td>${j.count}</td>
      <td><input type="text" class="newwcatname w100" value="${current.catname}" /></td>
      <td><select class="target_category">
          <c:forEach var="i" items="${category}" >
		  <option value="${i.ordernum}" <c:if test="${i.ordernum == current.ordernum}"> selected="selected"</c:if>>
			${i.catname}
           </option>
          </c:forEach>
        </select>
        로 게시물 <span class='btn_cat_move button bull' ordernum="${current.ordernum}"><a>이동</a></span></td>
      <td><span class='btn_cat_up button bull'><a>수정</a></span></td>
      <td><c:if test="${current.ordernum != '0'}"> <span class='btn_cat_del button bull'><a>삭제</a></span> </c:if></td>
    </tr>
  </c:forEach>
</table>
