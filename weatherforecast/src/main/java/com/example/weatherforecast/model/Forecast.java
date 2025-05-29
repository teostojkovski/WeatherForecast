package com.example.weatherforecast.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
public class Forecast {

    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private LocalDate date;
    private double maxTemperature;
    private boolean isRainy;
    private String description;
}
