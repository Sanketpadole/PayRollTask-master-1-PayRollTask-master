package com.example.nimap.PayrollTask.springboot.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimap.PayrollTask.springboot.ServiceImpl.StockDataService;

@RestController
@RequestMapping("/stocks")
public class StockController {
	private final StockDataService stockDataService = new StockDataService();

	@GetMapping("/stock")
	public ResponseEntity<?> getStockData(@RequestParam("symbol") String symbol) {
		System.err.println("shdfjjvsd999" + symbol);
		String stockData = stockDataService.getStockData(symbol);

		if (stockData == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(stockData, HttpStatus.OK);
		}
	}

}
