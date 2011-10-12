<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Basic Info</title>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.plugins/jquery.validator-1.0.1.js"></script>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	//메일스킨 설정
	$("#btn_mainskin").click(function(){
		window.open('../util/wizmail/adminforwiz.php','WizMailSkinSelectWindow','')
	});

	//저장
	$(".btn_save").click(function(){
		if($('#s_form').formvalidate()){
			$("#s_form").submit();
		}
	});

});
//-->
</script>

</head>
<body>

<table class="table_outline">
	<tr>
		<td>
<fieldset class="desc w_desc" >
<legend>몰 기본환경 설정</legend>
<div class="notice">[note]</div>
<div class="comment"> 기본정보 및 환경을 설정하실 수 있습니다.<br />
	이 곳에 설정된 내용은 각종 메일의 기본 정보 및 결제단의 중요정보가 됩니다.<br />
	특히 쇼핑몰명은 <span class="orange">반드시 특수문자나 공백이 없어야합니다.(결제단의 에러요인이
	될 수있습니다.)</span> </div>
</fieldset>
<div class="space20"></div>
<form name="s_form" id="s_form" action='${pageContext.request.contextPath}/admin/basic/basicInfo_x.do' method='post'>

	관리자정보 및 환경을 변경합니다.
	<table class="table_main w_default">
		<col width="120px" />
		<col width="*" />
		<col width="120px" />
		<col width="*" />
		<th>관리자아이디</th>
			<td>${member.user_id}
			</td>
			<th>관리자 성명</th>
			<td><input type="text" value="${member.user_name}" name="user_name" class="required" msg="관리자 이름을 입력하세요" />
			</td>
		</tr>
		<tr>
			<th>현재관리패스워드</th>
			<td><input type="password" value="" name="current_user_passwd" class="required" msg="현재 패스워드를 입력하세요"  />
			</td>
			<th>로그인fail제한횟수</th>
			<td>
				<select name="LoginLimitCnt" id="LoginLimitCnt">
					<option value=''>선택해주세요</option>
				<c:forEach var="i" items="${LoginFailCount}">
					<option value='${i.key}'>${i.value}</option>
				</c:forEach>
				</select>

				</select>

			</td>
		</tr>
		<tr>
			<th>관리패스워드</th>
			<td><input type="password" value="" name="user_passwd" class="required text_grp" msg="패스워드를 입력하세요"  group="text_grp" />
			</td>
			<th>패스워드확인</th>
			<td><input type="password" value="" name="cpasswd" class="text_grp" />
			</td>
		</tr>
	</table>
	<span class="orange">* 관리패스워드는 쇼핑몰
	관리자화면으로 입장시 필요한 것입니다.<br />
	* 패스워드는 중요하므로 타인에게 노출되지 않도록 해주시기 바랍니다.</span>
	<table class="table_main w_default">
		<col width="120px" />
		<col width="*" />
		<col width="120px" />
		<col width="*" />
		<tr>
			<th>쇼핑몰명</th>
			<td colspan="3"> (한글)
				<input type="text" name="admin_title" value='${basicinfo["admin_title"]}' size="18">
				, (영문)
				<input type="text" name="admin_title_e" value='${basicinfo["admin_title_e"]}' size="18">
		(<span class="orange">특수문자나 공백이 없어야합니다</span>.)		</tr>
		<tr>
			<th>회사도메인</th>
			<td colspan="3"><input type="text" name="company_domain" value='${basicinfo["company_domain"]}' class="w300">
				(&quot;http://&quot;를 포함해서 입력 - 결제단용) </td>
		</tr>
		<!--<tr>
			<th>WaterMark용</th>
			<td colspan="3">문자:
				<input type="text" name="str_watermark" value='${basicinfo["str_watermark"]}' />
				,
				이미지:
				<input type="text" name="img_watermark" value='${basicinfo["img_watermark"]}' />
				(절대경로사용) </td>
		</tr>-->
		<tr>
			<th>홈페이지 주소</th>
			<td colspan=3><input type="text" name="home_url" value='${basicinfo["home_url"]}' class="w300" />			</td>
		</tr>
		<tr>
			<th>이메일 주소</th>
			<td><input type="text" value='${basicinfo["admin_email"]}' name="admin_email" />
				<span class="button bull" id="btn_mainskin"><a>이메일 스킨 설정</a></span></td>
			<th>대표전화번호</th>
			<td><input type="text" value='${basicinfo["admin_tel"]}' name="admin_tel" /></td>
		</tr>
		<tr>
			<th>상호명</th>
			<td><input type="text" value='${basicinfo["company_name"]}' name="company_name" /></td>
			<th>대표자성함</th>
			<td><input type="text" value='${basicinfo["president"]}' name="president" /></td>
		</tr>
		<tr>
			<th>사업자등록번호</th>
			<td><input type="text" value='${basicinfo["company_num"]}' name="company_num" /></td>
			<th> 통신판매업신고 </th>
			<td><input type="text" value='${basicinfo["company_licence_num"]}' name="company_licence_num" /></td>
		</tr>

		<tr>
			<th>고객상담전화</th>
			<td><input type="text" value='${basicinfo["customer_service_tel"]}' name="customer_service_tel" /></td>
			<th>팩스번호</th>
			<td><input type="text" value='${basicinfo["customer_service_fax"]}' name="customer_service_fax" /></td>
		</tr>
		<tr>
			<th>사업장주소</th>
			<td colspan=3><input type="text" value='${basicinfo["company_address"]}' name="company_address" class="w500" /></td>
		</tr>
	</table>
	<!--
	<span  class="orange">SMS 관리</span>
	<table class="table_main w_default">
		<col width="120px" />
		<col width="*" />
		<col width="120px" />
		<col width="*" />
		<tr>
			<th>SMS 업체</th>
			<td><select name=smsModule id="smsModule">

				</select>
				<input type="checkbox" name="sms_rec_enable" value="1">
				주문접수시 관리자 문자수신 </td>
			<th>Mobil No.</th>
			<td><input type="text" name="sms_mobil" id="sms_mobil" value='${basicinfo["sms_mobil"]}' />
			</td>
		</tr>
		<tr>
			<th>SMS ID </th>
			<td><input type="text" name="sms_id" id="sms_id" value='${basicinfo["sms_id"]}' >
			</td>
			<th>SMS PWD</th>
			<td><input name="sms_pwd" type="password" id="sms_pwd" value='${basicinfo["sms_pwd"]}'" />
			</td>
		</tr>
	</table>
	-->
	<div class="btn_box w_default">
		<span class="btn_save button bull"><a>설정완료</a></span>
	</div>
</form>
</td></tr></table>
</body>
</html>