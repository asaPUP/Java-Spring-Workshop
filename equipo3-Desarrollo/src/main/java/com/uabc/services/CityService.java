package com.uabc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.Category;
import com.uabc.entities.City;
import com.uabc.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	
	public Optional<City> findById(Integer id) 
	{
		return cityRepository.findById(id);
	}
	
	public List<City> findCityById(Integer id){
		List<City> cities = new ArrayList<City>();
		List<City> city = new ArrayList<City>();
		City city2 = new City();
		cities = cityRepository.findAll();
		for(int i=0; i< cities.size(); i++) 
		{
			city2 = cities.get(i);
			if(city2.getCountryId()== id) 
			{
				city.add(city2);
			}
		}
		return cities;
	}
	
	public List<Map> cuidadesPorPais (Integer id){
		List<City> respuesta = cityRepository.findByCountryId(id);
		List<Map> c = respuesta.stream().map(item -> 
		new HashMap<String, Object>(
				Map.of("id", item.getCityId(), "text", item.getCity())
		)).collect(Collectors.toList());
		return c;
	}

}
