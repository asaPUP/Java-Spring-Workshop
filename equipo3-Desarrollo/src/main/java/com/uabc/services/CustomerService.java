package com.uabc.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.uabc.entities.Address;
import com.uabc.entities.CatalagoCustomers;
import com.uabc.entities.Customer;
import com.uabc.entities.Rental;
import com.uabc.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Optional<Customer> findCustomerById(Integer id) {
		return customerRepository.findById(id);
	}
	
	public List<CatalagoCustomers> findAll() {
		return customerRepository.obtenerCustomers();
	}
	
	public Customer InsertCustomer(Customer customer) 
	{
		return customerRepository.save(customer);
	}
	
	public String emailCustomer(String email) 
	{
		return customerRepository.emailCustomer(email);
	}
	
}
