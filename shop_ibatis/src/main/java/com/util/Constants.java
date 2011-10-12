package com.util;

import java.util.HashMap;
import java.util.TreeMap;

public class Constants {
	// ######################### 에러 코드 #################################
	/**
	 * 권한 없음 에러시
	 */
	//public final static String ERROR_NOT_AUTH = "ERROR_NOT_AUTH";

	/**
	 * 아이디 없을경우
	 */
	//public final static String ERROR_NOT_ID = "ERROR_NOT_ID";

	// ######################### 에러 코드 #################################


	/**
	 *
	 * data 폴더 경로
	 * C:/Users/YoungHyeongRyu/Workspaces/oneul/shop/WebRoot/data/"
	 */
	public final static String AbsolutePath	= "C:/Users/YoungHyeongRyu/Workspaces/oneul/shop_ibatis/WebRoot/data/";

	//모바일 및 일반전화 국번
	public static final String[] MobileNum = {"010","011","016","017","018","019"};
	public static final String[] PhoneNum = {"070","02","031","032","033","041","042","043","051","052","053","054","055","061","062","063","064"};




/**
 * 이메일 계정
 * @return
 */
	public static HashMap<String, String> MailAddress(){
		HashMap<String, String> rtn = new HashMap<String, String>();
		rtn.put("천리안","chollian.net");
		rtn.put("다음","daum.net");
		rtn.put("드림위즈","dreamwiz.com");
		rtn.put("엠팔","empal.com");
		rtn.put("프리첼","freechal.com");
		rtn.put("프리미","freeme.co.kr");
		rtn.put("한메일","hanmail.net");
		rtn.put("한미르","hanmir.com");
		rtn.put("하이홈","hihome.com");
		rtn.put("하이텔","hitel.com");
		rtn.put("핫메일","hotmail.com");
		rtn.put("인티즌","intizen.com");
		rtn.put("깨비","kebi.com");
		rtn.put("코리아","korea.com");
		rtn.put("라이코스","lycos.co.kr");
		rtn.put("네이버","naver.com");
		rtn.put("네이트","nate.com");
		rtn.put("네띠앙","netian.com");
		rtn.put("오케이메일","okmail.com");
		rtn.put("오르지오","orgio.net");
		rtn.put("팝스메일","popsmail.com");
		rtn.put("심마니","simmani.com");
		rtn.put("유니텔","unitel.co.kr");
		rtn.put("야후","yahoo.co.kr");
		rtn.put("웨피","weppy.co.kr");
		//list.add(MailAddress);
		return rtn;
	}

	/**
	 * 회원등급
	 * @return
	 */
		public static HashMap<String, String> MemberGrade(){

			HashMap<String, String> rtn = new HashMap<String, String>();
			rtn.put("10","관리자");
			rtn.put("100","일반회원");
			return rtn;
		}


/**
 * 은행코드
 * @return
 */
	public static HashMap<String, String> BankCode(){

		HashMap<String, String> rtn = new HashMap<String, String>();
		rtn.put("경남은행","");
		rtn.put("광주은행","");
		rtn.put("국민은행","");
		rtn.put("기업은행","");
		rtn.put("농협중앙회","");
		rtn.put("농협","");
		rtn.put("대구은행","");
		rtn.put("부산은행","");
		rtn.put("새마을금고","");
		rtn.put("서울은행","");
		rtn.put("수협","");
		rtn.put("신한은행","");
		rtn.put("씨티은행","");
		rtn.put("외환은행","");
		rtn.put("우리은행","");
		rtn.put("우체국","");
		rtn.put("전북은행","");
		rtn.put("제주은행","");
		rtn.put("평화은행","");
		rtn.put("한미은행","");
		rtn.put("SC제일은행","");
		return rtn;
	}

	/**
	 * 상품주문단계 (50 번을 기준으로 포인트 지급이 되므로 이부분을 변경할 경우는 주의 깊게 하여야 한다.)
	 * @return
	 */
	public static TreeMap<Integer, String> OrderStatus(){//HashMap
		TreeMap<Integer, String> rtn = new TreeMap<Integer, String>();
		rtn.put(new Integer(10),"주문접수");
		rtn.put(new Integer(20),"입금대기");
		rtn.put(new Integer(30),"입금확인");
		rtn.put(new Integer(40),"배송준비");
		rtn.put(new Integer(50),"배송완료");
		rtn.put(new Integer(60),"물품반송");
		rtn.put(new Integer(70),"주문삭제");
		return rtn;
	}

