package com.daniel.staging.controllers;

import com.daniel.staging.Services.WeatherService;
import com.daniel.staging.dto.WeatherResult;
import com.daniel.staging.entities.WeatherEntity;
import com.daniel.staging.repositories.YandexRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Main Controller
 */

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    /**
     * Method to get Temperature with date (received with ?param=...)
     * Trying to get line from DB, if there is no line with current date,
     * parsing yandex.ru and getting weather from the web page
     * */
    @GetMapping(value = {"/currentWeather"})
    public WeatherResult currentWeather(@RequestParam(value = "date", required = false, defaultValue = "none") String date) {
        //trying to parse string from RequestParam to LocalDate
        try {
            LocalDate ld = service.createDate(date);
            List<WeatherEntity> tmp = service.getTemperature(ld);
            return service.resultBuilder(tmp.get(0));
        } catch (Exception e) {
            System.out.println("Wrong date format!");
            if (date.equals("none")) return new WeatherResult("Please input date to url address:(.../currentWeather?date=yyyy-MM-dd)");
            return new WeatherResult("Invalid date format. Correct format is: yyyy-MM-dd");
        }

    }


    //test method
    @GetMapping(value = {"/ya"})
    public WeatherResult yaPage() {
        int r = service.getYandexTemperature();
        String result = "";
        if (r >= 0) result = "+";
        result += r + "°";
        return new WeatherResult(result);
    }

}
