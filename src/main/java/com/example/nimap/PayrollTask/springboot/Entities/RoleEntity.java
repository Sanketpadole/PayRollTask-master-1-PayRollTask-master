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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "roles")
//@Where(clause = "is_Active=true")
//@SQLDelete(sql="UPDATE ROLES SET is_active=false WHERE id=?")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String roleName;
	private String description;
	//private boolean isActive = true;
//	public RoleEntity(boolean isActive) {
//		super();
//		this.isActive = isActive;
//	}
//
//	public boolean isActive() {
//		return isActive;
//	}
//
//	public void setActive(boolean isActive) {
//		this.isActive = isActive;
//	}

	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleEntity")
	private List<UserRoleEntity> userRole;

	public RoleEntity(long id, String roleName, String description, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.description = description;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public RoleEntity() {
		super();

	}

	public RoleEntity(long id, String roleName, String description) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.description = description;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
