package com.example.nimap.PayrollTask.springboot.Services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.example.nimap.PayrollTask.springboot.Dto.IJobListDto;
import com.example.nimap.PayrollTask.springboot.Dto.IListRecruiterJobsListDto;
import com.example.nimap.PayrollTask.springboot.Dto.JobDto;

public interface JobsIntf {

	void addjobs(JobDto jobDto, HttpServletRequest request);

	void updatejobs(Long id, JobDto jobDto);

	void deletejobById(Long id);

	Page<IJobListDto> getallListofJobs(String search, String pageNumber, String pageSize);

	List<IListRecruiterJobsListDto> getAllJobsByRecruiter(HttpServletRequest request);

}
