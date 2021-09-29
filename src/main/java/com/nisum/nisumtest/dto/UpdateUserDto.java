package com.nisum.nisumtest.dto;

import java.util.UUID;

public class UpdateUserDto extends NewUserDto {

	private UUID id;

	private boolean isactive;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

}
