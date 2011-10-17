package com.domain;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.util.BaseObject;

/**
 *
 * @author Pondol
 *
 */
public class Point  extends BaseObject {
	private static final long serialVersionUID = 1L;
	public Point() {

	}

	/**
	 * TID : AutoIncreament Value
	 */
	@Column(name = "TID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)//자동 증가일경우
	@Id
	Integer tid;

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getTid() {
		return this.tid;
	}


	/**
	 * USER_ID : 회원아이디
	 */
	@Column(name = "USER_ID", length = 20,  nullable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_id;
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id() {
		return this.user_id;
	}


	/**
	 * ORDER_ID : 주문코드아이디(제품 구매시 올라가는 아이디로 실제로 주문 코드 아이디가 들어간다)
	 */
	@Column(name = "ORDER_ID", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String order_id;

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_id() {
		return this.order_id;
	}


	/**
	 * PTYPE : 포인트 형식(이 코드값을 기존으로 어디에서 포인트가 생성되었는지를 체크한다.
	 * 포인트 내용(ptype)
	 * member :1: 회원가입 ,2: 로그인포인트, 3: 회원추천->contents:추천인아이디
	 * board : 11:글등록->contents(bid:gid:uid:(main/reple/ment) <!-- main:글, reple:리플, reply:댓글 ,
	 * 12: 글삭제 등록양식과 동일하게처리
	 * 13 : 추천(비추천) 참여시, 14 : reply, 15:reple
	 * order : 21:물품구매->contents(wizCart.uid), 22:물품환불(취소)->contents(wizCart.uid), 23:포인트결제->contents:wizBuyers.OrderID, 24:포인트결제취소->contents:wizBuyers.OrderID
	 * event : 기타코드-> 기타코드
	 * admin : 41
	 */
	@Column(name = "PTYPE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int ptype;

	public void setPtype(int ptype) {
		this.ptype = ptype;
	}

	public int getPtype() {
		return this.ptype;
	}

	/**
	 * POINT : 포인트
	 */
	@Column(name = "POINT", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int point;

	public void setPoint(int point) {
		this.point = point;
	}

	public int getPoint() {
		return this.point;
	}

	/**
	 * FLAG : 플래그
	 * flag 0 : 즉시 실행, flag 1:보류(보류일경우 관리자 확인 필요, flag 6: 경험치(돈과 연관없이 커뮤니티용))
	 */
	@Column(name = "FLAG", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int flag;

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return this.flag;
	}

	/**
	 * MEMO :  적용내용등 간단한 내용이 이곳에 들어간다.
	 */
	@Column(name = "MEMO", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String memo;

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMemo() {
		return this.memo;
	}

	/**
	 * WDATE : 포인터 적용일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WDATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Date wdate;

	public void setWdate(java.util.Date date) {
		this.wdate = date;
	}
	public Date getWdate() {
		return this.wdate;
	}
}
