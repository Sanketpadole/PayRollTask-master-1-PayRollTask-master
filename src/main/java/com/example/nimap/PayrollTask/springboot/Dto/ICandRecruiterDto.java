package com.example.nimap.PayrollTask.springboot.Dto;

import com.example.nimap.PayrollTask.springboot.Entities.RoleEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;

public interface ICandRecruiterDto {
	public Long getId();
	public String getRoleName();
	public Users getUsers();
	public RoleEntity getRoleEntity();
	public String getEmail();
	public String getUsername();

}
