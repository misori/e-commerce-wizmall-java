package com.web.admin;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.VisitMain;
import com.domain.VisitReferer;
import com.service.VisitMainService;
import com.service.VisitRefererService;
import com.util.StringUtil;

@Controller
public class AdminVisitController {
	@Autowired
	private VisitMainService visitMainService;

	@Autowired
	private VisitRefererService visitRefererService;

	@RequestMapping("/admin/visit/visit")
	public ModelAndView visit(HttpServletRequest request) throws Exception {

		ModelAndView mav;
		HashMap<String, String> params = new HashMap<String, String>();

		Integer no	= Integer.parseInt(request.getParameter("no"));
		if (no == null) no = 1;

		params.put("no", Integer.toString(no));
		params.put("year", request.getParameter("year"));
		params.put("month", request.getParameter("month"));
		params.put("day", request.getParameter("day"));




		switch(no){
			case 2:
				mav = visit02(request);
				break;
			default:
				mav = visit01(request);
				break;
		}
		mav.addObject("params", params);
		mav.setViewName("admin/visit/visit.jsp");
		return mav;
	}

	/**
	 * 총 방문자 통계
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ModelAndView visit01(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> params = new HashMap<String, String>();
		//StringUtil sutil	= new StringUtil();




		//전체 카운트 구해오기
		VisitMain visitMainTotal	= visitMainService.getVisitMainTotal();
		params.put("total_hit", Integer.toString(visitMainTotal.getUnique_counter()));
		params.put("total_view", Integer.toString(visitMainTotal.getPageview()));


		// 오늘 카운터 읽어오는 부분
		Calendar cal = Calendar.getInstance();
		VisitMain visitMainToday	= visitMainService.getVistMainByDate(cal.getTime());
		params.put("today_hit", Integer.toString(visitMainToday.getUnique_counter()));
		params.put("today_view", Integer.toString(visitMainToday.getPageview()));


		// 어제 카운터 읽어오는 부분
		cal.add(Calendar.DATE, -1);
		VisitMain visitMainYesterDay	= visitMainService.getVistMainByDate(cal.getTime());
		if(visitMainYesterDay == null){
			params.put("yesterday_hit", "0");
			params.put("yesterday_view", "0");
		}else{
			params.put("yesterday_hit", Integer.toString(visitMainYesterDay.getUnique_counter()));
			params.put("yesterday_view", Integer.toString(visitMainYesterDay.getPageview()));
		}



		// 최고 카운터 읽어오는 부분
		VisitMain visitMainMax	= visitMainService.getVistMainMax();
		params.put("max_hit", Integer.toString(visitMainMax.getUnique_counter()));
		params.put("max_view", Integer.toString(visitMainMax.getPageview()));



		// 최저 카운터 읽어오는 부분
		VisitMain visitMainMin	= visitMainService.getVistMainMin();
		params.put("min_hit", Integer.toString(visitMainMin.getUnique_counter()));
		params.put("min_view", Integer.toString(visitMainMin.getPageview()));

		System.out.println(params);
		mav.addObject("data", params);


		return mav;
	}






	/**
	 * 금일 방문자 통계
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	private ModelAndView visit02(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		StringUtil stringUtil	= new StringUtil();
		HashMap<String, String> params = new HashMap<String, String>();
		HashMap<String, String> sqlParams;
		Calendar cal = Calendar.getInstance();

		String year		= request.getParameter("year");
		if(year == null)	year = Integer.toString(cal.get(cal.YEAR));

		Integer mm			= request.getParameter("month") == null ? cal.get(cal.MONTH)+1 :Integer.parseInt(request.getParameter("month"));
		String month		= mm < 10 ? "0"+Integer.toString(mm):Integer.toString(mm);

		Integer dd			= request.getParameter("day") == null ? cal.get(cal.DATE) : Integer.parseInt(request.getParameter("day"));
		String day		= dd < 10 ? "0"+Integer.toString(dd):Integer.toString(dd);

		System.out.println("year:"+year+",month:"+month+",day:"+day);

		Integer max1	= 1;
		Integer max2	= 1;
		String hour;
		for(int i = 0; i<24; i++){
			hour	= i < 10 ? "0"+Integer.toString(i):Integer.toString(i);
			String paramDate	= year+month+day+hour;
			VisitReferer visitReferer = visitRefererService.countVisitRefererByHour(paramDate);

			max1	= max1 >  visitReferer.getCount1() ? max1 : visitReferer.getCount1();
			max2	= max2 >  visitReferer.getCount2() ? max2 : visitReferer.getCount2();

			params.put("hit", Integer.toString(visitReferer.getCount1()));
			params.put("view", Integer.toString(visitReferer.getCount2()));

		}
		/*
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


		  */

		mav.addObject("data", params);
		return mav;
	}













}
