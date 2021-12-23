package com.daniel.staging.controllers.myvalidator;


import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Custom annotation. Used for input validation
 * */

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface Date {

    String message() default "{Date.invalid.CurrentDateTypeIs={dd-MM-yyyy}}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
