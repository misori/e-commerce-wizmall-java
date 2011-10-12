<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("newLineChar", "\n");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>상품 상세보기 페이지</title>
<script src="${pageContext.request.contextPath}/js/mall.js"></script>
<script>
$(function(){

	$(".btn_savewish").click(function(){
		location.href="${pageContext.request.contextPath}/cart/WishListSave.do?tid=${product.tid}";
	});
});

function gotoList(){
	
	//$("#view_form").submit();
}

function num_plus(num){
	gnum = parseInt(num.qty.value);
	num.qty.value = gnum + 1;
	return;
}
function num_minus(num){
	gnum = parseInt(num.qty.value);
if( gnum > 1 ){
	num.qty.value = gnum - 1;
	}
	return;
}

function wishreally(){
if (confirm('\n\n정말로 본 제품을 위시리스트에 담으시겠습니까?\n\n')) return true;
return false;
}


function baropay(){
	var f=document.view_form;

	if(autoCheckForm(f)){
		f.sub_query.value = "baro";
		f.submit();
	}
}

function checkForm(f){
	if(autoCheckForm(f)) return true;
	else return false;
}

function checkthis(v){

	var i,currEl,splitvalue,commanewprice;
	var f = eval("document."+v.form.name);
	var currPrice = parseInt(f.GoodsPrice.value);
	var newprice = 0;


    for(i = 0; i < f.elements.length; i++){
		currEl = f.elements[i];
		if (currEl.getAttribute("oflag") != null) {
			if(currEl.value){
				if(currEl.oflag == "1"){//가격추가
					splitvalue = currEl.value.split('|');
					newprice += parseInt(splitvalue[1]);
				}else if(currEl.oflag == "1"){//상품가격변경
					currPrice = parseInt(splitvalue[1]);
				}
			}
		}
	}

	newprice = currPrice + newprice;
	commanewprice = SetComma1(newprice);
	if (document.layers) {
		document.layers.item_price.document.write(commanewprice);
		document.layers.item_price.document.close();
	}else if (document.all) item_price.innerHTML = commanewprice;
}


function getBigPicture(no){
	wizwindow('${pageContext.request.contextPath}/product/ImageViewer.pop?no='+no, 'BICIMAGEWINDOW','width=750,height=592,statusbar=no,scrollbars=no,toolbar=no,resizable=no')
}

</script>

  </head>

  <body>
<div class="navy">Home
	<?=$navy_path?>
</div>
<form action="${pageContext.request.contextPath}/cart/cartSave.do" id="view_form"  name="view_form" method="POST">
<input type="hidden" name="tid" value="${tid}">
<input type="hidden" name="price" value="${product.price}">
<input type="hidden" name="point" value="${product.point}">
<input type="hidden" name="code" value="${code}">


	<input type="hidden" name="hidden_pid" VALUE="{info.view.PID}">
	<input type="hidden" name="hidden_category" VALUE="{info.view.CATEGORY}">
	<input type="hidden" name="sub_query" VALUE= "">

	<div class="product_view">
		<dl>
			<dt><a href="javascript:getBigPicture(${tid})"> <c:if test="${product.attached != '' && product.attached != null}">
 	<img src="${pageContext.request.contextPath}/data/product/${product.attached}" width="200" height="200" />
 </c:if></a>
				<div class="agn_c"><span class="button bull"><a href="javascript:getBigPicture(${tid})">크게보기</a></span></div>
			</dt>
			<dd> <span class="b orange"> ${product.name}
				(${product.model})
				</span>
				<table class="table_main noside w100p list">
					<col width="120" />
					<col width="*" />
					<tr>
						<th class="agn_l">가격</th>
						<td><fmt:formatNumber value="${product.price}" /> 원
							<input type='hidden' name='GoodsPrice' value='${product.price}'></td>
					</tr>

					<tr>
						<th class="agn_l">시중가격</th>
						<td><fmt:formatNumber value="${product.price1}" /> 원</td>
					</tr>

					<tr>
						<th class="agn_l">브랜드</th>
						<td>${product.brand}</td>
					</tr>

					<tr>
						<th class="agn_l">적립포인트</th>
						<td><fmt:formatNumber value="${product.point}" /></td>
					</tr>

					<tr>
						<th class="agn_l">제조사/원산지</th>
						<td>${product.compname}</td>
					</tr>


					<tr>
						<th class="agn_l"><?=$oname?></th>
						<td> :

							<select name="optionfield[]" class="formline"  oflag="" onchange="checkthis(this)">
								<option value=''>

								선택</option>

							</select>

						</td>
					</tr>

					<tr>
						<th class="agn_l">주문수량</th>
						<td><table >
								<tr>
									<td rowspan=2><input type="text" name="qty" maxlength=5 value="1" onKeyPress="is_number()" class="w30"></td>
									<td><a href="javascript:num_plus(document.view_form);"><img src="${pageContext.request.contextPath}/images/shop/num_plus.gif"></a></td>
									<td rowspan=2>EA</td>
								</tr>
								<tr>
									<td><a href="javascript:num_minus(document.view_form);"><img src="${pageContext.request.contextPath}/images/shop/num_minus.gif"></a></td>
								</tr>
							</table></td>
					</tr>
				</table>
				<div class="btn_box">
					<span class="btn_save_cart button bull"><a>쇼핑백에 넣기</a></span> 
					<!-- <span class="button bull"><a href="javascript:baropay();">바로구매</a></span>-->
					<span class="button bull"><a href="{info.view.prepage}">리스트</a></span>
					<span class="button bull btn_savewish"><a>상품찜</a></span>
				</div>
			</dd>
		</dl>
	</div>
