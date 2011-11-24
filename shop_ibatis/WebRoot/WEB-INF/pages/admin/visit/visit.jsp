<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Visit Statisics</title>

<script  language="javascript" src="<c:url value="/js/jquery.plugins/jquery.wizchart-1.0.3.js"/>"></script>
<script  language="JavaScript">
<!--
$(function(){
	$( ".s_date" ).datepicker( {dateFormat:"yy-mm-dd"} );
	$(".uniquebar").chart({	height:5,bgcolor:"blue"});
	$(".pageviewbar").chart({	height:5,bgcolor:"red"});

	$(".btn_set_date").click(function(){

		$("#s_form").submit();
	});
});


function reset_confirm(){
	if(confirm('\n초기화 하시겠습니까?\n 초기화 하실경우 기존 방문자 통계 로그는 초기화 됩니다.\n')){
		location.href="${pageContext.request.contextPath}/admin/visit/visit.do?no=8";
	}else return;
}
//-->
</script>
<style type="text/css">
<!--
li {
	list-style:none;
	height:10px
}
-->
</style>
</head>
<body>
<table class="table_outline">
	<tr>
		<td><fieldset class="desc">
			<legend>방문자통계</legend>
			<div class="notice">[note]</div>
			<div class="comment">unique 값은 금일 순수 방문자수를 표시합니다.(1인 1번 이상 책크되지
				않음)<br />
				초기화를 클릭하시면 방문자가 초기화 됩니다. <br />
				방문자 로그는 데이타베이스에 상당한 량을 차지하므로 주기적으로 초기화 시켜 주시기 바랍니다.</div>
			</fieldset>
			<div class="space20"></div>
			<form method="post" id="s_form" action="${pageContext.request.contextPath}/admin/visit/visit.do?no=${params.no}">
				<input type="hidden" name="no" value="<?=$no?>">
				<table class="table_main">
					<tr>
						<td><div>
								<div class="fleft">방문자 통계 보기</div>
								<div class="fright"> <span class="button bull"><a href="javascript:reset_confirm();">초기화 하기</a></span></div>
							</div></td>
					</tr>
					<tr>
						<td><input type="text" class="w100 s_date" name="s_date" value="${params.s_date}" />
							<div class="fright"> <span class="button bull btn_set_date"><a>방문자통계보기</a></span></div>
						</td>
					</tr>
				</table>
			</form>
			<div class="space20"></div>

<c:choose>
	<c:when test="${params.no == '1'}">
<!--
// 총방문자통계
//-->
			<table class="table_main">
				<tr>
					<td>전체 방문자수 : ${data.total_hit}
						<br />
						전체 페이지뷰 : ${data.total_view}
					</td>
				</tr>
				<tr>
					<td> 오늘 방문자수 : ${data.today_hit}
						<br />
						오늘 페이지뷰 : ${data.today_view}
					</td>
				</tr>
				<tr>
					<td>어제 방문자수 : ${data.yesterday_hit}
						<br />
						어제 페이지뷰 : ${data.yesterday_view}
					</td>
				</tr>
				<tr>
					<td>최고 방문자수 : ${data.max_hit}
						<br />
						최고 페이지뷰 : ${data.max_view}
					</td>
				</tr>
				<tr>
					<td>최저 방문자수 : ${data.min_hit}
						<br />
						최저 페이지뷰 : ${data.min_view}
					</td>
				</tr>
			</table>
</c:when>
<c:when test="${params.no == '2'}">
<!--
// 금일방문자통계
//-->
			<ul class="ul_normal_list">
				<li>
					${rtn_date[1]} 월 ${rtn_date[2]} 일 고유방문자수 : ${today.today_hit}
				</li>
				<li>
					${rtn_date[1]} 월 ${rtn_date[2]} 일 페이지뷰 : ${today.today_view}
				</li>
				<li>
					${rtn_date[1]} 월 ${rtn_date[2]} 일 시간대별 고유 방문자 및 페이지뷰수</li>
			</ul>
			<table class="table_main">
				<col width="70px" />
				<col width="*" />
				<col width="150px" />
				<tbody>


<c:forEach var="current" items="${data}" varStatus="i">
<c:set var="per_hit"  value="${current.hit/max.max_hit * 100}" />
<c:set var="per_view"  value="${current.view/max.max_view * 100}" />
<c:if test="${current.hit == '0'}"><c:set var="per_hit"  value="0" /></c:if>
<c:if test="${current.view == '0'}"><c:set var="per_view"  value="0" /></c:if>
					<tr>
						<td>-
							${i.index}
							시 </td>
						<td><ul style="text-align:left">
								<li  ratio="${per_hit}" class="uniquebar" alt='${i.index}시 방문자 : ${current.hit}'></li>
								<li  ratio="${per_view}" class="pageviewbar" alt='${i.index}시 페이지뷰 : ${current.view} '></li>
							</ul></td>
						<td> Unique : ${current.hit}
							<br />
							 PageView : ${current.view}
						</td>
					</tr>
</c:forEach>
				</tbody>
			</table>
