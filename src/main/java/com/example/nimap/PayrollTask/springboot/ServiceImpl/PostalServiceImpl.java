package com.example.nimap.PayrollTask.springboot.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.nimap.PayrollTask.springboot.Entities.PostOfficeResponseBean;
import com.example.nimap.PayrollTask.springboot.Services.PostInterface;

@Service
public class PostalServiceImpl implements PostInterface {
	private static final String API_ENDPOINT = "https://api.postalpincode.in/postoffice/{city}&apikey=YOUR_API_KEY";
	@Autowired
	RestTemplate restTemplate;

	@Override
	public PostOfficeResponseBean fetchPost(String city) {
//		String url = "https://api.postalpincode.in/postoffice/{city}";
//		url = url.replace("{city}", city);
//
//		ResponseEntity<PostOfficeResponseBean[]> post = restTemplate.getForEntity(url, PostOfficeResponseBean[].class);
//
//		System.out.println(post.getStatusCodeValue());
//		post.getBody();
//
//		PostOfficeResponseBean[] responseBeanArray = post.getBody();
//		for (PostOfficeResponseBean responseBean : responseBeanArray) {
//			List<PostOfficeDetailsBean> posyOfficeListBean = responseBean.getPostOffice();
//			for (PostOfficeDetailsBean pob : posyOfficeListBean) {
//				System.out.println(pob.getName() + "******" + pob.getCountry() + "****" + pob.getPincode());
//			}
//
//		}
//
//		return responseBeanArray[0];
//	}

		ResponseEntity<String> response = restTemplate.exchange(API_ENDPOINT, HttpMethod.GET, null, String.class);
		return null;
	}

}
