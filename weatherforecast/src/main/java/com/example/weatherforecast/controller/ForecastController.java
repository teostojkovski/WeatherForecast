package com.example.weatherforecast.controller;

import com.example.weatherforecast.model.Forecast;
import com.example.weatherforecast.service.ForecastService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forecast")
@CrossOrigin  // Allow cross-origin requests
public class ForecastController {

    private final ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping(value = "/hot-days", produces = "application/json")
    public List<Forecast> getHotDays() {
        return forecastService.getHotDays();
    }

    @GetMapping(value = "/rainy-days", produces = "application/json")
    public List<Forecast> getRainyDays() {
        return forecastService.getRainyDays();
    }
}
