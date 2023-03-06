package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.nimap.PayrollTask.springboot.Entities.FileUploadEntity;
import com.example.nimap.PayrollTask.springboot.Exception.FileStorageException;
import com.example.nimap.PayrollTask.springboot.Repository.FileUploadRepository;
import com.example.nimap.PayrollTask.springboot.Services.FileUploadInterface;
import com.springboot.nimap.PayrollTask.springboot.Util.FileStorageProperties;

@Service
public class FileUploadImpl implements FileUploadInterface {

	@Autowired
	private FileUploadRepository fileUploadRepository;

	private final Path fileStorageLocation;

	public FileUploadImpl(FileStorageProperties fileStorageProperties) throws Exception {

		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {

			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}

	}

	@Override
	public FileUploadEntity storeFile(MultipartFile file, HttpServletRequest request) throws Exception {
		UUID uuid = UUID.randomUUID();
		String fileNames = uuid + file.getOriginalFilename();
		String fileName = StringUtils.cleanPath(fileNames);

		try {
			if (fileName.contains("..")) {

				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			FileUploadEntity fileUploadEntity = new FileUploadEntity();
			fileUploadEntity.setEncoding(request.getCharacterEncoding());
			fileUploadEntity.setFilename(fileName);
			fileUploadEntity.setMimetype(file.getContentType());
			fileUploadEntity.setOriginalName(file.getOriginalFilename());
			fileUploadEntity.setSize(file.getSize());
			FileUploadEntity fileDetail = fileUploadRepository.save(fileUploadEntity);
			return fileDetail;
		} catch (Exception ex) {

			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);

		}
	}
}
