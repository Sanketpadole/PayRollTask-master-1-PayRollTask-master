package com.example.nimap.PayrollTask.springboot.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostOfficeResponseBean {
	@JsonProperty("Message")
	private String message;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("PostOffice")
	List<PostOfficeDetailsBean> PostOffice;

	public PostOfficeResponseBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostOfficeResponseBean(String message, String status, List<PostOfficeDetailsBean> postOffice) {
		super();
		this.message = message;
		this.status = status;
		PostOffice = postOffice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PostOfficeDetailsBean> getPostOffice() {
		return PostOffice;
	}

	public void setPostOffice(List<PostOfficeDetailsBean> postOffice) {
		PostOffice = postOffice;
	}

}
