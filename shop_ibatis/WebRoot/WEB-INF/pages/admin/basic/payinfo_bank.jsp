<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	$("#btn_bank_save").click(function(){
		$.post("${pageContext.request.contextPath}/admin/basic/payinfo_bank_x.do", { bankname:$("#input_bankname").val()
			, account_no:$("#input_account_no").val()
			, account_owner:$("#input_account_owner").val()
			},
			function(data){
				//alert(data);
				loadbankList();
		});
	});

	$(".btn_bank_up").click(function(){
		var tid = $(this).attr("tid");
		var index = $(".btn_bank_up").index(this);
		var bankname		= $(".bankname").eq(index).val();
		var account_no		= $(".account_no").eq(index).val();
		var account_owner	= $(".account_owner").eq(index).val();


		$.post("${pageContext.request.contextPath}/admin/basic/payinfo_bank_x.do", {tid:tid
			, bankname:bankname
			, account_no:account_no
			, account_owner:account_owner
			},
			function(data){
				loadbankList();
		});
	});

	$(".btn_bank_del").click(function(){
		var tid = $(this).attr("tid");

		$.post("${pageContext.request.contextPath}/admin/basic/payinfo_bank_del_x.ajax", {tid:tid},
			function(data){
				loadbankList();
		});
	});

});
	function qryStatus(uid, qry){
		var f = document.PublicForm;
		f.uid.value = uid;
		f.query.value = qry;
		f.submit();
	}
//-->
</script>

<table class="table_sub">
        <tr>
          <th>은행명</th>
          <th>계좌번호</th>
          <th>예금주</th>
          <th>&nbsp;</th>
        </tr>

<c:forEach items="${info}" var="current" varStatus="i">
        <tr>
          <td class="tr">
			<select name="bankname" class="bankname w100p">
					<option value="">은행명</option>
				<c:forEach var="i" items="${BankCode}">
					<option value='${i.key}'<c:if test="${current.bankname == i.key}"> selected</c:if>>${i.key}</option>
				</c:forEach>
					<option value='etc'>기타</option>
			</select>

          </td>
          <td><input name="account_no" type="text" class="account_no w100p" value="${current.account_no}"></td>
          <td><input name="account_owner" type="text" class="account_owner w100p" value="${current.account_owner}"></td>
          <td>
		  <span class="btn_bank_up button bull" tid="${current.tid}"><a>수정</a></span>
		  <span class="btn_bank_del button bull" tid="${current.tid}"><a>삭제</a></span></td>
        </tr>
</c:forEach>
        <tr>
          <td>
            <select name="input_bankname" id="input_bankname" class="w100p">
					<option value="">은행명</option>
				<c:forEach var="i" items="${BankCode}">
					<option value='${i.key}'>${i.key}</option>
				</c:forEach>
					<option value='etc'>기타</option>
			</select>
          </td>
          <td><input name="input_account_no" type="text" id="input_account_no" class="w100p" ></td>
          <td><input name="input_account_owner" type="text" id="input_account_owner" class="w100p"></td>
          <td>
          <span class="button bull" id="btn_bank_save"><a>등록</a></span></td>
        </tr>
</table>