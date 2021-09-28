package com.nisum.nisumtest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nisum.nisumtest.security.entity.PrincipalUser;
import com.nisum.nisumtest.security.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.getByUserName(userName).get();
		return PrincipalUser.build(user);
	}

}
