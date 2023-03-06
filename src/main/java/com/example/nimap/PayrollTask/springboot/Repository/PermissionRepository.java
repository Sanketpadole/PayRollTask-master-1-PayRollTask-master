package com.example.nimap.PayrollTask.springboot.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nimap.PayrollTask.springboot.Dto.IPermissionListDto;
import com.example.nimap.PayrollTask.springboot.Dto.IRoleListDto;
import com.example.nimap.PayrollTask.springboot.Entities.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

	Page<IRoleListDto> findByOrderById(Pageable paging, Class<IPermissionListDto> class1);

	Page<IRoleListDto> findByactionName(String search, Pageable paging, Class<IPermissionListDto> class1);

	List<PermissionEntity> findByIdIn(List<Long> permissionId);

}
