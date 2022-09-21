package com.uabc.entities;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uabc.repository.RentalRepository;
import com.uabc.services.RentalService;

@Entity
@Table (name = "rental")
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id")
	private Integer rental_id;
	
	@Column(name = "staff_id")
	private Integer staff_id;
	
	@Column(name="return_date")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp return_date;
	
	@Column(name="rental_date")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp rental_date;
	
	@Column(name="last_update")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp last_update;
	
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;
	
	@Transient
	private Boolean busqueda;

	@Transient
	private Boolean seleccionFecha;
	

	public Boolean getSeleccionFecha() {
		return seleccionFecha;
	}

	
	public void setSeleccionFecha(Boolean seleccionFecha) {
		this.seleccionFecha = seleccionFecha;
	}
	
	public Integer getRental_id() {
		return rental_id;
	}

	public void setRental_id(Integer rental_id) {
		this.rental_id = rental_id;
	}

	public Integer getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}

	public java.sql.Timestamp getReturn_date() {
		return return_date;
	}

	public void setReturn_date(java.sql.Timestamp return_date) {
		this.return_date = return_date;
	}

	public java.sql.Timestamp getRental_date() {
		return rental_date;
	}

	public void setRental_date(java.sql.Timestamp rental_date) {
		this.rental_date = rental_date;
	}

	public java.sql.Timestamp getLast_update() {
		return last_update;
	}

	public void setLast_update(java.sql.Timestamp last_update) {
		this.last_update = last_update;
	}

	public Inventory getInventory_id() {
		return inventory;
	}

	public void setInventory_id(Inventory inventory_id) {
		this.inventory = inventory_id;
	}

	public Customer getCustomer_id() {
		return customerId;
	}

	public void setCustomer_id(Customer customer_id) {
		this.customerId = customer_id;
	}

	public Boolean getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(Boolean busqueda) {
		this.busqueda = busqueda;
	}

	public Rental(Integer rental_id, Integer staff_id, Timestamp return_date, Timestamp rental_date,
			Timestamp last_update, Inventory inventory_id, Customer customer_id) {
		super();
		this.rental_id = rental_id;
		this.staff_id = staff_id;
		this.return_date = return_date;
		this.rental_date = rental_date;
		this.last_update = last_update;
		this.inventory = inventory_id;
		this.customerId = customer_id;
	}

	public Rental() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
