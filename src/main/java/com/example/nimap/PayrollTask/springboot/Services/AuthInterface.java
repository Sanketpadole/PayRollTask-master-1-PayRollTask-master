package com.example.nimap.PayrollTask.springboot.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.ModelDto;
import com.example.nimap.PayrollTask.springboot.Dto.UsersDto;
import com.example.nimap.PayrollTask.springboot.Entities.OtpEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;

@Service
public interface AuthInterface {

	void registerUser(UsersDto usersDto);

	public Boolean comparePassword(String password, String hashPassword);

	Boolean updateUserwithPassword(ModelDto modelDto, Users userEntity, OtpEntity otpEntity) throws Exception;

	UserDetails loadUserByUsername(String email);

}
