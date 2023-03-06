package com.example.nimap.PayrollTask.springboot.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "userrole")
@Where(clause = "is_Active=true")

@SQLDelete(sql = "UPDATE USERROLE SET is_active=false WHERE id=?")
public class UserRoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	public UserRoleEntity(Long id, Users users, RoleEntity roleEntity) {
		super();
		this.id = id;
		this.users = users;
		this.roleEntity = roleEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserId")
	private Users users;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RoleId")
	private RoleEntity roleEntity;

	private boolean isActive = true;

	public UserRoleEntity(boolean isActive) {
		super();
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	public UserRoleEntity() {
		super();

	}

}
