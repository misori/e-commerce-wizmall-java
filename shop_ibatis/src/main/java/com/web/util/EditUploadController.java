package com.web.util;
//
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.util.file.FileUpload;
import com.util.file.FileUploadUtil;

@Controller
public class EditUploadController {
	@RequestMapping("/SmartEditorUploadForm")
	public ModelAndView SmartEditorUploadForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//form=memberForm&zip1=zip1_1&zip2=zip1_2&firstaddress=address1&secondaddress=address2
		System.out.println("EditUploadController");
		String id				= request.getParameter("id");
		mav.addObject("id", id);
		mav.setViewName("util/editor/SmartEditor/imgupload.jsp");
		return mav;

	}

	@SuppressWarnings("restriction")
	@RequestMapping("/SmartEditorUpload_x")
	public ModelAndView SmartEditorUpload_x(HttpServletRequest request) {
		String id	= request.getParameter("id");
		//System.out.println("id:"+id);
		ModelAndView mav = new ModelAndView();
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		//파일을 업로드 한다.
		List<FileUpload> FileList = fileUploadUtil.getFileList(request, "editor/");

		mav.addObject("id", id);
		mav.addObject("filename", FileList.get(0).getFileName());

		//자	바 스크립트를 호출한다.


/*


		 ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
        ScriptEngine jsEngine = scriptEngineMgr.getEngineByName("JavaScript");

        try {

            jsEngine.eval("parent.parent.insertIMG('"+id+"','/editor/"+FileList.get(0).getFileName()+"')");
            jsEngine.eval("parent.parent.oEditors.getById['"+id+"'].exec('SE_TOGGLE_IMAGEUPLOAD_LAYER')");

          } catch (ScriptException ex) {
              ex.printStackTrace();
          }
*/

          mav.setViewName("util/editor/SmartEditor/imgupload_result.jsp");
          return mav;

	}
}
