package com.nisum.nisumtest.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nisum.nisumtest.dto.NewUserDto;
import com.nisum.nisumtest.dto.UpdateUserDto;
import com.nisum.nisumtest.entity.Role;
import com.nisum.nisumtest.entity.User;
import com.nisum.nisumtest.enums.RoleName;
import com.nisum.nisumtest.repository.UserRepository;
import com.nisum.nisumtest.utils.ResponseMessage;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleService roleService;

	public Optional<User> getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findById(UUID id) {
		return userRepository.findById(id);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public boolean existsById(UUID userId) {
		return userRepository.existsById(userId);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User updateUser(UpdateUserDto updateUser) {
		User user = new User(updateUser.getName(), updateUser.getEmail(),
				passwordEncoder.encode(updateUser.getPassword()), updateUser.getPhones(), updateUser.isIsactive());
		user.setId(updateUser.getId());
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
		if (updateUser.getRoles().contains("admin")) {
			roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
		}
		user.setRoles(roles);
		return userRepository.save(user);
	}

	public User save(NewUserDto newUser) {

		User user = new User(newUser.getName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()),
				newUser.getPhones(), true);
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
		if (newUser.getRoles().contains("admin")) {
			roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
		}
		user.setRoles(roles);
		return userRepository.save(user);
	}

	public ResponseEntity<ResponseMessage> deleteUser(UUID userId) {

		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			return new ResponseEntity<>(new ResponseMessage("Eliminado satisfactoriamente"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage("El usuario que intenta eliminar no existe"),
					HttpStatus.BAD_REQUEST);
		}

	}

	public void updateLastLogin(String email) {
		User user = userRepository.findByEmail(email).get();
		user.setLastLogin(Timestamp.from(Instant.now()));
		userRepository.save(user);
	}

}
