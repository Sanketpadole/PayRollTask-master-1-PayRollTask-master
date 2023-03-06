package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.LoggerDto;
import com.example.nimap.PayrollTask.springboot.Entities.LoggerEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;
import com.example.nimap.PayrollTask.springboot.Repository.LoggerRepository;
import com.example.nimap.PayrollTask.springboot.Services.LoggerService;

@Service
public class LoggerServiceImpl implements LoggerService {
	@Autowired
	private LoggerRepository loggerRepository;

	@Override
	public void createlogger(LoggerDto logger, Users users) {

		LoggerEntity logger1 = new LoggerEntity();
		logger1.setUserId(users);
		logger1.setToken(logger.getToken());
		logger1.setExpireAt(logger.getExpireAt());

		loggerRepository.save(logger1);

	}

	@Transactional
	@Override
	public void logoutUser(String token) {

		final String userToken = token.substring(7);

		loggerRepository.removeByToken(userToken);

	}

}
