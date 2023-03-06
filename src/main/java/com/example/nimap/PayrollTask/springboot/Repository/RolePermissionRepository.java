package com.example.nimap.PayrollTask.springboot.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Dto.IRolePermissionListDto;

import com.example.nimap.PayrollTask.springboot.Entities.RolePermissionEntity;
import com.example.nimap.PayrollTask.springboot.Services.IPermissionIdList;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {

	Page<IRolePermissionListDto> findByOrderByIdAsc(Pageable paging, Class<IRolePermissionListDto> class1);

	List<IPermissionIdList> findPermissionByRoleIdIn(ArrayList<Long> roles, Class<IPermissionIdList> class1);

}
