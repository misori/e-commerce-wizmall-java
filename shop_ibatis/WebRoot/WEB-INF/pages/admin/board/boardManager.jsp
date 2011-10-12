<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>List</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.plugins/jquery.validator.js" ></script>
<script language="javascript" type="text/javascript">
<!--
$(function(){
	//레이아웃 가져오기
	$("#btn_copy_layout").click(function(){
		var hidden_gid = $("#gid").val();
		var hidden_bid	= $("#bid").val();
		var layouttype	= $("#layouttype").val();
		jConfirm("가져오시기를 할경우 현재 테이블의 모든 속성이 변경됩니다. <br> 가져오시겠습니까","", function(r){
			$.post("/admin/board/copy_layout",{hidden_gid:hidden_gid, hidden_bid:hidden_bid, layouttype:layouttype}, function(){
				location.reload();
			});
		});
	});

	//category 폼 가져오기
	getCategory();

	//포인트 옵션 폼 가져오기
	var getPointOpt = function(){
		$.post("${pageContext.request.contextPath}/admin/board/pointOptionForm.ajax",{bid:$("#bid").val(), gid:$("#gid").val()}, function(data){
			$("#optionpointHTML").html(data);
		});
	}
	getPointOpt()

	
	//getPointOpt();

	//포인트 옵션 등록하기
	$("#btn_reg_point_opt").click(function(){
		var point		= $("#point").val();//점수
		var op_icon		= $("#op_icon").val();//아이콘
		var op_strong	= $('#op_strong').is(':checked')?"1":"0";//강조
		var op_color	= $("#op_color").val();//색상
		var op_em		= $('#op_em').is(':checked')?"1":"0";//이태릭
		var hidden_gid	= $("#gid").val();
		var hidden_bid	= $("#bid").val();

		$.post("/admin/board/reg_point_opt",{gid:hidden_gid,bid:hidden_bid,point:point, op_icon:op_icon, op_strong:op_strong, op_color:op_color, op_em:op_em}, function(data){
			getPointOpt();
		});
	});

	$(".btn_mod_point_opt").live("click", function(){
		var tid	= $(this).attr("tid");

		$(".ins_point").each(function (index) {
			if($(this).attr("tid") == tid){
				point = $(this).val();
				return;
			}
		});

		$(".ins_op_icon").each(function (index) {
			if($(this).attr("tid") == tid){
				op_icon = $(this).val();
				return;
			}
		});

		$(".ins_op_strong").each(function (index) {
			if($(this).attr("tid") == tid){
				op_strong = $(this).val();
				return;
			}
		});

		$(".ins_op_color").each(function (index) {
			if($(this).attr("tid") == tid){
				op_color = $(this).val();
				return;
			}
		});

		$(".ins_op_em").each(function (index) {
			if($(this).attr("tid") == tid){
				op_em = $(this).val();
				return;
			}
		});

		var hidden_gid	= $("#gid").val();
		var hidden_bid	= $("#bid").val();

		$.post("/admin/board/mod_point_opt",{tid:tid, gid:hidden_gid,bid:hidden_bid,point:point, op_icon:op_icon, op_strong:op_strong, op_color:op_color, op_em:op_em}, function(data){
			getPointOpt();
		});

	});

	$(".btn_del_point_opt").live("click", function(){
		var tid	= $(this).attr("tid");
		$.post("/admin/board/del_point_opt",{tid:tid}, function(data){
			getPointOpt();
		});
	});

	// 상단 레이아웃
	$("#showhidden_top").click(function(){
		if($('#showhidden_top').is(':checked')) $("#table_top").show();
		else $("#table_top").hide();
	});

	//하단 레이아웃
	$("#showhidden_bottom").click(function(){
		if($('#showhidden_bottom').is(':checked')) $("#table_bottom").show();
		else $("#table_bottom").hide();
	});

	//전체 변경 수정
	$("#btn_save").click(function(){
		
		//$("#s_form").attr("action", "${pageContext.request.contextPath}/admin/board/boardManage_x.do");
		//$("#s_form").submit();
		//alert("submit");
		$.post("${pageContext.request.contextPath}/admin/board/boardManage_x.do", $("#s_form").serialize(), function(){
			jAlert('변경되었습니다.');
		});
	});

});

