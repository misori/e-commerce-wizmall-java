package com.datasource;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.domain.Members;
import com.service.MembersService;

/**
 * @author: Altug Bilgin ALTINTAS
 */

public class MyAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {
    private UserDetailsService usrServ;

	@Autowired
	private MembersService membersService;

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, javax.servlet.ServletException {
        System.out.println("Successful Login");
       // 로그인 정보 업데이트
        Members members	= ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMembersInfo();
        membersService.updateLogin(members);//로그인 카운트, 로그인 데이타, 로그인 IP를 업데이트 한다.
        super.successfulAuthentication(request,response,authResult);
    }

    public UserDetailsService getUsrServ() {
        return usrServ;
    }

    public void setUsrServ(UserDetailsService usrServ) {
        this.usrServ = usrServ;
    }
}