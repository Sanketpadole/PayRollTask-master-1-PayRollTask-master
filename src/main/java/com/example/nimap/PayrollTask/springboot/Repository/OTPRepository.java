package com.example.nimap.PayrollTask.springboot.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Entities.OtpEntity;

@Repository
public interface OTPRepository extends JpaRepository<OtpEntity, Long> {

	OtpEntity findByEmail(String email);

	OtpEntity findByOtp(Integer otp);

	@Query(value = "DELETE FROM otp_logger u WHERE u.email=:email", nativeQuery = true)
	@Transactional
	@Modifying
	void deleteAllByEmail(@Param("email") String email);

}
