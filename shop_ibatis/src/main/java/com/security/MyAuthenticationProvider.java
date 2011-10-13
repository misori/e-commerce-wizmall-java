package com.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

/**
 * @author: Altug Bilgin ALTINTAS
 */

public class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    UserDetailsService userDetailsService;

    public Md5PasswordEncoder getMd5Encoder() {
        return md5Encoder;
    }

    public void setMd5Encoder(Md5PasswordEncoder md5Encoder) {
        this.md5Encoder = md5Encoder;
    }

    Md5PasswordEncoder md5Encoder;


    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String eposta, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser;
        loadedUser = this.getUserDetailsService().loadUserByUsername(eposta);
        System.out.println("MyAuthenticationProvider");
        if ( md5Encoder.isPasswordValid(loadedUser.getPassword(), authentication.getCredentials().toString(), null)) {
            return loadedUser;  // if it comes from directly from user
        }  else if (loadedUser.getPassword().equals(authentication.getCredentials().toString())) {
            return loadedUser; // if it comes from cookie
        }
        throw new BadCredentialsException("Invalid Password");
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;

    }

}