package com.example.nimap.PayrollTask.springboot.Services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.example.nimap.PayrollTask.springboot.Dto.IRolePermissionListDto;

import com.example.nimap.PayrollTask.springboot.Dto.RolePermissionDto;

public interface RolePermissionIntf {

	void addRolePermissions(RolePermissionDto rolePermissionDto);

	Page<IRolePermissionListDto> getAllRolePermission(String pageNumber, String pageSize);

	void deleterolePermission(Long id);

	ArrayList<String> getPermissionByUserId(Long id);

}
