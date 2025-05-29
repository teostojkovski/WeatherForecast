package com.example.weatherforecast;

import com.example.weatherforecast.model.Forecast;
import com.example.weatherforecast.repository.ForecastRepository;
import com.example.weatherforecast.service.ForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WeatherforecastApplicationTests {

	@Autowired
	private ForecastRepository forecastRepository;

	@Autowired
	private ForecastService forecastService;

	@BeforeEach
	void setUp() {
		forecastRepository.deleteAll();
	}

	@Test
	void contextLoads() {
		assertNotNull(forecastService);
		assertNotNull(forecastRepository);
	}

	@Test
	void testGetHotDays() {
		// Create test data
		Forecast hotDay1 = new Forecast();
		hotDay1.setCity("Skopje");
		hotDay1.setDate(LocalDate.now());
		hotDay1.setMaxTemperature(28.0);
		hotDay1.setRainy(false);

		Forecast hotDay2 = new Forecast();
		hotDay2.setCity("Ohrid");
		hotDay2.setDate(LocalDate.now());
		hotDay2.setMaxTemperature(26.0);
		hotDay2.setRainy(false);

		forecastRepository.saveAll(Arrays.asList(hotDay1, hotDay2));

		// Test getHotDays
		List<Forecast> hotDays = forecastService.getHotDays();
		assertFalse(hotDays.isEmpty());
		assertTrue(hotDays.stream().allMatch(f -> f.getMaxTemperature() > 25.0));
	}

	@Test
	void testGetRainyDays() {
		// Create test data
		Forecast rainyDay1 = new Forecast();
		rainyDay1.setCity("Skopje");
		rainyDay1.setDate(LocalDate.now());
		rainyDay1.setMaxTemperature(20.0);
		rainyDay1.setRainy(true);

		Forecast rainyDay2 = new Forecast();
		rainyDay2.setCity("Ohrid");
		rainyDay2.setDate(LocalDate.now());
		rainyDay2.setMaxTemperature(22.0);
		rainyDay2.setRainy(true);

		forecastRepository.saveAll(Arrays.asList(rainyDay1, rainyDay2));

		// Test getRainyDays
		List<Forecast> rainyDays = forecastService.getRainyDays();
		assertFalse(rainyDays.isEmpty());
		assertTrue(rainyDays.stream().allMatch(Forecast::isRainy));
	}
}