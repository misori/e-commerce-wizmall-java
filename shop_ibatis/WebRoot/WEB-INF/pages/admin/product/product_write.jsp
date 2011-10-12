<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>상품등록단</title>
<script  language="javascript" src="<c:url value="/js/jquery.plugins/jquery.selectboxes.min.js"/>"></script>
<script  language="javascript" src="<c:url value="/js/jquery.plugins/jquery.uploadfile.js"/>"></script>
<script  type="text/javascript" src="<c:url value="/js/Smart/js/HuskyEZCreator.js"/>" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="<c:url value="/js/Smart/css/default.css"/>" />
<script>
$(function(){

	$(".btn_submit").click(function(){
		//alert('전송1');
		$("#writeForm").submit();
		//alert('전송2');
	})

	//분류카테고리 변경에 따른 값 변경
	$("#input_category1").change(function(){
		var val	= $("#input_category1 > option:selected").val();
		getCategory(val, 1)
	});

	$("#input_category2").change(function(){
		var val	= $("#input_category2 > option:selected").val();
		getCategory(val, 2)
	});

});

function getCategory(v, step) {
	if(step == 1){
		$("#input_category2").removeOption(/./);
		$("#input_category3").removeOption(/./);
	}else if(step == 2) $("#input_category3").removeOption(/./);

	$.post("${pageContext.request.contextPath}/admin/category/getCategoryListToJson.ajax", {cat_no:v, step:step}, function(obj){
		//alert(data);
		//eval("var obj = "+data);
		for(var i=0; i<obj.length; i++){
			//alert(obj[i][0]);//카테고리코드
			//alert(obj[i][1]);//카테고리명
			var cat_no = $(this).find('cat_no').text();
			var cat_name = $(this).find('cat_name').text();
			if(step == 1) $("#input_category2").addOption(obj[i][0], obj[i][1], false);
			else if(step == 2) $("#input_category3").addOption(obj[i][0], obj[i][1], false);
		}
		//alert(obj[0][0]);
	});


}
</script>

  </head>

  <body>
  <table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>제품등록</legend>
			<div class="notice">[note]</div>
			<div class="comment"> 제품을 카테고리에 맞게 분류하여 등록하십시오.<br />
				등록카테고리를 지정하실 때에는 가장 하위 카테고리에 등록해 주시기 바랍니다. <br />
				스킨마다 약간식의 적용 옵션이 달라 질 수 도 있습니다.</div>
			</fieldset>
			<div class="space20"></div>


			<form  action='${pageContext.request.contextPath}/admin/product/productReg_x.do' method='post' name="writeForm" id="writeForm" enctype='multipart/form-data'>
				
				<input type="hidden" name='tid' value='${product.tid}'>

				<table class="table_main">
				<col width="120px" />
				<col width="*" />
					<tr>
						<th>등록카테고리</th>
						<td>
						${product.category}
						<select name="input_category1" id="input_category1">
								<option value="" selected>대분류 </option>
								<option value="">----------------</option>
<c:forEach items="${categoryList.depth1}" var="current" varStatus="i">
<option value="${current.cat_no}"<c:if test = "${current.cat_no == fn:substring(product.category, 0, 3)}" > selected="selected"</c:if>>${current.cat_name}</option>
</c:forEach>
							</select>
							<select name="input_category2" id="input_category2">
								<option value="" selected>중분류</option>
								<option value="">----------------</option>
<c:forEach items="${categoryList.depth2}" var="current" varStatus="i">
	<c:if test="${fn:substring(current.cat_no,0,3) == fn:substring(product.category,0,3)}">
		<option value="${current.cat_no}"<c:if test = "${current.cat_no == fn:substring(product.category, 0, 6)}" > selected="selected"</c:if>>${current.cat_name}</option>
	</c:if>
</c:forEach>
							</select>
							<select name="input_category3" id="input_category3">
								<option value="" selected>소분류</option>
								<option value="">----------------</option>