</c:when>
<c:when test="${params.no == '3'}">

			<ul class="ul_normal_list">
				<li>${day_term.startday} ~ ${day_term.endday} 고유 방문자수 :
					${WeekTotal.today_hit}
				</li>
				<li>${day_term.startday} ~ ${day_term.endday} 일 페이지뷰 :
					${WeekTotal.today_view}
				</li>
				<li>${day_term.startday} ~ ${day_term.endday} 일 일자별 고유 방문자 및 페이지뷰수 </li>
			</ul>
			<table class="table_main">
				<col width="70px" />
				<col width="*" />
				<col width="150px" />
				<tbody>
<c:forEach var="current" items="${data}" varStatus="i">
<c:set var="per_hit"  value="${current.hit/max.max_hit * 100}" />
<c:set var="per_view"  value="${current.view/max.max_view * 100}" />
<c:if test="${current.hit == '0'}"><c:set var="per_hit"  value="0" /></c:if>
<c:if test="${current.view == '0'}"><c:set var="per_view"  value="0" /></c:if>
<c:choose>
       <c:when test="${i.index == 0}"><c:set var="week"  value="일요일" /></c:when>
       <c:when test="${i.index == 1}"><c:set var="week"  value="월요일" /></c:when>
       <c:when test="${i.index == 2}"><c:set var="week"  value="화요일" /></c:when>
	   <c:when test="${i.index == 3}"><c:set var="week"  value="수요일" /></c:when>
	   <c:when test="${i.index == 4}"><c:set var="week"  value="목요일" /></c:when>
	   <c:when test="${i.index == 5}"><c:set var="week"  value="금요일" /></c:when>
	   <c:when test="${i.index == 6}"><c:set var="week"  value="토요일" /></c:when>
       <c:otherwise></c:otherwise>
   </c:choose>
					<tr>
						<td>-

   ${week}
							(
							<?=$md[$i]?>
							) </td>
						<td><div>
								<ul style="text-align:left">
									<li ratio="${per_hit}" class="uniquebar" alt='${week} 방문자수 : ${current.hit}'></li>
									<li ratio="${per_view}"  class="pageviewbar" alt='${week} 페이지뷰 : ${current.view}'></li>
								</ul>
							</div></td>
						<td> Unique : ${current.hit}
							<br />
							  PageView : ${current.view}
						</td>
					</tr>
</c:forEach>
				</tbody>
			</table>
</c:when>
<c:when test="${params.no == '4'}">
<!--
// 월간방문자통계
//-->
			<ul class="ul_normal_list">
				<li>
					${rtn_date[1]}월 방문자수 : ${total.hit}
				</li>
				<li>
					${rtn_date[1]}월 페이지뷰 :	${total.view}
				</li>
			</ul>
			<table class="table_main">
				<col width="70px" />
				<col width="*" />
				<col width="150px" />
				<tbody>
<c:forEach var="current" items="${data}" varStatus="i">
<c:set var="per_hit"  value="${current.hit/max.max_hit * 100}" />
<c:set var="per_view"  value="${current.view/max.max_view * 100}" />
<c:if test="${current.hit == '0'}"><c:set var="per_hit"  value="0" /></c:if>
<c:if test="${current.view == '0'}"><c:set var="per_view"  value="0" /></c:if>

					<tr>
						<td>-
							${i.index + 1}일
						</td>
						<td><div>
								<ul style="text-align:left">
									<li ratio="${per_hit}" class="uniquebar" alt='Unique : ${current.hit}'></li>
									<li ratio="${per_view}" class="pageviewbar" alt='PageView : ${current.view}'></li>
								</ul>
							</div></td>
						<td>Unique : ${current.hit}
							<br />
							  PageView : ${current.view}
						</td>
					</tr>
</c:forEach>
				</tbody>
			</table>
</c:when>
<c:when test="${params.no == '5'}">
<!--
// 년간방문자통계
//-->
			<ul class="ul_normal_list">
				<li>
					${rtn_date[0]}년 방문자수 :${total.hit} </li>
				<li>
					${rtn_date[0]}년 페이지뷰 :${total.view}
				</li>
			</ul>
			<table class="table_main">
				<col width="70px" />
				<col width="*" />
				<col width="150px" />
				<tbody>
<c:forEach var="current" items="${data}" varStatus="i">
<c:set var="per_hit"  value="${current.hit/max.max_hit * 100}" />
<c:set var="per_view"  value="${current.view/max.max_view * 100}" />
<c:if test="${current.hit == '0'}"><c:set var="per_hit"  value="0" /></c:if>
<c:if test="${current.view == '0'}"><c:set var="per_view"  value="0" /></c:if>
					<tr>
						<td>- ${i.index + 1} 월
						</td>
						<td><div>
								<ul style="text-align:left">
									<li ratio="${per_hit}" class="uniquebar" alt='${i.index + 1}월 방문자수 : ${current.hit}'></li>
									<li ratio="${per_view}" class="pageviewbar" alt='${i.index + 1}월 페이지뷰 : ${current.view}'></li>
								</ul>
							</div></td>
						<td>Unique : ${current.hit}
							<br />
							  PageView : ${current.view}
						</td>
					</tr>
