package com.nisum.nisumtest.services;

import java.util.List;


import com.nisum.nisumtest.dto.UserDto2;
import com.nisum.nisumtest.model.User2;
import com.nisum.nisumtest.utils.MessageResponse;

public interface UserService2 {

	List<User2> getAllUsers();

	User2 getUserById(Long id);

	MessageResponse createUser(UserDto2 user);

	void updateUser(Long id, User2 user);

	void deleteUser(Long id);

}
