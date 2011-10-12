package com.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileManage {
	/**
	 * 파일 삭제
	 * @param path : must be "c:/folder/filename.txt"
	 * @return 0:success, 1:fail, 2:no file
	 */
	public int DeleteFile(String path){
		File file = new File(path);
		if(file.exists())
		{
			boolean deleteFlag = file.delete();

			if(deleteFlag){
				System.out.println("success");
				return 0;
			}else{
				System.out.println("fail");
				return 1;
			}
		}else{
			System.out.println("no file");
			return 2;
		}
	}

	/**
	 * 폴더 삭제(단일 폴더만 삭제)
	 * @param path : must be "c:/folder/"
	 * @return 0:success, 1:fail, 2:no director
	 */
	public int DeleteDirOnly(String path){
		File file = new File(path);
		if(file.exists())
		{
			boolean deleteFlag = file.delete();

			if(deleteFlag){
				System.out.println("success");
				return 0;
			}else{
				System.out.println("fail");
				return 1;
			}
		}else{
			System.out.println("no director");
			return 2;
		}
	}

	/**
	 * 폴더 삭제(내부 파일까지 모두 삭제)
	 * @param path : must be "c:/folder/"
	 * @return 0:success, 1:fail, 2:no director
	 */
	public int DeleteDir(String path){
		return DeleteDir(new File(path));
	}
	public int DeleteDir(File targetFolder){

		if(targetFolder.exists())
		{
			File[] childFile = targetFolder.listFiles();
		    int size = childFile.length;
		    //System.out.println("DeleteDir size : " + size);

		    if (size > 0) {
		    	for (int i = 0; i < size; i++) {
			    	if (childFile[i].isFile()) {
				    	childFile[i].delete();
				    	//System.out.println("childFile[i].delete() ");
				    	//return 0;
			    	} else {//파일이 아닌경우(디렉토리일경우) 다시 돌린다.
			    		DeleteDir(childFile[i]);
			    	}
		    	}
		    }
		    //폴더삭제(이부분이 없으면 내부 파일들만 삭제된다.)
	    	//System.out.println("targetFolder.delete() ");
	    	targetFolder.delete();

		}
	    return 0;
	}

	/**
	 * 폴더 생성
	 * @param path : must be "c:/folder"
	 * @return
	 */
	public int makeDir(String path){
		File f = new File(path);
		try{
			if(f.mkdir()){
				System.out.println("Directory Created");
				return 0;
			}else{
				System.out.println("Directory is not created");
				return 1;
			}
		}catch(Exception e){
			e.printStackTrace();
			return 2;
		}
	}


	/**
	 * 파일카피 (Channel을 이용한 파일복사) 자세한 내용은 FileCopy.java 참조
	 * @param source
	 * @param target
	 */
	public void fileCopy(String source, String target){
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
