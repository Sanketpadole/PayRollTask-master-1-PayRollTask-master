package com.example.nimap.PayrollTask.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Entities.FileUploadEntity;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUploadEntity, Long> {

	void deleteByfilename(String filename);

}
