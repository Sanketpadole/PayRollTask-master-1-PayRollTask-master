package com.example.nimap.PayrollTask.springboot.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostOfficeDetailsBean {
	@JsonProperty("Name")
	private String Name;
	@JsonProperty("Description")
	private String Description;
	@JsonProperty("SubPostOffice")
	private String SubPostOffice;
	@JsonProperty("DeliveryStatus")
	private String DeliveryStatus;
	@JsonProperty("Circle")
	private String Circle;
	@JsonProperty("District")
	private String District;
	@JsonProperty("Division")
	private String Division;
	@JsonProperty("Region")
	private String Region;
	@JsonProperty("State")
	private String State;
	@JsonProperty("Country")
	private String Country;
	@JsonProperty("Pincode")
	private Long Pincode;

	public PostOfficeDetailsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostOfficeDetailsBean(String name, String description, String subPostOffice, String deliveryStatus, String circle,
			String district, String division, String region, String state, String country, Long pincode) {
		super();
		Name = name;
		Description = description;
		SubPostOffice = subPostOffice;
		DeliveryStatus = deliveryStatus;
		Circle = circle;
		District = district;
		Division = division;
		Region = region;
		State = state;
		Country = country;
		Pincode = pincode;
	}

	public PostOfficeDetailsBean(Long pincode) {
		super();
		Pincode = pincode;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getSubPostOffice() {
		return SubPostOffice;
	}

	public void setSubPostOffice(String subPostOffice) {
		SubPostOffice = subPostOffice;
	}

	public String getDeliveryStatus() {
		return DeliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		DeliveryStatus = deliveryStatus;
	}

	public String getCircle() {
		return Circle;
	}

	public void setCircle(String circle) {
		Circle = circle;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getDivision() {
		return Division;
	}

	public void setDivision(String division) {
		Division = division;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public Long getPincode() {
		return Pincode;
	}

	public void setPincode(Long pincode) {
		Pincode = pincode;
	}

}
