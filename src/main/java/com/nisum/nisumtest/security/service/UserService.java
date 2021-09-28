package com.nisum.nisumtest.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nisum.nisumtest.security.entity.User;
import com.nisum.nisumtest.security.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public Optional<User> getByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public void save(User user) {
		userRepository.save(user);
	}

}
