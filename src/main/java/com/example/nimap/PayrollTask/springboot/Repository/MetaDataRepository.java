package com.example.nimap.PayrollTask.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Entities.StockDataRequest;

@Repository
public interface MetaDataRepository extends JpaRepository<StockDataRequest, Long> {

	void save(double openPrice);

}
