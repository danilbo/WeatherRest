package com.daniel.staging.controllers.myvalidator;

/**
 * Custom input class to validate data input
 * Using custom(handmade) Date annotation
 */

public class Input {
    @Date
    private String date;

    public Input(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
