package com.web.admin;

import java.text.SimpleDateFormat;
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
import com.util.DateTimeUtil;
import com.util.StringUtil;

@Controller
public class AdminVisitController {
	@Autowired
	private VisitMainService visitMainService;

	@Autowired
	private VisitRefererService visitRefererService;

	private StringUtil stringUtil;
	private DateTimeUtil dateUtil;

	public AdminVisitController (){
		stringUtil	= new StringUtil();
		dateUtil	= new DateTimeUtil();
	}

	@RequestMapping("/admin/visit/visit")
	public ModelAndView visit(HttpServletRequest request) throws Exception {

		ModelAndView mav;
		HashMap<String, String> params = new HashMap<String, String>();

		Integer no	= stringUtil.strToint(request.getParameter("no"));
		if (no == 0) no = 1;

		params.put("no", Integer.toString(no));
		params.put("s_date", request.getParameter("s_date"));

		/*
		params.put("year", request.getParameter("year"));
		params.put("month", request.getParameter("month"));
		params.put("day", request.getParameter("day"));
		 */



		switch(no){
			case 2://금일방문자통계
				mav = visit02(request);
				break;
			case 3://주간방문자통계
				//System.out.println(visit03(request));
				mav = visit03(request);
				break;
			case 4://월간방문자통계
				mav = visit04(request);
				break;
			case 5://년간방문자통계
				mav = visit05(request);
				break;
			case 6://방문자경로
				mav = visit06(request);
				break;
			case 7://월간방문자경로
				mav = visit07(request);
				break;
			case 9://검색사별
				mav = visit09(request);
				break;
			default://총방문자통계(1)
				mav = visit01(request);
				break;
		}
		mav.addObject("params", params);
		mav.addObject("rtn_date", setCurDate(request.getParameter("s_date")));
		//System.out.println(mav);
		//System.out.println(mav.);
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

		String[] rtn_date	= setCurDate(request.getParameter("s_date"));
		//StringUtil sutil	= new StringUtil();

		//전체 카운트 구해오기
		VisitMain visitMainTotal	= visitMainService.getVisitMainTotal();
		params.put("total_hit", Integer.toString(visitMainTotal.getUnique_counter()));
		params.put("total_view", Integer.toString(visitMainTotal.getPageview()));

		// 오늘 카운터 읽어오는 부분
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(rtn_date[0]), Integer.parseInt(rtn_date[1])-1, Integer.parseInt(rtn_date[2]));
		VisitMain visitMainToday	= visitMainService.getVistMainByDate(cal.getTime());
		if(visitMainToday == null){
			params.put("today_hit", "0");
			params.put("today_view", "0");
		}else{
			params.put("today_hit", stringUtil.intTostr(visitMainToday.getUnique_counter()));
			params.put("today_view", stringUtil.intTostr(visitMainToday.getPageview()));
		}


		// 어제 카운터 읽어오는 부분
		cal.add(Calendar.DATE, -1);
		VisitMain visitMainYesterDay	= visitMainService.getVistMainByDate(cal.getTime());
		if(visitMainYesterDay == null){
			params.put("yesterday_hit", "0");
			params.put("yesterday_view", "0");
		}else{
			params.put("yesterday_hit", stringUtil.intTostr(visitMainYesterDay.getUnique_counter()));
			params.put("yesterday_view", stringUtil.intTostr(visitMainYesterDay.getPageview()));
		}

		// 최고 카운터 읽어오는 부분
		VisitMain visitMainMax	= visitMainService.getVistMainMax();
		params.put("max_hit", stringUtil.intTostr(visitMainMax.getUnique_counter()));
		params.put("max_view", stringUtil.intTostr(visitMainMax.getPageview()));

		// 최저 카운터 읽어오는 부분
		VisitMain visitMainMin	= visitMainService.getVistMainMin();
		params.put("min_hit", stringUtil.intTostr(visitMainMin.getUnique_counter()));
		params.put("min_view", stringUtil.intTostr(visitMainMin.getPageview()));

		//System.out.println(params);
		mav.addObject("data", params);


