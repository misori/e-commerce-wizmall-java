package com.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.util.BaseObject;

/**
 *
 * @author Pondol
 *
 */
public class BoardFile extends BaseObject {
	private static final long serialVersionUID = 1L;
	public BoardFile() {

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
	 * PID : Board Tid
	 */
	@Column(name = "PID", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int pid;
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getPid() {
		return this.pid;
	}

	/**
	 * BID : Board Id
	 */
	@Column(name = "BID", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String bid;
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBid() {
		return this.bid;
	}

	/**
	 * GID : Board Group ID
	 */
	@Column(name = "GID", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String gid;
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGid() {
		return this.gid;
	}

	/**
	 * SEQ : 이미지 순서
	 */
	@Column(name = "SEQ", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int seq;
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getSeq() {
		return this.seq;
	}

	/**
	 * FILENAME : 파일명
	 */
	@Column(name = "FILENAME", length = 255, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String filename;
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilename() {
		return this.filename;
	}
}