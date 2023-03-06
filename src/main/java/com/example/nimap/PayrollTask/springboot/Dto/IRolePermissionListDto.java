package com.example.nimap.PayrollTask.springboot.Dto;

import com.example.nimap.PayrollTask.springboot.Entities.PermissionEntity;
import com.example.nimap.PayrollTask.springboot.Entities.RoleEntity;

public interface IRolePermissionListDto {
	public Long getId();

	public RoleEntity getRole();

	public PermissionEntity getPermission();

}
