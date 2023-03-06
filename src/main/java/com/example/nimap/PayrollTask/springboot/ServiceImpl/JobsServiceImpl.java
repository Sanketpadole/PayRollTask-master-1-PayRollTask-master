package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.IJobListDto;
import com.example.nimap.PayrollTask.springboot.Dto.IListRecruiterJobsListDto;
import com.example.nimap.PayrollTask.springboot.Dto.JobDto;
import com.example.nimap.PayrollTask.springboot.Entities.JobEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;
import com.example.nimap.PayrollTask.springboot.Exception.ResourceNotFoundException;
import com.example.nimap.PayrollTask.springboot.Repository.JobRepository;
import com.example.nimap.PayrollTask.springboot.Repository.UserRepository;
import com.example.nimap.PayrollTask.springboot.Repository.UserRoleRepository;
import com.example.nimap.PayrollTask.springboot.Security.JwtTokenUtil;
import com.example.nimap.PayrollTask.springboot.Services.JobsIntf;
import com.example.nimap.PayrollTask.springboot.page.Pagination;

@Service
public class JobsServiceImpl implements JobsIntf {
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void addjobs(JobDto jobDto, HttpServletRequest request) {

		final String header = request.getHeader("Authorization");
		String requestToken = header.substring(7);

		final String email = jwtTokenUtil.getUsernameFromToken(requestToken);

		Users users = this.userRepository.findByEmail(email);

		JobEntity jobEntity = new JobEntity();
		jobEntity.setDescription(jobDto.getDescription());

		jobEntity.setJobName(jobDto.getJobName());

		jobEntity.setRecruiterId(users);
		jobRepository.save(jobEntity);

	}

	@Override

	public Page<IJobListDto> getallListofJobs(String search, String pageNumber, String pageSize) {

		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		if ((search == "") || (search == null) || (search.length() == 0)) {

			return jobRepository.findByOrderByIdDesc(paging, IJobListDto.class);

		} else {
			return jobRepository.findByjobName(search, paging, IJobListDto.class);
		}
	}

	@Override
	public void updatejobs(Long id, JobDto jobDto) {
		JobEntity jobEntity = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
		jobEntity.setDescription(jobDto.getDescription());
		jobEntity.setJobName(jobDto.getJobName());
		jobRepository.save(jobEntity);

	}

	@Override
	public void deletejobById(Long id) {
		JobEntity jobEntity = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
		jobRepository.deleteById(id);

	}
//	@Override
//	public List<IListRecruiterJobsListDto> getAllJobsByRecruiter(HttpServletRequest request) {
//
//		final String header = request.getHeader("Authorization");
//		String requestToken = header.substring(7);
//
//		final String email = jwtTokenUtil.getUsernameFromToken(requestToken);
//
//		Users users = userRepository.findByEmail(email);
//
//		Long user = users.getId();
//		System.out.println("hhhh1");
//
//		UserRoleEntity userRole = userRoleRepository.findByusersId(user);
//
//		Long recruiterId = userRole.getRoleEntity().getId();
//		System.out.println("hhhh2");
//
//		String roleName = userRole.getRoleEntity().getRoleName();
//
//		if (roleName.equals("recruiter")) {
//			List<IListRecruiterJobsListDto> allJobsbyCandidate = jobRepository.findByByRecruiterId(recruiterId);
//
//			return allJobsbyCandidate;
//		} else {
//			throw new ResourceNotFoundException("Recruiter can see this list");
//		}
//
//	}

	@Override
	public List<IListRecruiterJobsListDto> getAllJobsByRecruiter(HttpServletRequest request) {

		final String header = request.getHeader("Authorization");
		String requestToken = header.substring(7);

		final String email = jwtTokenUtil.getUsernameFromToken(requestToken);

		Users users = userRepository.findByEmail(email);

		Long recuriter_id = users.getId();
		System.out.println("userId" + recuriter_id);

		System.out.println("qwe");
		List<IListRecruiterJobsListDto> list = jobRepository.findByByRecruiterId(recuriter_id);

		return list;

	}

}