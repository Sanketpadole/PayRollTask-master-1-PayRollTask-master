package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Repository.UserRepository;
import com.example.nimap.PayrollTask.springboot.Services.UserServiceIntf;

@Service
public class UserServiceImpl implements UserServiceIntf {
	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<?> getpermissions() {

		this.userRepository.findAll();
		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("success", "success", this.userRepository.findAll()), HttpStatus.ACCEPTED);

	}

}
