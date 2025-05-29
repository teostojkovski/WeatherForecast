package com.example.weatherforecast.controller;

import com.example.weatherforecast.service.ForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Order(1)  // Give this controller higher priority
public class WebController {
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    private final ForecastService forecastService;

    public WebController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping(value = {"/", "/index", "/home"}, produces = "text/html")
    public ModelAndView index() {
        logger.info("WebController: Handling request for index page");
        try {
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("hotDays", forecastService.getHotDays());
            modelAndView.addObject("rainyDays", forecastService.getRainyDays());
            logger.info("WebController: Successfully prepared model with {} hot days and {} rainy days", 
                forecastService.getHotDays().size(), 
                forecastService.getRainyDays().size());
            return modelAndView;
        } catch (Exception e) {
            logger.error("WebController: Error preparing index page", e);
            throw e;
        }
    }
} 