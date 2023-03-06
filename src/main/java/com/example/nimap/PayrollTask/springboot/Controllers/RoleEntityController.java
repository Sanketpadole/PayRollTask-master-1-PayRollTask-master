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

import com.example.nimap.PayrollTask.springboot.Dto.ErrorResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.IRoleListDto;
import com.example.nimap.PayrollTask.springboot.Dto.RoleDto;
import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Repository.RoleEntityRepository;
import com.example.nimap.PayrollTask.springboot.Services.RoleEntityIntf;

@RestController
@RequestMapping("/roles")
public class RoleEntityController {
	@Autowired
	private RoleEntityIntf roleInterface;

	@Autowired
	private RoleEntityRepository roleRepository;

	@PreAuthorize("hasRole('rolesadd')")
	@PostMapping("/rolesadd")
	public ResponseEntity<?> addRoles(@RequestBody RoleDto roleDto) {

		roleInterface.addRoles(roleDto);
		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("roleAdded successfully", "success", roleDto), HttpStatus.ACCEPTED);

	}

	@PreAuthorize("hasRole('rolesupdate')")
	@PutMapping("/{id}/rolesupdate")

	public ResponseEntity<?> updateRoles(@RequestBody RoleDto roleDto, @PathVariable Long id) {

		roleInterface.updateRoles(id, roleDto);

		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("RoleUpdatedSuccessfully", "Role", roleDto), HttpStatus.ACCEPTED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateRoles(@PathVariable Long id, @RequestBody RoleDto roleDto) {
		roleInterface.updateRoles(id, roleDto);
		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("RoleupdatedSuccessfully", "success", roleDto), HttpStatus.ACCEPTED);
	}

	@PreAuthorize("hasRole('rolesview')")
	@GetMapping("/rolesview")
	public ResponseEntity<?> getAllRoles(@RequestParam(defaultValue = "") String Search,
			@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
		Page<IRoleListDto> allRoles = roleInterface.getAllroles(Search, pageNumber, pageSize);
		if (allRoles.getTotalElements() != 0) {

			return new ResponseEntity<>(allRoles.getContent(), HttpStatus.OK);
		}
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("not found", "not found", null),
				HttpStatus.BAD_REQUEST);

	}

	@PreAuthorize("hasRole('rolesdel')")
	@DeleteMapping("/{id}/rolesdel")
	public ResponseEntity<?> deleteRolebyId(@PathVariable Long id) {
		if (this.roleRepository.findById(id) != null) {
			roleInterface.deleteRolebyId(id);
		} else {
			return new ResponseEntity<ErrorResponseDto>(
					new ErrorResponseDto("role not found with given id", "not found"), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("deleted successfully", "deleted", null),
				HttpStatus.ACCEPTED);

	}

}