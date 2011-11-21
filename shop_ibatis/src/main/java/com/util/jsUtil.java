package com.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pondol
 *
 */
public class jsUtil {
	/****************************************************************************************
	 * 메세지출력후 액션.
	 ****************************************************************************************/

	/*******************************************************************************
	 * 팝업창을 닫고, opener 페이지를 reload()한다.
	 *******************************************************************************/
	public static void goOpenerPageReload(HttpServletResponse res, String src) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";
			if( src != null && src.length() > 0 ){
				msg += "	alert('" + src + "');";
			}
			msg += "opener.location.reload();self.close(); </script>";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}

	}

	/*******************************************************************************
	 * opener 페이지의 TOP프레임을 reload()한다.
	 *******************************************************************************/
	public static void goOpenerReload(HttpServletResponse res, String src) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";
			if( src != null && src.length() > 0 ){
				msg += "	alert('" + src + "');";
			}
			msg += "opener.location.reload(); </script>";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}

	}

	/*******************************************************************************
	 * 팝업창을 닫고, opener 페이지의 TOP프레임을 지정된 URL로 이동한다.
	 *******************************************************************************/
	public static void goOpenerTopPageReload(HttpServletResponse res, String src, String url) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";

			if( src != null && src.length() > 0 ){
				msg += "	alert('" + src + "');";
			}
			msg += "opener.top.location='" + url + "';self.close();  </script>";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}

	}


	/*******************************************************************************
	 * 팝업창을 닫고, opener 페이지의 지정된 프레임을 reload()한다.
	 *******************************************************************************/
	public static void goOpenerFrameReload(HttpServletResponse res, String src, String frameNm) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";
			if( src != null && src.length() > 0 ){
				msg += "	alert('" + src + "');";
			}
			msg += "opener.document."+frameNm+".reload();self.close(); </script>";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}

	}

	/*******************************************************************************
	 * paren 페이지를 새로고침함.
	 *******************************************************************************/
	public static void goParentPageReload(HttpServletResponse res) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";
			msg += "parent.location.reload();</script>";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}

	}

	/*******************************************************************************
	 * 메세지를 출력하고 부모창을 새로고침.
	 *******************************************************************************/
	public static void goMsgParentPageReload(HttpServletResponse res, String src) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";
			if( src != null && src.length() > 0 ){
				msg += "	alert('" + src + "');";
			}
			msg += "opener.location.reload();self.close(); </script>";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}

	}

	/*******************************************************************************
	 * 메세지를 출력하고 새로고침.
	 *******************************************************************************/
	public static void goMsgPageReload(HttpServletResponse res, String src) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";
			if( src != null && src.length() > 0 ){
				msg += "	alert('" + src + "');";
			}
			msg += "location.reload();</script>";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}

	}

	/*******************************************************************************
	 * 메세지를 출력하고 지정된 URL로 이동.
	 *******************************************************************************/
	public static void goMsgMovePage(HttpServletResponse res, String src, String url) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";
			if( src != null && src.length() > 0 ){
				msg += "	alert('" + src + "');";
			}
			msg += "location='"+ url +"';</script>";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}
	}

	/*******************************************************************************
	 * 메세지를 출력하고 TOP프레임을 지정된 URL 이동.
	 *******************************************************************************/
	public static void goMsgMoveTopPage(HttpServletResponse res, String src, String url) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";
			if( src != null && src.length() > 0 ){
				msg += "	alert('" + src + "');";
			}
			msg += "top.location='"+ url +"';</script>";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}

	}

	/*******************************************************************************
	 * 메세지를 출력하고 opener페이지를 지정된 URL 이동.
	 *******************************************************************************/
	public static void goMsgMoveOpenerPage(HttpServletResponse res, String src, String url) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient = res.getWriter();

			String msg = 	"<script language='JavaScript'>";
			if( src != null && src.length() > 0 ){
				msg += "	alert('" + src + "');";
			}
			msg += "opener.location='"+ url +"';self.close();</script>'";
			toClient.println(msg);
			toClient.flush();

		}catch( Exception e){
			e.printStackTrace();
		}

	}

	/*******************************************************************************
	 * 에러 발생시 에러 메시지( javascript alert())를 보여주고 창을 닫는다.
	 *******************************************************************************/
	public static void closeAlter(HttpServletResponse res, String src) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient =res.getWriter();
			if( src != null && src.length() > 0 ){
				String msg = "<script language='JavaScript'>alert('";
				msg = msg + src + "');";
				msg = msg + "self.close();</script>";
				toClient.println(msg);
				toClient.flush();
			}
		}catch( Exception e){
			e.printStackTrace();
		}
	}

	/*******************************************************************************
	 * 메세지만 출력한다.
	 *******************************************************************************/
	public static void alterMsg(HttpServletResponse res, String message) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient =res.getWriter();
			if( message != null && message.length() > 0 ){
				String msg = "<script language='JavaScript'>alert('";
				msg = msg + message + "');";
				msg = msg + "</script>";
				toClient.println(msg);
				toClient.flush();
			}
		}catch( Exception e){
			e.printStackTrace();
		}
	}

	public static void loginCheck ( HttpServletResponse res, String url ) {

		try {
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter printWriter = res.getWriter();

			StringBuffer jsString = new StringBuffer();

			jsString
			.append("\n<script language=\"javascript\">")
			.append("\n  <!--")
			.append("\n      alert(\"로그인 정보가 없습니다.\");")
			.append("\n      if (opener == null) {")
			.append("\n        top.location.href=\"/login.dwm\";")
			.append("\n      } else {")
			.append("\n        this.close();")
			.append("\n        opener.top.location.href=\"/login.dwm\";")
			.append("\n      }")
			.append("\n  //-->")
			.append("\n</script>");

			printWriter.println(jsString.toString());
			printWriter.flush();

		} catch (Exception ex ) {
			ex.printStackTrace();
		}

	}



	/*******************************************************************************
	 * 메세지 출력하고, 자바스크립트 실행 (GreatYSI)
	 *******************************************************************************/
	public static void alterMsgMethod( HttpServletResponse res, String message, String javascript ) {
		if( message != null && message.length() > 0 && javascript != null && javascript.length() > 0 ) {
			try{
				res.setContentType("text/html; charset=euc-kr");
				PrintWriter toClient =res.getWriter();
				StringBuffer msg = new StringBuffer();

				msg
				.append(" <script language='JavaScript'> ")
				.append(" alert('" + message + "');      ")
				.append("  " + javascript + "            ")
				.append(" </script>                      ");

				toClient.println( msg.toString() );
				toClient.flush();
			}catch( Exception e){
				e.printStackTrace();
			}
		}
	}


	/*******************************************************************************
	 * 메세지 출력하고, history.back(-1) (GreatYSI)
	 *******************************************************************************/
	public static void alterMsgBack( HttpServletResponse res, String message ) {
		if( message != null && message.length() > 0 ) {
			try{
				res.setContentType("text/html; charset=euc-kr");
				PrintWriter toClient = res.getWriter();
				StringBuffer msg     = new StringBuffer();

				msg
				.append(" <script language='JavaScript'> ")
				.append(" alert('" + message + "');      ")
				.append(" history.go(-1);                ")
				.append(" </script>                      ");

				toClient.println( msg.toString() );

				toClient.flush();
			}catch( Exception e){
				e.printStackTrace();
			}
		}
	}

	/********************************************************************************
	 * 지정한 창에 특정한 메소드를 호출한다.
	 *******************************************************************************/
	public static void goOpenerMethod(HttpServletResponse res, String target, String method) {

		try{
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter toClient =res.getWriter();
			if( method != null && method.length() > 0 ){
				String 	msg = "<script language='JavaScript'>" +
							  target + "." + method +
							  "</script>";
				toClient.println(msg);
				toClient.flush();
			}
		}catch( Exception e){
			e.printStackTrace();
		}
	}
}
