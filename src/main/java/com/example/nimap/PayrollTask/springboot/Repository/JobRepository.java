package com.example.nimap.PayrollTask.springboot.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Dto.IJobListDto;
import com.example.nimap.PayrollTask.springboot.Dto.IListRecruiterJobsListDto;
import com.example.nimap.PayrollTask.springboot.Entities.JobEntity;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

	Page<IJobListDto> findByjobName(String search, Pageable paging, Class<IJobListDto> class1);

	List<JobEntity> findByIdIn(List<Long> jobId);

	Page<IJobListDto> findByOrderByIdDesc(Pageable paging, Class<IJobListDto> class1);

	
	@Query(value = "select jobs.job_name as jobName,jobs.id,jobs.description,users.id as userId,users.username,users.email from jobs inner join userjob on jobs.id=userjob.jobs_id inner join users on users.id=userjob.users_id where jobs.recruiter_id=:recruiter_id", nativeQuery = true)
	List<IListRecruiterJobsListDto> findByByRecruiterId(@Param("recruiter_id") Long recruiter_id);

	

	@Query(value="select * from jobs j where j.id=:recruiterId", nativeQuery = true)
	List<JobEntity> findByRecruiterId(Long recruiterId);

	

	

	//List<JobEntity> findByByRecruiterId(Long recruiterId);

	

//	@Query(value = "select * from jobs j where j.recruiter_id=:recruiter_id", nativeQuery = true)
//	List<JobEntity> findByrecruiterId(@Param("recruiter_id") Long recruiter_id);

//	@Query(value = "select jobs.id,jobs.job_name as JobName ,jobs.description,users.username as Name from users inner join userjob on users.id=userjob.users_id inner join jobs on jobs.id=userjob.jobs_id ", nativeQuery = true)
//	List<IJobList1Dto> findByJobUserJobList(Class<IJobList1Dto> class1);

//	@Query(value = "select jobs.id,jobs.job_name as JobName ,jobs.description,users.username from users inner join userjob on users.id=userjob.users_id inner join jobs on jobs.id=userjob.jobs_id where users.id=:userId", nativeQuery = true)
//	List<IJobList1Dto> findByJobListById(@Param("userId") Long userId, Class<IJobList1Dto> class1);

//	@Query(value = "select jobs.id,jobs.job_name as JobName ,jobs.description,users.username from users inner join userjob on users.id=userjob.users_id inner join jobs on jobs.id=userjob.jobs_id where jobs.id=:jobId", nativeQuery = true)
//	List<IJobList1Dto> findByUserListByjobId(@Param("jobId") Long jobId, Class<IJobList1Dto> class1);

}
