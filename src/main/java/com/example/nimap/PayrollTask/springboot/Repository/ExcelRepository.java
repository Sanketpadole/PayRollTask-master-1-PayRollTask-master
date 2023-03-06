package com.example.nimap.PayrollTask.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Entities.Excel;

@Repository
public interface ExcelRepository extends JpaRepository<Excel, Long> {

}
