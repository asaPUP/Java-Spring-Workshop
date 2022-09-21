package com.uabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.Payment;
import com.uabc.repository.PaymentRepository;

@Service
public class PaymentService {

	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public void insertPayment(Payment payment) 
	{
		paymentRepository.save(payment);
	}

}
