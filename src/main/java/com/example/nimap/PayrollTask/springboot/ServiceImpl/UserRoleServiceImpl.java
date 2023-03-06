package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nimap.PayrollTask.springboot.Dto.IUserRoleListDto;
import com.example.nimap.PayrollTask.springboot.Dto.UserRoleDto;
import com.example.nimap.PayrollTask.springboot.Entities.RoleEntity;
import com.example.nimap.PayrollTask.springboot.Entities.UserRoleEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;
import com.example.nimap.PayrollTask.springboot.Exception.ResourceNotFoundException;
import com.example.nimap.PayrollTask.springboot.Repository.AuthRepository;
import com.example.nimap.PayrollTask.springboot.Repository.RoleEntityRepository;
import com.example.nimap.PayrollTask.springboot.Repository.UserRoleRepository;
import com.example.nimap.PayrollTask.springboot.Services.UserRoleIntf;

@Service
public class UserRoleServiceImpl implements UserRoleIntf {
	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private RoleEntityRepository roleEntityRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public void addUserRoles(UserRoleDto userRoleDto) {
		Users users = authRepository.findById(userRoleDto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));
		List<RoleEntity> role = roleEntityRepository.findByIdIn(userRoleDto.getRoleId());

		if (role.size() == userRoleDto.getRoleId().size()) {
			ArrayList<UserRoleEntity> userRoleList = new ArrayList<>();

			for (int i = 0; i < role.size(); i++) {
				UserRoleEntity userRole = new UserRoleEntity();
				userRole.setUsers(users);
				userRole.setRoleEntity(role.get(i));
				userRoleList.add(userRole);
				userRoleRepository.saveAll(userRoleList);

			}
		} else {
			new ResourceNotFoundException("Resource not found");
		}
	}

	@Override
	public void deleteuserrole(HttpServletRequest request, Long id) {

		UserRoleEntity userrole = this.userRoleRepository.findById(id)
				.orElseThrow(() -> (new ResourceNotFoundException("Not found with given id")));
		if (userrole != null) {
			this.userRoleRepository.delete(userrole);
		}

	}

	@Override
	public List<IUserRoleListDto> getrecruitercandidate(Long candidateId, Long recruiterId) {

		List<IUserRoleListDto> dtos;

		dtos = userRoleRepository.findByOrderById(candidateId, recruiterId, IUserRoleListDto.class);

		return dtos;
	}

	@Override
	public void updateuserRoles(Long id, UserRoleDto userRoleDto) {

		Users users = authRepository.findById(userRoleDto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));

		List<RoleEntity> role = roleEntityRepository.findByIdIn(userRoleDto.getRoleId());

		if (role.size() == userRoleDto.getRoleId().size()) {

			for (int i = 0; i < role.size(); i++) {
				UserRoleEntity userrole = userRoleRepository.findById(id)
						.orElseThrow(() -> (new ResourceNotFoundException("Not found with given id")));
				userrole.setUsers(users);
				userrole.setRoleEntity(role.get(i));

				userRoleRepository.save(userrole);

			}

		}
	}
}