		return mav;
	}






	/**
	 * 금일 방문자 통계
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ModelAndView visit02(HttpServletRequest request) throws Exception {
		ModelAndView mav							= new ModelAndView();

		Object[] params	= new Object[24];//시간별 최대/최소 통계
		HashMap<String, Integer> params_max		= new HashMap<String, Integer>();//일별 최대
		String[] rtn_date	= setCurDate(request.getParameter("s_date"));


		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(rtn_date[0]), Integer.parseInt(rtn_date[1])-1, Integer.parseInt(rtn_date[2]));

		// 오늘 카운터 읽어오는 부분
		HashMap<String, String> paramstoday	= new HashMap<String, String>();
		VisitMain visitMainToday	= visitMainService.getVistMainByDate(cal.getTime());
		if(visitMainToday == null){
			paramstoday.put("today_hit", "0");
			paramstoday.put("today_view", "0");
		}else{
			paramstoday.put("today_hit", stringUtil.intTostr(visitMainToday.getUnique_counter()));
			paramstoday.put("today_view", stringUtil.intTostr(visitMainToday.getPageview()));
		}
		String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String search_date	= sdf.format(cal.getTime());

		Integer max_hit		= 1;
		Integer max_view	= 1;
		String hour;
		HashMap<String, String> paramstmp;

		//오늘 시간대별 카운트 읽어 오기
		for(int i = 0; i<24; i++){
			paramstmp	= new HashMap<String, String>();
			hour	= i < 10 ? "0"+Integer.toString(i):Integer.toString(i);
			String paramDate	= search_date+hour;
			VisitReferer visitReferer = visitRefererService.countVisitRefererByHour(paramDate);

			max_hit		= max_hit >  visitReferer.getCount1() ? max_hit : visitReferer.getCount1();
			max_view	= max_view >  visitReferer.getCount2() ? max_view : visitReferer.getCount2();

			paramstmp.put("hit", Integer.toString(visitReferer.getCount1()));
			paramstmp.put("view", Integer.toString(visitReferer.getCount2()));
			params[i]	= paramstmp;
		}

		params_max.put("max_hit", max_hit);
		params_max.put("max_view", max_view);

		//System.out.println(params);
		mav.addObject("data", params);
		mav.addObject("today", paramstoday);
		mav.addObject("max", params_max);
		return mav;
	}

	/**
	 * 주간 일별 방문자 통계
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ModelAndView visit03(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Object[] params	= new Object[7];//주별 최대/최소 통계
		HashMap<String, Integer> params_max		= new HashMap<String, Integer>();//일별 최대
		HashMap<String, String> params_day_term		= new HashMap<String, String>();//시작일과 끝나는 일

		String[] rtn_date	= setCurDate(request.getParameter("s_date"));

		Calendar cal = Calendar.getInstance();

		cal.set(Integer.parseInt(rtn_date[0]), Integer.parseInt(rtn_date[1])-1, Integer.parseInt(rtn_date[2]));
		cal	=	dateUtil.getStartSunOfWeek(cal);//일요일의 날짜로 세팅을 바꾼다.



		Integer max_hit		= 1;
		Integer max_view	= 1;
		String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		params_day_term.put("startday", sdf.format(cal.getTime()));
		HashMap<String, String> paramstmp;
		for(int i = 0; i<7; i++){

			VisitReferer visitReferer = visitRefererService.countVisitRefererByDay(sdf.format(cal.getTime()));

			max_hit		= max_hit >  visitReferer.getCount1() ? max_hit : visitReferer.getCount1();
			max_view	= max_view >  visitReferer.getCount2() ? max_view : visitReferer.getCount2();

			paramstmp	= new HashMap<String, String>();
			paramstmp.put("hit", Integer.toString(visitReferer.getCount1()));
			paramstmp.put("view", Integer.toString(visitReferer.getCount2()));

			params[i]	= paramstmp;
			if(i == 6){//마지막 일을 세팅해 준다.
				params_day_term.put("endday", sdf.format(cal.getTime()));
			}
			cal.add(Calendar.DATE, 1);//날짜를 올려준다.

			//System.out.println(visitReferer);
		}

		// 금주 토탈 방문자수 읽어 오기
		HashMap<String, String> paramsthisWeek	= new HashMap<String, String>();
		VisitMain visitMainThisWeek	= visitMainService.getVistMainByTerm(params_day_term);
		if(visitMainThisWeek == null){
			paramsthisWeek.put("today_hit", "0");
			paramsthisWeek.put("today_view", "0");
		}else{
			paramsthisWeek.put("today_hit", stringUtil.intTostr(visitMainThisWeek.getUnique_counter()));
			paramsthisWeek.put("today_view", stringUtil.intTostr(visitMainThisWeek.getPageview()));
		}

		mav.addObject("day_term", params_day_term);
		mav.addObject("WeekTotal", paramsthisWeek);
		params_max.put("max_hit", max_hit);
		params_max.put("max_view", max_view);
		//System.out.println(params.length);
		mav.addObject("data", params);
		mav.addObject("max", params_max);
		return mav;
	}

	/**
	 * 월간 일별 방문자통계
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ModelAndView visit04(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();

		HashMap<String, Integer> params_max		= new HashMap<String, Integer>();//일별 최대
		String[] rtn_date	= setCurDate(request.getParameter("s_date"));

		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(rtn_date[0]), Integer.parseInt(rtn_date[1])-1, 1);

		Integer max_hit		= 1;
		Integer max_view	= 1;
		String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		HashMap<String, String> paramstmp;


		// 금월 토탈 방문자수 읽어 오기
		HashMap<String, String> paramsthisMonth	= new HashMap<String, String>();
		VisitMain visitMainThisMonth	= visitMainService.getVistMainByMonth(sdf.format(cal.getTime()));
		if(visitMainThisMonth == null){
			paramsthisMonth.put("hit", "0");
			paramsthisMonth.put("view", "0");
		}else{
			paramsthisMonth.put("hit", stringUtil.intTostr(visitMainThisMonth.getUnique_counter()));
			paramsthisMonth.put("view", stringUtil.intTostr(visitMainThisMonth.getPageview()));
		}


		Integer countDay	= cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Object[] params	= new Object[countDay];//주별 최대/최소 통계
		for(int i = 0; i<countDay; i++){

			VisitReferer visitReferer = visitRefererService.countVisitRefererByDay(sdf.format(cal.getTime()));

			max_hit		= max_hit >  visitReferer.getCount1() ? max_hit : visitReferer.getCount1();
			max_view	= max_view >  visitReferer.getCount2() ? max_view : visitReferer.getCount2();

			paramstmp	= new HashMap<String, String>();
			paramstmp.put("hit", Integer.toString(visitReferer.getCount1()));
			paramstmp.put("view", Integer.toString(visitReferer.getCount2()));

			params[i]	= paramstmp;
			cal.add(Calendar.DATE, 1);//날짜를 올려준다.
			//System.out.println(visitReferer);
		}

		params_max.put("max_hit", max_hit);
		params_max.put("max_view", max_view);
		//System.out.println(params.length);
		mav.addObject("total", paramsthisMonth);
		mav.addObject("data", params);
		mav.addObject("max", params_max);
		return mav;
	}

	/**
	 * 년간방문자통계
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	private ModelAndView visit05(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Object[] params	= new Object[12];//년별 최대/최소 통계
		HashMap<String, Integer> params_max		= new HashMap<String, Integer>();//월별 최대

		Calendar cal = Calendar.getInstance();
		Integer IntYear;


		IntYear		= request.getParameter("year") == null ? cal.get(cal.YEAR) :Integer.parseInt(request.getParameter("year"));

		Integer max_hit		= 1;
		Integer max_view	= 1;
		String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		HashMap<String, String> paramstmp;
		for(int i = 0; i<12; i++){

			cal.set(IntYear, i, 1);
			VisitReferer visitReferer = visitRefererService.countVisitRefererByMonth(sdf.format(cal.getTime()).substring(0, 6));

			max_hit		= max_hit >  visitReferer.getCount1() ? max_hit : visitReferer.getCount1();
			max_view	= max_view >  visitReferer.getCount2() ? max_view : visitReferer.getCount2();

			paramstmp	= new HashMap<String, String>();
			paramstmp.put("hit", Integer.toString(visitReferer.getCount1()));
			paramstmp.put("view", Integer.toString(visitReferer.getCount2()));

			params[i]	= paramstmp;

		}


		// 금년 토탈 방문자수 읽어 오기
		HashMap<String, String> paramsthisYear	= new HashMap<String, String>();
		VisitMain visitMainThisYear	= visitMainService.getVistMainByYear(sdf.format(cal.getTime()));
		if(visitMainThisYear == null){
			paramsthisYear.put("hit", "0");
			paramsthisYear.put("view", "0");
		}else{
			paramsthisYear.put("hit", stringUtil.intTostr(visitMainThisYear.getUnique_counter()));
			paramsthisYear.put("view", stringUtil.intTostr(visitMainThisYear.getPageview()));
		}


/*
		Calendar cal = Calendar.getInstance();
		Integer IntYear;


		IntYear		= request.getParameter("year") == null ? cal.get(cal.YEAR) :Integer.parseInt(request.getParameter("year"));

		Integer max_hit		= 1;
		Integer max_view	= 1;
		String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		HashMap<String, String> paramstmp;
		for(int i = 0; i<12; i++){

			cal.set(IntYear, i, 1);
			VisitReferer visitReferer = visitRefererService.countVisitRefererByMonth(sdf.format(cal.getTime()).substring(0, 6));

			max_hit		= max_hit >  visitReferer.getCount1() ? max_hit : visitReferer.getCount1();
			max_view	= max_view >  visitReferer.getCount2() ? max_view : visitReferer.getCount2();

			paramstmp	= new HashMap<String, String>();
			paramstmp.put("hit", Integer.toString(visitReferer.getCount1()));
			paramstmp.put("view", Integer.toString(visitReferer.getCount2()));

			params[i]	= paramstmp;
			cal.add(Calendar.DATE, 1);//날짜를 올려준다.
			//System.out.println(visitReferer);
		}
*/
		mav.addObject("total", paramsthisYear);
		params_max.put("max_hit", max_hit);
		params_max.put("max_view", max_view);
		System.out.println(params.length);
		mav.addObject("data", params);
		mav.addObject("max", params_max);
		return mav;
	}

	/**
	 * 방문자경로
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ModelAndView visit06(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> params = new HashMap<String, String>();
		String[] rtn_date	= setCurDate(request.getParameter("s_date"));

		return mav;
	}

	/**
	 * 월간방문자경로
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ModelAndView visit07(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> params = new HashMap<String, String>();
		String[] rtn_date	= setCurDate(request.getParameter("s_date"));

		return mav;
	}

	/**
	 * 검색사별경로
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ModelAndView visit09(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		HashMap<String, String> params = new HashMap<String, String>();

		return mav;
	}


	private String[] setCurDate(String s_date){
		if(s_date == null){
			Calendar cal = Calendar.getInstance();
			String DATE_FORMAT = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			s_date	= sdf.format(cal.getTime());
		}

		String[] rtn_date	= s_date.split("-");
		return rtn_date;

	}




}
