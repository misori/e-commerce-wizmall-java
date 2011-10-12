package com.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Serialization {

	/**
	* 배열을 받아서 시리얼라이즈 한다.
	* @param str
	*/
	public void Serialize(String outPath, String[] userdata) {
		try{
			FileOutputStream f = new FileOutputStream(outPath);
			ObjectOutput s = new ObjectOutputStream(f);
			s.writeObject(userdata);
			s.flush();
		}

		catch(IOException e) { }
	}

	public void Serialize(String outPath, HashMap<String, String>  userdata) {
		try{
			FileOutputStream f = new FileOutputStream(outPath);
			ObjectOutput s = new ObjectOutputStream(f);
			s.writeObject(userdata);
			s.flush();
		}

		catch(IOException e) { }
	}


	/**
	* 현재 시리얼라이즈 되어 있는 것을 배열로 변환한다.
	* @param getPath
	*/
	public String[] unSerialize(String getPath) {
		String[] userdata	= new String[3];
		try {
			FileInputStream in = new FileInputStream(getPath);
			ObjectInput s = new ObjectInputStream(in);
			userdata = (String[])s.readObject();
		}

		catch(IOException e) { }
		catch(ClassNotFoundException e) {}
		return userdata;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> unSerialize(String getPath, String type) {

		//System.out.println("unSerialize:getPath"+getPath);
		HashMap<String, String> userdata = new HashMap<String, String>();
		try {
			FileInputStream in = new FileInputStream(getPath);
			ObjectInput s = new ObjectInputStream(in);
			//System.out.println("s"+s);
			userdata = (HashMap<String, String>) s.readObject();
		}

		catch(IOException e) { }
		catch(ClassNotFoundException e) {}
		return userdata;
	}
}