<c:forEach items="${categoryList.depth3}" var="current" varStatus="i">
	<c:if test="${fn:substring(current.cat_no,0,6) == fn:substring(product.category,0,6)}">
		<option value="${current.cat_no}"<c:if test = "${current.cat_no == fn:substring(product.category, 0, 9)}" > selected="selected"</c:if>>${current.cat_name}</option>
	</c:if>
</c:forEach>
							</select>
							(등록카테고리가 없을시 카테고리를 <a href="${pageContext.request.contextPath}/admin/category.do">등록</a> 하세요)</td>
					</tr>
					<tr>
						<th>상품명</th>
						<td><input name="name" value="${product.name}">
						</td>
					</tr>
					<tr>
						<th>상품모델명</th>
						<td><input name="model" value="${product.model}">
							- 사용하지 않을경우 공백처리</td>
					</tr>
					<tr>
						<th>판매가<br />
							(즉시구매가)</th>
						<td><input name="price" value="${product.price}">
							콤마(,)없이 숫자로만 입력(보기:10000)</td>
					</tr>
					<tr>
						<th>소비자가</th>
						<td><input name="price1" value="${product.price1}" maxLength=30>
							콤마(,)없이 숫자로만 입력(보기:10000)- 사용하지 않을경우 공백처리</td>
					</tr>
					<tr>
						<th>부가 포인트</th>
						<td><input name="point" value="${product.point}">
							이 상품에 부가할 마일리지포인트. </td>
					</tr>
					<tr>
						<th>큰그림</th>
						<td>
						
<c:forEach items="${attachedInfo}" var="current" varStatus="i">
<c:choose>
       <c:when test="${current.orderid == '0'}">
			<c:set var="attach0"  value="${current.filename}" />
       </c:when>
       <c:when test="${current.orderid == '1'}">
			<c:set var="attach1"  value="${current.filename}" />
       </c:when>
       <c:when test="${current.orderid == '2'}">
			<c:set var="attach2"  value="${current.filename}" />
       </c:when>
       <c:otherwise>
           
       </c:otherwise>
   </c:choose>
</c:forEach>						
						<table>
								<tr>
									<td><input type='file' name='fileattach[0]'></td>
									<td>
										<a href='${pageContext.request.contextPath}/data/product/${attach0}' target=_blank><img src="${pageContext.request.contextPath}/data/product/${attach0}" width="30" height="30" /></a>
											${attach0}
										(권장사이즈 : 500 x 500)(상세보기팝업이미지)</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<th>작은그림<br />
							(copy
							<input name="copyimg[1]" type="checkbox" id="copyimg[1]" value="1" />
							)</th>
						<td><table>
								<tr>
									<td><input type='file' name='fileattach[1]'></td>
									<td>
										<a href='${pageContext.request.contextPath}/data/product/${attach1}' target=_blank><img src="${pageContext.request.contextPath}/data/product/${attach1}" width="30" height="30" /></a>
										${attach1}
										(권장사이즈 : 100 x 100)리스트이미지</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<th>중간그림<br />
							(copy
							<input name="copyimg[2]" type="checkbox" id="copyimg[2]" value="1" />
							)</th>
						<td><table>
								<tr>
									<td><input type='file' name='fileattach[2]'></td>
									<td>
										<a href='${pageContext.request.contextPath}/data/product/${attach2}' target=_blank><img src="${pageContext.request.contextPath}/data/product/${attach2}" width="30" height="30" /></a>
										${attach2}
										(권장사이즈 : 300 x 300) 상세보기 큰이미지</td>
								</tr>
							</table></td>
					</tr>
					
					<tr>
						<th>자세한설명
							<input type="hidden" value="1" name="des_type1">
						</th>
						<td><textarea name="description1" rows=20 id="ir1" style="width:98%">${product.description1}
</textarea></td>
					</tr>
					<script >
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir1",
	sSkinURI: "${pageContext.request.contextPath}/js/Smart/SEditorSkin.html",
	fCreator: "createSEditorInIFrame"
});

