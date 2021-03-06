package com.daniel.staging.controllers.myvalidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Validation Error Response class
 * Used to keep error information
 * */

public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }
}

