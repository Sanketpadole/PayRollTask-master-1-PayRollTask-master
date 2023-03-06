package com.example.nimap.PayrollTask.springboot.Services;

import org.springframework.data.domain.Page;

import com.example.nimap.PayrollTask.springboot.Dto.IRoleListDto;
import com.example.nimap.PayrollTask.springboot.Dto.PermissionDto;

public interface PermissionIntf {

	void addPermissions(PermissionDto permissionDto);

	void updatePermissions(Long id, PermissionDto permissionDto);

	Page<IRoleListDto> getAllpermissions(String search, String pageNumber, String pageSize);

	void deletePermission(Long id);

}
