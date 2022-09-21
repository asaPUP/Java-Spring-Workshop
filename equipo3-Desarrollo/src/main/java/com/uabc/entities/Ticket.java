package com.uabc.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Integer ticket_id;
	
	@Column(name="ticket_date")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp ticket_date;
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;
	
	@OneToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "active")
	private boolean active;

	public Integer getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}

	public java.sql.Timestamp getTicket_date() {
		return ticket_date;
	}

	public void setTicket_date(java.sql.Timestamp ticket_date) {
		this.ticket_date = ticket_date;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Rental getInventory() {
		return rental;
	}

	public void setInventory(Rental inventory) {
		this.rental = inventory;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Ticket(Integer ticket_id, Timestamp ticket_date, Customer customerId, Rental rental, BigDecimal amount,
			boolean active) {
		super();
		this.ticket_id = ticket_id;
		this.ticket_date = ticket_date;
		this.customerId = customerId;
		this.rental = rental;
		this.amount = amount;
		this.active = active;
	}

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [ticket_id=");
		builder.append(ticket_id);
		builder.append(", ticket_date=");
		builder.append(ticket_date);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", inventory=");
		builder.append(rental);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}
	
	
}
