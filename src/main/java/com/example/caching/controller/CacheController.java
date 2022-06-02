package com.example.caching.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.caching.model.Customer;
import com.example.caching.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CacheController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping()
	public ResponseEntity<Customer> getCustomerById(@RequestParam String customerId) throws Exception {
		Customer customer = null;
		try{
		customer = customerService.getCustomerById(customerId);
		}catch(Exception e) {
			return new ResponseEntity<Customer>(new Customer(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getCustomers(){
		List<Customer> customers = new ArrayList<>();
		customers = customerService.getAllCustomer();
		
		if(customers.size()>0) {
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.NOT_FOUND);
		
	}
}
