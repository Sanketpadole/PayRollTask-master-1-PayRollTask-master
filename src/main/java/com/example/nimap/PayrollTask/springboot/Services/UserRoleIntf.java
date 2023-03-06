package com.example.nimap.PayrollTask.springboot.Services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.nimap.PayrollTask.springboot.Dto.IUserRoleListDto;
import com.example.nimap.PayrollTask.springboot.Dto.UserRoleDto;

public interface UserRoleIntf {

	void addUserRoles(UserRoleDto userRoleDto);

	void deleteuserrole(HttpServletRequest request, Long id);

	List<IUserRoleListDto> getrecruitercandidate(Long candidateId, Long recruiterId
			);

	void updateuserRoles(Long id, UserRoleDto userRoleDto);

}
