package com.example.nimap.PayrollTask.springboot.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Dto.IUserRoleListDto;
import com.example.nimap.PayrollTask.springboot.Entities.UserRoleEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

	@Query(value = "SELECT *FROM userrole u WHERE u.user_id=:userid", nativeQuery = true)
	ArrayList<UserRoleEntity> getRolesOfUser(@Param("userid") Long userid);

	@Query(value = "SELECT *FROM userrole u WHERE u.user_id=:user", nativeQuery = true)
	List<UserRoleEntity> findByuserId(Long user);

	@Query(value = "SELECT *FROM userrole u WHERE u.role_id=:l", nativeQuery = true)
	List<UserRoleEntity> findByroleId(Long l);

	@Query(value = "SELECT *FROM userrole u WHERE u.role_id=:id", nativeQuery = true)
	UserRoleEntity findByroleId1(Long id);

	@Query(value = "select u.username,r.role_name,ur.user_id,ur.role_id from users u Inner join userrole ur on u.id= ur.user_id Inner join roles r on r.id= ur.role_id where r.role_name='recruiter'\r\n", nativeQuery = true)
	IRecruiter findRoleEntityIdByUsersId();

	Page<IUserRoleListDto> findByOrderById(Pageable paging, Class<IUserRoleListDto> class1);

	@Query(value = "select * from userrole ur where ur.user_id=:user_id", nativeQuery = true)
	UserRoleEntity finduserroleByuserId(@Param("user_id") Long userId);

	UserRoleEntity findByusersId(Long user_id);

	@Query(value = "select u.id,u.username,ur.user_id   ,r.role_name from users u Inner join userrole ur On u.id=ur.user_id Inner join roles r On r.id=ur.role_id where ur.role_id=:candidateId OR ur.role_id=:recruiterId\r\n", nativeQuery = true)
	List<IUserRoleListDto> findByOrderById(@Param("candidateId") Long candidateId,
			@Param("recruiterId") Long recruiterId, Class<IUserRoleListDto> class1);

	//UserRoleEntity findByUserById(Long user_id);

}
