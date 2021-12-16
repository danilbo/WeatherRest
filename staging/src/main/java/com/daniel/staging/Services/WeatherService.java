package com.daniel.staging.Services;

import com.daniel.staging.dto.WeatherResult;
import com.daniel.staging.entities.WeatherEntity;
import com.daniel.staging.repositories.WeatherRepo;
import com.daniel.staging.repositories.YandexRepo;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Weather Service
 * Working with repositories
 */

@Service
public class WeatherService {
    @Autowired
    private WeatherRepo repository;
    @Autowired
    private YandexRepo yandexRepo;

    /**
     * return all DB lines
     */
    public String getAll() {
        return repository.findAll().toString();
    }

    /**
     * return lines by date
     */
    public List<WeatherEntity> getTemperature(LocalDate date) {
        return repository.findByDate(date);
    }

    /**
     * return yandex temperature
     */
    public int getYandexTemperature() {
        try {
            return yandexRepo.getTemperature();
        } catch (IOException e) {
            e.printStackTrace();
            return -999;
        }
    }

    /**
     *add new line into DB
     */
    @Transactional
    public WeatherEntity addHistory(LocalDate date, int temperature) {
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
     * */
    public LocalDate createDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(s, formatter);
    }

    /**
     * Build JSON response
     * */
    public WeatherResult resultBuilder(WeatherEntity weather) {

        String result = "";
        int t = weather.getTemperature();

        if (t >= 0) result += "+";
        result += t + "°";
        return new WeatherResult(result);
    }
}
