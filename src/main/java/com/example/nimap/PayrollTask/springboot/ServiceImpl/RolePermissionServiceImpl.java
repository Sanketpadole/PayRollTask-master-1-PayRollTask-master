package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.IRolePermissionListDto;

import com.example.nimap.PayrollTask.springboot.Dto.RolePermissionDto;
import com.example.nimap.PayrollTask.springboot.Entities.PermissionEntity;
import com.example.nimap.PayrollTask.springboot.Entities.RoleEntity;
import com.example.nimap.PayrollTask.springboot.Entities.RolePermissionEntity;
import com.example.nimap.PayrollTask.springboot.Entities.UserRoleEntity;
import com.example.nimap.PayrollTask.springboot.Repository.PermissionRepository;
import com.example.nimap.PayrollTask.springboot.Repository.RoleEntityRepository;
import com.example.nimap.PayrollTask.springboot.Repository.RolePermissionRepository;
import com.example.nimap.PayrollTask.springboot.Repository.UserRoleRepository;
import com.example.nimap.PayrollTask.springboot.Services.IPermissionIdList;
import com.example.nimap.PayrollTask.springboot.Services.RolePermissionIntf;
import com.example.nimap.PayrollTask.springboot.page.Pagination;

import com.example.nimap.PayrollTask.springboot.Exception.ResourceNotFoundException;

@Service
public class RolePermissionServiceImpl implements RolePermissionIntf {
	@Autowired
	private RoleEntityRepository roleEntityRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	@Autowired
	private UserRoleRepository userroleRepository;

	@Override
	public void addRolePermissions(RolePermissionDto rolePermissionDto) {
		RoleEntity role = roleEntityRepository.findById(rolePermissionDto.getRoleId())
				.orElseThrow(() -> new ResourceNotFoundException("roleNot found with given id"));
		List<PermissionEntity> permission = permissionRepository.findByIdIn(rolePermissionDto.getPermissionId());
		if (permission.size() == rolePermissionDto.getPermissionId().size()) {
			ArrayList<RolePermissionEntity> rolepermissionList = new ArrayList<>();
			for (int i = 0; i < permission.size(); i++) {
				RolePermissionEntity rolePermission = new RolePermissionEntity();
				rolePermission.setRole(role);
				rolePermission.setPermission(permission.get(i));
				rolepermissionList.add(rolePermission);
				rolePermissionRepository.saveAll(rolepermissionList);

			}

		}
	}

	@Override
	public Page<IRolePermissionListDto> getAllRolePermission(String pageNumber, String pageSize) {
		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		Page<IRolePermissionListDto> irolePermissionListDto;

		return irolePermissionListDto = rolePermissionRepository.findByOrderByIdAsc(paging,
				IRolePermissionListDto.class);

	}

	@Override
	public void deleterolePermission(Long id) {
		this.rolePermissionRepository.deleteById(id);

	}

	@Override
	public ArrayList<String> getPermissionByUserId(Long userid) {

		ArrayList<UserRoleEntity> userrole = userroleRepository.getRolesOfUser(userid);

		ArrayList<Long> roles = new ArrayList<>();
		for (int i = 0; i < userrole.size(); i++) {
			roles.add(userrole.get(i).getRoleEntity().getId());

		}
		List<IPermissionIdList> rolesPermission = rolePermissionRepository.findPermissionByRoleIdIn(roles,
				IPermissionIdList.class);

		ArrayList<String> permissions = new ArrayList<>();
		for (IPermissionIdList element : rolesPermission) {

			permissions.add(element.getPermissionActionName());

		}

		return permissions;

	}

}