</c:forEach>
				</tbody>
			</table>
</c:when>
<c:when test="${params.no == '6'}">
<!--
// 방문자경로
//-->
elseif($no=="6"):
?>
			<table class="table_main">
				<tr>
					<td><?
	$whereis = "where date >= $fromtoday and date <= $totoday";
	$sqlstr = "select count(referer) as total, referer from wizcounter_referer $whereis group by referer order by total desc";
  $ip=$dbcon->_query("$sqlstr");
  while($data=$dbcon->_fetch_array($ip))
  {
  $data[referer] = urldecode($data[referer]);
   if(strlen($data[referer])>90)
   {
    $temp=substr($data[referer],0,90);
    $text="$temp...";
   }
   else $text=$data[referer];
   if(!eregi("Typing or Bookmark", $data[referer])) $data[referer]="<a href=$data[referer] target=_blank><font color='black'>$text</a>";
   echo "&nbsp;&nbsp;&nbsp; - $data[referer] ($data[total])<br />";
  }
?></td>
				</tr>
			</table>
</c:when>
<c:when test="${params.no == '7'}">
<!--
// 검색사별
//-->
			<table class="table_main">
				<tr>
					<td><?
  $sqlqry=$dbcon->_query("select count(referer) as total, referer from wizcounter_referer where date > $month_start and date <='$today' group by referer order by total desc");
  $k=0;
  while($list = $dbcon->_fetch_array()):
  $split_unique_referer = split("\/", $list[referer]);
  if($split_unique_referer[2])
  $unique_referer[$k] = "http://".$split_unique_referer[2];
  else $unique_referer[$k] = "Typing or Bookmark Moving On This Site";
  $k++;
  endwhile;
/* 월간 경로중 unique 한 값을 구한다. */
if(is_array($unique_referer)){
  $unique_referer_value = array_unique($unique_referer);
  $k=0;
  for($i=0; $i < sizeof($unique_referer); $i++){
     if($unique_referer_value[$i]){
     $refer_value[$k] = $unique_referer_value[$i];
     $k++;
     }
  }
}

/* 아래는 실제 unique 한 방문 경로를 넣어서 실제 값을 도출 한다.*/
for($i=0; $i < sizeof($refer_value); $i++){


  $hit=$dbcon->get_one("select sum(hit) from wizcounter_referer where date > $month_start and date <='$today' and referer like '$refer_value[$i]%' order by hit desc");
  $countNo++;


   echo "&nbsp;&nbsp;&nbsp; - $refer_value[$i] ($hit)<br />";

  }

?></td>
				</tr>
			</table>
</c:when>
<c:when test="${params.no == '9'}">
<!--
// 검색엔진별 방문자 분석
//-->

			<table class="table_main">
				<tr>
					<th>검색엔진</th>
					<th>검색어(일부 검색어는 누락되어 표기 될 수 도 있습니다.)</th>
					<th>방문자수<br />
						unique(total) </th>
				</tr>
				<?
reset($engin);
while(list($key, $value) = each($engin)):
	$whereis = "where date >= $fromtoday and date <= $totoday and referer like '%$key%'";
	$groupby = "group by referer";
	$sqlstr = "select distinct(ip), referer  from wizcounter_referer $whereis $groupby";
	$sqlqry=$dbcon->_query($sqlstr);
		$unique_no=0;
		unset($serchingkeyword[$unique_no]);
		while($list = $dbcon->_fetch_array()):
			$referer = urldecode($list[referer]);
			//echo "referer = $referer <br />";
			$tmpvalue = preg_match("/^.*?(.*)/i", $referer, $matches);
				$tmpArr = split("&",  $matches[1]);
				while(list($key1, $value1) = each($tmpArr)):
					if(eregi($value."=",$value1)){
						$serchingkeyword[$unique_no] = str_replace($value."=","", $value1);
						break;
					}
				endwhile;
		$unique_no++;

		endwhile;

	$whereis = "where date >= $fromtoday and date <= $totoday and referer like '%$key%'";
	$sqlstr1 = "select referer  from wizcounter_referer $whereis";
	$sqlqry1=$dbcon->_query($sqlstr1);
	$total_no = $dbcon->_num_rows($sqlqry1);
?>
				<tr>
					<td><? echo $key; ?></td>
					<td><?
						  while(list($key1, $value1) = @each($serchingkeyword)):
						if($value1)  echo urldecode($value1)."<br />";
						  endwhile;
						  ?>
					</td>
					<td><? echo $unique_no."(".$total_no.")"; ?></td>
				</tr>
				<?
endwhile;
?>
				<!-- <tr>
                          <td><? echo $key; ?></td>
                          <td><? echo urldecode($keyword); ?></td>
                          <td><? echo $total; ?></td>
                        </tr> -->
			</table>


마지막 삭제
	$substr="truncate table wizcounter_referer";
	$result2 = $dbcon->_query($substr);

</c:when>
</c:choose>


		</td>
	</tr>
</table>






</body>
</html>