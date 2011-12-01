package com.security;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.domain.Members;
import com.service.MembersService;

/**
 * @author: Altug Bilgin ALTINTAS
 * 참조 : http://offmemory.blogspot.com/ : Spring Security 3 Ajaxified Login with jQuery (1.4.x)
 */

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private UserDetailsService usrServ;



	@Autowired
	private MembersService membersService;

	/**
	 * 로그인 성공시(일반적인 형식)
	 */
	/*
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
    	throws IOException, javax.servlet.ServletException {
        System.out.println("CustomUsernamePasswordAuthenticationFilter Successful Login");

        // 로그인 정보 업데이트
       // Members members	= ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMembersInfo();
        Members members	= ((CustomUser) authResult.getPrincipal()).getMembersInfo();
        membersService.updateLogin(members);//로그인 카운트, 로그인 데이타, 로그인 IP를 업데이트 한다.
        super.successfulAuthentication(request,response,authResult);
    }
    */
	/**
	 * 로그인 성공시(json 리턴)
	 */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
    	throws IOException, javax.servlet.ServletException {
        System.out.println("CustomUsernamePasswordAuthenticationFilter Successful Login");
        super.successfulAuthentication(request,response,authResult);

        // 로그인 정보 업데이트
       // Members members	= ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMembersInfo();
        Members members	= ((CustomUser) authResult.getPrincipal()).getMembersInfo();
        membersService.updateLogin(members);//로그인 카운트, 로그인 데이타, 로그인 IP를 업데이트 한다.

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        responseWrapper.setContentType("text/json");
        String json = "{authenticated:true, navTag : \"" + request.getRequestURI() + "#Crew \"}";
        //log.debug("Successful Authentication, writing JSON success Response: " + json );

        renderResponse(responseWrapper, json);
    }
    private void renderResponse(HttpServletResponseWrapper responseWrapper, String json) throws IOException {
	  Writer out = responseWrapper.getWriter();
	  out.write(json);
	  out.close();
    }


    /**
     * 로그인 실패시(일반적인 형식)
     */
    /*
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
    	throws IOException, javax.servlet.ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        System.out.println("==failed login==");
        //fail일 경우 정보를 남긴다 . 사용자 로그인 실패 횟수..
    }

    */

    /**
     * 로그인 실패시(json 리턴)
     */

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
    	throws IOException, javax.servlet.ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        System.out.println("==failed login==");
        //fail일 경우 정보를 남긴다 . 사용자 로그인 실패 횟수..
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        String failureReason = request.getSession().getAttribute(SPRING_SECURITY_LAST_EXCEPTION_KEY) != null ?
          ((Exception)request.getSession().getAttribute(SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() :
           "Invalid login attempt, check your authentication credentials.";
        responseWrapper.setContentType("text/json");
        String json = "{ authenticated: false,\"errors\": { \"reason\": \" " + failureReason + " \"} }";
       // log.debug("Failed Authentication, writing JSON failure Response: " + json);
        renderResponse(responseWrapper, json);
    }



/*
    @Override
    protected boolean requiresAuthentication(HttpServletRequest request,
            HttpServletResponse response) {
        boolean retVal = false;
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");

        if (username != null && password != null) {
            Authentication authResult = null;
            try {
                //authResult = attemptAuthentication(request);
                if (authResult == null) {
                    retVal = false;
                }

            } catch (AuthenticationException failed) {
                try {
                    unsuccessfulAuthentication(request, response, failed);
                } catch (Exception e) {
                    retVal = false;
                }
                retVal = false;
            }

            try {
                successfulAuthentication(request, response, authResult);
            } catch (Exception e) {
                retVal = false;
            }

            return false;
        } else {
            retVal = super.requiresAuthentication(request, response);
        }
        return retVal;
    }
*/


    public UserDetailsService getUsrServ() {
        return usrServ;
    }

    public void setUsrServ(UserDetailsService usrServ) {
        this.usrServ = usrServ;
    }
}