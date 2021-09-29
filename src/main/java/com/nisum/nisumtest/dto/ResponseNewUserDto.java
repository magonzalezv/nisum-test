package com.nisum.nisumtest.dto;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNewUserDto {

	private UUID id;
	private Timestamp created;
	private Timestamp modified;
	private Timestamp last_login;
	private boolean isactive;

}
