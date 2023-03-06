package com.example.nimap.PayrollTask.springboot.Controllers;

import java.time.LocalDateTime;

public final class DateUtils {

	private DateUtils() {
		// Prevent instantiation
	}

	public static void validateStartEndDate(LocalDateTime startDate, LocalDateTime endDate) {
		// Check if the start date is greater than the end date
		if (endDate != null && startDate.isAfter(endDate)) {
			throw new IllegalArgumentException("Start date cannot be after end date");
		}

		// Check if the end date is before the start date
		if (startDate != null && endDate.isBefore(startDate)) {
			throw new IllegalArgumentException("End date cannot be before start date");
		}
	}

}
