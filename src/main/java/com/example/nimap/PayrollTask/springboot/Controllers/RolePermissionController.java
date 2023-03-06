package com.example.nimap.PayrollTask.springboot.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimap.PayrollTask.springboot.Dto.ErrorResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.IRolePermissionListDto;

import com.example.nimap.PayrollTask.springboot.Dto.RolePermissionDto;
import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Repository.RolePermissionRepository;
import com.example.nimap.PayrollTask.springboot.Services.RolePermissionIntf;

@RestController
@RequestMapping("/rolepermission")
public class RolePermissionController {
	@Autowired
	private RolePermissionIntf rolePermissionIntf;

	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	@PreAuthorize("hasRole('rolepermissionsadd')")
	@PostMapping("/rolepermissionsadd")
	ResponseEntity<?> addRolePermissions(@RequestBody RolePermissionDto rolePermissionDto) {
		rolePermissionIntf.addRolePermissions(rolePermissionDto);
		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("rolePermissionAddedSuccessfully", "success", rolePermissionDto),
				HttpStatus.ACCEPTED);

	}

	@PreAuthorize("hasRole('rolepermissionsview')")
	@GetMapping("/{id}/rolepermissionsview")
	public ResponseEntity<?> getPermissionByUserId(@PathVariable("id") Long id) {
		try {

			ArrayList<String> user = this.rolePermissionIntf.getPermissionByUserId(id);

			return new ResponseEntity<>(
					new SuccessResponseDto(" Fetc All User Permissions Successfully", "Success", user), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "User Role And Permission Not Found"),
					HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('userroleview')")
	@GetMapping("/userroleview")
	public ResponseEntity<?> getAllUserRole(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
		Page<IRolePermissionListDto> users = rolePermissionIntf.getAllRolePermission(pageNumber, pageSize);

		if (users.getTotalElements() != 0) {

			return new ResponseEntity<>(users.getContent(), HttpStatus.OK);

		}

		return new ResponseEntity<>(new ErrorResponseDto("", "Failed!!"), HttpStatus.BAD_REQUEST);

	}

	@PreAuthorize("hasRole('rolepermissiondel')")
	@DeleteMapping("/{id}/rolepermissiondel")
	public ResponseEntity<?> deleterolePermission(@PathVariable Long id) {

		if (this.rolePermissionRepository.findById(id) != null) {
			rolePermissionIntf.deleterolePermission(id);
		} else {
			return new ResponseEntity<ErrorResponseDto>(
					new ErrorResponseDto("role not found with given id", "not found"), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("deleted successfully", "deleted", null),
				HttpStatus.ACCEPTED);

	}

}
