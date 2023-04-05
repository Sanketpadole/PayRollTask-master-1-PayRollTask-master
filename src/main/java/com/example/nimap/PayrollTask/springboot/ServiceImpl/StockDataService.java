package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.nimap.PayrollTask.springboot.Entities.MyEntity;
import com.example.nimap.PayrollTask.springboot.Repository.MetaDataRepository;

@Service
@ComponentScan(basePackages = "com.example.nimap.PayrollTask.springboot")
public class StockDataService {
	@Autowired
	MetaDataRepository metaRepository;

	private static final String API_ENDPOINT = "https://www.alphavantage.co/query?function={function}&symbol={symbol}&interval=5min&apikey=YOUR_API_KEY";

	private final RestTemplate restTemplate = new RestTemplate();

	public String getStockData(String symbol) {

		ResponseEntity<String> response = restTemplate.exchange(API_ENDPOINT, HttpMethod.GET, null, String.class,
				"TIME_SERIES_INTRADAY", symbol, "OZHHDG0717QXKQDQ");
		System.err.println("1111" + response);
		String responseBody = response.getBody();

		// create an entity object
		MyEntity entity = new MyEntity();

		// extract meta data values
		try {
			JSONObject jsonObj = new JSONObject(responseBody);
			JSONObject metaData = jsonObj.getJSONObject("Meta Data");
			entity.setInformation(metaData.getString("1. Information"));
			entity.setSymbol(metaData.getString("2. Symbol"));
			entity.setLastRefreshed(metaData.getString("3. Last Refreshed"));
			entity.setInterval(metaData.getString("4. Interval"));
			entity.setOutputSize(metaData.getString("5. Output Size"));
			entity.setTimeZone(metaData.getString("6. Time Zone"));
		} catch (Exception e) {
		}

		try {
			// extract time series data values
			JSONObject jsonObj = new JSONObject(responseBody);
			JSONObject metaData = jsonObj.getJSONObject("Meta Data");
			JSONObject timeSeries = jsonObj.getJSONObject("Time Series (5min)");
			org.springframework.boot.configurationprocessor.json.JSONArray timeStamps = timeSeries.names(); // get all
																											// time

			// stamp keys
			for (int i = 0; i < timeStamps.length(); i++) {
				String timeStamp = timeStamps.getString(i);
				JSONObject values = timeSeries.getJSONObject(timeStamp);

				// create a new entity object for each time stamp
				MyEntity timeStampEntity = new MyEntity();
				timeStampEntity.setId(entity.getId());
				timeStampEntity.setInformation(entity.getInformation());
				timeStampEntity.setSymbol(entity.getSymbol());
				timeStampEntity.setLastRefreshed(entity.getLastRefreshed());
				timeStampEntity.setInterval(entity.getInterval());
				timeStampEntity.setOutputSize(entity.getOutputSize());
				timeStampEntity.setTimeZone(entity.getTimeZone());

				// set the time stamp and time series values
				// timeStampEntity.setTimestamp(timeStamp);
				timeStampEntity.setOpen(values.getString("1. open"));
				timeStampEntity.setHigh(values.getString("2. high"));
				timeStampEntity.setLow(values.getString("3. low"));
				timeStampEntity.setClose(values.getString("4. close"));
				timeStampEntity.setVolume(values.getString("5. volume"));
				System.out.println("BHUSHANMORE123" + timeStampEntity);

				// store the entity object to database
				try {
					System.out.println("BHUSHANMORE1234" + timeStampEntity);
					metaRepository.save(timeStampEntity);

					System.out.println("BHUSHANMORE12345" + metaRepository.save(timeStampEntity));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("error");
				}
			}
		} catch (Exception e) {

		}
		return responseBody;
	}

}
