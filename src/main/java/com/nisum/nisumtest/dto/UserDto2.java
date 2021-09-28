package com.nisum.nisumtest.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.nisum.nisumtest.model.Phone2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto2 {

	@NotNull
	private String name;

	@Email
	@NotNull
	private String email;

	@NotNull
	private String password;

	private List<Phone2> phones;

}
