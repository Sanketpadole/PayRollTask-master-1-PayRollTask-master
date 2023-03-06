package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.OTPDto;
import com.example.nimap.PayrollTask.springboot.Entities.OtpEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;
import com.example.nimap.PayrollTask.springboot.Repository.OTPRepository;
import com.example.nimap.PayrollTask.springboot.Exception.ResourceNotFoundException;

@Service
public class OTPServiceImpl {
	@Autowired
	private OTPRepository otpRepository;

	public OtpEntity saveOtp(OTPDto otpDto, Users users) throws Exception {
		try {

			OtpEntity otpentity = this.otpRepository.findByEmail(otpDto.getEmail());

			if (otpentity != null) {

				throw new ResourceNotFoundException("Something went wrong");
			}

			else {

				OtpEntity entities = new OtpEntity();
				entities.setUserId(users);
				entities.setEmail(users.getEmail());
				entities.setOtp(otpDto.getOtp());
				entities.setExpireAt(otpDto.getSetExpiry());

				otpRepository.save(entities);
//				otpRepository.delete(entities);
				return entities;
			}
		} catch (Exception e) {

			throw new Exception("Something went wrong !!!!");
		}

	}

}
