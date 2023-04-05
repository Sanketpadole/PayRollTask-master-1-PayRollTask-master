package com.example.nimap.PayrollTask.springboot.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class MetaData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public MetaData(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("1. Information")
	private String information;
	@JsonProperty("2. Symbol")
	private String symbol;
	@JsonProperty("3. Last Refreshed")
	private String lastRefreshed;
	@JsonProperty("4. Interval")
	private String interval;
	@JsonProperty("5. Output Size")
	private String outputSize;
	@JsonProperty("6. Time Zone")
	private String timeZone;

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLastRefreshed() {
		return lastRefreshed;
	}

	public void setLastRefreshed(String lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getOutputSize() {
		return outputSize;
	}

	public void setOutputSize(String outputSize) {
		this.outputSize = outputSize;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public MetaData(String information, String symbol, String lastRefreshed, String interval, String outputSize,
			String timeZone) {
		super();
		this.information = information;
		this.symbol = symbol;
		this.lastRefreshed = lastRefreshed;
		this.interval = interval;
		this.outputSize = outputSize;
		this.timeZone = timeZone;
	}

	public MetaData() {
		super();
		// TODO Auto-generated constructor stub
	}
}
