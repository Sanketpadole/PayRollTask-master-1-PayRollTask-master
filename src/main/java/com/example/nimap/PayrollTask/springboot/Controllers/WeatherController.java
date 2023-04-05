package com.example.nimap.PayrollTask.springboot.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimap.PayrollTask.springboot.ServiceImpl.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping("/{city}")
	public ResponseEntity<String> getWeather(@PathVariable String city) {
		try {
			System.err.println("sdgksbjkgdfkg" + city);
			String weatherData = weatherService.getWeather(city);
			System.err.println("sdgksbjkgdfkg11" + weatherData);
			return new ResponseEntity<>(weatherData, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
