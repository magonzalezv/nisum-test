package com.nisum.nisumtest.security.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.nisum.nisumtest.security.entity.Phone;

import lombok.Data;

@Data
public class NewUserDto {

	@NotBlank
	private String name;

	@Email
	private String email;

	@NotBlank
	private String password;
	
	private List<Phone> phones;

	private Set<String> roles = new HashSet<>();

}
