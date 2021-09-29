package com.nisum.nisumtest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Validations {
	
	public boolean validatePassword(String password) {
		String regex = "^(?=.*[0-9]{2,})(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();

	}

}
