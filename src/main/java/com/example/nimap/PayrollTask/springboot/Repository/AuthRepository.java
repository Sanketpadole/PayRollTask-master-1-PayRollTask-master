package com.example.nimap.PayrollTask.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Entities.Users;

@Repository
public interface AuthRepository extends JpaRepository<Users, Long> {

	Users findByEmail(String email);

}
