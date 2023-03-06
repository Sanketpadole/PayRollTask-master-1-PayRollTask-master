package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.IPermissionListDto;
import com.example.nimap.PayrollTask.springboot.Dto.IRoleListDto;
import com.example.nimap.PayrollTask.springboot.Dto.PermissionDto;
import com.example.nimap.PayrollTask.springboot.Entities.PermissionEntity;

import com.example.nimap.PayrollTask.springboot.Repository.PermissionRepository;
import com.example.nimap.PayrollTask.springboot.Services.PermissionIntf;
import com.example.nimap.PayrollTask.springboot.page.Pagination;
import com.example.nimap.PayrollTask.springboot.Exception.ResourceNotFoundException;

@Service
public class PermissionserviceImpl implements PermissionIntf {
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public void addPermissions(PermissionDto permissionDto) {
		PermissionEntity permission = new PermissionEntity();
		permission.setActionName(permissionDto.getActionName());
		permission.setBaseUrl(permissionDto.getBaseUrl());
		permission.setDescription(permissionDto.getDescription());
		permission.setMethod(permissionDto.getMethod());
		permission.setPath(permissionDto.getPath());
		permissionRepository.save(permission);

	}

	@Override
	public void updatePermissions(Long id, PermissionDto permissionDto) {
		PermissionEntity permission = permissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("permission with given id not found"));
		permission.setActionName(permissionDto.getActionName());
		permission.setDescription(permissionDto.getDescription());
		permission.setBaseUrl(permissionDto.getBaseUrl());
		permission.setMethod(permissionDto.getMethod());
		permission.setPath(permissionDto.getPath());
		permissionRepository.save(permission);

	}

	public Page<IRoleListDto> getAllpermissions(String search, String pageNumber, String pageSize) {

		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		if ((search == "") || (search == null) || (search.length() == 0)) {

			return permissionRepository.findByOrderById(paging, IPermissionListDto.class);

		} else {
			return permissionRepository.findByactionName(search, paging, IPermissionListDto.class);
		}
	}

	@Override
	public void deletePermission(Long id) {
		permissionRepository.deleteById(id);

	}

}
