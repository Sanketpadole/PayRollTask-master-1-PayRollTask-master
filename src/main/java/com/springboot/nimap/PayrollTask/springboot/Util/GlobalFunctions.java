package com.springboot.nimap.PayrollTask.springboot.Util;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	public static String createRandomString(int length) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'

		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(length)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	public static String getFileUrl(String url) {
		if (url == null) {
			return null;
		}

		String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(url)
				.toUriString();

		String urls = fileUrl.replace("http", "https");
		return urls;
	}

	public static String getClientIP(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(request.getRemoteAddr())) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}
