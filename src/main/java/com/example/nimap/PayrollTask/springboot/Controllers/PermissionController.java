package com.example.nimap.PayrollTask.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimap.PayrollTask.springboot.Dto.IRoleListDto;
import com.example.nimap.PayrollTask.springboot.Dto.PermissionDto;

import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Entities.PermissionEntity;
import com.example.nimap.PayrollTask.springboot.Repository.PermissionRepository;
import com.example.nimap.PayrollTask.springboot.Services.PermissionIntf;
import com.example.nimap.PayrollTask.springboot.Exception.ResourceNotFoundException;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
	@Autowired
	private PermissionIntf permissionIntf;
	@Autowired
	private PermissionRepository permissionRepository;

	@PreAuthorize("hasRole('permissionsadd')")
	@PostMapping("/permissionsadd")
	public ResponseEntity<?> addPermissions(@RequestBody PermissionDto permissionDto) {
		permissionIntf.addPermissions(permissionDto);
		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("PermissionAdded successfully", "permission added successfully", permissionDto),
				HttpStatus.ACCEPTED);

	}

	@PreAuthorize("hasRole('permissionsview')")
	@GetMapping("/permissionsview")
	public ResponseEntity<?> getAllPermissions(@RequestParam(defaultValue = "") String Search,
			@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
		Page<IRoleListDto> allPermissions = permissionIntf.getAllpermissions(Search, pageNumber, pageSize);
		if (allPermissions.getTotalElements() != 0) {

			return new ResponseEntity<>(allPermissions.getContent(), HttpStatus.OK);
		}
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("not found", "not found", null),
				HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePermissions(@PathVariable Long id, @RequestBody PermissionDto permissionDto) {
		permissionIntf.updatePermissions(id, permissionDto);
		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("PermissionupdatedSuccessfully", "success", permissionDto), HttpStatus.ACCEPTED);
	}

	@PreAuthorize("hasRole('permissionsedel')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePermissions(@PathVariable Long id) {
		try {
			PermissionEntity permission = permissionRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("permission not found with given id"));

		} catch (Exception e) {
			permissionIntf.deletePermission(id);
			return new ResponseEntity<SuccessResponseDto>(
					new SuccessResponseDto("permissionDeleted successfully", "success", null), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("permissionDeleted successfully", "success", null), HttpStatus.ACCEPTED);

	}

}
