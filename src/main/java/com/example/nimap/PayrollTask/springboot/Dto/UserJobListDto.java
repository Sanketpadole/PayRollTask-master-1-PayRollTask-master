package com.example.nimap.PayrollTask.springboot.Dto;

import com.example.nimap.PayrollTask.springboot.Entities.JobEntity;
import com.example.nimap.PayrollTask.springboot.Entities.RoleEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;

//
//import com.example.nimap.PayrollTask.springboot.Entities.JobEntity;
//import com.example.nimap.PayrollTask.springboot.Entities.Users;

public interface UserJobListDto {

	
	public Long getId();
	
	
		public IUsersListDto getUsers();

	public IJobListDto getJobs();
//	public Users getUsers();
//
//	public JobEntity getJobs();


}
