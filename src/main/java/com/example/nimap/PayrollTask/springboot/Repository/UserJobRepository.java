package com.example.nimap.PayrollTask.springboot.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Dto.IJobList1Dto;
import com.example.nimap.PayrollTask.springboot.Dto.UserJobListDto;
import com.example.nimap.PayrollTask.springboot.Entities.UserJobEntity;

@Repository
public interface UserJobRepository extends JpaRepository<UserJobEntity, Long> {

	List<UserJobListDto> findByusersId(Long user);

	Page<UserJobListDto> findByOrderByIdDesc(Pageable paging, Class<UserJobListDto> class1);

	Page<UserJobListDto> findByOrderById(Pageable paging, Class<UserJobListDto> class1);

	List<UserJobListDto> findByjobsId(Long id2, Class<UserJobListDto> class1);

	List<UserJobListDto> findByusersId(Long ids, Class<UserJobListDto> class1);

	List<UserJobListDto> findByid(Long id, Class<UserJobListDto> class1);

//	@Query(value = "select jobs.id,jobs.job_name as JobName ,jobs.description,users.username from users inner join userjob on users.id=userjob.users_id inner join jobs on jobs.id=userjob.jobs_id  AND (:userId = '' OR userjob.users_id IN (select unnest(cast(string_to_array(:userId,',') as bigint[]))))\r\n"
//			+ "AND (:jobId = '' OR userjob.jobs_id IN (select unnest(cast(string_to_array(:jobId,',') as bigint[])))) ", nativeQuery = true)

	@Query(value = "select u.username as UserName,j.job_name as JobName ,j.description as Description,j.id  from users u \r\n"
			+ "            join userjob uj on uj.users_id=u.id \r\n" + "            join jobs j on uj.jobs_id=j.id \r\n"
			+ "             AND (:userId= '' OR uj.users_id IN (select unnest(cast(string_to_array(:userId, '') as bigint[]))))\r\n"
			+ "            AND (:jobId= '' OR  uj.jobs_id IN (select unnest(cast(string_to_array(:jobId,'') as bigint[]))))", nativeQuery = true)
	Page<IJobList1Dto> findUserIdjobIdById(Pageable paging, @Param("userId") String userId,
			@Param("jobId") String jobId, Class<IJobList1Dto> class1);

}
