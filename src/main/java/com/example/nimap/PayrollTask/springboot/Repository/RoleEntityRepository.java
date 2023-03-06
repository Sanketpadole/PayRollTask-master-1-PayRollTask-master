package com.example.nimap.PayrollTask.springboot.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nimap.PayrollTask.springboot.Dto.IRoleListDto;

import com.example.nimap.PayrollTask.springboot.Entities.RoleEntity;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

	Page<IRoleListDto> findByOrderById(Pageable paging, Class<IRoleListDto> class1);

	Page<IRoleListDto> findByroleName(String search, Pageable paging, Class<IRoleListDto> class1);

	List<RoleEntity> findByIdIn(ArrayList<Long> arrayList);

	RoleEntity findByroleName(String string);



}
