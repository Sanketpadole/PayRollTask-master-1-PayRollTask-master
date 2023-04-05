package com.example.nimap.PayrollTask.springboot.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class MyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String information;
	private String symbol;
	private String lastRefreshed;
	private String interval;
	private String outputSize;
	private String timeZone;
	private String timestamp;
	private String open;
	private String high;
	private String low;
	private String close;
	private String volume;

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

	public MyEntity(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public MyEntity(String information, String symbol, String lastRefreshed, String interval, String outputSize,
			String timeZone, String timestamp, String open, String high, String low, String close, String volume) {
		super();
		this.information = information;
		this.symbol = symbol;
		this.lastRefreshed = lastRefreshed;
		this.interval = interval;
		this.outputSize = outputSize;
		this.timeZone = timeZone;
		this.timestamp = timestamp;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public MyEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	// getters and setters

}
