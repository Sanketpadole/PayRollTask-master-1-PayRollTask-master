package com.example.nimap.PayrollTask.springboot.Dto;

public class AuthResponseDto {
	private String jwtToken;

	private String refreshToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public AuthResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponseDto(String jwtToken, String refreshToken) {
		super();
		this.jwtToken = jwtToken;
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
