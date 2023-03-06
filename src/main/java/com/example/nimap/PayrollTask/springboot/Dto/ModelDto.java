package com.example.nimap.PayrollTask.springboot.Dto;

public class ModelDto {
	String email;
	Integer otp;
	String password;

	public ModelDto() {
		super();

	}

	public ModelDto(String email, Integer otp) {
		super();
		this.email = email;
		this.otp = otp;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
