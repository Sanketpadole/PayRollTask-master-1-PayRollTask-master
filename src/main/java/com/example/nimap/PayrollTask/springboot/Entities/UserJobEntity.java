package com.example.nimap.PayrollTask.springboot.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "userjob")

//@Where(clause = "is_Active=true")
//@SQLDelete(sql = "UPDATE USERROLE SET is_active=false WHERE id=?")
public class UserJobEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//private boolean isActive = true;

//	public UserJobEntity(boolean isActive) {
//		super();
//		this.isActive = isActive;
//	}

//	public boolean isActive() {
//		return isActive;
//	}
//
//	public void setActive(boolean isActive) {
//		this.isActive = isActive;
//	}

	// @JsonIgnore
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)

	private Users users;
//	@JsonIgnore
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)

	private JobEntity jobs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public JobEntity getJobs() {
		return jobs;
	}

	public void setJobs(JobEntity jobs) {
		this.jobs = jobs;
	}

	public UserJobEntity(Long id, Users users, JobEntity jobs) {
		super();
		this.id = id;
		this.users = users;
		this.jobs = jobs;
	}

	public UserJobEntity() {
		super();

	}

}
