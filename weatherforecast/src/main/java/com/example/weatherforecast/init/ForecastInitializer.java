package com.example.weatherforecast.init;

import com.example.weatherforecast.repository.ForecastRepository;
import com.example.weatherforecast.service.ForecastService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ForecastInitializer implements CommandLineRunner {

    private final ForecastService forecastService;
    private final ForecastRepository forecastRepository;

    public ForecastInitializer(ForecastService forecastService, ForecastRepository forecastRepository) {
        this.forecastService = forecastService;
        this.forecastRepository = forecastRepository;
    }

    @Override
    public void run(String... args) {
        // Clear existing data
        forecastRepository.deleteAll();
        System.out.println("Cleared existing forecast data");

        // Fetch new forecasts
        System.out.println("Fetching new forecast data...");
        forecastService.fetchAndStoreForecast("Skopje", 41.9981, 21.4254);
        forecastService.fetchAndStoreForecast("Ohrid", 41.1231, 20.8016);
        forecastService.fetchAndStoreForecast("Kicevo", 41.5126, 20.9581);
        System.out.println("Forecast data updated successfully");
    }
}
