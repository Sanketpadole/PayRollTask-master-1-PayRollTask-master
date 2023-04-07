package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.nimap.PayrollTask.springboot.Entities.PostOfficeDetailsBean;
import com.example.nimap.PayrollTask.springboot.Services.PostInterface;

@Service
public class PostalServiceImpl implements PostInterface {
	private static final String API_ENDPOINT = "https://api.postalpincode.in/postoffice/{city}";
	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public String fetchPost(String city) {

		ResponseEntity<String> response = restTemplate.exchange(API_ENDPOINT, HttpMethod.GET, null, String.class, city);
		String responseBody = response.getBody();
		for (int i = 0; i < responseBody.length(); i++) {
			PostOfficeDetailsBean post = new PostOfficeDetailsBean();
			post.setCircle(responseBody);

		}
		return responseBody;

	}
}
