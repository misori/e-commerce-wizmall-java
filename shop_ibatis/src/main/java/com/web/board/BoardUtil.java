package com.web.board;

import java.util.HashMap;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.junit.Test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@SuppressWarnings("restriction")
public class BoardUtil {

	/**
	 * opflag 에 들어갈 각종 값들을 json으로 생성한다.
	 */
	@Test
	public String toJsonOpFlag(HashMap<String, String> op){
		JSONObject jsonObject = JSONObject.fromObject( op );
		return Base64.encode(jsonObject.toString().getBytes());
	}

	@Test
	public HashMap<String, String> formJsonOpFlag(String input){

		HashMap<String, String> op = new HashMap<String, String>();
		if (input == null) return op;
		String jsonTxt = new String(Base64.decode(input));
		JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt.toString() );
		op.put("owner", json.get("owner").toString());//관리자단에서 볼경우 owner에 admin값이 들어감, 기타 null
		return op;
	}

}
