package com.nisum.nisumtest.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalUser implements UserDetails {

	private static final long serialVersionUID = 6111583904671586270L;

	private String name;

	private String email;

	private String password;

	private List<Phone> phones;

	private Collection<? extends GrantedAuthority> authorities;

	public PrincipalUser(String name, String email, String password, List<Phone> phones,
			Collection<? extends GrantedAuthority> authorities) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
		this.authorities = authorities;
	}

	public static PrincipalUser build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRoleName().name())).collect(Collectors.toList());
		return new PrincipalUser(user.getName(), user.getEmail(), user.getPassword(), user.getPhones(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getName() {
		return name;
	}

	public List<Phone> getPhones() {
		return phones;
	}

}
