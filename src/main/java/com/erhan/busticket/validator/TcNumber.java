package com.erhan.busticket.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TcNumberValidator.class)
@Documented
public @interface TcNumber {
	String message() default "T.C. Kimlik numaranız hatalı";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
