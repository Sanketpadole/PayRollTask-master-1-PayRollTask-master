package com.example.nimap.PayrollTask.springboot.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Pagination {

	public Pagination() {
		super();

	}

	public Pageable getPagination(String pageNumber, String Search) {
		return PageRequest.of(Integer.parseInt(pageNumber) - 1, Integer.parseInt(Search));

	}

}
