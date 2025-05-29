package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    List<Forecast> findByMaxTemperatureGreaterThan(double maxTemperature);
    List<Forecast> findByIsRainyTrue();
}
