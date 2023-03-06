package com.example.nimap.PayrollTask.springboot.Services;

import org.springframework.data.domain.Page;

import com.example.nimap.PayrollTask.springboot.Dto.IRoleListDto;
import com.example.nimap.PayrollTask.springboot.Dto.RoleDto;

public interface RoleEntityIntf {

	void addRoles(RoleDto roleDto);

	void updateRoles(Long id, RoleDto roleDto);

	Page<IRoleListDto> getAllroles(String search, String pageNumber, String pageSize);

	void deleteRolebyId(Long id);

}
