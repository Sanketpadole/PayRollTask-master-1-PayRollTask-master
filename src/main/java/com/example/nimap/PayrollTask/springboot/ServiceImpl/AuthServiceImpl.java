package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//
//import com.example.nimap.PayrollTask.springboot.page.CacheOperation;

import com.example.nimap.PayrollTask.springboot.Dto.ModelDto;
import com.example.nimap.PayrollTask.springboot.Dto.UsersDto;
import com.example.nimap.PayrollTask.springboot.Entities.OtpEntity;
import com.example.nimap.PayrollTask.springboot.Entities.Users;
import com.example.nimap.PayrollTask.springboot.Repository.AuthRepository;
import com.example.nimap.PayrollTask.springboot.Repository.OTPRepository;
import com.example.nimap.PayrollTask.springboot.Services.AuthInterface;

import com.example.nimap.PayrollTask.springboot.Exception.ResourceNotFoundException;

import com.springboot.nimap.PayrollTask.springboot.Util.PasswordValidator;

@Service

public class AuthServiceImpl implements AuthInterface, UserDetailsService {
	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RolePermissionServiceImpl rolePermission;

	@Autowired
	private OTPRepository otpRepository;

//	@Autowired
//	private CacheOperation cache;

	@Override
	public void registerUser(UsersDto usersDto) {

		String password = usersDto.getPassword();
		if (PasswordValidator.isValid(password)) {
		} else {
			throw new ResourceNotFoundException("Password does not match");
		}

		Users users = new Users();

		users.setEmail(usersDto.getEmail());
		users.setPassword(this.passwordEncoder.encode(usersDto.getPassword()));
		users.setUsername(usersDto.getUsername());

		this.authRepository.save(users);

	}

//*********1st
	@Override
	public UserDetails loadUserByUsername(String email) {
		System.out.println("aJAS");

		Users users;
//		if (!cache.isKeyExist(email, email)) {
//			System.out.println("aJAS1");

		users = this.authRepository.findByEmail(email);
		System.out.println("aJAS2" + users);

//		System.out.println("from db");
//		cache.addInCache(email, email, users);
//		
//		} else {
//		
//
//		users = (Users) cache.getFromCache(email, email); // redisTemplate.opsForHash().get(email, email);
//		
//		System.out.println("from cache");
//		}

		if (users == null) {

			throw new UsernameNotFoundException("User not found with Email: " + email);
		}

		return new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(),
				getAuthority(users));
	}

//	*******2nd
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Users users = new Users();
//
//		if (!cache.isKeyExist(email, email)) {
//
//			users = this.authRepository.findByEmail(email);
//			cache.addInCache(email, email, users.toString());
//		} else {
//
//			String jsonString = (String) cache.getFromCache(email, email);
//			JSONObject jsonObject = null;
//			try {
//				jsonObject = new JSONObject(jsonString);
//				users.setId(Long.parseLong(jsonObject.getString("id")));
//				users.setEmail(jsonObject.getString("email"));
//				users.setPassword(jsonObject.getString("password"));
//
//			} catch (JSONException e) {
//				users = null;
//			}
//
//		}
//
//		if (users== null)
//
//		{
//
//			throw new ResourceNotFoundException("User OR Password not found");
//		}
//
//		return new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(),
//				getAuthority(users));
//	}

	private ArrayList<SimpleGrantedAuthority> getAuthority(Users user) {

		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		ArrayList<String> permissions = this.rolePermission.getPermissionByUserId(user.getId());
		permissions.forEach(e -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + e));

		});
		return authorities;
	}

//	****3rd
//	private ArrayList<SimpleGrantedAuthority> getAuthority(Users user) {
//	ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//	if (!cache.isKeyExist(user.getId() + "permission", user.getId() + "permission")) {
//		ArrayList<SimpleGrantedAuthority> authorities1 = new ArrayList<>();
//
//		ArrayList<String> permissions = this.rolePermission.getPermissionByUserId(user.getId());
//
//		permissions.forEach(e -> {
//			authorities1.add(new SimpleGrantedAuthority("ROLE_" + e));
//
//		});
//		authorities = authorities1;
//
//		cache.addInCache(user.getId() + "permission", user.getId() + "permission", authorities1);
//	} else {
//
//		authorities = (ArrayList<SimpleGrantedAuthority>) cache.getFromCache(user.getId() + "permission",
//				user.getId() + "permission");
//
//	}
//	return authorities;
//}

//	
//	*****4th
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		System.out.println("adkb");
//		Users user;
//		if (!cache.isKeyExist(email, email)) {
//			System.out.println("hvjh");
//			user = authRepository.findByEmail(email);
//			System.out.println("weekghk");
//			System.out.println("from db");
//			cache.addInCache(email, email, user);
//		} else {
//
//			user = (Users) cache.getFromCache(email, email); // redisTemplate.opsForHash().get(email, email);
//			System.out.println("from cache");
//		}
//
//		if (user == null) {
//			throw new UsernameNotFoundException("User not found with Email: " + email);
//		}
//
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//				getAuthority(user));
//	}
//
//	private ArrayList<SimpleGrantedAuthority> getAuthority(Users user) {
//		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//		if (!cache.isKeyExist(user.getId() + "permission", user.getId() + "permission")) {
//			ArrayList<SimpleGrantedAuthority> authorities1 = new ArrayList<>();
//
//			ArrayList<String> permissions = this.rolePermissionServiceImpl.getPermissionByUserId(user.getId());
//
//			permissions.forEach(e -> {
//				authorities1.add(new SimpleGrantedAuthority("ROLE_" + e));
//
//			});
//			authorities = authorities1;
//
//			cache.addInCache(user.getId() + "permission", user.getId() + "permission", authorities1);
//		} else {
//
//			authorities = (ArrayList<SimpleGrantedAuthority>) cache.getFromCache(user.getId() + "permission",
//					user.getId() + "permission");
//
//		}
//		return authorities;
//	}

	@Override
	public Boolean updateUserwithPassword(ModelDto modelDto, Users userEntity, OtpEntity otpEntity) throws Exception {

		userEntity.setPassword(passwordEncoder.encode(modelDto.getPassword()));
		this.authRepository.save(userEntity);
		otpRepository.delete(otpEntity);

		return true;

	}

	@Override
	public Boolean comparePassword(String password, String hashPassword) {
		return passwordEncoder.matches(password, hashPassword);
	}

}
