package com.daniel.staging.controllers.myvalidator;


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
