package com.nisum.nisumtest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.nisumtest.dto.JwtDto;
import com.nisum.nisumtest.dto.LoginUserDto;
import com.nisum.nisumtest.dto.NewUserDto;
import com.nisum.nisumtest.dto.ResponseNewUserDto;
import com.nisum.nisumtest.entity.User;
import com.nisum.nisumtest.security.jwt.JwtProvider;
import com.nisum.nisumtest.service.UserService;
import com.nisum.nisumtest.utils.ResponseMessage;
import com.nisum.nisumtest.utils.Validations;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	Validations validations;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody NewUserDto newUser, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(err -> {
				errors.put(err.getField(), err.getDefaultMessage());
			});
			return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
		}

		if (userService.existsByEmail(newUser.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Ese email ya esta registrado"), HttpStatus.BAD_REQUEST);
		}

		if (!validations.validatePassword(newUser.getPassword())) {
			return new ResponseEntity<>(new ResponseMessage("El password no cumple con los requisitos minimos"),
					HttpStatus.BAD_REQUEST);
		}

		User savedUser = userService.save(newUser);

		ResponseNewUserDto responseUser = new ResponseNewUserDto(savedUser.getId(), savedUser.getCreated(),
				savedUser.getModified(), savedUser.getLastLogin(), savedUser.isActive());

		return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUserDto loginUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new ResponseMessage("Campos incorrectos"), HttpStatus.BAD_REQUEST);
		}
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		
		userService.updateLastLogin(userDetails.getUsername());

		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}

}
