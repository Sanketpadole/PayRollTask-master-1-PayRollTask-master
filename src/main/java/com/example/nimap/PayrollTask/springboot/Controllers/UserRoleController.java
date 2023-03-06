package com.example.nimap.PayrollTask.springboot.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.nimap.PayrollTask.springboot.Dto.IUserRoleListDto;
import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.UserRoleDto;
import com.example.nimap.PayrollTask.springboot.Services.UserRoleIntf;

@RestController
@RequestMapping("/userrole")

public class UserRoleController {
	@Autowired
	private UserRoleIntf userRoleIntf;

	@PreAuthorize("hasRole('userrolesadd')")
	@PostMapping("/userrolesadd")
	public ResponseEntity<?> addUserRoles(@RequestBody UserRoleDto userRoleDto) {
		userRoleIntf.addUserRoles(userRoleDto);
		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("UserRoleAdded successfully", "success", userRoleDto), HttpStatus.OK);

	}

	@PreAuthorize("hasRole('userroledel')")
	@DeleteMapping("/{id}/userroledel")
	public ResponseEntity<?> deleteuserrole(HttpServletRequest request, @PathVariable Long id) {

		userRoleIntf.deleteuserrole(request, id);

		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Deleted successfully", "Deleted", null),
				HttpStatus.OK);

	}

	@PreAuthorize("hasRole('candidaterecruiterview')")
	@GetMapping("/candidaterecruiterview")
	public ResponseEntity<?> getAllTasks1(@RequestParam(required = false) Long candidateId,
			@RequestParam(required = false) Long recruiterId) {

		List<IUserRoleListDto> allRoles = userRoleIntf.getrecruitercandidate(candidateId, recruiterId);
		System.out.println("aaa" + allRoles);

		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Not found", "Not found", allRoles),
				HttpStatus.BAD_REQUEST);
	}

	@PreAuthorize("hasRole('userroleupdate')")
	@PutMapping("/{id}/userroleupdate")

	public ResponseEntity<?> updateuserRoles(@RequestBody UserRoleDto userRoleDto, @PathVariable Long id) {

		userRoleIntf.updateuserRoles(id, userRoleDto);

		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("roleUpdatedSuccessfully", "role", userRoleDto), HttpStatus.OK);
	}

}
