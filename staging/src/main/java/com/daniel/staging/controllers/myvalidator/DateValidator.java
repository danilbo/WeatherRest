package com.daniel.staging.controllers.myvalidator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * My custom validator
 * Trying to parse String into LocalDate, if there are any exceptions, input is invalid
 */

public class DateValidator implements ConstraintValidator<Date,String> {

    @Override
    public boolean isValid(String dateStr,
                           ConstraintValidatorContext cxt) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}