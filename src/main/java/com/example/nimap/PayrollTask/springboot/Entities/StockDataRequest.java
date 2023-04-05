package com.example.nimap.PayrollTask.springboot.Entities;

public class StockDataRequest {

	private String function;
	private String symbol;
	private String apiKey;

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getApiKey() {
		return apiKey;
	}

	public StockDataRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockDataRequest(String function, String symbol, String apiKey) {
		super();
		this.function = function;
		this.symbol = symbol;
		this.apiKey = apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
