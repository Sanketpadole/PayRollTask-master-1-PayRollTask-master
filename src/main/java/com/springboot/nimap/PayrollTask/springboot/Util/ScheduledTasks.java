package com.springboot.nimap.PayrollTask.springboot.Util;

import org.springframework.scheduling.annotation.Scheduled;

public class ScheduledTasks {

	@Scheduled(cron = "0 0 * * *")
	public void runBackup() {
		// Perform the backup here
	}

}
