<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>관리자 Left Menu</title>
    </head>
	<body>

<table width="188" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="188" height="75" align='center' background="img/adminlogin_bg.gif"><table width="94%" border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td height="50" colspan="2" align="center" bgcolor="A8E5A8" style="font-size:12px; color:#FFFFFF">
			관리자 님으로<br />
            접속중입니다</td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="275903"><table width="100" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td ><a href="${pageContext.request.contextPath}/" target="_blank"><img src="${pageContext.request.contextPath}/images/admin/adminsite_icon.gif" width="108" height="29" border="0" /></a></td>
          <td ><a href="${pageContext.request.contextPath}/logout"><img src="${pageContext.request.contextPath}/images/admin/adminlogin_icon03.gif" width="70" height="29" border="0" /></a></td>
        </tr>
      </table></td>
  </tr>
</table>
<img src="${pageContext.request.contextPath}/images/admin/adminS_title01.gif" width="187" height="70">
<c:choose>
  <c:when test="${LeftMenu == 'menu0'}">
    <div id="menu0"> </div>
  </c:when>
  <c:when test="${LeftMenu == 'menu1'}">
<div id="menu1">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />기본환경</div>
  <ul class="leftmenulist">
    <li><a href="${pageContext.request.contextPath}/admin/basic/basicInfo.do">기본환경설정</a></li>
    <li><a href="${pageContext.request.contextPath}/admin/basic/payInfo.do">결제환경 설정</a></li>
	<li><a href="${pageContext.request.contextPath}/admin/basic/memberInfo.do">회원관리 설정</a></li>
	<li><a href="${pageContext.request.contextPath}/admin/basic/bannerList.do">베너관리</a></li>
	<li><a href="${pageContext.request.contextPath}/admin/basic/deliverer.do">택배사관리</a></li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu2'}">
<div id="menu2">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />상품관리</div>
  <ul class="leftmenulist">
    <li><a href="${pageContext.request.contextPath}/admin/product/productWrite.do">상품신규등록</a></li>
    <li><a href="${pageContext.request.contextPath}/admin/product/productList.do">상품수정/삭제</a> </li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu3'}">
<div id="menu3">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />매장관리</div>
  <ul class="leftmenulist">
    <li><a href="${pageContext.request.contextPath}/admin/category.do">카테고리설정</a></li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu4'}">
<div id="menu4">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />주문/배송관리</div>
  <ul class="leftmenulist">
    <li><a href="${pageContext.request.contextPath}/admin/order/orderList.do">주문상품보기</a></li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu5'}">
<div id="menu5">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />제휴업체관리</div>
  <ul class="leftmenulist">
    <li><a href="./main.php?menushow=menu1&theme=basicconfig/basic_info2">기본환경설정</a></li>
  </ul>
  <table width="187" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" valign="top"></td>
    </tr>
    <tr>
      <td align="left" valign="top" bgcolor="f0f0f0" height="5"><table width="187" border="0" cellspacing="0" cellpadding="0" bgcolor="f0f0f0">
          <tr>
            <td align="left" valign="top"><table width="187" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td align="left" valign="top"><table width="190" border="0" cellspacing="0" cellpadding="0" class="notice">
                      <tr>
                        <td align="left" valign="top" height="2"><img src="img/admin_left_sub_bar.gif" width="180" height="2"></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top"><table width="190" border="0" cellspacing="0" cellpadding="0" height="25">
                            <tr align="left">
                              <td width="25" align="center" bgcolor="#FFFFFF"><img src="img/erpleft_icon.gif" width="9" height="9"></td>
                              <td bgcolor="#FFFFFF"><a><strong><font color="#666666" style="font-size:18px">입점몰</font></strong></a></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top" height="2"><img src="img/admin_left_sub_bar.gif" width="180" height="2"></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top"><table width="190" border="0" cellspacing="0" cellpadding="0" height="25">
                            <tr align="left">
                              <td width="25" align="center"><img src="img/erpleft_icon.gif" width="9" height="9"></td>
                              <td><a href="./main.php?menushow=menu5&theme=agent/naver">네이버입점몰</a></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top" height="2"><img src="img/admin_left_sub_bar.gif" width="180" height="2"></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top"><table width="190" border="0" cellspacing="0" cellpadding="0" height="25">
                            <tr align="left">
                              <td width="25" align="center" bgcolor="#FFFFFF"><img src="img/erpleft_icon.gif" width="9" height="9"></td>
                              <td bgcolor="#FFFFFF"><a><strong><font color="#666666" style="font-size:18px">PG 서비스</font></strong></a></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top" height="2"><img src="img/admin_left_sub_bar.gif" width="180" height="2"></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top"><table width="190" border="0" cellspacing="0" cellpadding="0" height="25">
                            <tr align="left">
                              <td width="25" align="center"><img src="img/erpleft_icon.gif" width="9" height="9"></td>
                              <td><a href="http://www.bluepay.co.kr/" target="_blank">블루페이</a><a href="./main.php?menushow=menu6&theme=member/member1&grade=5"></a></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top" height="2"><img src="img/admin_left_sub_bar.gif" width="180" height="2"></td>
                      </tr>

                      <tr>
                        <td align="left" valign="top"><table width="190" border="0" cellspacing="0" cellpadding="0" height="25">
                            <tr align="left">
                              <td width="25" align="center" bgcolor="#FFFFFF"></td>
                              <td bgcolor="#FFFFFF"><a><strong><font color="#666666" style="font-size:18px">가격비교사이트</font></strong></a></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top" height="2"><img src="img/admin_left_sub_bar.gif" width="180" height="2" /></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top"><table width="190" border="0" cellspacing="0" cellpadding="0" height="25">
                            <tr align="left">
                              <td width="25" align="center"><img src="img/erpleft_icon.gif" width="9" height="9" /></td>
                              <td><a href="./main.php?menushow=menu5&amp;theme=agent/compare">가격비교사이트</a><a href="./main.php?menushow=menu6&theme=member/member1&grade=5"></a></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top" height="2"><img src="img/admin_left_sub_bar.gif" width="180" height="2"></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top">&nbsp;</td>
                      </tr>
                    </table></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu6'}">
