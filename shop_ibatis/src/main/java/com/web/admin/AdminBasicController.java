package com.web.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.BannerDAO;
import com.dao.DelivererDAO;
import com.dao.MembersDAO;
import com.domain.Account;
import com.domain.Banner;
import com.domain.Deliverer;
import com.domain.Members;
import com.service.AccountService;
import com.service.BannerService;
import com.service.DelivererService;
import com.service.MembersService;
import com.util.Constants;
import com.util.Serialization;
import com.util.file.FileUpload;
import com.util.file.FileUploadUtil;

@Controller
public class AdminBasicController {

	@Autowired
	private AccountService accountService;

	//@Autowired
	//private AccountDAO accountDAO;

	@Autowired
	private DelivererDAO delivererDAO;


	@Autowired
	private MembersDAO membersDAO;

	@Autowired
	private BannerDAO bannerDAO;

	@Autowired
	private BannerService bannerService;

	@Autowired
	private MembersService membersService;

	@Autowired
	private DelivererService delivererService;
	/**
	 * 기본 정보 출력 폼(url 및 관리자 정보 변경등)
	 * @return
	 */
	@RequestMapping("/admin/basic/basicInfo")
	public ModelAndView basicInfo(HttpServletRequest request) {//Principal principal  final String userId = principal.getName();
		ModelAndView mav = new ModelAndView();

		final String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		Members mem			= new Members();
		mem					= membersService.getMemberByUserid(userId);
		mav.addObject("member", mem);

		mav.addObject("LoginFailCount", LoginFailCount());


		//기본 정보를 가져온다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/basicInfo";
		HashMap<String, String> basicinfo = serialization.unSerialize(outPath, "hashMap");
		mav.addObject("basicinfo", basicinfo);

		mav.setViewName("admin/basic/basicinfo.jsp");
		//System.out.println(mav);
		return mav;
	}

	@RequestMapping("/admin/basic/basicInfo_x")
	public String basicInfo_x(HttpServletRequest request, Principal principal) {

		final String userId = principal.getName();

		//기본 관리자 정보 로딩 및 save
		Members mem =new Members();
		mem	= membersDAO.getMemberByUserid(userId);

		mem.setUser_name(request.getParameter("user_name"));
		if(request.getParameter("user_passwd") != ""){
			mem.setUser_passwd(request.getParameter("user_passwd"));
		}
		membersService.saveMember(mem);

		//기타 정보는 파일로 남긴다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/basicInfo";
		HashMap<String, String> basicinfo = new HashMap<String, String>();
		basicinfo.put("LoginLimitCnt", request.getParameter("LoginLimitCnt"));//관리자단 로그인 실패 제한 횟수
		basicinfo.put("admin_title", request.getParameter("admin_title"));//쇼핑몰명(한글)
		basicinfo.put("admin_title_e", request.getParameter("admin_title_e"));//쇼핑몰명(영문)
		basicinfo.put("company_domain", request.getParameter("company_domain"));//쇼핑몰명
		basicinfo.put("home_url", request.getParameter("home_url"));//홈페이지 주소
		basicinfo.put("admin_email", request.getParameter("admin_email"));//대표이메일
		basicinfo.put("admin_tel", request.getParameter("admin_tel"));//대표전화번호
		basicinfo.put("company_name", request.getParameter("company_name"));//상호명
		basicinfo.put("president", request.getParameter("president"));//대표자번호
		basicinfo.put("company_num", request.getParameter("company_num"));//사업자등록번호
		basicinfo.put("company_licence_num", request.getParameter("company_licence_num"));//통신판매업신고
		basicinfo.put("customer_service_tel", request.getParameter("customer_service_tel"));//고객상담전화
		basicinfo.put("customer_service_fax", request.getParameter("customer_service_fax"));//팩스번호
		basicinfo.put("company_address", request.getParameter("company_address"));//사업장주소
		basicinfo.put("company_domain", request.getParameter("company_domain"));//
		basicinfo.put("company_domain", request.getParameter("company_domain"));//
		basicinfo.put("company_domain", request.getParameter("company_domain"));//
		basicinfo.put("company_domain", request.getParameter("company_domain"));//

		serialization.Serialize(outPath, basicinfo);

		return "redirect:/admin/basic/basicInfo.do";
	}

