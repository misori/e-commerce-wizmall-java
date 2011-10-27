<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<title>Visit Statisics</title>

<script  language="javascript" src="<c:url value="/js/jquery.plugins/jquery.wizchart-1.0.3.js"/>"></script>
<script  language="JavaScript">
<!--
$(function(){
	$(".uniquebar").chart({	height:5,bgcolor:"blue"});
	$(".pageviewbar").chart({	height:5,bgcolor:"red"});
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
			<form method="post" action="<?=$PHP_SELF?>">
				<input type="hidden" name="no" value="<?=$no?>">
				<table class="table_main">
					<tr>
						<td><div>
								<div class="fleft">방문자 통계 보기</div>
								<div class="fright"> <span class="button bull"><a href="javascript:reset_confirm();">초기화 하기</a></span></div>
							</div></td>
					</tr>
					<tr>
						<td>Year :
							<select name="year">
								<?
$ThisYear = date("Y");
for($i=${ThisYear};$i>2003;$i--) {
if($year == $i) echo "<option value='$i' selected>$i</option>\n";
else echo "<option value='$i'>$i</option>\n";
}
?>
							</select>
							&nbsp;&nbsp; Month :
							<select name="month">
								<?
for($i=1;$i<=12;$i++) {
if($month == $i) echo "<option value='$i' selected>$i</option>\n";
else echo "<option value='$i'>$i</option>\n";
}
?>
							</select>
							&nbsp; Day :
							<select name="day">
								<?
for($i=1;$i<=31;$i++) {
if($day == $i) echo "<option value='$i' selected>$i</option>\n";
else echo "<option value='$i'>$i</option>\n";
}
?>
							</select>
							<input type=image width=51 src="img/move.gif" align=absMiddle name="image">
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
					<?=$month?>
					월
					<?=$day?>
					일 고유방문자수 :
					<?=$count[today_hit]?>
				</li>
				<li>
					<?=$month?>
					월
					<?=$day?>
					일 페이지뷰 :
					<?=$count[today_view]?>
				</li>
				<li>
					<?=$month?>
					월
					<?=$day?>
					일 시간대별 고유 방문자 및 페이지뷰수</li>
			</ul>
			<table class="table_main">
				<col width="70px" />
				<col width="*" />
				<col width="150px" />
				<tbody>
					<?
  $max1=1;
  $max2=1;
  for($i=0;$i<24;$i++)
  {
   $time1=mktime($i,0,0,$month,$day,$year);
   $time2=mktime($i,59,59,$month,$day,$year);
   $sqlstr = "select distinct(ip) from wizcounter_referer where date>='$time1' and date<='$time2'";
   $dbcon->_query($sqlstr);
   $time_count1[$i]=$dbcon->_num_rows();

   $sqlstr = "select ip from wizcounter_referer where date>='$time1' and date<='$time2'";
   $dbcon->_query($sqlstr);
   $time_count2[$i]=$dbcon->_num_rows();

   if($max1<$time_count1[$i]) $max1=$time_count1[$i];
   if($max2<$time_count2[$i]) $max2=$time_count2[$i];
  }

  for($i=0;$i<24;$i++)
  {
   $per1=(int)($time_count1[$i]/$max1*100);
   $per2=(int)($time_count2[$i]/$max2*100);
   if($per1>100) $per1=99;
   if($per2>100) $per2=99;
?>
					<tr>
						<td>-
							<?=$i?>
							시 </td>
						<td><ul style="text-align:left">
								<li  ratio="<?=$per1?>" class="uniquebar" alt='<?=$i?>시 방문자 : <?=$time_count1[$i]?>'></li>
								<li  ratio="<?=$per2?>" class="pageviewbar" alt='<?=$i?>시 페이지뷰 : <?=$time_count2[$i]?>'></li>
							</ul></td>
						<td>&nbsp; Unique :
							<?=$time_count1[$i]?>
							<br />
							&nbsp;&nbsp;PageView :
							<?=$time_count2[$i]?>
							&nbsp; </td>
					</tr>
					<?
  } /* for($i=0;$i<24;$i++)문 닫음 */
?>
				</tbody>
			</table>
</c:when>
<c:when test="${params.no == '3'}">
<!--
// 주간방문자통계
//-->
elseif($no=="3"):
$w = date(w, mktime(0,0,0,$month, $day, $year));
 $start_day=mktime(0,0,0,$month,$day-$w,$year);
 $last_day=mktime(0,0,-1,$month,$day+7-$w,$year);
 $detail=$dbcon->_fetch_array($dbcon->_query("select sum(unique_counter), sum(pageview) from wizcounter_main where date>=$start_day and date<=$last_day"));
 $count[week_hit]=$detail[0];
 $count[week_view]=$detail[1];
?>
			<ul class="ul_normal_list">
				<li><? echo date("m월 d일", $start_day);?> ~ <? echo date("m월 d일", $last_day);?> 일 고유 방문자수 :
					<?=$count[week_hit]?>
				</li>
				<li><? echo date("m월 d일", $start_day);?> ~ <? echo date("m월 d일", $last_day);?> 일 페이지뷰 :
					<?=$count[week_view]?>
				</li>
				<li><? echo date("m월 d일", $start_day);?> ~ <? echo date("m월 d일", $last_day);?> 일 일자별 고유 방문자 및 페이지뷰수 </li>
			</ul>
			<table class="table_main">
				<col width="70px" />
				<col width="*" />
				<col width="150px" />
				<tbody>
					<?
  $max1=1;
  $max2=1;
  for($i=0;$i<7;$i++)
  {
   $time=mktime(0,0,0,$month,$day-$w+$i,$year);
	$md[$i] = date("m월 d일", $time);
   $temp=$dbcon->_fetch_array($dbcon->_query("select unique_counter, pageview from wizcounter_main where date='$time'"));
   $time_count1[$i]=$temp[0];
   if($max1<$time_count1[$i]) $max1=$time_count1[$i];
   $time_count2[$i]=$temp[1];
   if($max2<$time_count2[$i]) $max2=$time_count2[$i];
  }
  $week=array("일요일","월요일","화요일","수요일","목요일","금요일","토요일");
  for($i=0;$i<7;$i++)
  {
   $per1=(int)($time_count1[$i]/$max1*100);
   $per2=(int)($time_count2[$i]/$max2*100);
   if($per1>100)$per1=100;
   if($per2>100)$per2=100;
?>
					<tr>
						<td>-
							<?=$week[$i]?>
							(
							<?=$md[$i]?>
							) </td>
						<td><div>
								<ul style="text-align:left">
									<li ratio="<?=$per1?>" class="uniquebar" alt='<?=$week[$i]?> 방문자수 : <?=$time_count1[$i]?>'></li>
									<li ratio="<?=$per2?>"  class="pageviewbar" alt='<?=$week[$i]?> 페이지뷰 : <?=$time_count2[$i]?>'></li>
								</ul>
							</div></td>
						<td>&nbsp; Unique :
							<?=$time_count1[$i]?>
							<br />
							&nbsp; PageView :
							<?=$time_count2[$i]?>
						</td>
					</tr>
					<?
  }/* for($i=0;$i<7;$i++) */
?>
				</tbody>
			</table>
</c:when>
<c:when test="${params.no == '4'}">
<!--
// 월간방문자통계
//-->

elseif($no=="4"):
  $total_month_counter=$dbcon->_fetch_array($dbcon->_query("select sum(unique_counter), sum(pageview) from wizcounter_main where date>='$month_start' and date<='$lastdate'"));
?>
			<ul class="ul_normal_list">
				<li>
					<?=$month?>
					월 방문자수 :
					<?=$total_month_counter[0]?>
				</li>
				<li>
					<?=$month?>
					월 페이지뷰 :
					<?=$total_month_counter[1]?>
				</li>
			</ul>
			<table class="table_main">
				<col width="70px" />
				<col width="*" />
				<col width="150px" />
				<tbody>
					<?
  // 이번달 카운터 (각각)
  $max=$dbcon->_fetch_array($dbcon->_query("select max(unique_counter), max(pageview) from wizcounter_main where date>='$month_start' and date<='$lastdate'"));
  $month_counter=$dbcon->_query("select date, unique_counter, pageview from wizcounter_main where date>='$month_start' and date<='$lastdate'");
  while($data=$dbcon->_fetch_array($month_counter)):
   $per1=(int)($data[unique_counter]/$max[0]*100);
   $per2=(int)($data[pageview]/$max[1]*100);
?>
					<tr>
						<td>-
							<?=date("d 일",$data[date])?>
						</td>
						<td><div>
								<ul style="text-align:left">
									<li ratio="<?=$per1?>" class="uniquebar" alt='Unique : <?=$data[unique_counter]?>'></li>
									<li ratio="<?=$per2?>" class="pageviewbar" alt='PageView : <?=$data[pageview]?>'></li>
								</ul>
							</div></td>
						<td>&nbsp; Unique :
							<?=$data[unique_counter]?>
							<br />
							&nbsp; PageView :
							<?=$data[pageview]?>
						</td>
					</tr>
					<?
  endwhile;
?>
				</tbody>
			</table>
</c:when>
<c:when test="${params.no == '5'}">
<!--
// 년간방문자통계
//-->
elseif($no=="5"):
  $year_start=mktime(0,0,0,1,1,$year);
  $year_last=mktime(23,59,59,12,31,$year);
  $total_year_counter=$dbcon->_fetch_array($dbcon->_query("select sum(unique_counter), sum(pageview) from wizcounter_main where date>='$year_start' and date<='$year_last' and no <> 1"));
?>
			<ul class="ul_normal_list">
				<li>
					<?=$year?>
					년 방문자수 : </li>
				<li>
					<?=$year?>
					년 페이지뷰 :
					<?=$total_year_counter[1]?>
				</li>
			</ul>
			<table class="table_main">
				<col width="70px" />
				<col width="*" />
				<col width="150px" />
				<tbody>
					<?
  // 이번달 카운터 (각각)
$max1=1;
  $max2=1;
  for($i=0;$i<7;$i++)
  {
   $time=mktime(0,0,0,$month,$start_day+$i,$year);
   $temp=$dbcon->_fetch_array($dbcon->_query("select unique_counter, pageview from wizcounter_main where date='$time'"));
   $time_count1[$i]=$temp[0];
   if($max1<$time_count1[$i]) $max1=$time_count1[$i];
   $time_count2[$i]=$temp[1];
   if($max2<$time_count2[$i]) $max2=$time_count2[$i];
  }
  $max=1;
  $max2=1;
  for($i=0;$i<12;$i++)
  {
   $sdate=mktime(0,0,0,$i+1,1,$year);
   $edate=mktime(0,0,-1,$i+2,1,$year);
   $year_counter=$dbcon->_query("select sum(unique_counter), sum(pageview) from wizcounter_main where date>='$sdate' and date<='$edate'");
   $temp=$dbcon->_fetch_array($year_counter);
   $time_count1[$i]=$temp[0];
   if($max1<$time_count1[$i]) $max1=$time_count1[$i];
   $time_count2[$i]=$temp[1];
   if($max2<$time_count2[$i]) $max2=$time_count2[$i];
  }

  for($i=0;$i<12;$i++)
  {
   $per1=(int)($time_count1[$i]/$max1*100);
   $per2=(int)($time_count2[$i]/$max2*100);
   if($per1>100)$per1=99;
   if($per2>100)$per2=99;
   $j=$i+1;
?>
					<tr>
						<td>-
							<?=$j?>
							월 </td>
						<td><div>
								<ul style="text-align:left">
									<li ratio="<?=$per1?>" class="uniquebar" alt='<?=$week[$i]?> 방문자수 : <?=$time_count1[$i]?>'></li>
									<li ratio="<?=$per2?>" class="pageviewbar" alt='<?=$week[$i]?> 페이지뷰 : <?=$time_count2[$i]?>'></li>
								</ul>
							</div></td>
						<td>&nbsp; Unique :
							<?=$time_count1[$i]?>
							<br />
							&nbsp; PageView :
							<?=$time_count2[$i]?>
						</td>
					</tr>
					<?
  } /* for($i=0;$i<12;$i++) */
?>
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