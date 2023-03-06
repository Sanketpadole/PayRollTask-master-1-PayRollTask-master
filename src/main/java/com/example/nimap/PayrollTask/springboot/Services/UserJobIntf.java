package com.example.nimap.PayrollTask.springboot.Services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.example.nimap.PayrollTask.springboot.Dto.IJobList1Dto;
import com.example.nimap.PayrollTask.springboot.Dto.UserJobDto;
import com.example.nimap.PayrollTask.springboot.Dto.UserJobListDto;

public interface UserJobIntf {

	void addUserJob(UserJobDto userJobDto, HttpServletRequest request);

	Page<UserJobListDto> getallcandidatesAndJobs(String pageNumber, String pageSize);

	List<UserJobListDto> getuserJobById2(HttpServletRequest request);

	void deleteuserjobById(Long id);

	Page<IJobList1Dto> getuserJobbyuserjobId(String userId, String jobId, String pageNo, String pageSize);

}
