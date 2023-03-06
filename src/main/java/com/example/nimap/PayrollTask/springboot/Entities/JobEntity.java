package com.example.nimap.PayrollTask.springboot.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Jobs")
@Where(clause = "is_Active=true")
@SQLDelete(sql = "UPDATE JOBS SET is_active=false WHERE id=?")
public class JobEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String jobName;
	private String description;
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY,targetEntity = Users.class)
	@JoinColumn(name = "recruiter_id")
	private Users recruiterId;

	private boolean isActive = true;
	@JsonIgnore
//	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jobs")
	private List<UserJobEntity> userJobEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Users getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(Users users) {
		this.recruiterId = users;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<UserJobEntity> getUserJobEntity() {
		return userJobEntity;
	}

	public void setUserJobEntity(List<UserJobEntity> userJobEntity) {
		this.userJobEntity = userJobEntity;
	}

	public JobEntity(Long id, String jobName, String description, Date createdAt, Date updatedAt,
			Users recruiterId, boolean isActive, List<UserJobEntity> userJobEntity) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.recruiterId = recruiterId;
		this.isActive = isActive;
		this.userJobEntity = userJobEntity;
	}

	public JobEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
