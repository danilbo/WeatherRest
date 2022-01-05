package com.daniel.staging.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


/**
 * Weather entity
 */


@Entity
@Table(name = "weather_history")
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "temperature")
    private String temperature;

    public WeatherEntity() {}

    public WeatherEntity(Long id, LocalDate date, String temperature) {
        this.id = id;
        this.date = date;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof WeatherEntity))
            return false;
        WeatherEntity w = (WeatherEntity) o;
        return Objects.equals(this.id, w.id) && Objects.equals(this.date, w.date)
                && Objects.equals(this.temperature, w.temperature);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id,this.date,this.temperature);
    }
    @Override
    public String toString() {
        return "Weather{" + "id=" + this.id + ", date=" + this.date + ", temperature=" + this.temperature +'}';
    }

}
