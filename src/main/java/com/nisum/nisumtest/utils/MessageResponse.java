package com.nisum.nisumtest.utils;

import com.nisum.nisumtest.dto.UserDto2;
import com.nisum.nisumtest.model.User2;

public class MessageResponse {

	private String statusMessage;
	private User2 user;

	public MessageResponse(String statusMessage, User2 user) {
		super();
		this.statusMessage = statusMessage;
		this.user = user;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public User2 getUser() {
		return user;
	}

	public void setUser(User2 user) {
		this.user = user;
	}

}
