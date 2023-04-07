package com.example.nimap.PayrollTask.springboot.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimap.PayrollTask.springboot.Dto.ErrorResponseDto;
import com.example.nimap.PayrollTask.springboot.Dto.IJobListDto;
import com.example.nimap.PayrollTask.springboot.Dto.IListRecruiterJobsListDto;
import com.example.nimap.PayrollTask.springboot.Dto.JobDto;
import com.example.nimap.PayrollTask.springboot.Dto.SuccessResponseDto;
import com.example.nimap.PayrollTask.springboot.Services.JobsIntf;
import com.springboot.nimap.PayrollTask.springboot.Util.GlobalFunctions;

@RestController
@RequestMapping("/jobs")
public class JobController {
	@Autowired
	private JobsIntf jobsIntf;

	// @PreAuthorize("hasRole('jobsview')")
	@GetMapping("/jobsview")
	ResponseEntity<?> getallListofJobs(@RequestParam(defaultValue = "") String Search,
			@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {

		Page<IJobListDto> Ijob = jobsIntf.getallListofJobs(Search, pageNumber, pageSize);

		if (Ijob.getTotalElements() != 0) {

			return new ResponseEntity<SuccessResponseDto>(
					new SuccessResponseDto("Success", "Success", Ijob.getContent()), HttpStatus.OK);

		}

		return new ResponseEntity<>(new ErrorResponseDto("Something Went Wrong", "Failed!!"), HttpStatus.BAD_REQUEST);

	}

	// @PreAuthorize("hasRole('jobadd')")
	@PostMapping("/jobadd")
	ResponseEntity<?> addjobs(@Valid @RequestBody JobDto jobDto, HttpServletRequest request,
			@RequestAttribute(GlobalFunctions.CUSTUM_ATTRIBUTE_USER_ID) Long userId) {
		System.err.println("SURAJ123");
		if (jobDto.getJobName() == "") {

			return new ResponseEntity<>(new ErrorResponseDto("Field is empty", "Failed!!"), HttpStatus.BAD_REQUEST);
		} else {

			jobsIntf.addjobs(jobDto, request, userId);
			return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", jobDto),
					HttpStatus.OK);
		}

	}

	@PreAuthorize("hasRole('updatejob')")
	@PutMapping("/{id}")
	ResponseEntity<?> updatejobs(@PathVariable Long id, @RequestBody JobDto jobDto) {
		jobsIntf.updatejobs(id, jobDto);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Success", "success", jobDto),
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('jobdel')")
	@DeleteMapping("/{id}/jobdel")
	ResponseEntity<?> deletejobById(@PathVariable Long id) {

		jobsIntf.deletejobById(id);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Success", "Success", null),
				HttpStatus.OK);
	}

	// @PreAuthorize("hasRole('jobpostedByrecruiter')")
	@GetMapping("/jobs/recruiter")
	public ResponseEntity<?> getAllJobsByRecruiterAndCandidate(HttpServletRequest request) {
		try {

			List<IListRecruiterJobsListDto> jobs = jobsIntf.getAllJobsByRecruiter(request);
			System.out.println("az" + jobs);
			return new ResponseEntity<>(new SuccessResponseDto("Success.", "Success..", jobs), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), " Other than recruiter cannot see list"),
					HttpStatus.NOT_FOUND);
		}

	}

}
