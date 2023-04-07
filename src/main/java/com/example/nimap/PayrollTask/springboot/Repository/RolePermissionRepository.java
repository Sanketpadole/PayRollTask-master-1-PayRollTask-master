package com.example.nimap.PayrollTask.springboot.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Dto.IRolePermissionListDto;
import com.example.nimap.PayrollTask.springboot.Entities.RolePermissionEntity;
import com.example.nimap.PayrollTask.springboot.Services.IPermissionIdList;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {

	Page<IRolePermissionListDto> findByOrderByIdAsc(Pageable paging, Class<IRolePermissionListDto> class1);

	List<IPermissionIdList> findPermissionByRoleIdIn(ArrayList<Long> roles, Class<IPermissionIdList> class1);

	@Query(value = "select p.action_name from permissions p join role_permission rp on rp.permission_id=p.id\r\n"
			+ "		join roles r on r.id=rp.role_id  join user_role ur on ur.role_id=r.id join users u on ur.user_id=u.id  \r\n"
			+ "			where u.id=:userId and ur.is_active=true and rp.is_active=true", nativeQuery = true)
	ArrayList<String> getPermissionOfUser(@Param("userId") Long userId);

}
