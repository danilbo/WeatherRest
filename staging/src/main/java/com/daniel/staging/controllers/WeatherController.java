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
 * Controller
 */

@RestController
@Validated
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    /**
     * Method to get Temperature from DB
     * */
    @GetMapping(value = {"/currentWeather/{date}"})
    public WeatherResult currentWeather(@PathVariable String date) {
        //trying to parse string from RequestParam to LocalDate
            return service.getTemperature(new @Valid Input(date));
    }


}
