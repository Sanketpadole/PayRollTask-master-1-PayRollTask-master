package com.example.nimap.PayrollTask.springboot.Services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.example.nimap.PayrollTask.springboot.Entities.FileUploadEntity;

public interface FileUploadInterface {

	FileUploadEntity storeFile(MultipartFile file, HttpServletRequest request) throws Exception;

	Resource loadFileAsResource(String fileName) throws Exception;

	boolean delete(Long id);

}
