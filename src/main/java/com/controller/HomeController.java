package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.BookTransaction;
import com.service.Service_Impl;

@RestController
public class HomeController {

	@Autowired
	private Service_Impl service_Impl;

	@PostMapping("/Create")
	public String create_user(@RequestBody BookTransaction bookTransaction) {
		System.out.println(bookTransaction);

		String resp = service_Impl.save(bookTransaction);

		return resp;

	}

	@GetMapping("getById/{id}")
	public BookTransaction getById(@PathVariable long id) {
		return service_Impl.getById(id);
	}

	@PutMapping("/Update")
	public String updatedById(@RequestBody BookTransaction bookTransaction) {
		return service_Impl.updateById(bookTransaction);

	}

}
