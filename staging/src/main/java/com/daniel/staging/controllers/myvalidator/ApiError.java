package com.daniel.staging.controllers.myvalidator;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApiError {

    private HttpStatus status;
    private String message;
    private Set<Object> errors;

    public ApiError(HttpStatus status, String message, Set<Object> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors.add(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Set<Object> getErrors() {
        return errors;
    }
}