//category 폼 가져오기
function getCategory(){
	$.post("${pageContext.request.contextPath}/admin/board/categoryForm.ajax",{bid:$("#bid").val(), gid:$("#gid").val()}, function(data){
		$("#categoryList").html(data);
	});
}

//-->
</script>
</head>
<body>
<form method="post" action="" id="s_form">
			<input type="hidden" name="gid" id="gid" value="${info.gid}" />
			<input type="hidden" name="bid" id="bid" value="${info.bid}" />
			<input type="hidden" name="smode" id="smode" value="" />
			<table class="table_main">
				<col width="150" />
				<col width="200" />
				<col width="150" />
				<col width="*" />
				<tr>
					<th>LayOut 가져오기</th>
					<td colspan="3"><select name="layouttype" id="layouttype">


{@ info.boardconfig.get_layout}
							<option value="{.value_}">{.value_}</option>
{/}


						</select>
						<span class='button bull'><a id="btn_copy_layout">가져오기</a></span> </td>
				</tr>
				<tr>
					<th>보드 스킨타입</th>
					<td colspan="3">
						<select name="BOARD_SKIN_TYPE">
						<c:forEach var="i" items="${skinfolder}"> 
							<option value="${i}"<c:if test="${info.BOARD_SKIN_TYPE == i}"> selected="selected"</c:if>>${i}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>테이블폭(WIDTH)</th>
					<td colspan="3"><input type="text" name="TABLE_WIDTH" value="${info.TABLE_WIDTH}" class="w50" />
						<select name="TABLE_WIDTH_UNIT">
							<option value="%"{? info.table_width_unit =="%"} selected="selected"{/}>%</option>
							<option value="px"{? info.table_width_unit =="px"} selected="selected"{/}>pixels</option>
						</select>
						(테이블 폭은 px로 잡아 주는 것이  좋습니다.) </td>
				</tr>
				<tr>
					<th>테이블 정렬(ALIGN)</th>
					<td colspan="3"><select name="TABLE_ALIGN">
							<option value="default"<c:if test="${info.TABLE_ALIGN == 'default'}"> selected="selected"</c:if>>Default</option>
							<option value="left"<c:if test="${info.TABLE_ALIGN == 'left'}"> selected="selected"</c:if>>Left</option>
							<option value="right"<c:if test="${info.TABLE_ALIGN == 'right'}"> selected="selected"</c:if>>Right</option>
							<option value="center"<c:if test="${info.TABLE_ALIGN == 'center'}"> selected="selected"</c:if>>Center</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>파일첨부갯수</th>
					<td colspan="3"><select name="ATTACHEDCOUNT">
<c:forEach var="i" begin="0" end="10" step="1"> 
							<option value='${i}'<c:if test="${info.ATTACHEDCOUNT == i}">  selected="selected"</c:if>>${i}개</option>
