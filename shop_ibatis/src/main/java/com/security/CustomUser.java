package com.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.domain.Members;


/**
 * User: Altug Bilgin ALTINTAS
 * Date: 18.May.2010
 * Time: 03:13:46
 */
public class CustomUser extends User {

	private Members members;
    public CustomUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<GrantedAuthority> authorities, Members members) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.members = members;

		// TODO Auto-generated constructor stub
	}

    public Members getMembersInfo() {
        return members;
    }

}