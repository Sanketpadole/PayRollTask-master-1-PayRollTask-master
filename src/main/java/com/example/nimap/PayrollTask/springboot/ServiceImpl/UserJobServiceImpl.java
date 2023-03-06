package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.IJobList1Dto;
import com.example.nimap.PayrollTask.springboot.Dto.UserJobDto;
import com.example.nimap.PayrollTask.springboot.Dto.UserJobListDto;
import com.example.nimap.PayrollTask.springboot.Entities.JobEntity;
import com.example.nimap.PayrollTask.springboot.Entities.UserJobEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;
import com.example.nimap.PayrollTask.springboot.Exception.ResourceNotFoundException;
import com.example.nimap.PayrollTask.springboot.Repository.JobRepository;
import com.example.nimap.PayrollTask.springboot.Repository.UserJobRepository;
import com.example.nimap.PayrollTask.springboot.Repository.UserRepository;
import com.example.nimap.PayrollTask.springboot.Security.JwtTokenUtil;
import com.example.nimap.PayrollTask.springboot.Services.UserJobIntf;
import com.example.nimap.PayrollTask.springboot.page.Pagination;

@Service
public class UserJobServiceImpl implements UserJobIntf {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private UserJobRepository userJobRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private EmailService emailService;

	@Override
	public void addUserJob(UserJobDto userJobDto, HttpServletRequest request) {

		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);

		Users ids = userRepository.findByEmail(email);

		final String url = "job has been applied";

		List<JobEntity> job = jobRepository.findByIdIn(userJobDto.getJobId());

		for (int i = 0; i < job.size(); i++) {

			Long ID = job.get(i).getRecruiterId().getId();
			String emai = job.get(i).getRecruiterId().getEmail();
			System.out.println("eam" + emai);
			this.emailService.sendSimpleMessage(emai, "subject", url);
		}

		ArrayList<UserJobEntity> userJobList = new ArrayList<>();
		for (int i = 0; i < job.size(); i++) {
			UserJobEntity userJob = new UserJobEntity();
			userJob.setUsers(ids);
			userJob.setJobs(job.get(i));
			userJobList.add(userJob);

			userJobRepository.saveAll(userJobList);
			String email1 = ids.getEmail();

			this.emailService.sendSimpleMessage(ids.getEmail(), "subject", url);
		}
	}

	@Override
	public List<UserJobListDto> getuserJobById2(HttpServletRequest request) {

		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);

		Long ids = userRepository.findByEmail(email).getId();

		List<UserJobListDto> iJobUserTaskDto = this.userJobRepository.findByusersId(ids, UserJobListDto.class);

		return iJobUserTaskDto;
	}

	@Override
	public Page<UserJobListDto> getallcandidatesAndJobs(String pageNumber, String pageSize) {
		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		Page<UserJobListDto> iuserTechnologyListDto;

		return iuserTechnologyListDto = userJobRepository.findByOrderByIdDesc(paging, UserJobListDto.class);
	}

	@Override
	public void deleteuserjobById(Long id) {

		UserJobEntity userjobEntity = userJobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found"));
		if (userjobEntity != null) {
			this.userJobRepository.delete(userjobEntity);

		}

	}

	@Override
	public Page<IJobList1Dto> getuserJobbyuserjobId(String userId, String jobId, String pageNo, String pageSize) {
		Page<IJobList1Dto> ijob;
		System.out.println("Page" + pageNo);
		System.out.println("Pages" + pageSize);
		Pageable paging = new Pagination().getPagination(pageNo, pageSize);

		ijob = this.userJobRepository.findUserIdjobIdById(paging, userId, jobId, IJobList1Dto.class);
		return ijob;
	}

}
