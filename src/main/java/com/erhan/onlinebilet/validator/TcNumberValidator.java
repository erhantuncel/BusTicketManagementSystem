package com.erhan.onlinebilet.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class TcNumberValidator implements ConstraintValidator<TcNumber, String> {

	@Override
	public void initialize(TcNumber value) {
		
	}

	@Override
	public boolean isValid(String tcNumber, ConstraintValidatorContext constraintContext) {
		if(tcNumber != null && (tcNumber.length() != 11 || !isDigits(tcNumber))) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isDigits(String s) {
		return Pattern.matches("\\d+", s);
	}
}
