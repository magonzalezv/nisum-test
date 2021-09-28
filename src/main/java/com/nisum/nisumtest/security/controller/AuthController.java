package com.nisum.nisumtest.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.nisumtest.security.dto.JwtDto;
import com.nisum.nisumtest.security.dto.LoginUser;
import com.nisum.nisumtest.security.dto.NewUser;
import com.nisum.nisumtest.security.entity.Role;
import com.nisum.nisumtest.security.entity.User;
import com.nisum.nisumtest.security.enums.RoleName;
import com.nisum.nisumtest.security.jwt.JwtProvider;
import com.nisum.nisumtest.security.service.RoleService;
import com.nisum.nisumtest.security.service.UserService;
import com.nisum.nisumtest.security.utils.ResponseMessage;
import com.nisum.nisumtest.utils.MessageResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new ResponseMessage("Campos incorrectos"), HttpStatus.BAD_REQUEST);
		}

		if (userService.existsByUserName(newUser.getUserName())) {
			return new ResponseEntity(new ResponseMessage("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		}

		if (userService.existsByEmail(newUser.getEmail())) {
			return new ResponseEntity(new ResponseMessage("Ese email ya existe"), HttpStatus.BAD_REQUEST);
		}

		User user = new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(),
				passwordEncoder.encode(newUser.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
		if (newUser.getRoles().contains("admin")) {
			roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
		}
		user.setRoles(roles);

		userService.save(user);

		return new ResponseEntity(new ResponseMessage("usuario creado"), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new ResponseMessage("Campos incorrectos"), HttpStatus.BAD_REQUEST);
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

}
