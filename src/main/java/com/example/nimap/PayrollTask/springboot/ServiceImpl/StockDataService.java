package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.nimap.PayrollTask.springboot.Repository.MetaDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
@ComponentScan(basePackages = "com.example.nimap.PayrollTask.springboot")
public class StockDataService {
	@Autowired
	MetaDataRepository metaRepository;

	private static final String API_ENDPOINT = "https://www.alphavantage.co/query?function={function}&symbol={symbol}&interval=5min&apikey=YOUR_API_KEY";

	private final RestTemplate restTemplate = new RestTemplate();

	public String getStockData(String symbol) throws JsonMappingException, JsonProcessingException {

		ResponseEntity<String> response = restTemplate.exchange(API_ENDPOINT, HttpMethod.GET, null, String.class,
				"TIME_SERIES_INTRADAY", symbol, "OZHHDG0717QXKQDQ");
		// System.err.println("1111" + response);
		String responseBody = response.getBody();

		return responseBody;
	}
}
