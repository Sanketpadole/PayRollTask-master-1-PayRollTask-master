package com.example.nimap.PayrollTask.springboot.Services;

import com.example.nimap.PayrollTask.springboot.Entities.PostOfficeResponseBean;

public interface PostInterface {

	PostOfficeResponseBean fetchPost(String city);
	// public Postal fetchPost(String city);

}
