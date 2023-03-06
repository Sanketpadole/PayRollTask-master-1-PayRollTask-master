package com.example.nimap.PayrollTask.springboot.Dto;

import java.util.ArrayList;

public class UserRoleDto {
	private Long userId;

	private ArrayList<Long> roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ArrayList<Long> getRoleId() {
		return roleId;
	}

	public void setRoleId(ArrayList<Long> roleId) {
		this.roleId = roleId;
	}
}
