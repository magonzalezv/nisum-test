package com.nisum.nisumtest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.nisumtest.dto.UserDto2;
import com.nisum.nisumtest.model.User2;
import com.nisum.nisumtest.services.UserService2;
import com.nisum.nisumtest.utils.MessageResponse;

@RestController
@RequestMapping("/user2")
public class UserController2 {

	@Autowired
	UserService2 userService;

	@GetMapping("/list")
	public @ResponseBody ResponseEntity<List<User2>> getAllUsers() {
		List<User2> users = userService.getAllUsers();
		if (users.size() > 0) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			users = new ArrayList<>();
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping({ "/{userId}" })
	public @ResponseBody ResponseEntity<User2> getUser(@PathVariable Long userId) {
		User2 userFound = userService.getUserById(userId);
		if (null != userFound) {
			return new ResponseEntity<>(userFound, HttpStatus.OK);
		} else {

			return new ResponseEntity<>(userFound, HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping
	public @ResponseBody ResponseEntity<MessageResponse> createUser(@RequestBody @Valid UserDto2 user,
			BindingResult result) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {

				errores.put(err.getField(), err.getDefaultMessage());

			});

			return new ResponseEntity<>(new MessageResponse(errores.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		MessageResponse userCreated = userService.createUser(user);

		if (null != userCreated.getUser()) {
			return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
			

		} else {
			return new ResponseEntity<>(userCreated, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	

        
        

//	@PutMapping({ "/{userId}" })
//	public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
//		userService.updateUser(userId, user);
//		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
//	}
//
//	@DeleteMapping({ "/{userId}" })
//	public ResponseEntity<User> deleteTodo(@PathVariable("userId") Long userId) {
//		userService.deleteUser(userId);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}

}