</c:forEach> 
						</select>
						동일파일 덮어씌움
						<input name="UpLoadingOverWriteEnable" type="checkbox" id="UpLoadingOverWriteEnable" value="Y"<c:if test="${info.UpLoadingOverWriteEnable == 'Y'}">  checked="checked"</c:if> /></td>
				</tr>
				<tr>
					<th>html편집기 사용</th>
					<td colspan="3"><input name="editorenable" type="checkbox" id="editorenable" value="Y"<c:if test="${info.editorenable == 'Y'}">  checked="checked"</c:if>  /></td>
				</tr>
				<tr>
					<th>관리자전용</th>
					<td colspan="3"><input type="radio" name="AdminOnly" value="Y"<c:if test="${info.AdminOnly == 'Y'}">  checked="checked"</c:if> />
						yes
						<input type="radio" name="AdminOnly" value="N"<c:if test="${info.AdminOnly == 'N'}">  checked="checked"</c:if> />
						no (yes선택시 write버튼이 표시되지않음) </td>
				</tr>
				<tr>
					<th>1:1상담게시판</th>
					<td colspan="3"><input type="radio" name="qnaboard" value="Y"<c:if test="${info.qnaboard == 'Y'}">  checked="checked"</c:if> />
						yes
						<input type="radio" name="qnaboard" value="N"<c:if test="${info.qnaboard == 'N'}">  checked="checked"</c:if> />
						no(1대1상담용-타인의글은 리스트되지 않음)<br />
						view 메뉴에서 리플라이:no, 코멘트:yes<br />
						option메뉴에서 쓰기:회원전용 으로 각각 설정요망</td>
				</tr>
				<tr>
					<th>인클루드 몰 스킨</th>
					<td colspan="3"><input type="radio" name="INCLUDE_MALL_SKIN" value="Y"<c:if test="${info.INCLUDE_MALL_SKIN == 'Y'}">  checked="checked"</c:if> />
						yes &nbsp;&nbsp;
						<input type="radio" name="INCLUDE_MALL_SKIN" value="N"<c:if test="${info.INCLUDE_MALL_SKIN == 'N'}">  checked="checked"</c:if> />
						no (위즈몰과 연동시 yes를 책크해 주세요)</td>
				</tr>
				<tr>
					<th>보안관련</th>
					<td colspan="3"><input name="setsecurityiframe" type="checkbox" id="setsecurityiframe" value="Y"<c:if test="${info.setsecurityiframe == 'Y'}">  checked="checked"</c:if> />
						iframe 금지
						<input name="setsecurityscript" type="checkbox" id="setsecurityscript" value="Y"<c:if test="${info.setsecurityscript == 'Y'}">  checked="checked"</c:if> />
						스크립트 금지</td>
				</tr>
				<tr>
					<th>상단레이아웃 <input type="checkbox" name="checkbox" id="showhidden_top"/>
									펼침</th>
					<td colspan="3"><textarea name="table_top" rows="15" wrap="virtual" id="table_top" style="width:100%;word-break:break-all;display:none">{info.top}</textarea></td>
				</tr>
				<tr>
					<th>하단레이아웃 <input type="checkbox" name="checkbox" id="showhidden_bottom" />
									펼침</th>
					<td colspan="3"><textarea name="table_bottom" rows="15" wrap="virtual" id="table_bottom" style="width:100%;word-break:break-all;display:none";>{info.bottom}</textarea></td>
				</tr>
				<tr>
					<th>제목글자 자르기</th>
					<td><input type="text" name="SubjectLength"  class="w50" value="${info.SubjectLength}" />
						자</td>
					<th>글쓴이 이름 자르기</th>
					<td><input type="text" name="NameLength"  class="w50" value="${info.NameLength}" />
						자</td>
				</tr>
				<tr>
					<th>리스트수</th>
					<td><input type="text" name="ListNo"  class="w50" value="${info.ListNo}" />
						개 </td>
					<th>페이지표시수</th>
					<td><input type="text" name="PageNo" class="w50" value="${info.PageNo}" />
					</td>
				</tr>
				<tr>
					<th>정렬방식</th>
					<td>
						<select name="AdminAlign" id="AdminAlign">
					<c:forEach var="i" items="${mapAdminAlign}"> 
						<option value="${i.key}"<c:if test="${info.AdminAlign == i.key}"> selected="selected"</c:if>>${i.value}</option>
					</c:forEach>
						</select>
					</td>
					<th>&quot;new&quot;아이콘 표시일</th>
					<td><input type="text" name="NewTime" class="w50" value="${info.NewTime}" />
						일 </td>
				</tr>
				<tr>
					<th>제목 글자 자르기 </th>
					<td><input type="text" name="VSubjectLength" class="w50" value="${info.VSubjectLength}" />
						자</td>
					<th> 이름 글자 자르기 </th>
					<td><input type="text" name="VNameLength" class="w50" value="${info.VNameLength}" />
						자</td>
				</tr>
				<tr>
					<th>리플라이 </th>
					<td colspan="3"><input type="radio" name="ReplyBtn" value="Y"<c:if test="${info.ReplyBtn == 'Y'}">  checked="checked"</c:if> />
						yes
						<input type="radio" name="ReplyBtn" value="N"<c:if test="${info.ReplyBtn == 'N'}">  checked="checked"</c:if> />
						no (yes선택시 리플라이가능)</td>
				</tr>
				<tr>
					<th>자동링크 </th>
					<td colspan="3"><input type="radio" name="AutoLink" value="Y"<c:if test="${info.AutoLink == 'Y'}">  checked="checked"</c:if> />
						yes
						<input type="radio" name="AutoLink" value="Y"<c:if test="${info.AutoLink == 'N'}">  checked="checked"</c:if> />
						no (yes선택시 자동링크 가능)</td>
				</tr>
				<tr>
					<th>코멘트 </th>
					<td colspan="3"><input type="radio" name="CommentEnable" value="Y"<c:if test="${info.CommentEnable == 'Y'}">  checked="checked"</c:if> />
						yes
						<input type="radio" name="CommentEnable" value="0"<c:if test="${info.CommentEnable == 'N'}">  checked="checked"</c:if> />
						no (yes선택시 코멘트(하단글달기)가능)</td>
				</tr>
				<tr>
					<th>리스트페이지삽입 </th>
					<td colspan="3"><input type="radio" name="ListEnable" value="Y"<c:if test="${info.ListEnable == 'Y'}">  checked="checked"</c:if> />
						yes
						<input type="radio" name="ListEnable" value="N"<c:if test="${info.ListEnable == 'N'}">  checked="checked"</c:if> />
						no (yes선택시 view페이지에 리스트페이지삽입)</td>
				</tr>
				<tr>
					<th>금지단어설정(본문) - 예) <br />
						섹스,광고,야동(&quot;,&quot;로 분리) </th>
					<td colspan="3">
						<textarea name="Write_prohibition_Word" rows="5" wrap="virtual" id="Write_prohibition_Word" style="width:99%";>${info.Write_prohibition_Word}</textarea></td>
				</tr>
				<tr>
					<td height="30" colspan="4">회원관련(일부 스킨만 적용가능, 1~10등급가능,
						WizMember와 호환)</td>
				</tr>
				<tr>
					<th>회원전용(읽기)
						<input name="ReadForMember" type="checkbox" id="ReadForMember" value="Y"<c:if test="${info.ReadForMember == 'Y'}">  checked="checked"</c:if> />
					</th>
					<td colspan="3">
						<select name="ReadMemberGrade">
						<c:forEach var="i" items="${MemberGrade}"> 
							<option value="${i.key}"<c:if test="${info.ReadMemberGrade == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>

						<select name="ReadMemberGradeBoundary">
						<c:forEach var="i" items="${mapGradeBoundary}"> 
							<option value="${i.key}"<c:if test="${info.ReadMemberGradeBoundary == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>

						<select name="ReadMemberGenderBoundary">
						<c:forEach var="i" items="${mapGenderBoundary}"> 
							<option value="${i.key}"<c:if test="${info.ReadMemberGenderBoundary == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>

					</td>
				</tr>
				<tr>
					<th>회원전용(쓰기)
						<input name="WriteForMember" type="checkbox" value="Y"{? info.WriteForMember == "Y"} checked="checked"{/} />
					</th>
					<td colspan="3">
						<select name="WriteMemberGrade">
						<c:forEach var="i" items="${MemberGrade}"> 
							<option value="${i.key}"<c:if test="${info.WriteMemberGrade == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>

						<select name="WriteMemberGradeBoundary">
						<c:forEach var="i" items="${mapGradeBoundary}"> 
							<option value="${i.key}"<c:if test="${info.ReadMemberGradeBoundary == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>

						<select name="WriteMemberGenderBoundary">
						<c:forEach var="i" items="${mapGenderBoundary}"> 
							<option value="${i.key}"<c:if test="${info.WriteMemberGenderBoundary == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>회원전용(다운)
						<input name="DownForMember" type="checkbox" value="Y"{? info.DownForMember == "Y"} checked="checked"{/} />
					</th>
					<td colspan="3">
						<select name="DownMemberGrade">
						<c:forEach var="i" items="${MemberGrade}"> 
							<option value="${i.key}"<c:if test="${info.DownMemberGrade == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>

						<select name="DownMemberGradeBoundary">
						<c:forEach var="i" items="${mapGradeBoundary}"> 
							<option value="${i.key}"<c:if test="${info.DownMemberGradeBoundary == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>

						<select name="DownMemberGenderBoundary">
						<c:forEach var="i" items="${mapGenderBoundary}"> 
							<option value="${i.key}"<c:if test="${info.DownMemberGenderBoundary == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>					
					</td>
				</tr>
				<tr>
					<th>회원전용(리스트)
						<input name="ListForMember" type="checkbox" value="Y"{? info.ListForMember == "Y"} checked="checked"{/} />
					</th>
					<td colspan="3">
						<select name="ListMemberGrade">
						<c:forEach var="i" items="${MemberGrade}"> 
							<option value="${i.key}"<c:if test="${info.ListMemberGrade == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>

						<select name="ListMemberGradeBoundary">
						<c:forEach var="i" items="${mapGradeBoundary}"> 
							<option value="${i.key}"<c:if test="${info.ListMemberGradeBoundary == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>

						<select name="ListMemberGenderBoundary">
						<c:forEach var="i" items="${mapGenderBoundary}"> 
							<option value="${i.key}"<c:if test="${info.ListMemberGenderBoundary == i.key}"> selected="selected"</c:if>>${i.value}</option>
						</c:forEach>
						</select>					
					</td>
				</tr>
				<tr>
					<td colspan="4">게시물점수(게시물당 점수 부여)</td>
				</tr>
				<tr>
					<th> 추천 </th>
					<td><input name="bp_recommand" type="text" id="bp_recommand" value="${info.bp_recommand}"  class="w50" />
						포인트 </td>
					<th>비추천</th>
					<td><input name="bp_nonerecommand" type="text" id="commentPoint" value="${info.bp_nonerecommand}" class="w50" />
						(감점:음수기입)</td>
				</tr>
				<tr>
					<th> 코멘트</th>
					<td><input name="bp_reple" type="text" id="bp_reple" value="${info.bp_reple}" class="w50" />
						포인트 </td>
					<th>답글</th>
					<td><input name="bp_reply" type="text" id="commentPoint" value="${info.bp_reply}" class="w50" />
						포인트 </td>
				</tr>
				<tr>
					<td colspan="4">게시물 레벨 설정(상기 게시물에 따른 등급 부여)</td>
				</tr>
				<tr>
					<th>중박 게시물</th>
					<td colspan="3"><input name="np_lv10" type="text" id="np_lv10" value="${info.np_lv10}" class="w50" /></td>
				</tr>
				<tr>
					<th>대박 게시물</th>
					<td colspan="3"><input name="np_lv20" type="text" id="np_lv20" value="${info.np_lv20}" class="w50" /></td>
				</tr>
				<tr>
					<th>명예 게시물</th>
					<td colspan="3"><input name="np_lv30" type="text" id="np_lv30" value="${info.np_lv30}" class="w50" /></td>
				</tr>
				<tr>
					<td colspan="4">게시판 활동점수(게시판 활동을 통한 회원 포인트 설정)<br />
						회수에서 -1 일경우 무제한</td>
				</tr>
				<tr>
					<th> 글쓰기</th>
					<td colspan="3">경험치
						<input type="text" name="writeExp" class="w50" value="${info.writeExp}" />
						,  머니
						<input type="text" name="writePoint" class="w50" value="${info.writePoint}" />
						,
						<input type="text" name="writePer" class="w50" value="${info.writePer}" />
						회(하루)</td>
				</tr>
				<tr>
					<th> 코멘트</th>
					<td colspan="3">경험치
						<input type="text" name="commentExp" class="w50" value="${info.commentExp}" />
						,  머니
						<input name="commentPoint" type="text" id="commentPoint" value="${info.commentPoint}" class="w50" />
						,
						<input type="text" name="commentPer" class="w50" value="${info.commentPer}" />
						회(하루)</td>
				</tr>
				<tr>
					<th> 답글/토론참여</th>
					<td colspan="3">경험치
						<input type="text" name="replyExp" class="w50" value="${info.replyExp}" />
						,  머니
						<input name="replyPoint" type="text" id="replyPoint" value="${info.replyPoint}" class="w50" />
						,
						<input type="text" name="replyPer" class="w50" value="${info.replyPer}" />
						회(하루)</td>
				</tr>
				<tr>
					<th> 추천/비추천</th>
					<td colspan="3">경험치
						<input type="text" name="rccomExp" class="w50" value="${info.rccomExp}" />
						,  머니
						<input name="rccomPoint" type="text" id="rccomPoint" value="${info.rccomPoint}" class="w50" />
						,
						{info.rccomPer}
						회(총게시판별)</td>
				</tr>
				<tr>
					<td colspan="4">파일업로딩 금지 확장자 설정하기(","로 나열)</td>
				</tr>
				<tr align="center">
					<td colspan="4"><textarea name="ProhibitExtention" style="width:98%;">{info.ProhibitExtention}
				</textarea></td>
				</tr>
				<tr>
					<td colspan="4">카테고리사용
						<input name="CategoryEnable" type="checkbox" value="Y"<c:if test="${info.CategoryEnable == 'Y'}">  checked="checked"</c:if> />
						(카테고리 링크방법은 /board/boardList.do?bid=보드아이디&amp;gid=그룹아이디&amp;category=카테고리번호)</td>
				</tr>
				<tr>
					<td colspan="4">표시형식 :
						<input type="radio" name="CategoryType" value="radio"<c:if test="${info.CategoryType == 'radio'}">  checked="checked"</c:if> />
						라디오버튼
						<input type="radio" name="CategoryType" value="select"<c:if test="${info.CategoryType == 'select'}">  checked="checked"</c:if> />
						실렉트버튼</td>
				</tr>


				<tr>
					<td colspan="4" id="categoryList"><!-- 카테고리 리스트 입력 --></td>
				</tr>
				<tr>
					<td colspan="4">획득포인트별 제목 강조</td>
				</tr>
				<tr>
					<td colspan="4"><table>
							<tr align="center" bgcolor="F7F7ED">
								<td>점수(이상)</td>
								<td>강조옵션</td>
								<td bgcolor="F7F7ED">삭제</td>
							</tr>
							<tr align="center" bgcolor="F7F7F7">
								<td><input name="point" type="text" id="point" style="width:50px" /></td>
								<td bgcolor="F7F7F7">아이콘
									<select name="op_icon" id="op_icon">
									</select>
									, 강조
									<input name="op_strong" type="checkbox" id="op_strong" value="1" />
									, 색상
									<input type="text" name="op_color" id="op_color" />
									, 이태릭
									<input name="op_em" type="checkbox" id="op_em" value="1" /></td>
								<td><span class='button bull'><a id="btn_reg_point_opt">등록</a></span></td>
							</tr>
							<span id="optionpointHTML">

							</span>

							<!-- 수정용 -->

						</table></td>
				</tr>

			</table><span class='button bull'><a id="btn_save">확인</a></span>
			</form>




</body>
</html>