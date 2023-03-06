package com.example.nimap.PayrollTask.springboot.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorials")
public class Excel {

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "candidate")
	private String candidate;

	@Column(name = "recruiter")
	private String recruiter;

	@Column(name = "job")
	private String job;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public String getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Excel(long id, String candidate, String recruiter, String job) {
		super();
		this.id = id;
		this.candidate = candidate;
		this.recruiter = recruiter;
		this.job = job;
	}

	public Excel() {
		super();

	}

}