	/**
	 * 상품등록옵션
	 * @return
	 */
	public static HashMap<String, String> ProdRegOption(){
		HashMap<String, String> rtn = new HashMap<String, String>();
		rtn.put("10","추천");
		rtn.put("20","신규");
		rtn.put("30","인기");
		rtn.put("40","히트");
		rtn.put("50","베스트");
		rtn.put("60","스페셜");
		return rtn;
	}


	/**
	 * 베너 포지션
	 * @return
	 */
	public static TreeMap<Integer, String> BannerPosition(){
		TreeMap<Integer, String> rtn = new TreeMap<Integer, String>();
		rtn.put(new Integer(10),"상단로고");
		rtn.put(new Integer(20),"하단로고");
		rtn.put(new Integer(30),"메인TOP");
		rtn.put(new Integer(40),"메인CENTER");
		rtn.put(new Integer(50),"메인LEFT");
		rtn.put(new Integer(60),"메인RIGHT");
		return rtn;
	}

	//HashMap MailAddress = new HashMap();
	//MailAddress.put(new String("ad"), new String("adfa"));

/*
	public static ArrayList<HashMap<String, String>> MailAddress(){

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> MailAddress = new HashMap<String, String>();
		MailAddress.put("천리안","chollian.net");
		MailAddress.put("다음","daum.net");
		MailAddress.put("다음","daum.net");


		list.add(MailAddress);

		return list;
	}
*/
	//Map<K, V>.Entry<"COL1", "121">;
	//MailAddress.put("COL1", "121");



	//public static final String STUDENT_STRING = "전체,삭제,취소,신청중,승인";

	//public static final String[] STU_TYPE = { "전체", "이론,실습,견학", "해외연수", "방문교육"};	//, "농고,농과대학", "창업농" };

	//public static final Long CLS_TASK_CONFIRM = new Long(2009);

	//public static final Integer BOARD_MST_GOVERNMENT_NOTICE_ID = new Integer(1000);


	//public static final Integer[] ORG_TYPE_KEY = {new Integer(1) , new Integer(2) , new Integer(3) , new Integer(4) , new Integer(5) , new Integer(6),  new Integer(7) , new Integer(8) , new Integer(9) , new Integer(10) , new Integer(11), new Integer(12), new Integer(13)};

	//public static int START_YEAR = 2007;
	//public static int END_YEAR = 2017;

	//LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	//Map<K, V>.Entry<"COL1", "121">;
	//map.put("COL1", "121");
	//map.put("COL2", getArrayData("COL2",10));
	//map.put("COL3", getArrayData("COL3",10));
	//map.put("COL4", getArrayData("COL4",10));
	//map.put("COL5", getArrayData("COL5",10));

	/*
	 * public static ArrayList<String[][]> teamLists(){
		ArrayList<String[][]> list = new ArrayList<String[][]>();
		list.add(new String[][]("10", "[BIZ] 사업"));
		list.add(new TeamList(20, "[CON] 콘텐츠"));
		list.add(new TeamList(30, "[SVC] 서비스"));
		list.add(new TeamList(40, "[B2B] B2B"));
		list.add(new TeamList(50, "[SVR] 서버"));
		list.add(new TeamList(60, "[APL] APPLICATION"));
		list.add(new TeamList(70, "[DBE] DB"));
		list.add(new TeamList(80, "[APP] APP(SNG)"));
		list.add(new TeamList(90, "[WEB] WEB"));
		list.add(new TeamList(100, "[B2B2] B2B(2)"));
		list.add(new TeamList(110, "[ECM] E-Commerce"));
		return list;
	}
*/
}

/*
 * public class TeamList {
	private Integer sn;
	private String name;
	public TeamList(Integer sn, String name){
		this.sn=sn;
		this.name=name;
	}
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public static List<TeamList> teamLists(){
		List<TeamList> list = new ArrayList<TeamList>();
		list.add(new TeamList(10, "[BIZ] 사업"));
		list.add(new TeamList(20, "[CON] 콘텐츠"));
		list.add(new TeamList(30, "[SVC] 서비스"));
		list.add(new TeamList(40, "[B2B] B2B"));
		list.add(new TeamList(50, "[SVR] 서버"));
		list.add(new TeamList(60, "[APL] APPLICATION"));
		list.add(new TeamList(70, "[DBE] DB"));
		list.add(new TeamList(80, "[APP] APP(SNG)"));
		list.add(new TeamList(90, "[WEB] WEB"));
		list.add(new TeamList(100, "[B2B2] B2B(2)"));
		list.add(new TeamList(110, "[ECM] E-Commerce"));
		return list;
	}
}

 */



