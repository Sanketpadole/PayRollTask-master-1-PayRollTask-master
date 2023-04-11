package com.springboot.nimap.PayrollTask.springboot.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nimap.PayrollTask.springboot.Repository.OTPRepository;

@Component
public class GlobalFunctions {
	public final static String CUSTUM_ATTRIBUTE_USER_ID = "X-user-id";
	public final static String CUSTUM_ATTRIBUTE_USER_ROLES = "X-user-roles";
	public final static String CUSTUM_ATTRIBUTE_USER_PERMISSIONS = "X-user-permissions";

	@Autowired
	OTPRepository otpRepository;

	public void deleteExistingOTPForGivenEmail(String email) {
		otpRepository.deleteAllByEmail(email);
	}

}
