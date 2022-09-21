package com.uabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.Rental;
import com.uabc.entities.Ticket;
import com.uabc.repository.RentalRepository;
import com.uabc.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	public Ticket insertTicket(Ticket ticket) 
	{
		return ticketRepository.save(ticket);
	}
}
