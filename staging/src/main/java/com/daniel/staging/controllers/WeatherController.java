package com.daniel.staging.controllers;

import com.daniel.staging.Services.WeatherService;
import com.daniel.staging.controllers.myvalidator.Date;
import com.daniel.staging.controllers.myvalidator.Input;
import com.daniel.staging.dto.WeatherResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Main Controller
 */

@RestController
@Validated
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    /**
     * Method to get Temperature with date (received with ?param=...)
     * Trying to get line from DB, if there is no line with current date,
     * parsing yandex.ru and getting weather from the web page
     * */
    @GetMapping(value = {"/currentWeather/{date}"})
    public WeatherResult currentWeather(@PathVariable String date) {
        //trying to parse string from RequestParam to LocalDate
            return service.getTemperature(new @Valid Input(date));
    }


    //test method
    @GetMapping(value = {"/ya"})
    public WeatherResult yaPage() {
//        int r = service.getYandexTemperature();
//        String result = "";
//        if (r >= 0) result = "+";
//        result += r + "°";
//        return new WeatherResult(result);

        throw new RuntimeException("testException");

    }


}
