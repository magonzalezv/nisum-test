package com.nisum.nisumtest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.nisumtest.dto.UpdateUserDto;
import com.nisum.nisumtest.entity.User;
import com.nisum.nisumtest.service.UserService;
import com.nisum.nisumtest.utils.ResponseMessage;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping({ "/{userId}" })
	public @ResponseBody ResponseEntity<?> getUser(@PathVariable UUID userId) {
		User userFound = userService.findById(userId);
		if (null != userFound) {
			return new ResponseEntity<>(userFound, HttpStatus.OK);
		} else {

			return new ResponseEntity<>(new ResponseMessage("No hay usuarios registrados con el id proporcionado"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/list")
	public @ResponseBody ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if (users.size() > 0) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			users = new ArrayList<>();
			return new ResponseEntity(new ResponseMessage("No hay usuarios registrados"), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<ResponseMessage> updateUser(@Valid @RequestBody UpdateUserDto user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new ResponseMessage("Campos incorrectos"), HttpStatus.BAD_REQUEST);
		}

		if (!userService.existsById(user.getId())) {
			return new ResponseEntity<>(new ResponseMessage("El usuario no se encuentra registrado"),
					HttpStatus.BAD_REQUEST);
		}
		userService.updateUser(user);
		return new ResponseEntity<>(new ResponseMessage("Usuario actualizado correctamente"), HttpStatus.OK);
	}

	@DeleteMapping({ "/{userId}" })
	public ResponseEntity<ResponseMessage> deleteTodo(@PathVariable("userId") UUID userId) {

		return userService.deleteUser(userId);
	}

}
