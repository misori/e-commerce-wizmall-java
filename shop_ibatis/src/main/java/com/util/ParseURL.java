package com.util;

import java.net.MalformedURLException;
import java.net.URL;

public class ParseURL {
	static String protocol;
	static String authority;
	static String host;
	static int port;
	static String path;
	static String query;
	static String filename;
	static String ref;

	public void parseURL(String url){
        URL aURL;
		try {
			//aURL = new URL("http://java.sun.com:80/docs/books/tutorial/index.html?name=networking#DOWNLOADING");
			aURL = new URL(url);

			protocol	= aURL.getProtocol();
	        authority	= aURL.getAuthority();
	        host		= aURL.getHost();
	        port		= aURL.getPort();
	        path		= aURL.getPath();
	        query		= aURL.getQuery();
	        filename	= aURL.getFile();
	        ref			= aURL.getRef();


		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getProtocol(){
		return protocol;
	}

	public String getPath(){
		return path;
	}
}
