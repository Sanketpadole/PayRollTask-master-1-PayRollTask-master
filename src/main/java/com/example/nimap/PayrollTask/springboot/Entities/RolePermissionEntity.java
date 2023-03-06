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

@Entity
@Table(name = "rolepermission")
@Where(clause = "is_Active=true")

@SQLDelete(sql = "UPDATE ROLEPERMISSION SET is_Active=false WHERE id=?")
public class RolePermissionEntity {
	public RolePermissionEntity() {
		super();

	}

	public RolePermissionEntity(Long id, RoleEntity role, PermissionEntity permission) {
		super();
		this.id = id;
		this.role = role;
		this.permission = permission;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	private RoleEntity role;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permissionId")
	private PermissionEntity permission;

	private boolean isActive = true;

	public RolePermissionEntity(boolean isActive) {
		super();
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public PermissionEntity getPermission() {
		return permission;
	}

	public void setPermission(PermissionEntity permission) {
		this.permission = permission;
	}

}
