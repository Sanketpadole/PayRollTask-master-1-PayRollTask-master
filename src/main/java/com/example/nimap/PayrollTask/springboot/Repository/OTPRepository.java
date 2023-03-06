package com.example.nimap.PayrollTask.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Entities.OtpEntity;

@Repository
public interface OTPRepository extends JpaRepository<OtpEntity, Long> {

	OtpEntity findByEmail(String email);

	OtpEntity findByOtp(Integer otp);

}
