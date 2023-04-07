package com.example.nimap.PayrollTask.springboot.Entities;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.nimap.PayrollTask.springboot.Repository.RolePermissionRepository;
import com.example.nimap.PayrollTask.springboot.Repository.UserRepository;
import com.example.nimap.PayrollTask.springboot.Repository.UserRoleRepository;
import com.example.nimap.PayrollTask.springboot.Services.JwtTokenUtilInterface;
import com.springboot.nimap.PayrollTask.springboot.Util.ApiUrls;

public class AuthLogger implements HandlerInterceptor {
	@Autowired
	JwtTokenUtilInterface jwtTokenUtilInterface;

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	RolePermissionRepository rolePermissionRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String authHeader = request.getHeader("Authorization");
		String tokenString = (null != authHeader) ? authHeader.split(" ")[1] : null;
		ArrayList<String> urlsWithoutHeader = new ArrayList<>(Arrays.asList(ApiUrls.URLS_WITHOUT_HEADER));
		final String requestUrl = request.getRequestURI();
		if (!urlsWithoutHeader.contains(requestUrl)) {
			if (null != tokenString) {
				final String emailString = jwtTokenUtilInterface.getUsernameFromToken(tokenString);
				Users user = userRepository.findByEmail(emailString);
				if (null != user) {
					ArrayList<UserRoleEntity> userRoleEntity = userRoleRepository.getRolesOfUser(user.getId());
					ArrayList<String> roles = new ArrayList<>();
					for (int i = 0; i < userRoleEntity.size(); i++) {
						roles.add(userRoleEntity.get(i).getRoleEntity().getRoleName());
					}
					ArrayList<String> rolePermissionEntities = this.rolePermissionRepository
							.getPermissionOfUser(user.getId());
					request.setAttribute("X-user-permissions", rolePermissionEntities);
					request.setAttribute("X-user-roles", roles);
					request.setAttribute("X-user-id", user.getId());
				}
			}

		}
		return HandlerInterceptor.super.preHandle(request, response, handler);

	}

}
