package com.uabc.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.uabc.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>  {

}
