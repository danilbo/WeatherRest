package com.daniel.staging.dto;

/**
 * Container for resulting response
 */
public class WeatherResult {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    public WeatherResult(String temperature) {
        this.temperature = temperature;
    }
}
