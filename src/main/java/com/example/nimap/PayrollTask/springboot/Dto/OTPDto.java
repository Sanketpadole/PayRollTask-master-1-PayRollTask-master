package com.example.nimap.PayrollTask.springboot.Dto;

import java.util.Date;

public class OTPDto {
	private Integer id;
	private Integer otp;

	private String email;

	private Date setExpiry;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public Date getSetExpiry() {
		return setExpiry;
	}

	public void setSetExpiry(Date setExpiry) {
		this.setExpiry = setExpiry;
	}

	public OTPDto() {
		super();

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
