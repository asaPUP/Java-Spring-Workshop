package com.uabc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uabc.entities.Address;
import com.uabc.entities.Customer;
import com.uabc.entities.Store;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	@Query(value ="select a.district ,a.district  from address a, store s where a.city_id  = ?1 and s.address_id = a.address_id  order by district ", nativeQuery=true)
	List<Address> findByCityId(Integer id);

}
