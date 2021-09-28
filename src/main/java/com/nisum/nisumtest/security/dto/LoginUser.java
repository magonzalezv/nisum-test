package com.nisum.nisumtest.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginUser {

	@NotBlank
	private String userName;

	@NotBlank
	private String password;
}
