# Weather Forecast Application

## Project Overview

This project is a web application built with Java and Spring Boot that fetches and displays the 5-Day / 3-Hour weather forecast for Skopje, Kičevo, and Ohrid using the OpenWeatherMap API.
Due to the 16-day forecast being a paid feature, the free 5-day forecast was used instead. The application highlights the days when the temperature exceeds 25°C. All forecast data is stored in a MySQL database, and users can access the results through a REST API and a simple, responsive web interface. 

## Technologies Used

-	Java with Spring Boot
-	MySQL
-	OpenWeatherMap API
-	HTML & CSS
-	AJAX & JSON
-	JUnit 

## Project Setup

### Requirements:
-	Java 17+
-	Maven
-	MySQL
-	IDE (IntelliJ)

### Database Setup:

1. Create a MySQL database (e.g. weather_db).
2. To connect the backend with the database, update the file src/main/resources/application.properties with the following:
<pre lang="markdown">   spring.datasource.url=jdbc:mysql://localhost:3306/weather_db 
   spring.datasource.username=your_username 
   spring.datasource.password=your_password 
   spring.jpa.hibernate.ddl-auto=update</pre>

Make sure that MySQL is running, then run the Spring Boot Application. After a successful start you should be able to see the web interface on http://localhost:8080. The API endpoints with JSON responses are available on:
- Web Interface:
  - http://localhost:8080
- API Endpoints (JSON responses):
   - http://localhost:8080/api/forecast/hot-days – returns days where temperature exceeds 25°C
   - http://localhost:8080/api/forecast/rainy-days – returns days with rain



### API Key Setup:
To fetch weather data, you need an API key from OpenWeatherMap.  
Steps:  
1. Register at OpenWeatherMap and generate your free API key.  
2. In application.properties, replace the placeholder with your actual API key:  
<pre lang="markdown">   openweathermap.api.key=c911428c59cb38affeca30a8ca021827</pre>


## Testing:
Unit tests are located in src/test/java/com.example.weatherforecast/.
