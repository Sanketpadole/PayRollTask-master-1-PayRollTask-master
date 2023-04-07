package com.example.nimap.PayrollTask.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimap.PayrollTask.springboot.ServiceImpl.PostalServiceImpl;

@RestController
@RequestMapping("/postal")
public class PostDetailsController {
	@Autowired
	PostalServiceImpl PostalS;

	@RequestMapping(value = "/bycity", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public String getPostalByCity(@RequestParam String city) {
//		String post;
//		post = PostalS.fetchPost(city);
//		return post;
//
//	}

	public ResponseEntity<?> getPostalByCity(@RequestParam String city) {

		String post;
		post = PostalS.fetchPost(city);

		if (post == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(post, HttpStatus.OK);
		}
	}

}
