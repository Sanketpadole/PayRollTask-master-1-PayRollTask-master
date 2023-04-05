package com.example.nimap.PayrollTask.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimap.PayrollTask.springboot.Entities.PostOfficeResponseBean;
import com.example.nimap.PayrollTask.springboot.ServiceImpl.PostalServiceImpl;

@RestController
@RequestMapping("/postal")
public class PostDetailsController {
	@Autowired
	PostalServiceImpl PostalS;

	@RequestMapping(value = "/bycity", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PostOfficeResponseBean getPostalByCity(@RequestParam String city) {
		PostOfficeResponseBean post;
		post = PostalS.fetchPost(city);
		return post;

	}

}