<div id="menu6">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />회원가입정보</div>
  <ul class="leftmenulist">
    <li><a href="${pageContext.request.contextPath}/admin/member/memberList.do">총회원정보보기</a></li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu7'}">
<div id="menu7">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />보드관리</div>
  <ul class="leftmenulist">
<c:forEach var="i" items="${gnameList}"> 
    <li style="background:#E8E8E8; height:30px; padding:5px 0 0 20px">
      ${i.value}
    </li>	
	<c:forEach var="j" items="${mainList}"> 
    <li> <a href="${pageContext.request.contextPath}/admin/board/boardList.do?bid=${j.bid}&gid=${j.gid}&opflag=${opFlag}">
      ${j.title}
      </a></li>
	  </c:forEach>
</c:forEach>
    <li style="height:30px"></li>
    <li><a href="${pageContext.request.contextPath}/admin/board/boardManageList.do"> 보드관리</a></li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu8'}">
<div id="menu8">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />방문자통계</div>
  <ul class="leftmenulist">
    <li><a href="${pageContext.request.contextPath}/admin/visit/visit.do?no=1">총방문자통계</a></li>
    <li><a href="${pageContext.request.contextPath}/admin/visit/visit.do?no=2">금일방문자통계</a></li>
    <li><a href="${pageContext.request.contextPath}/admin/visit/visit.do?no=3">주간방문자통계</a></li>
    <li><a href="${pageContext.request.contextPath}/admin/visit/visit.do?no=4">월간방문자통계</a></li>
    <li><a href="${pageContext.request.contextPath}/admin/visit/visit.do?no=5">년간방문자통계</a></li>
    <li><a href="${pageContext.request.contextPath}/admin/visit/visit.do?no=6">방문자경로</a></li>
    <li><a href="${pageContext.request.contextPath}/admin/visit/visit.do?no=9">검색사별</a></li>
    <li><a href="${pageContext.request.contextPath}/admin/visit/visit.do?no=7">월간방문자경로</a></li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu9'}">
<div id="menu9">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />매출/통계분석</div>
  <ul class="leftmenulist">
    <li><a href="./main.php?menushow=menu9&theme=mallstatistic/statistic2">제품별판매분석</a></li>
    <li><a href="./main.php?menushow=menu9&theme=mallstatistic/statistic10">일별판매분석</a></li>
    <li><a href="./main.php?menushow=menu9&theme=mallstatistic/statistic6">월별 결제분석 통계</a></li>
    <li><a href="./main.php?menushow=menu9&theme=mallstatistic/statistic4">구매지역</a></li>
    <li><a href="./main.php?menushow=menu9&theme=mallstatistic/statistic9">판매처별
      미수관리 </a></li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu10'}">
<div id="menu10"> </div>
  </c:when>
  <c:when test="${LeftMenu == 'menu11'}">
<div id="menu11">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />메일발송</div>
  <ul class="leftmenulist">
    <li><a href="./main.php?menushow=menu11&theme=mail/mailer1">메일 발송</a></li>
    <li><a href="./main.php?menushow=menu11&theme=mail/mailer3">발송된메일</a></li>
    <li><a href="./main.php?menushow=menu11&theme=mail/address1">주소록관리</a></li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu12'}">

  </c:when>
  <c:when test="${LeftMenu == 'menu13'}">
<div id="menu13">
  <div id="title"><img src="${pageContext.request.contextPath}/images/admin/ba_icon01.gif" width="38" height="33" />기타관리</div>
  <ul class="leftmenulist">
    <li><a href="./main.php?menushow=menu13&theme=inquire/inquire1&iid=INQ1">A/S신청문의</a></li>
    <li><a href="./main.php?menushow=menu13&theme=util/util1">투표창관리</a></li>
    <li><a href="./main.php?menushow=menu13&theme=util/popup1">팝업창관리</a></li>
    <li><a href="./main.php?menushow=menu13&theme=util/util2">다이어리</a></li>
    <li><a href="./main.php?menushow=menu13&theme=coupon/coupon_list">쿠폰관리</a></li>
    <li><a href="./main.php?menushow=menu13&theme=util/webftp">WEB FTP</a></li>
  </ul>
</div>
  </c:when>
  <c:when test="${LeftMenu == 'menu14'}">

  </c:when>
  <c:otherwise> </c:otherwise>
</c:choose>
	</body>
</html>
