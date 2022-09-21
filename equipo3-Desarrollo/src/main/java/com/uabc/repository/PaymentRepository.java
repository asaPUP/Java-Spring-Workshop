package com.uabc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uabc.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	

}
