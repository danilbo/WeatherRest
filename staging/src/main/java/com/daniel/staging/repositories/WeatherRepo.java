package com.daniel.staging.repositories;

import com.daniel.staging.entities.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Weather repository for work with DB
 */

public interface WeatherRepo extends JpaRepository<WeatherEntity,Long> {
    ArrayList<WeatherEntity> findByDate(LocalDate date);
}
