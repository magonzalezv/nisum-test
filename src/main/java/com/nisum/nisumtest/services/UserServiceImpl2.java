package com.nisum.nisumtest.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nisum.nisumtest.dto.UserDto2;
import com.nisum.nisumtest.model.Phone2;
import com.nisum.nisumtest.model.User2;
import com.nisum.nisumtest.repository.PhoneRepository2;
import com.nisum.nisumtest.repository.UserRepository2;
import com.nisum.nisumtest.utils.MessageResponse;


@Service
public class UserServiceImpl2 implements UserService2{

	@Autowired
	UserRepository2 userRepository;

	@Autowired
	PhoneRepository2 phoneRepository;

	@Override
	public List<User2> getAllUsers() {

		List<User2> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);

		return users;
	}

	@Override
	public User2 getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public MessageResponse createUser(UserDto2 userDto) {

		MessageResponse messageResponse;

		User2 user = new User2();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());

		user.setPhone(userDto.getPhones());

		if (!userRepository.existsByEmail(userDto.getEmail())) {
//			if (validateEmail(user.getEmail())) {
				if (validatePassword(user.getPassword())) {
					messageResponse = new MessageResponse("Creado correctamente", userRepository.save(user));
				} else {
					messageResponse = new MessageResponse("El password no cumple con los requisitos minimos", null);
				}
//			} else {
//				messageResponse = new MessageResponse("Email invalido", null);
//			}
		} else {
			messageResponse = new MessageResponse("El correo ya esta registrado", null);
		}

		return messageResponse;

	}


	@Override
	public void updateUser(Long id, User2 user) {
//		User userFromDB = userRepository.findById(id).get();
//		System.out.println(userFromDB.toString());
//		userFromDB.setName(user.getName());
//		userFromDB.setEmail(user.getEmail());
//		userFromDB.setPassword(user.getPassword());
//		userFromDB.setNumber(user.getNumber());
//		userFromDB.setCityCode(user.getCityCode());
//		userFromDB.setCountryCode(user.getCountryCode());
//
//		userRepository.save(userFromDB);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);

	}

	private boolean validateEmail(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}

	private boolean validatePassword(String password) {
		String regex = "^(?=.*[0-9]{2,})(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();

	}

	

}
