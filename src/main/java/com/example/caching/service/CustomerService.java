package com.example.caching.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.caching.listener.CacheListener;
import com.example.caching.model.Customer;

@Service
public class CustomerService {
	private List<Customer> customers;
	private static final Logger LOG= LoggerFactory.getLogger(CustomerService.class);
	public CustomerService(){
		customers = new ArrayList<>();
		customers.addAll(Arrays.asList(new Customer("1", "abbc.gmail.com", "abc", "khan"), new Customer("2", "bccd.gmail.com", "bcd", "khan"), new Customer("3", "cdde.gmail.com", "cde", "khan")));
	}
	
	@Cacheable(cacheNames = "customerList", key= "'customerList'")
	public List<Customer> getAllCustomer(){
		LOG.info("inside getAllCustomer meathod");
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	@Cacheable(cacheNames = "customer", key= "#Id")
	public Customer getCustomerById(String Id) throws Exception {
		LOG.info("inside getCustomerById meathod");
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Customer customer: customers) {
			if(customer.getId().equals(Id)) {
				LOG.info("found customer wiht Id {} and has email: {}", customer.getId(), customer.getEmail());
				return customer;
			}
		}
		
		throw new Exception(String.format("Customer Id: {} not found.", Id)); 
		
	}
}
