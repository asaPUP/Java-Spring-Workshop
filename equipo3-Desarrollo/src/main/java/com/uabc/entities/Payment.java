package com.uabc.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.el.lang.ELArithmetic.BigDecimalDelegate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer paymentId;
	
	@Column(name = "customer_id")
	private Integer customerId;
	
	@Column(name = "staff_id")
	private Integer staffId;
	
	@Column(name = "rental_id")
	private Integer rentalId;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "payment_date")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp paymentDate;

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getRentalId() {
		return rentalId;
	}

	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public java.sql.Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(java.sql.Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Payment(Integer paymentId, Integer customerId, Integer staffId, Integer rentalId, BigDecimal amount,
			Timestamp paymentDate) {
		super();
		this.paymentId = paymentId;
		this.customerId = customerId;
		this.staffId = staffId;
		this.rentalId = rentalId;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
