package com.web.board;

//import java.util.ArrayList;
import java.util.List;

import com.util.BaseObject;

public class BoardFileController extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//private List<String> boardFiles = null;
	private List<String> file = null;

	private String s_title;

	public BoardFileController() {
		//boardFiles = new ArrayList();
		//file = new ArrayList();
	}

	public List<String> getFile() {
		return file;
	}

	public void setFile(List<String> file) {
		this.file = file;
	}

	//기타 각종 입력 정리
	public void setS_title(String s_title) {
		this.s_title = s_title;
	}
	public String getS_title() {
		return this.s_title;
	}



}
