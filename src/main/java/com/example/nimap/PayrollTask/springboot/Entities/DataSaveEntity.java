package com.example.nimap.PayrollTask.springboot.Entities;

public class DataSaveEntity {

	private StockData stockData;

	private StockDataRequest dataRequest;

	public DataSaveEntity(StockData stockData, StockDataRequest dataRequest) {
		super();
		this.stockData = stockData;
		this.dataRequest = dataRequest;
	}

	public StockData getStockData() {
		return stockData;
	}

	public void setStockData(StockData stockData) {
		this.stockData = stockData;
	}

	public StockDataRequest getDataRequest() {
		return dataRequest;
	}

	public void setDataRequest(StockDataRequest dataRequest) {
		this.dataRequest = dataRequest;
	}

}
