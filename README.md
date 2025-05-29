# 🌤️ Weather Forecast Application

## 📌 Project Overview

This project is a web application in **Java (Spring Boot)** that fetches and displays the **5 Day / 3 Hour Forecast** for **Skopje**, **Kičevo**, and **Ohrid** using the **OpenWeatherMap API**.

> ⚠️ Note: The 16-day forecast is a paid feature. This app uses the 5-day forecast available in the free plan.

The project highlights the days where the temperature exceeds **25°C**. The data is stored in a **MySQL database**, and users can access results via a **REST API** and a **simple web interface**.

---

## 🛠️ Technologies Used

- **Java with Spring Boot**
- **MySQL**
- **OpenWeatherMap API**
- **HTML & CSS**
- **AJAX & JSON**
- **JUnit (for testing)**

---

## ⚙️ Project Setup

### ✅ Requirements

- Java 17+
- Maven
- MySQL
- IDE (e.g., IntelliJ IDEA)

---

### 🗄️ Database Setup

1. Create a MySQL database (e.g., `weather_db`).
2. Configure the connection in:  
   `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/weather_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
