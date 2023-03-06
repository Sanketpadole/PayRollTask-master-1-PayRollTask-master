package com.example.nimap.PayrollTask.springboot.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "otp_logger")
public class OtpEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Integer otp;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private Users userId;
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private Date expireAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
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

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date experAt) {
		this.expireAt = experAt;
	}

	public OtpEntity(long id, Integer otp, Users userId, Date createdAt, Date updatedAt, Date expireAt) {
		super();
		this.id = id;
		this.otp = otp;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.expireAt = expireAt;
	}

	public OtpEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
