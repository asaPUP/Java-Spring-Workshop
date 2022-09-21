package com.uabc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.Address;
import com.uabc.entities.Customer;
import com.uabc.entities.Store;
import com.uabc.repository.AddressRepository;


@Service
public class AddressService {
	
	@Autowired
	private AddressRepository  repository;
	
	public Address InsertAddress(Address address) 
	{
		return repository.save(address);
	}

	public List<Address> findByCityId(Integer id)
	{
		return repository.findByCityId(id);
	}
	
}
