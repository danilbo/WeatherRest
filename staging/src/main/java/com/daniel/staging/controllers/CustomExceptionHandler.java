package com.daniel.staging.controllers;

import com.daniel.staging.controllers.exceptions.MyDateException;
import com.daniel.staging.controllers.myvalidator.ApiError;
import com.daniel.staging.controllers.myvalidator.ValidationErrorResponse;
import com.daniel.staging.controllers.myvalidator.Violation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.*;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


//    @ExceptionHandler({ ConstraintViolationException.class })
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ConstraintViolationException hhandleCustomException(ConstraintViolationException ce) {
//        return new ConstraintViolationException("Bad address format!", null);
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getViolations().add( new Violation("HTTP", HttpStatus.BAD_REQUEST.toString()));
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }





//    @ExceptionHandler({ ConstraintViolationException.class })
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiError handleCustomException(ConstraintViolationException ce) {
//
//        return new ApiError(HttpStatus.BAD_REQUEST, "Wrong date format! correct is: dd-MM-yyyy", Collections.singleton(ce.getConstraintViolations()));
//    }

//    @ExceptionHandler({ ConstraintViolationException.class })
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> hhandleCustomException(ConstraintViolationException ce, HttpHeaders headers,
//                                                         HttpStatus status, WebRequest request) {
//
//        String error = "Wrong date format! correct is: dd-MM-yyyy";
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ce.getLocalizedMessage(), error);
//        return new ResponseEntity<Object>(
//                apiError, new HttpHeaders(), apiError.getStatus());
//    }

}

