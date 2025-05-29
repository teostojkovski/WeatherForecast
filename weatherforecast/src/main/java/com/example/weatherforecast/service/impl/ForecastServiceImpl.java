package com.example.weatherforecast.service.impl;

import com.example.weatherforecast.model.Forecast;
import com.example.weatherforecast.repository.ForecastRepository;
import com.example.weatherforecast.service.ForecastService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${openweathermap.api.key}")
    private String apiKey;

    public ForecastServiceImpl(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void fetchAndStoreForecast(String city, double lat, double lon) {
        String url = String.format("https://api.openweathermap.org/data/2.5/forecast?lat=%f&lon=%f&appid=%s&units=metric",
                lat, lon, apiKey);

        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);

            JsonNode listArray = root.get("list");
            List<Forecast> forecasts = new ArrayList<>();

            for (JsonNode item : listArray) {
                double maxTemp = item.get("main").get("temp_max").asDouble();
                long dt = item.get("dt").asLong();
                LocalDate date = Instant.ofEpochSecond(dt).atZone(ZoneId.systemDefault()).toLocalDate();

                String description = "";
                JsonNode weatherArray = item.get("weather");
                if (weatherArray.isArray()) {
                    Iterator<JsonNode> it = weatherArray.elements();
                    if (it.hasNext()) {
                        description = it.next().get("description").asText().toLowerCase();
                    }
                }

                // Check if description contains rain-related keywords
                boolean isRainy = isDescriptionRainy(description);

                Forecast forecast = new Forecast();
                forecast.setCity(city);
                forecast.setDate(date);
                forecast.setMaxTemperature(maxTemp);
                forecast.setRainy(isRainy);
                forecast.setDescription(description);

                forecasts.add(forecast);
            }

            forecastRepository.saveAll(forecasts);

        } catch (Exception e) {
            System.out.println("Error while fetching/storing forecast: " + e.getMessage());
        }
    }

    private boolean isDescriptionRainy(String description) {
        return description.contains("rain") || description.contains("shower") || description.contains("drizzle");
    }

    @Override
    public List<Forecast> getHotDays() {
        List<Forecast> allHotDays = forecastRepository.findByMaxTemperatureGreaterThan(25.0);
        return groupAndFilterForecasts(allHotDays);
    }

    @Override
    public List<Forecast> getRainyDays() {
        List<Forecast> allRainyDays = forecastRepository.findByIsRainyTrue();
        return groupAndFilterForecasts(allRainyDays);
    }

    private List<Forecast> groupAndFilterForecasts(List<Forecast> forecasts) {
        // Group forecasts by city and date
        Map<String, Map<LocalDate, Forecast>> groupedByCityAndDate = forecasts.stream()
                .collect(Collectors.groupingBy(
                        Forecast::getCity,
                        Collectors.groupingBy(
                                Forecast::getDate,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .max(Comparator.comparingDouble(Forecast::getMaxTemperature))
                                                .orElse(null)
                                )
                        )
                ));

        // Flatten the map back to a list
        return groupedByCityAndDate.values().stream()
                .flatMap(dateMap -> dateMap.values().stream())
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Forecast::getDate))
                .collect(Collectors.toList());
    }
}