function insertIMG(irid,fileame)
{

    var sHTML = "<img src='" + fileame + "'>";
    oEditors.getById[irid].exec("PASTE_HTML", [sHTML]);

}
</script>
					<tr>
						<th>배송정보<br />
							<input name="des_type3" type="checkbox" value="1">
							HTML사용</th>
						<td><textarea name="description2" rows="6"  style="width:98%">${product.description2}</textarea></td>
					</tr>
					<tr>
						<th>간단한
							설명</th>
						<td><textarea name="description3" rows="3"  style="width:98%">${product.description3}</textarea>
							<br />
							<br />
							상품에 대한 간단한 설명을 넣는 곳입니다. 스킨에 따라 적용되지 않을 수도 있습니다.<br />
						</td>
					</tr>
				</table>
				<br />
				<div class="agn_c">
					<span class="button bull btn_submit"><a>전송</a></span>
				</div>
				<br />
				<br />
				<span> 옵션 입력사항</span>
				<table class="table_main">
				<col width="120px" />
				<col width="*" />
					<tr>
						<th>브랜드명</th>
						<td><input name="brand" value="${product.brand}" maxLength=30></td>
					</tr>
					<tr>
						<th>제조사/원산지</th>
						<td><input name="compname" value="${product.compname}" maxLength=30></td>
					</tr>
					<tr>
						<th>등록옵션</th>
						<td>
						
<c:forEach items="${regOption}" var="current" varStatus="i">
<input type='checkbox' name='regoption' value='${current.key}' <c:forEach items="${regoption}" var="c"><c:if test = "${c == current.key}"> checked="checked"</c:if></c:forEach>>${current.value}</option>
</c:forEach></td>
					</tr>


				</table>
				<br />
				<table class="table_main">
				<col width="120px" />
				<col width="*" />
					<tr>
						<th>품절</th>
						<td><input type="checkbox" value="1" name="none">
							품절상품은 리스트는 되나 장바구니에 담겨지지 않습니다.</td>
					</tr>
					<tr>
						<th>공급처</th>
						<td><select name="vendor">
								<option value=''>공급거래처 없음</option>
								<option value=''>------------------</option>
								<?
$sqlcompstr = "SELECT UID,CompName,CompID FROM wizCom WHERE (CompSort = '01' or CompSort = '02' or CompSort = '03')";
$sqlcompqry = $dbcon->_query($sqlcompstr);
while( $complist = $dbcon->_fetch_array( $sqlcompqry ) ) {
unset($selected);
if(!strcmp(trim($complist[CompID]),trim($list[GetComp]))) $selected = " selected";
        echo "<option value='$complist[CompID]'$selected>$complist[CompName]</option>";
}
?>
							</select>
							<span class="button bull"> <a href="<?=$PHP_SELF?>?menushow=<?=$menushow?>&theme=member/member5">공급처등록</a></span> </td>
					</tr>
					<tr>
						<th>원가 </th>
						<td><input name="inputprice" value="${product.inputprice}"
                        maxLength=30>
							콤마(,)없이 숫자로만 입력(보기:10000) - 원 상품가</td>
					</tr>
					<tr>
						<th>단위</th>
						<td><input name="unit" value="${product.unit}">
						</td>
					</tr>
					<tr>
						<th>재고수량<br />
							(공동구매수량)</th>
						<td><? if($uid){ ?>
							현재고수량:
							<?=$list["Stock"]?>
							<?=$list[Unit]?>
							(입하량의 추가/변경 일경우 <a href="javascript:wizwindow('./product/product2_2.php?uid=<?=$uid?>&Name=<?=$list[Name]?>&Output=<?=$list[Output]?>&Icomid=<?=$list[GetComp]?>','InputChangeWindow','width=300,0')">이곳을
							눌러</a> 변경해 주세요)
							<?
			  }else{
			  ?>
							<input name="Stock" value="">
							<?
			  }
			  ?></td>
					</tr>
				</table>
				<br />
				<div class="agn_c">
					<span class="button bull btn_submit"><a>전송</a></span>
				</div>
			</form></td>
	</tr>
</table>
  </body>
</html>
