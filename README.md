Weather Forecast Application

Project Overview

This project is a web application in Java using Java Spring Boot that fetches and displays the 5 Day / 3 Hour Forecast forecast for Skopje, Kicevo and Ohrid using the OpenWeatherMap API. I have used this API because of it being in the free plan, while the 16 day forecast is a paid feature. 
This project hilights the days where the temperature exceeds 25°C. The data is stored in a MySQL database, and the users can access results via a REST API and a simple web interface. 

Technologies Used

-	Java with Spring Boot
-	MySQL
-	OpenWeatherMap API
-	HTML & CSS
-	AJAX & JSON
-	JUnit 

Project Setup

Requirements:
-	Java 17+
-	Maven
-	MySQL
-	IDE (IntelliJ)

Database Setup:

1. Create a MySQL database (e.g. weather_db).
2. To connect the backend with the database, update the file src/main/resources/application.properties with the following:
spring.datasource.url=jdbc:mysql://localhost:3306/weather_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update 

After making sure the MySQL is running, run the Spring Boot Application. After a successful start you should be able to see the web interface on http://localhost:8080. The API endpoints with JSON responses are available on:
http://localhost:8080/api/forecast/hot-days and
http://localhost:8080/api/forecast/rainy-days.



API:
API Keys available at https://home.openweathermap.org/api_keys

Testing:
Unit tests are located in src/test/java/com.example.weatherforecast/.
