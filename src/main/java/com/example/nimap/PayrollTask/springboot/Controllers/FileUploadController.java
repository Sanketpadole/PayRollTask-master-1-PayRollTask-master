package com.example.nimap.PayrollTask.springboot.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.nimap.PayrollTask.springboot.Dto.ErrorResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Entities.FileUploadEntity;
import com.example.nimap.PayrollTask.springboot.Services.FileUploadInterface;

@RestController
public class FileUploadController {
	@Autowired
	private FileUploadInterface fileUploadInterface;

	@PostMapping("/upload-file")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {

		FileUploadEntity uploadEntity = new FileUploadEntity();
		try {
			uploadEntity = this.fileUploadInterface.storeFile(file, request);
		} catch (Exception e) {
			new ErrorResponseDto("not found", "not found");
		}
		return new ResponseEntity<>(new SuccessResponseDto("success", "success", file), HttpStatus.BAD_REQUEST);
	}

}
