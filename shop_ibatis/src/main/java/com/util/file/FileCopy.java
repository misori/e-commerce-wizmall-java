/*
* author 신윤섭
* 출처 : http://www.yunsobi.com/blog/406
* Stream > Buffer > Channel 복사시간 : Channel을 사용하기를 권고
*/
package com.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileCopy {

	/**
	 * Stream을 이용한 파일복사 코드 스니핏
	* source에서 target으로의 파일 복사
	* @param source
	* @param target
	*/
	public void StreamCopy(String source, String target) {
		//복사 대상이 되는 파일 생성
		File sourceFile = new File( source );

		//스트림 선언
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;

		try {
			//스트림 생성
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(target);

			int bytesRead = 0;
			//인풋스트림을 아웃픗스트림에 쓰기
			byte[] buffer = new byte[1024];
			while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

		} catch (Exception e) {
				e.printStackTrace();
		}finally{
				//자원 해제
			try{
				outputStream.close();
			}catch(IOException ioe){}
			try{
				inputStream.close();
			}catch(IOException ioe){}
		}

	}

	/**
	 * Buffer를 이용한 파일복사 코드 스니핏
	* source에서 target으로의 파일 복사
	* @param source
	* @param target
	*/
	public void BufferCopy(String source, String target) {
		//복사 대상이 되는 파일 생성
		File sourceFile = new File( source );

		//스트림, 버퍼 선언
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;

		try {
			//스트림 생성
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(target);
			//버퍼 생성
			bin = new BufferedInputStream(inputStream);
			bout = new BufferedOutputStream(outputStream);

			//버퍼를 통한 스트림 쓰기
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = bin.read(buffer, 0, 1024)) != -1) {
				bout.write(buffer, 0, bytesRead);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//자원 해제
			try{
				outputStream.close();
			}catch(IOException ioe){}
			try{
				inputStream.close();
			}catch(IOException ioe){}
			try{
				bin.close();
			}catch(IOException ioe){}
			try{
				bout.close();
			}catch(IOException ioe){}
		}
	}

	/**
	 * NIO Channel을 이용한 파일복사 코드 스니핏
	* source에서 target으로의 파일 복사
	* @param source 복사할 파일명을 포함한 절대 경로
	* @param target 복사될 파일명을 포함한 절대경로
	*/
	public void ChannelCopy(String source, String target) {
		//복사 대상이 되는 파일 생성
		File sourceFile = new File( source );

		//스트림, 채널 선언
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel fcin = null;
		FileChannel fcout = null;

		try {
			//스트림 생성
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(target);
			//채널 생성
			fcin = inputStream.getChannel();
			fcout = outputStream.getChannel();

			//채널을 통한 스트림 전송
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//자원 해제
			try{
				fcout.close();
			}catch(IOException ioe){}
			try{
				fcin.close();
			}catch(IOException ioe){}
			try{
				outputStream.close();
			}catch(IOException ioe){}
			try{
				inputStream.close();
			}catch(IOException ioe){}
		}
	}

}