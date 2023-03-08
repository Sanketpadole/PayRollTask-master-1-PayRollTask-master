package com.example.nimap.PayrollTask.springboot.Controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.nimap.PayrollTask.springboot.Dto.ErrorResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Entities.FileUploadEntity;
import com.example.nimap.PayrollTask.springboot.Services.FileUploadInterface;
import com.springboot.nimap.PayrollTask.springboot.Util.ApiUrls;

@RestController
public class FileUploadController {
	@Autowired
	private FileUploadInterface fileUploadInterface;

	@PreAuthorize("hasRole('uploadFile')")
	@PostMapping("/upload-file")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {

		FileUploadEntity uploadEntity = new FileUploadEntity();

		try {
			uploadEntity = this.fileUploadInterface.storeFile(file, request);

		} catch (Exception e) {

			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("error", "error"), HttpStatus.ACCEPTED);

		}

		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Success", "Success", file),
				HttpStatus.OK);

	}

	@GetMapping(ApiUrls.DOWNLOAD_FILE)
	public ResponseEntity<?> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

		Resource resource = null;

		try {

			resource = fileUploadInterface.loadFileAsResource(fileName);

		} catch (Exception e) {

			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("error", "error"), HttpStatus.ACCEPTED);

		}

		//// Try to determine file's content type
		String contentType = null;

		try {

			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

		} catch (IOException ex) {

		}

		if (contentType == null) {

			contentType = "application/octet-stream";

		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
				.body(resource);

	}
}