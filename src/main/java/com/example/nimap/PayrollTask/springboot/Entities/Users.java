package com.example.nimap.PayrollTask.springboot.Entities;

import java.sql.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Where(clause = "is_Active=true")

@SQLDelete(sql = "UPDATE USERS SET is_Active=false WHERE id=?")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
	private boolean is_Active = true;

	public Users(List<UserRoleEntity> userRole) {
		super();
		this.userRole = userRole;
	}

	public List<UserRoleEntity> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRoleEntity> userRole) {
		this.userRole = userRole;
	}

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;
	@JsonManagedReference
//	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	private List<UserRoleEntity> userRole;
//	@JsonIgnore
	@JsonManagedReference

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	private List<UserJobEntity> userJob;

	public Users(Long id, String username, String email, String password, Date createdAt, Date updatedAt,
			List<UserRoleEntity> userRole, List<UserJobEntity> userJob) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userRole = userRole;
		this.userJob = userJob;
	}

	public List<UserJobEntity> getUserJob() {
		return userJob;
	}

	public void setUserJob(List<UserJobEntity> userJob) {
		this.userJob = userJob;
	}

	public Users(boolean isActive, Date createdAt, Date updatedAt) {
		super();
		this.is_Active = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isActive() {
		return is_Active;
	}

	public void setActive(boolean isActive) {
		this.is_Active = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users() {
		super();

	}

	public Users(Long id, String username, String email, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

//	@Override
//	public String toString() {
//		return " {id:" + id + ", username:" + username + ", email:" + email + ", password:" + password + ", isActive:"
//				+ is_Active + "}";
//	}

}