	/**
	 * 로그인 횟수 제한
	 * @return
	 */
	public static HashMap<Integer, String> LoginFailCount(){
		HashMap<Integer, String> rtn = new HashMap<Integer, String>();
		rtn.put(0,"무한정");
		rtn.put(5,"5회");
		rtn.put(10,"10회");
		rtn.put(20,"20회");
		rtn.put(50,"50회");
		rtn.put(100,"100회");
		return rtn;
	}



	/**
	 * 결제정보 리스트 폼
	 * @return
	 */
	@RequestMapping("/admin/basic/payInfo")
	public ModelAndView payInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		//기본 정보를 가져온다.
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/payinfo";
		HashMap<String, String> payinfo = serialization.unSerialize(outPath, "hashMap");
		mav.addObject("payinfo", payinfo);


		mav.setViewName("admin/basic/payinfo.jsp");
		//System.out.println(mav);

		return mav;
	}


	/**
	 * 결제정보 수정 실행
	 * @return
	 */
	@RequestMapping("/admin/basic/payinfo_x")
	public String payinfo_x(HttpServletRequest request) {

		//기본 정보 입력
		Serialization serialization	= new Serialization();
		String outPath				= Constants.AbsolutePath+"config/payinfo";
		HashMap<String, String> payinfo = new HashMap<String, String>();
		payinfo.put("ONLINE_ENABLE", request.getParameter("ONLINE_ENABLE"));//온라인무통장 결제(기본)
		payinfo.put("CARD_ENABLE", request.getParameter("CARD_ENABLE"));//신용카드 결제
		payinfo.put("PHONE_ENABLE", request.getParameter("PHONE_ENABLE"));//핸드폰결제
		payinfo.put("AUTOBANKING_ENABLE", request.getParameter("AUTOBANKING_ENABLE"));//실시간자동이체
		payinfo.put("POINT_ENABLE", request.getParameter("POINT_ENABLE"));//포인트 결제

		payinfo.put("CARD_ID", request.getParameter("CARD_ID"));//상점아이디
		payinfo.put("CARD_PASS", request.getParameter("CARD_PASS"));//상점패스워드
		payinfo.put("CARD_ENABLE_MONEY", request.getParameter("CARD_ENABLE_MONEY"));//신용카드 최소 제품구매액

		payinfo.put("POINT_ENABLE_MONEY", request.getParameter("POINT_ENABLE_MONEY"));//최소 구매 허용 포인트

		payinfo.put("TACKBAE_MONEY", request.getParameter("TACKBAE_MONEY"));//배송비
		payinfo.put("TACKBAE_ALL", request.getParameter("TACKBAE_ALL"));//배송비 적용여부
		payinfo.put("TACKBAE_CUTLINE", request.getParameter("TACKBAE_CUTLINE"));//배송비 적용 금액

		serialization.Serialize(outPath, payinfo);


		return "redirect:/admin/basic/payInfo.do";
	}



	/**
	 * 결제정보 리스트 폼_은행 정보 입력폼
	 * @return
	 */
	@RequestMapping("/admin/basic/payinfo_bank")
	public ModelAndView payinfo_bank(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();


		//mav.addObject("info", productService.getProduct(pageNav.getStartRow(), blockList));
		mav.addObject("info", accountService.getAllAccountList());
		mav.addObject("BankCode", Constants.BankCode());
		mav.setViewName("admin/basic/payinfo_bank.jsp");
//		System.out.println(mav);

		return mav;
	}

	/**
	 * 결제정보 리스트 폼_은행 정보 입력폼 실행(입력/수정)
	 * @return
	 */
	@RequestMapping("/admin/basic/payinfo_bank_x")
	public String payinfo_bank_x(HttpServletRequest request) {
		//ModelAndView mav = new ModelAndView();
		Account	account	= new Account();
		//Account account	= accountDAO.getAccountByTid(account.getTid());
		//System.out.println("request.getParameter('tid'):"+request.getParameter("tid"));
		if(request.getParameter("tid") != null)
		account.setTid(Integer.parseInt(request.getParameter("tid")));
		account.setAccount_no(request.getParameter("account_no"));
		account.setAccount_owner(request.getParameter("account_owner"));
		account.setBankname(request.getParameter("bankname"));
		accountService.saveAccount(account);

		return "redirect:/admin/basic/payinfo_bank.ajax";
	}

	/**
	 * 결제정보 리스트 폼_은행 정보 삭제
	 * @return
	 */
	@RequestMapping("/admin/basic/payinfo_bank_del_x")
	public String payinfo_bank_del_x(HttpServletRequest request) {
		//ModelAndView mav = new ModelAndView();

		//System.out.println("request.getParameter('tid'):"+request.getParameter("tid"));
		if(request.getParameter("tid") != null){//null 일경우 에러메시지 출력
			//Account	account	= new Account();
			//Account account	= accountDAO.getAccountByTid(Integer.parseInt(request.getParameter("tid")));
			//account.setTid(Integer.parseInt(request.getParameter("tid")));
			//accountService.deleteAccount(account);
			int tid	= Integer.parseInt(request.getParameter("tid"));
			accountService.deleteAccount(tid);

		}
		return "redirect:/admin/basic/payinfo_bank.do";
	}


	/**
	 * 베너 리스트 폼
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/basic/bannerList")
	public ModelAndView bannerList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		//베너 포지션을 불러온다.
		TreeMap <Integer, String> position = Constants.BannerPosition();

		//현재 베너를 불러온다.
		List<Banner> banner	= bannerDAO.getAllBannerList();

		mav.addObject("position", position);
		mav.addObject("info", bannerSetList(banner));//현재 저장된 베너를 포지션별로 분리하여 가져온다.
		mav.setViewName("admin/basic/bannerList.jsp");
		return mav;
	}

	/**
	 * 베너 write 폼
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/basic/bannerWrite")
	public ModelAndView bannerWrite(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String banner_pos	= request.getParameter("banner_pos");
		Integer	tid;
		//System.out.println("tid:"+request.getParameter("tid"));
		if(request.getParameter("tid") != null && request.getParameter("tid") != ""){
			tid	= Integer.parseInt(request.getParameter("tid"));
			mav.addObject("info", bannerService.getBannerByTid(tid));
		}

		mav.addObject("banner_pos", banner_pos);
		mav.setViewName("admin/basic/bannerWrite.jsp");
		return mav;
	}

	/**
	 * 베너 등록 및 수정
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/basic/banner_x")
	public String banner_x(HttpServletRequest request) {

		Banner banner;


		if(!request.getParameter("tid").trim().equals("") && request.getParameter("tid") != null){
			banner	= bannerDAO.getBannerByTid(Integer.parseInt(request.getParameter("tid")));
		}else{
			banner	= new Banner();
			banner.setCnt(0);
			banner.setBanner_pos(Integer.parseInt(request.getParameter("banner_pos")));
			banner.setShowflag(1);
			banner.setSubject("");
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			date = cal.getTime();
			banner.setWdate(cal.getTime());
		}

		banner.setOrdernum(Integer.parseInt(request.getParameter("ordernum")));
		banner.setUrl(request.getParameter("url"));
		banner.setTarget(request.getParameter("target"));



		//첨부화일을 새로이 저장하고 그 값들을 List에 넣어둔다.(파일 삭제는 추후에 다시 볼것)
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		List<FileUpload> FileList = fileUploadUtil.getFileList(request, "banner/");
		if(FileList.get(0).getFileSize() != 0){
			banner.setAttached(FileList.get(0).getFileName());
		}
		//현제 데이타를 저장한다.
		bannerService.saveBanner(banner);

		return "redirect:/admin/basic/bannerList.do";
	}
	/**
	 * 베너 삭제
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/basic/bannerDelete")
	public String bannerDelete(HttpServletRequest request) {

		//Banner banner;


		if(!request.getParameter("tid").trim().equals("") && request.getParameter("tid") != null){
			bannerService.deleteBanner(Integer.parseInt(request.getParameter("tid")));
		}else{
			//잘못된 접근
		}

		return "redirect:/admin/basic/bannerList.do";
	}

	/**
	 * 현재 베너를 포지션별로 분리하여 TreeMap에 넣어 준다.
	 * @return
	 */
	public TreeMap<Integer, Object> bannerSetList(List<Banner> banner){//static
		//BannerDAO user = mock(BannerDAO.class);
		//BannerDAOImpl bannerdao	= new BannerDAOImpl();



		TreeMap<Integer, Object> bannerList = new TreeMap<Integer, Object>();
		//Banner[][] bannerList	= new Banner[100][];
		Iterator<Banner>	iter = banner.iterator();

		//현재 베너위치 만큼의 ArrayList를 만들어 준다.
		ArrayList<Banner> Banner10	= new ArrayList<Banner>();
		ArrayList<Banner> Banner20	= new ArrayList<Banner>();
		ArrayList<Banner> Banner30	= new ArrayList<Banner>();
		ArrayList<Banner> Banner40	= new ArrayList<Banner>();
		ArrayList<Banner> Banner50	= new ArrayList<Banner>();
		ArrayList<Banner> Banner60	= new ArrayList<Banner>();
		while(iter.hasNext()){
			Banner ban	= iter.next();
			switch(ban.getBanner_pos()){
			case 10:Banner10.add(ban);break;
			case 20:Banner20.add(ban);break;
			case 30:Banner30.add(ban);break;
			case 40:Banner40.add(ban);break;
			case 50:Banner50.add(ban);break;
			case 60:Banner60.add(ban);break;
			}

		}
		bannerList.put(new Integer(10), Banner10);
		bannerList.put(new Integer(20), Banner20);
		bannerList.put(new Integer(30), Banner30);
		bannerList.put(new Integer(40), Banner40);
		bannerList.put(new Integer(50), Banner50);
		bannerList.put(new Integer(60), Banner60);
		return bannerList;
	}


	/**
	 * 택배사 관리 페이지
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/basic/deliverer")
	public ModelAndView deliverer(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("info", delivererDAO.getAllDelivererList());
		mav.setViewName("admin/basic/deliverer.jsp");
		return mav;
	}

	/**
	 * 택배사 관리 페이지(수정 및 저장)
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/basic/deliverer_x")
	public String deliverer_x(HttpServletRequest request) {

		Deliverer deliverer	= new Deliverer();

		if(request.getParameter("tid") != null)
			deliverer.setTid(Integer.parseInt(request.getParameter("tid")));
		deliverer.setD_code(request.getParameter("d_code"));
		deliverer.setD_inquire_url(request.getParameter("d_inquire_url"));
		deliverer.setD_method(request.getParameter("d_method"));
		deliverer.setD_name(request.getParameter("d_name"));
		deliverer.setD_url(request.getParameter("d_url"));

		delivererService.saveDeliverer(deliverer);

		return "redirect:/admin/basic/deliverer.do";
	}

	/**
	 * 텍배사  삭제
	 * @return
	 */
	@RequestMapping("/admin/basic/deliverer_del_x")
	public String deliverer_del_x(HttpServletRequest request) {
		if(request.getParameter("tid") != null){//null 일경우 에러메시지 출력
			//Account	account	= new Account();
			delivererService.deleteDeliverer(Integer.parseInt(request.getParameter("tid")));
		}
		return "redirect:/admin/basic/deliverer.do";
	}
}
