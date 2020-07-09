package com.example.rest.util;

import org.springframework.validation.BindingResult;

public class StringUtil {
	
	public static String getErrorMsgFromValidator(BindingResult bindingResult) {
		StringBuilder errStringBuilder = new StringBuilder();
		bindingResult.getAllErrors()
			.stream()
			.forEach(error -> errStringBuilder.append(error.getDefaultMessage() + ", "));
		return errStringBuilder.toString();
	}

}
