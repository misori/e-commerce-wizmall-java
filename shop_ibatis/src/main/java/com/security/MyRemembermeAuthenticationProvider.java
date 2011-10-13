package com.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: Altug Bilgin ALTINTAS
 */

public class MyRemembermeAuthenticationProvider extends RememberMeAuthenticationProvider {


    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private Md5PasswordEncoder md5Encoder;

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    public Md5PasswordEncoder getMd5Encoder() {
        return md5Encoder;
    }

    public void setMd5Encoder(Md5PasswordEncoder md5Encoder) {
        this.md5Encoder = md5Encoder;
    }


    public MyRemembermeAuthenticationProvider() {

        super();    //To change body of overridden methods use File | Settings | File Templates.
        System.out.println("MyRemembermeAuthenticationProvider2");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	System.out.println("MyRemembermeAuthenticationProvider1");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        return authentication;
    }
}