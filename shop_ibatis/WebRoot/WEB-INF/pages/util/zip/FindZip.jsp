<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>우편번호찾기</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/jquery.plugins/jquery-ui-1.7.2/js/jquery-ui-1.7.2.custom.min.js"/>"></script>

		<link rel="stylesheet" href="<c:url value="/css/base.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/css/layout.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/css/homepage.css"/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/js/jquery.plugins/jqalerts/jquery.alerts.css"/>" type="text/css" />

		<script language=javascript src="<c:url value="/js/jquery.plugins/jquery.validator.js"/>"></script>
		<script language="javascript">
			$(function(){
				$("#btn_search1").click(function(){
					if($('#zipSearchForm').formvalidate()){
						$("#zipSearchForm").submit();
					}
				});

				$(".addressLIst").dblclick(function(){
					var sel_val = $(this).val();
					if(sel_val == ""){
						alert('선택된 값이 없습니다.');
					}
					else{
						var split = sel_val.split("^");
						var split1	= split[0].split("-");
						$("#sel_zip1").val(split1[0]);
						$("#sel_zip2").val(split1[1]);
						$("#sel_address1").val(split[1]);
						//alert(split[1]);
						$(".searchResult1").hide();
						$(".searchResult2").show();
					}
 					//onDblClick='javascript:GotoStep3(this.form);
				})

				$(".btn_close").click(function(){
					self.close();
				})
				$(".btn_goback").click(function(){
					history.go(-1);
				})

				$(".btn_apply_address").click(function(){
					$("#${zip1}",opener.document).val($("#sel_zip1").val());
					$("#${zip2}",opener.document).val($("#sel_zip2").val());
					$("#${firstaddress}",opener.document).val($("#sel_address1").val());
					$("#${secondaddress}",opener.document).val($("#sel_address2").val());
					$("#${secondaddress}",opener.document).focus();

					window.close();
					return false;
				});

			});

			window.resizeTo(412,290);

		//-->
		</script>
		</head>
<body topmargin="0" leftmargin="0" >


<table height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" bgcolor="#F5F5F5"><table width="385" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
        <tr>
          <td width="350" height="35">우편번호
            찾기</td>
          <td align="right"><a href="javascript:self.close();">닫기</a></td>
        </tr>

	<c:choose>
	<c:when test="${searchStep == 'step1'}">
		<!-- 초기화면 로딩 시작 -->
        <tr>
          <td colspan="2" style="padding:10 0 10 25"> 찾고자
            하는 주소의 동(읍/면/리)를 입력해주세요 <BR>
            &nbsp;&nbsp;예) 역삼2동, 서초동, 송파동 </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          <form method=post id="zipSearchForm">
              <input type="hidden" name="form" value="${form}" />
              <input type="hidden" name="zip1" value="${zip1}" />
              <input type="hidden" name="zip2" value="${zip2}" />
              <input type="hidden" name="firstaddress" value="${firstaddress}" />
              <input type="hidden" name="secondaddress" value="${secondaddress}" />
              <input type="hidden" name="searchStep" value="step2" />
              <table  width="363" height="40" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td align="center"><input type="text" name="keyword" class="required" msg="주소를 입력해 주세요">
                    &nbsp;
                    <span class="button bull"><a id="btn_search1">검색</a></span>
                  </td>
                </tr>
              </table>
            </form>
            </td>
        </tr>

        <!-- 초기화면 로딩 끝-->
</c:when>
<c:when test="${searchStep == 'step2'}">

		<!-- 검색결과 리스트 시작-->
		<script>window.resizeTo(412,370);</script>
        <tr>
          <td colspan="2" align="center">

            아래의 주소 중에서 선택하신후 <b>더블클릭</b> 해주세요.

            <form name="zipSearchForm" id="zipSearchForm" method ="post">
              <input type="hidden" name="form" value="${form}" />
              <input type="hidden" name="zip1" value="${zip1}" />
              <input type="hidden" name="zip2" value="${zip2}" />
              <input type="hidden" name="firstaddress" value="${firstaddress}" />
              <input type="hidden" name="secondaddress" value="${secondaddress}" />
              <input type="hidden" name="searchStep" value="step3" />
              <table width="340" border="0" cellpadding="2" cellspacing="1" bgcolor="dddddd" class="searchResult1">
                <tr>
                  <td><select name='selAddress' style='width:340px' size='6' class="addressLIst"'>

<c:forEach items="${info}" var="current" varStatus="i">
	<option value='${current.zipcode}^${current.sido} ${current.gugun} ${current.dong}'>${current.sido} ${current.gugun} ${current.dong} ${current.bunji}</option>
</c:forEach>
                    </select>
                  </td>
                </tr>
                </table>
                <div class="agn_c searchResult1">
                <span class="button bull btn_close"><a>닫기</a></span>
                <span class="button bull btn_goback"><a>뒤로가기</a></span>
                </div>


              <table width="340" border="0" cellpadding="2" cellspacing="1" bgcolor="dddddd" class="none searchResult2">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">

                      <tr>
                        <td bgcolor="e5e5e5" width="1"></td>
                        <td bgcolor="f4f4f4">

                        <p>나머지
                            주소를 입력하시고, '주소입력'을 <b><font color="#CC3399">클릭</b>하세요.</p>

                            </td>
                        <td bgcolor="e5e5e5" width="1"></td>
                      </tr>
                      <tr>
                        <td bgcolor="e5e5e5" width="1"></td>
                        <td bgcolor="f4f4f4" width="100%" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td align=right>&nbsp;<b>우편 번호</b>&nbsp;</td>
                              <td align="left" width="220"><input type='text' id="sel_zip1" size=3 value='' readonly >
                                -
                                <input type='text' id="sel_zip2" size=3 value='' readonly >
                              </td>
                            </tr>
                            <tr>
                              <td height="10" colspan=2></td>
                            </tr>
                            <tr>
                              <td align=right valign=top>&nbsp;<b>주소</b>&nbsp;</td>
                              <td align=left valign=top>
                                <br>
                                <input type="text" id="sel_address1" value="" readonly>
                                <input type="text" id="sel_address2" size=40 maxlength=50 value=""  class="required" msg="상세 주소를 입력해 주세요"></td>
                            </tr>
                            <tr>
                              <td>&nbsp;</td>
                              <td>&nbsp;</td>
                            </tr>
                          </table></td>
                        <td bgcolor="E5E5E5" width="1"></td>
                      </tr>
                    </table></td>
                </tr>

              </table>
              <div class="agn_c none searchResult2">
	              <span class="button bull btn_apply_address"><a>적용</a></span>
	              <span class="button bull btn_close"><a>닫기</a></span>
	              <span class="button bull btn_goback"><a>새로검색</a></span>
              </div>
            </form>
<!-- 상세우편번호 넣기 끝-->
<!-- 검색결과 리스트 끝-->

</c:when>
<c:when test="${searchStep == 'step3'}">

		</c:when>
	   <c:otherwise>
           	잘못된 접근입니다.
       </c:otherwise>
	</c:choose>

          </td>
        </tr>

      </table></td>
  </tr>
</table>
</body>
</html>