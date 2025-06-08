# ☀️ Weather Forecast Application

## 🌐 Project Overview

This is a web application built with **Java** and **Spring Boot** that fetches and displays a **5-Day / 3-Hour weather forecast** for **Skopje**, **Kičevo**, and **Ohrid**, using the [OpenWeatherMap API](https://openweathermap.org/forecast5).  
Due to the 16-day forecast being a paid feature, the free 5-day forecast is used.  

Key Features:
- Highlights days when the temperature exceeds **25°C**  
- Stores forecast data in a **MySQL** database  
- Provides a **REST API** and a **responsive web interface** for users

---

## 🛠️ Technologies Used

- Java (Spring Boot)
- MySQL
- OpenWeatherMap API
- HTML & CSS
- AJAX & JSON
- JUnit (for testing)

---

## ⚙️ Project Setup

### Requirements

- Java 17+
- Maven
- MySQL
- IDE (e.g., IntelliJ)

---

### Database Configuration

1. Create a MySQL database (e.g., `weather_db`)
2. Open `src/main/resources/application.properties` and update the following:

<pre lang="markdown">
spring.datasource.url=jdbc:mysql://localhost:3306/weather_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update</pre>

###  OpenWeatherMap API Key Setup
1. Register at OpenWeatherMap and generate your free API key
2. Add the key to application.properties:
<pre lang="markdown">
openweathermap.api.key=yourapikeyhere</pre>

## 🔑 API Endpoints

The API endpoints are available on:
- Web Interface:
  - http://localhost:8080
- API Endpoints (JSON responses):
   - http://localhost:8080/api/forecast/hot-days – returns days where temperature exceeds 25°C
   - http://localhost:8080/api/forecast/rainy-days – returns days with rain

## 🧪 Testing

Unit tests are located in:
src/test/java/com.example.weatherforecast/

Use JUnit to run the tests and verify that the service behaves as expected.
