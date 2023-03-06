package com.example.nimap.PayrollTask.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nimap.PayrollTask.springboot.Entities.JobEntity;

public interface CustomerRepository extends JpaRepository<JobEntity, Integer> {
}
