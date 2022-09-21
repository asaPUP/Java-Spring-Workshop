package com.uabc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.Country;
import com.uabc.repository.CityRepository;
import com.uabc.repository.CountryRepository;

@Service
public class CountryService {
	
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> findAll()
	{
		return countryRepository.findAll();
	}

}
