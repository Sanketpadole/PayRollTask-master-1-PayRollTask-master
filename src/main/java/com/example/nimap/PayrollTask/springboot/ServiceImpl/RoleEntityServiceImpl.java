package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.IRoleListDto;
import com.example.nimap.PayrollTask.springboot.Dto.RoleDto;
import com.example.nimap.PayrollTask.springboot.Entities.RoleEntity;

import com.example.nimap.PayrollTask.springboot.Repository.RoleEntityRepository;

import com.example.nimap.PayrollTask.springboot.Services.RoleEntityIntf;
import com.example.nimap.PayrollTask.springboot.page.Pagination;
import com.example.nimap.PayrollTask.springboot.Exception.ResourceNotFoundException;

@Service
public class RoleEntityServiceImpl implements RoleEntityIntf {
	@Autowired
	private RoleEntityRepository roleRepository;

	@Override
	public void addRoles(RoleDto roleDto) {
		RoleEntity role = new RoleEntity();
		role.setRoleName(roleDto.getRoleName());
		role.setDescription(roleDto.getDescription());
		this.roleRepository.save(role);

	}

	@Override
	public void updateRoles(Long id, RoleDto roleDto) {
		RoleEntity role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("role with given id not found"));
		role.setRoleName(roleDto.getRoleName());
		role.setDescription(roleDto.getDescription());
		this.roleRepository.save(role);

	}

	public Page<IRoleListDto> getAllroles(String search, String pageNumber, String pageSize) {

		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		if ((search == "") || (search == null) || (search.length() == 0)) {

			return roleRepository.findByOrderById(paging, IRoleListDto.class);

		} else {
			return roleRepository.findByroleName(search, paging, IRoleListDto.class);
		}
	}

	@Override
	public void deleteRolebyId(Long id) {

		this.roleRepository.deleteById(id);

	}

}
