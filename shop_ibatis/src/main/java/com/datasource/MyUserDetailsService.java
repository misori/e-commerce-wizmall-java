package com.datasource;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Members;
import com.service.MembersService;
import com.datasource.MyUserDetails;

/**
 * @author: pondol
 */


public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MembersService membersService;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException, DataAccessException {

		System.out.println("MyUserDetailsService : loadUserByUsername");
		Members members = membersService.getMemberByUserid(username);
		//System.out.println(members);
		if(members == null){
			throw new UsernameNotFoundException("user not found");
		}else{
			//String user_name = members.getUser_name();
			String password					= members.getUser_passwd();
			boolean enabled					= true;
			boolean accountNonExpired		= true;
			boolean credentialsNonExpired	= true;
			boolean accountNonLocked		= true;

			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			//System.out.println("getUser_grade:"+members.getUser_grade());
			switch(members.getUser_grade()){
				case 10:
					authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
				break;
				case 100:
					authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
				break;
				default:
					authorities.add(new GrantedAuthorityImpl("ROLE_GUEST"));
				break;
			}

			UserDetails user = new MyUserDetails(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, members);
			//User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

			if (user == null) throw new UsernameNotFoundException("user not found");

			//membersService.updateLogin(members);//로그인 카운트, 로그인 데이타, 로그인 IP를 업데이트 한다.

			return user;
		}
	}
}