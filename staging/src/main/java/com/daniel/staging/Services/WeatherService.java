package com.daniel.staging.Services;

import com.daniel.staging.controllers.myvalidator.Input;
import com.daniel.staging.dto.WeatherResult;
import com.daniel.staging.entities.WeatherEntity;
import com.daniel.staging.repositories.WeatherRepo;
import com.daniel.staging.repositories.YandexRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


/**
 * Weather Service
 * Working with repositories
 */

@Service
@Validated
public class WeatherService {
    @Autowired
    private WeatherRepo repository;
    @Autowired
    private YandexRepo yandexRepo;

    /**
     * Trying to get information from DB, if there is no line with current date,
     * parsing yandex.ru and getting weather from the web page, then adding it into DB
     */
    public WeatherResult getTemperature(@Valid Input date) {
        try {
            LocalDate ld = createDate(date.getDate());
            List<WeatherEntity> list = repository.findByDate(ld);
            if (list.size() == 0) addHistory(ld, getYandexTemperature());
            list = repository.findByDate(ld);
            return resultBuilder(list.get(0));
        } catch (DateTimeParseException e) {
            return null;
        }

    }

    /**
     * return yandex temperature
     */
    public String getYandexTemperature() {
        try {
            return yandexRepo.getTemperature();
        } catch (IOException e) {
            e.printStackTrace();
            return "HTML PARSE ERROR!";
        }
    }

    /**
     * add new line into DB
     */
    @Transactional
    public WeatherEntity addHistory(LocalDate date, String temperature) {
        WeatherEntity newWeather = new WeatherEntity();
        newWeather.setDate(date);
        newWeather.setTemperature(temperature);
        WeatherEntity savedWeather = repository.save(newWeather);
        if (repository.findById(savedWeather.getId()).isPresent()) {
            ResponseEntity.accepted().body("Successfully Created");
            return savedWeather;
        } else {
            ResponseEntity.unprocessableEntity().body("Failed to Create");
            return null;
        }
    }

    /**
     * parse String to LocalDate
     */
    public LocalDate createDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(s, formatter);
    }

    /**
     * Build JSON response
     */
    public WeatherResult resultBuilder(WeatherEntity weather) {
        String result = weather.getTemperature();
        return new WeatherResult(result);
    }
}
