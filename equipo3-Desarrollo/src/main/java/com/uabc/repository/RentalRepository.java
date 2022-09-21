package com.uabc.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uabc.entities.Address;
import com.uabc.entities.Rental;

import com.uabc.entities.Staff;

import com.uabc.entities.Store;



public interface RentalRepository extends JpaRepository<Rental, Integer> {


	@Query(value ="select rental_id from rental r where r.return_date is null and inventory_id = ?1", nativeQuery=true)
	Integer findByReturn_dateNullAndinventoryId(Integer inventoryId);

	
	@Query(value ="select * from rental r, customer c ,inventory i ,film f  where r.inventory_id = i.inventory_id \r\n"
			+ "and i.film_id = f.film_id and r.customer_id = c.customer_id and i.inventory_id = ?1 and r.return_date is null order by r.rental_id desc limit 1", nativeQuery=true)
	Rental findByInventoryId(Integer id);
	
}