</form>
<div class="space15"></div>
<div class="title_box">상품상세정보</div>
${fn:replace(product.description1, newLineChar, "<br />")}
<div class="space15"></div>
<div class="title_box">배송관련정보</div>

<!-- 인기 판매 제품 시작 -->
<div class="space15"></div>
<div class="title_box">인기 판매 제품</div>
<table>
	<tr>

		<td></td>

	</tr>
</table>
<!-- 인기 판매 제품 끝 -->
<? endif;?>
<? if($cfg["skin"]["GoodsDisplayEstim"] == "checked"):?>
<!-- 상품 평가 시작 -->
<div class="space15"></div>
<div class="title_box">상품평 보기 | 고객님에게 꼭 맞는 상품 구입을 위한 유용한 자료로 사용하세요! <span class="button bull"><a href="#">상품평쓰기</a></span></div>
<script language="JavaScript">
<!--
function check_reple_Form(){
	var f=document.estimat;
	if(f.Name.value == ''){
		alert('성함을 입력해주세요');
		f.Name.focus();
		return false;
	} else if(f.Contents.value == ''){
		alert('상품사용후기를 입력해주세요');
		f.Contents.focus();
		return false;
	}
}

function reple_delete(uid){
var f=document.estimat;
	f.repleqry.value = "insert";
	f.mode.value = "";
	f.repleuid.value = uid;
	f.submit();

}
//-->
</script>
<form name="estimat" action="<?=$PHP_SELF?>" onsubmit='return check_reple_Form();'>
	<input type="hidden" name="query" value="<?=$query?>">
	<input type="hidden" name="code" value="<?=$code?>">
	<input type="hidden" name="no" value="<?=$view["source_id"]?>">
	<input type="hidden" name="repleqry" value="insert">
	<input type="hidden" name="Name" value="<?=$cfg["member"]["mname"]?>">
	<input type="hidden" name="repleuid" value="">
	<!--<? if($cfg["member"]):?>
                                <input name="image2" type="image" src="img/main/btn_sub.gif" width="51" height="21">
                                <? else: ?>
                                <a href="javascript:window.alert('로그인후 사용가능합니다.')"><img src="img/main/btn_sub.gif" width="51" height="21"></a>
                                <? endif;?>-->
</form>

<table class="table_main w100p">
	<tr>
		<th>글쓴이</th>
		<td><?=$list[Name]?>
			<? if($cfg["member"] == $list[ID]):?>
			&nbsp;&nbsp;&nbsp;<a href="javascript:reple_delete('<?=$list[UID]?>');" >x</a>
			<?endif;?></td>
		<th>고객선호도 </th>
		<td><img src="./skinwiz/viewer/<?=$cfg["skin"]["ViewerSkin"]?>/images/star<?=$list[Grade]?>.gif"></td>
	</tr>
	<tr>
		<td colspan="4"><?=$list[Subject]?></td>
	</tr>
	<tr>
		<td colspan="4" style="word-break:break-all;"><?=$list[Contents]?>
		</td>
	</tr>
</table>

<table>
	<tr>
		<td>≡ </td>
		<td>상품평은 개인의 체험을 바탕으로 한 주관적인 의견으로 사실과 다르거나,보는 사람에 따라
			차이가 있을 수 있습니다.</td>
	</tr>
</table>
<!-- 상품 평가 끝 -->





  </body>
</html>
