<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>카테고리입력단</title>
<script  type="text/javascript" src="<c:url value="/js/Smart/js/HuskyEZCreator.js"/>" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="<c:url value="/js/Smart/css/default.css"/>" />
<script language="javascript" type="text/javascript">
<!--
$(function(){
	$(".btn_reg1").click(function(){
		//$("#formcat1").submit();
	
		var cat_name = $("#category_name1").val();
		if(!cat_name){
			jAlert('카테고리명을 입력해 주세요');
		}else{
			$.post("${pageContext.request.contextPath}/admin/category/addCategory.do", {cat_name:cat_name}, function(data){
				//alert(data);
				$("#category_name1").val("");
				reloadList();
			});

		}
	})

	reloadList();
});

function reloadList(){

	$.post("${pageContext.request.contextPath}/admin/category/categoryForm.ajax", {}, function(data){
		$("#categoryListHTML").html(data);
	});
}
//-->
</script>
</head>
<body>
<table class="table_outline">
  <tr>
    <td><fieldset class="desc">
        <legend>카테고리 메니저</legend>
        <div class="notice">[note]</div>
        <div class="comment">상품카테고리를 등록, 수정, 변경 삭제 하는 곳입니다. 또한 카테고리별로 별도의 코딩을
          하실 수 도 있습니다. <br />
          ( <img src="/images/admin/cat_view.gif" width="16" height="16"> 매장보기 <img src="/images/admin/cat_modify.gif" width="16" height="16"> 카테고리등록 및 수정 <img src="/images/admin/cat_text.gif" width="16" height="16"> 카테고리별 상,하단 코딩 <img src="/images/admin/cat_delete.gif" width="14" height="16"> 카테고리 삭제 ) </div>
      </fieldset>
      <div class="space20"></div>
      <form action='' name="s_form" id="s_form" method="post" enctype="multipart/form-data">
      </form>
      <form action="${pageContext.request.contextPath}/admin/category/addCategory.do" id="formcat1" method="post">
        <input name="cat_name" id="category_name1" />
        <!-- <input name="category_name1" id="category_name1" />  -->
        <input name="file[]" type="file" id="file[]">
        <span class="btn_reg1 button bull"><a>대분류등록</a></span> <span class="button bull"><a href="./catmanager/shopmanager1_1.php?DownForExel=yes">전체리스트출력</a></span>
      </form>
      <br />
      <div id="categoryListHTML"></div>
      <form name = "SaveCatCoding" action="<?=$PHP_SELF?>" method="post">
        <input type="hidden" name="cat_no" value = "<?=$code?>">
        <input type="hidden" name="action" value = "in_desc">
        <input type="hidden" name="where" value = "">
        <input type="hidden" name="ccode" value="">
        <input type="hidden" name="cat_flag" value=''>
        <table class="w100p">
          <tr>
            <td>페이지
              html 삽입 : html로 등록해 주시고 등록시 &lt;table&gt;테크를 만들어 넣어 주시기를 추천합니다 <br />
              <span>상단코딩</span><br />
              <textarea name="cat_top" rows=5 class="w100p" id="ir1">
</textarea>
              <br />
              <span><br />
              하단코딩</span><br />
              <textarea name="cat_bottom" rows=5  class="w100p" id="ir2">
</textarea></td>
          </tr>
        </table>
      </form>
<script>

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir1",
	sSkinURI: "${pageContext.request.contextPath}/js/Smart/SEditorSkin.html",
	fCreator: "createSEditorInIFrame"
});

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir2",
	sSkinURI: "${pageContext.request.contextPath}/js/Smart/SEditorSkin.html",
	fCreator: "createSEditorInIFrame"
});

function insertIMG(irid,fileame)
{
    var sHTML = "<img src='" + fileame + "'>";
    oEditors.getById[irid].exec("PASTE_HTML", [sHTML]);
}

// 복수개의 에디터를 생성하고자 할 경우, 아래와 같은 방식으로 호출하고 oEditors.getById["ir2"]이나 oEditors[1]을 이용해 접근하면 됩니다.
/*

*/

function _onSubmit(elClicked){
	// 에디터의 내용을 에디터 생성시에 사용했던 textarea에 넣어 줍니다.
	oEditors.getById["ir1"].exec("UPDATE_IR_FIELD", []);
	oEditors.getById["ir2"].exec("UPDATE_IR_FIELD", []);

	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.

	try{
		elClicked.form.submit();
	}catch(e){}
}
</script> 
      <!--매장 카테고리 코딩 끝 --> 
      <br />
      <div class="btn_box">
        <input type="button" onClick="_onSubmit(this)" value="설정완료">
        </input>
      </div>
</td>
  </tr>
</table>
</body>
</html>