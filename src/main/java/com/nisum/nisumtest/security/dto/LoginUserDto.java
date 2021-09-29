package com.nisum.nisumtest.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginUserDto {

	@NotBlank
	private String email;

	@NotBlank
	private String password;
}
