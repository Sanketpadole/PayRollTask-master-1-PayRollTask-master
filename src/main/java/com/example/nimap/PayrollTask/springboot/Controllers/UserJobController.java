package com.example.nimap.PayrollTask.springboot.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimap.PayrollTask.springboot.Dto.ErrorResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.IJobList1Dto;
import com.example.nimap.PayrollTask.springboot.Dto.ListResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.PaginationResponse;
import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.UserJobDto;
import com.example.nimap.PayrollTask.springboot.Dto.UserJobListDto;
import com.example.nimap.PayrollTask.springboot.Entities.JobEntity;
import com.example.nimap.PayrollTask.springboot.Repository.JobRepository;
import com.example.nimap.PayrollTask.springboot.Services.UserJobIntf;

@RestController
@RequestMapping("/userjob")
public class UserJobController {
	@Autowired
	private UserJobIntf userJobIntf;

	@Autowired
	private JobRepository jobRepository;

	@PreAuthorize("hasRole('userjobadd')")
	@PostMapping("/userjobadd")
	ResponseEntity<?> addUserJob(@RequestBody UserJobDto userJobDto, HttpServletRequest request) {

		List<JobEntity> job = jobRepository.findByIdIn(userJobDto.getJobId());

		userJobIntf.addUserJob(userJobDto, request);

		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Success", "success", userJobDto),
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('userjobsview')")
	@GetMapping("/userjobsview")
	ResponseEntity<?> getallcandidatesAndJobs(@RequestParam(defaultValue = "") String Search,
			@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {

		Page<UserJobListDto> userjob = userJobIntf.getallcandidatesAndJobs(pageNumber, pageSize);
		if (userjob.getTotalElements() != 0) {

			return new ResponseEntity<SuccessResponseDto>(
					new SuccessResponseDto("Success", "Success", userjob.getContent()), HttpStatus.OK);

		}

		return new ResponseEntity<>(new ErrorResponseDto("", "Failed!!"), HttpStatus.BAD_REQUEST);

	}

	@PreAuthorize("hasRole('userjobsviewbyid')")
	@GetMapping("/userjobsviewbyid")
	public ResponseEntity<?> getuserJobById2(HttpServletRequest request) {

		List<UserJobListDto> iJobUserTaskDto = this.userJobIntf.getuserJobById2(request);

		return new ResponseEntity<>(new SuccessResponseDto("Success", "Successfull", iJobUserTaskDto), HttpStatus.OK);

	}

	@PreAuthorize("hasRole('userjobdel')")
	@DeleteMapping("/userjobdel/{id}")
	ResponseEntity<?> deleteuserjobById(@PathVariable Long id) {

		userJobIntf.deleteuserjobById(id);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Success", "Success", null),
				HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('getuserJobbyuserjobId')")
	@GetMapping()
	public ResponseEntity<?> getuserJobbyuserjobId(@RequestParam(defaultValue = "") String userId,
			@RequestParam(defaultValue = "") String jobId, @RequestParam(defaultValue = "1") String pageNo,
			@RequestParam(defaultValue = "25") String pageSize) {
		Page<IJobList1Dto> ijob = userJobIntf.getuserJobbyuserjobId(userId, jobId, pageNo, pageSize);
		PaginationResponse paginationResponse = new PaginationResponse();
		paginationResponse.setPageNumber(ijob.getNumber() + 1);
		paginationResponse.setPageSize(ijob.getSize());
		paginationResponse.setTotal(ijob.getTotalElements());

		return new ResponseEntity<ListResponseDto>(new ListResponseDto(ijob.getContent(), paginationResponse),
				HttpStatus.OK);
	}
}
