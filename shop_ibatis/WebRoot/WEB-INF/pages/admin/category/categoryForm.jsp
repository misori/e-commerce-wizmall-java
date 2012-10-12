<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="<c:url value="/js/jquery.plugins/tree.js"/>
"></script>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/tree.css"/>
"/>
<script type="text/javascript">
<!--
$(function(){
	$(".btn_save").click(function(){
		if($(".tree").find(".active").attr("code") == undefined){
			jAlert('작업카테고리를 선택해주세요');
		}else{
			var code	= $(".tree").find(".active").attr("code");
			//$("#hidden_c_code").val(code);
			//$("#formcat2").submit();

			var cat_name = $("#category_name2").val();
			var code	= $(".tree").find(".active").attr("code").split("|");
			//alert(code);
			if(!cat_name){
				jAlert('카테고리명을 입력해 주세요');
			}else if(!code){
				jAlert('작업 카테고리를 선택해주세요');
			}else{
				//alert(code);
				alert(cat_name+","+code[1]);
				$.post("${pageContext.request.contextPath}/admin/category/addCategory.ajax", {cat_name:cat_name, c_code:code[1]}, function(data){
					reloadList();
				});
			}
		}

 	});

	$(".btn_change").click(function(){
		var cat_name = $("#category_name2").val();
		var code	= $(".tree").find(".active").attr("code").split("|");
		//alert(code[0]);
		alert(cat_name+","+code[1]+","+code[0]);

		$.post("${pageContext.request.contextPath}/admin/category/chCategory.ajax", {cat_name:cat_name, c_code:code[1], tid:code[0]}, function(data){
			//alert(data);
			reloadList();
		});


	});

	$(".btn_delete").click(function(){
		if($(".tree").find(".active").attr("code") == undefined){
			jAlert('작업카테고리를 선택해주세요');
		}else{
			var code	= $(".tree").find(".active").attr("code").split("|");
			//alert(code[0]+","+code[1]);
			$.post("${pageContext.request.contextPath}/admin/category/delCategory.ajax", {c_code:code[1], tid:code[0]}, function(data){
				if(data.result == "0")reloadList();
			},"json");
		}
	});

	$(".btn_pre_ch").click(function(){
		var code = $(this).parent().attr("code");
		$(".tree li").removeClass("active");
		if(code == undefined){
			code = $(this).parent().parent().attr("code");
			$(this).parent().parent().addClass("active");
		}else{
			$(this).parent().addClass("active");
		}
		$("#sub_reg_form").show();
		//alert(code);

	});
});

//-->
</script>
<div class="tree">
  <ul>
    <c:forEach items="${categoryList.depth1}" var="current" varStatus="i">
      <li code="${current.tid}|${current.cat_no}">
        <div class="fleft w150 btn_pre_ch">${current.cat_name}</div>
        <div class="right"> <span class="button bull"><a href='${pageContext.request.contextPath}/product/productList.do?code=${current.cat_no}' target='_blank'>보기</a></span> <span class="btn_pre_ch button bull"><a>등록/수정</a></span> <span class="btn_pre_coding button bull"><a>코딩</a></span> <span class="btn_delete button bull"><a>삭제</a></span> </div>
        <!-- 2차 시작 -->

        <ul>
          <c:forEach items="${categoryList.depth2}" var="current1" varStatus="i">
            <c:if test="${fn:substring(current1.cat_no,0,3) == current.cat_no}">
              <li code="${current1.tid}|${current1.cat_no}">
                <div class="fleft w150 btn_pre_ch">${current1.cat_name}</div>
                <div class="right"> <span class="button bull"><a href='${pageContext.request.contextPath}/product/productList.do?code=${current1.cat_no}' target='_blank'>보기</a></span> <span class="btn_pre_ch button bull"><a>등록/수정</a></span> <span class="btn_pre_coding button bull"><a>코딩</a></span> <span class="btn_delete button bull"><a>삭제</a></span> </div>
                <!-- 3차 시작 -->
                <ul>
                  <c:forEach items="${categoryList.depth3}" var="current2" varStatus="i">
                    <c:if test="${fn:substring(current2.cat_no,0,6) == current1.cat_no}">
                      <li code="${current2.tid}|${current2.cat_no}">
                        <div class="fleft w150 btn_pre_ch">${current2.cat_name}</div>
                        <div class="right"> <span class="button bull"><a href='${pageContext.request.contextPath}/product/productList.do?code=${current2.cat_no}' target='_blank'>보기</a></span> <span class="btn_pre_ch button bull"><a>등록/수정</a></span> <span class="btn_pre_coding button bull"><a>코딩</a></span> <span class="btn_delete button bull"><a>삭제</a></span> </div>
                      </li>
                    </c:if>
                  </c:forEach>
                </ul>

                <!-- 3차 끝 -->
              </li>
            </c:if>
          </c:forEach>
        </ul>

        <!-- 2차 끝 -->
      </li>
    </c:forEach>
  </ul>
</div>
<!-- <form action='<?=$PHP_SELF?>' method="post" enctype="multipart/form-data" name="catfrm"> -->
<form action='${pageContext.request.contextPath}/admin/category/addCategory.do' method="post" name="catfrm">
  <input type="hidden" name="action" value=''>
  <input type="hidden" name="ccode" value=''>
  <input type="hidden" name="cat_flag" value=''>
</form>
<div id="sub_reg_form" class="none">
  <form action='${pageContext.request.contextPath}/admin/category/addCategory.do' method="post" name="catfrm" id="formcat2">
    <input type="hidden" name="c_code" value="" id="hidden_c_code"/>
    <input type="text" name="cat_name" id="category_name2">
    <input name="file[]" type="file" id="file[]">
    <span class="btn_save button bull"><a>등록</a></span> <span class="btn_change button bull"><a>변경</a></span>
  </form>
</div>