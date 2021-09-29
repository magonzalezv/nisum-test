package com.nisum.nisumtest.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class JwtDto {

	private String token;

	private String bearer = "Bearer";

	private String email;

	private Collection<? extends GrantedAuthority> authorities;

	public JwtDto(String token, String email, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.email = email;
		this.authorities = authorities;
	}

}
