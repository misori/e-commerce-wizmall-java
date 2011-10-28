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
		params.put("year", request.getParameter("year"));
		params.put("month", request.getParameter("month"));
		params.put("day", request.getParameter("day"));




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
	@SuppressWarnings("static-access")
	private ModelAndView visit02(HttpServletRequest request) throws Exception {
		ModelAndView mav							= new ModelAndView();

		Object[] params	= new Object[24];//시간별 최대/최소 통계
		HashMap<String, Integer> params_max		= new HashMap<String, Integer>();//일별 최대

		Calendar cal = Calendar.getInstance();

		String year		= request.getParameter("year");
		if(year == null)	year = Integer.toString(cal.get(cal.YEAR));

		Integer mm			= request.getParameter("month") == null ? cal.get(cal.MONTH)+1 :Integer.parseInt(request.getParameter("month"));
		String month		= mm < 10 ? "0"+Integer.toString(mm):Integer.toString(mm);

		Integer dd			= request.getParameter("day") == null ? cal.get(cal.DATE) : Integer.parseInt(request.getParameter("day"));
		String day		= dd < 10 ? "0"+Integer.toString(dd):Integer.toString(dd);

		Integer max_hit		= 1;
		Integer max_view	= 1;
		String hour;
		HashMap<String, String> paramstmp;
		for(int i = 0; i<24; i++){
			paramstmp	= new HashMap<String, String>();
			hour	= i < 10 ? "0"+Integer.toString(i):Integer.toString(i);
			String paramDate	= year+month+day+hour;
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
		mav.addObject("max", params_max);
		return mav;
	}

	/**
	 * 주간 일별 방문자 통계
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	private ModelAndView visit03(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Object[] params	= new Object[7];//주별 최대/최소 통계
		HashMap<String, Integer> params_max		= new HashMap<String, Integer>();//일별 최대

		Calendar cal = Calendar.getInstance();
		Integer IntYear, IntMonth, IntDay;


		IntYear		= request.getParameter("year") == null ? cal.get(cal.YEAR) :Integer.parseInt(request.getParameter("year"));

		IntMonth	= request.getParameter("month") == null ? cal.get(cal.MONTH)+1 :Integer.parseInt(request.getParameter("month"));

		IntDay		= request.getParameter("day") == null ? cal.get(cal.DATE) : Integer.parseInt(request.getParameter("day"));
		cal.set(IntYear, IntMonth-1, IntDay);
		cal	=	dateUtil.getStartSunOfWeek(cal);//일요일의 날짜로 세팅을 바꾼다.

		Integer max_hit		= 1;
		Integer max_view	= 1;
		String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		HashMap<String, String> paramstmp;
		for(int i = 0; i<7; i++){

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
		System.out.println(params.length);
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
	@SuppressWarnings("static-access")
	private ModelAndView visit04(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();

		HashMap<String, Integer> params_max		= new HashMap<String, Integer>();//일별 최대

		Calendar cal = Calendar.getInstance();
		Integer IntYear, IntMonth;


		IntYear		= request.getParameter("year") == null ? cal.get(cal.YEAR) :Integer.parseInt(request.getParameter("year"));

		IntMonth	= request.getParameter("month") == null ? cal.get(cal.MONTH)+1 :Integer.parseInt(request.getParameter("month"));

		//IntDay		= request.getParameter("day") == null ? cal.get(cal.DATE) : Integer.parseInt(request.getParameter("day"));
		cal.set(IntYear, IntMonth-1, 1);


		Integer max_hit		= 1;
		Integer max_view	= 1;
		String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		HashMap<String, String> paramstmp;

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
		System.out.println(params.length);
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
		HashMap<String, Integer> params_max		= new HashMap<String, Integer>();//일별 최대

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





}
