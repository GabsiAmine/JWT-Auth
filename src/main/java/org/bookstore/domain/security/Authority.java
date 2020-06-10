package org.bookstore.domain.security;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority,Serializable{

	private static final long serialVersionUID = 12354687L;
	
	private final String AUTHORITY;
		
	public Authority(String authority) {
		super();
		this.AUTHORITY = authority;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return AUTHORITY;
	}

}
