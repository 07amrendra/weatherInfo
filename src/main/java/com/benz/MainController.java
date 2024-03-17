package com.benz;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.io.*;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.benz.model.Weather;
import com.benz.service.WeatherService;

import netscape.javascript.JSObject;

@Controller
@CrossOrigin
public class MainController {
	
	/*
	 * @GetMapping("https://mercedes-hiring-2022.hackerearth.com/check/")
	 * 
	 * @ResponseBody public ResponseEntity<JSONObject> processInput(@RequestBody
	 * Map<String, Object> data) throws JSONException, MalformedURLException,
	 * IOException{ JSONObject json = new JSONObject(IOUtils.toString(new
	 * URL("https://mercedes-hiring-2022.hackerearth.com/check/"),
	 * Charset.forName("UTF-8"))); String N=json.get("Size").toString(); String
	 * Cur=json.get("Current State").toString(); String
	 * Des=json.get("Desired State").toString();
	 * System.out.println(N+" "+Cur+" "+Des); return null;
	 * 
	 * }
	 */
	
	@GetMapping("/")
	public String home() {
	    return "redirect:/weather";
	}


	  @Autowired
	  private WeatherService weatherService;

	  @GetMapping("/weather")
	  public String getWeather(@RequestParam String city, Model model) throws Exception {
	    Weather weather = weatherService.getWeather(city);
	    model.addAttribute("weather", weather);
	    return "weather"; // Name of the Thymeleaf template
	  }
}
