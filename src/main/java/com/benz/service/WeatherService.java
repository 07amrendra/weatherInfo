package com.benz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.benz.model.Weather;

@Service
public class WeatherService {
	
	 @Value("${openweathermap.api.key}")
	  private String apiKey;

	  @Value("${openweathermap.api.url}")
	  private String apiUrl; // https://api.openweathermap.org/data/2.5/weather

	  public Weather getWeather(String city) throws Exception {
	    String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric"; // Use metric units by default

	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Map<String, Object>> response = restTemplate.getForEntity(url, 
                (Class<Map<String, Object>>) (Class<?>) Map.class);

	    if (response.getStatusCode() == HttpStatus.OK) {
	      Map<String, Object> weatherData = response.getBody();
	      // Parse the weather data and populate a Weather object
	   // Assuming the ArrayList contains Map objects:
	      List<Map<String, Object>> weatherList = (ArrayList<Map<String, Object>>) weatherData.get("weather");
	      if (weatherList.isEmpty()) {
	        throw new Exception("No weather data found for city: " + city);
	      }
	      Map<String, Object> firstWeatherData = weatherList.get(0);  // Get the first weather object

	      Weather weather = new Weather();
	      weather.setCity(city);
	      weather.setDescription((String) firstWeatherData.get("description"));
	      weather.setTemperature((double) ((Map<String, Object>) weatherData.get("main")).get("temp"));
	      weather.setFeelsLike((double) ((Map<String, Object>) weatherData.get("main")).get("feels_like"));
	      weather.setHumidity((int) ((Map<String, Object>) weatherData.get("main")).get("humidity"));
	      System.out.println("weather "+weather.getTemperature()+" "+weather.getFeelsLike());
	      return weather;
	    } else {
	      throw new Exception("Error fetching weather data: " + response.getStatusCodeValue());
	    }
	  }
}
