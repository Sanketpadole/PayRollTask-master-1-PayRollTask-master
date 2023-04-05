package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import java.io.IOException;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

	private final String API_KEY = "YOUR_API_KEY_HERE";
	private final String BASE_URL = "https://maps.openweathermap.org/maps/2.0/radar/forecast/6/13/24?&appid={API key}&tm=1649094000";
//	private final HttpClient httpClient;

//	public WeatherService(HttpClient httpClient) {
//		this.httpClient = httpClient;
//	}

	public String getWeather(String city) throws IOException, InterruptedException, JSONException {
		System.out.println("afbsk333" + city);
		// String url = String.format("%s?q=%s&appid=%s", BASE_URL, city, API_KEY);
		// HttpRequest request =
		// HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		// System.out.println("afbsk444" + request);
		//
		// HttpResponse<String> response = httpClient.send(request,
		// HttpResponse.BodyHandlers.ofString());
		// System.out.println("afbsk555" + response);
		String url = "https://maps.openweathermap.org/maps/2.0/radar/6/13/24?&appid=d63ed8de089df6a9327a879a6fbacb4f&tm=1600781400";
		RestTemplate rest = new RestTemplate();
		String a = rest.getForObject(url, String.class);
		return a;

//		if (response.statusCode() == 401) {
//			return new JSONObject(response.body());
//		} else {
//			throw new IOException("API call failed with status code " + response.statusCode());
//		}
	}
}
