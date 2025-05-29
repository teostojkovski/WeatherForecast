package com.example.weatherforecast.service;

import com.example.weatherforecast.model.Forecast;
import java.util.List;

public interface ForecastService {
    void fetchAndStoreForecast(String city, double lat, double lon);
    List<Forecast> getHotDays();
    List<Forecast> getRainyDays();
}
