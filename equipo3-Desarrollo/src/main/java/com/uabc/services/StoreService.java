package com.uabc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.Address;
import com.uabc.entities.City;
import com.uabc.entities.Country;
import com.uabc.entities.Store;
import com.uabc.repository.CityRepository;
import com.uabc.repository.CountryRepository;
import com.uabc.repository.StoreRepository;

@Service
public class StoreService {


	@Autowired
	private StoreRepository  storeRepository;



	public List<Store> findAll()
	{
		return  storeRepository.findAll();
	}

	public List<Store> findByCityId(Integer id)
	{
		return storeRepository.findByCityId(id);
	}

	public List<Map> tiendasPorCiudad (Integer id, List<Store> stores){
		List<Map> c = stores.stream().map(item ->
		new HashMap<String, Object>(
				Map.of("id", item.getStoreId(), "text",item.getAddressId().getDistrict())
		)).collect(Collectors.toList());
		return c;
	}
	
	public List<Map> tiendas (Integer id, List<Store> stores){
		List<Map> c = stores.stream().map(item ->
		new HashMap<String, String>(
				Map.of("id", item.getStoreId().toString(), "text",item.getStoreId().toString())
		)).collect(Collectors.toList());
		return c;
	}
	

}
