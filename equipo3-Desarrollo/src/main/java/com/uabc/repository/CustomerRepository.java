package com.uabc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uabc.entities.Address;
import com.uabc.entities.CatalagoCustomers;
import com.uabc.entities.CatalogoIndex;
import com.uabc.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value ="select c.email from customer c where c.email = ?1 group by c.email" , nativeQuery=true)
	String emailCustomer(String filmId);

	@Query(value ="select c.customer_id, c.first_name, c.last_name, c.email, c.store_id, c.create_date from customer c\r\n"
			+ "	where activebool is true" , nativeQuery=true)
	String tableCustomer(String customer);

	@Query(value ="select c.customer_id ,c.first_name, c.last_name, c.email, c.store_id  from customer c ", nativeQuery = true)
	List<Customer> obtenerCustomerrs();

	@Query(nativeQuery = true)
	List<CatalagoCustomers> obtenerCustomers();

}
