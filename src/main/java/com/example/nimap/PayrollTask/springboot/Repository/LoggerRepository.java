package com.example.nimap.PayrollTask.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Entities.LoggerEntity;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {

	void removeByToken(String userToken);

}
