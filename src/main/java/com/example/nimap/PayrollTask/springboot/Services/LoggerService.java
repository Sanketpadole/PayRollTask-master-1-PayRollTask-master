package com.example.nimap.PayrollTask.springboot.Services;

import com.example.nimap.PayrollTask.springboot.Dto.LoggerDto;
import com.example.nimap.PayrollTask.springboot.Entities.Users;

public interface LoggerService {

	void createlogger(LoggerDto logger, Users users);

	void logoutUser(String token);

}
