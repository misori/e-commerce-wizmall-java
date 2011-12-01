package com.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.util.Constants;

public class FileUploadUtil {
    protected static Log logger = LogFactory.getLog(FileUploadUtil.class);
   // public static final boolean DEBUG = true ;
    String saveFileName;
    public FileUpload uploadFormFile(MultipartFile formFile, String realPath) {

    	try {
        	saveFileName = formFile.getOriginalFilename();
            File tempFile = getFileUploadFileName(realPath);

            System.out.println("saveFileName1:"+saveFileName);
            //파일이 존재 할 경우 파일명을 바꾸어 준다.
            OutputStream outputStream = new FileOutputStream( realPath + tempFile );
            System.out.println("saveFileName2:"+saveFileName);
            FileCopyUtils.copy( formFile.getInputStream(), outputStream );
            outputStream.close();

            if (logger.isDebugEnabled()) {
                logger.debug("The file has been written to \"" + realPath);
                       // + tempFileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileUpload boardFile = new FileUpload();
        boardFile.setFileName(saveFileName);
        boardFile.setFileSize(formFile.getSize());
        boardFile.setContentType(formFile.getContentType());

       // boardFile.setTempFileName(tempFileName);

        return boardFile;
    }

    /**
     * 등록된 위치를 알기위해 파일이 첨부되지 않았더라도 위치값을 반환시켜준다.
     * @return
     */
    public FileUpload uploadFormFile() { //빈 파일을 리턴 시켜준다.

        FileUpload boardFile = new FileUpload();
        boardFile.setFileName("");
        boardFile.setFileSize(0);
        //boardFile.setContentType(formFile.getContentType());
       // boardFile.setTempFileName(tempFileName);

        return boardFile;
    }

    /**
     * 파일 존재 유무를 따져서 파일이 이미 존재할 경우 새로운 이름을 return 한다.
     * @param realPath
     * @param saveFileName
     * @return
     */
    public File getFileUploadFileName(String realPath){
    	File tempFile = new File(saveFileName);
    	File f	= new File(realPath + tempFile);
        if(f.exists()){
        	int pos = saveFileName.lastIndexOf(".");
        	String fileName	= saveFileName.substring( 0, pos );
			String ext = saveFileName.substring( pos + 1 );
			saveFileName	= fileName+"_"+"."+ext;
			return getFileUploadFileName(realPath);
        }else{
        	return tempFile;
        }
    }

	/**
	 * 파일업로드 처리
	 * @param request
	 * @param filepath : "path/"
	 * @return
	 */
	public List<FileUpload> getFileList(HttpServletRequest request, String filepath) {

		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
		Iterator<String> fileNameIterator = mpRequest.getFileNames();
		List<FileUpload> FileList = new ArrayList<FileUpload>();
		int i = 0;
		while (fileNameIterator.hasNext()) {
			MultipartFile multiFile = mpRequest.getFile((String)fileNameIterator.next());
			//System.out.println("multiFile.getSize():"+multiFile.getSize());
			if (multiFile.getSize() > 0) {
				//String realUploadPath = Constants.AbsolutePath+filepath;//이렇게 하면 일괄적인 것은 좋으나 프로그램상 한계가 발생할 수 있다.
				String realUploadPath = filepath;
				//System.out.println("realUploadPath:"+realUploadPath);
				FileUpload attachFile = uploadFormFile(multiFile,realUploadPath);
				attachFile.setFileNo(i);
				FileList.add(attachFile);
			}else{//size가 없을 경우도 FileList를 채워주어야  자리수에 맞추어 출력할 수 있다.
				FileUpload attachFile = uploadFormFile();
				attachFile.setFileNo(i);
				FileList.add(attachFile);
			}
			i++;
		}


		return FileList;
	}
}
