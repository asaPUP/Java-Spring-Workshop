package com.uabc.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.security.Principal;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.uabc.entities.Address;
import com.uabc.entities.Country;
import com.uabc.entities.Customer;
import com.uabc.entities.Inventory;
import com.uabc.entities.Rental;
import com.uabc.entities.ReturnDate;
import com.uabc.entities.Ticket;
import com.uabc.services.RentalService;
import com.uabc.services.TicketService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ReturnController {
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private TicketService ticketService;

	@RequestMapping(value = {"/tickets"})
	public String index(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		
		
		return "views/registro_devolucion";
	}
	
	
	

	
	
	@ResponseBody
	@RequestMapping(value={"/busqueda/{id}"}, method = RequestMethod.GET)
	public ReturnDate consultarId(@PathVariable Integer id){
		if(id!=null) {
			Rental rental = rentalService.findByInventoryId(id);
			ReturnDate date = new ReturnDate();
			date.setTitle(rental.getInventory_id().getFilm().getTitle());
			date.setNombre(rental.getCustomer_id().getFirstName());
			date.setRental_date(rental.getRental_date());
			date.setDiasRenta(rental.getInventory_id().getFilm().getRentalDuration());
			date.sumarDias();
			return date;
		}
		
		
		return new ReturnDate();
	}
	
	@ResponseBody
	@RequestMapping(value={"/calendario/{id}/{fechaCalendar}"}, method = RequestMethod.GET)
	public ReturnDate consultarCalendario(@PathVariable Integer id,@PathVariable String fechaCalendar) throws ParseException{
		if(id!=null) {
			Rental rental = rentalService.findByInventoryId(id);
			ReturnDate date = new ReturnDate();
			date.setTitle(rental.getInventory_id().getFilm().getTitle());
			date.setNombre(rental.getCustomer_id().getFirstName());
			date.setRental_date(rental.getRental_date());
			date.setDiasRenta(rental.getInventory_id().getFilm().getRentalDuration());
			date.sumarDias();
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			Date tempdate = formatter.parse(fechaCalendar);			
			
			date.setDateR(tempdate);
			
			date.comparaFecha();
			long time = rental.getRental_date().getTime();
			Date dateRental= new Date(time);
			
			if(tempdate.compareTo(dateRental)<0) 
			{	
				return null;
			}
			
			
			return date;
		}
		
		
		return new ReturnDate();
	}
	
	@ResponseBody
	@RequestMapping(value={"/ticket/{id}/{fechaCalendar}"}, method = RequestMethod.GET)
	public void insertarTicket(@PathVariable Integer id,@PathVariable String fechaCalendar) throws ParseException{
		if(id!=null) {
			Rental rental = rentalService.findByInventoryId(id);
			ReturnDate date = new ReturnDate();
			date.setTitle(rental.getInventory_id().getFilm().getTitle());
			date.setNombre(rental.getCustomer_id().getFirstName());
			date.setRental_date(rental.getRental_date());
			date.setDiasRenta(rental.getInventory_id().getFilm().getRentalDuration());
			date.sumarDias();
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			Date tempdate = formatter.parse(fechaCalendar);			
			java.sql.Timestamp return_date = new Timestamp(tempdate.getTime());
			
			date.setDateR(tempdate);
			date.comparaFecha();
			
			
			rental.getCustomer_id().setActivebool(false);
			rental.setReturn_date(return_date);
			Ticket ticket = new Ticket();
			
			ticket.setActive(true);
			ticket.setAmount(new BigDecimal(date.getTotal()));
			ticket.setTicket_date(return_date);
			ticket.setCustomerId(rental.getCustomer_id());
			ticket.setInventory(rental);
			
			
			Ticket ticket2= ticketService.insertTicket(ticket);
			
			
		}
		
		
		
	}
	
	
	
	
	
	@RequestMapping("/pdf")
	public void downloadInvoice(HttpServletResponse response, Integer inventarioId, String fechaSeleccionada) throws JRException, IOException, ParseException{
		
		Rental rentalTemp = rentalService.findByInventoryId(inventarioId);
		if(rentalTemp!=null) {
			ReturnDate date = new ReturnDate();
			date.setDiasRenta(rentalTemp.getInventory_id().getFilm().getRentalDuration());
			date.setRental_date(rentalTemp.getRental_date());
			date.setNombre(rentalTemp.getCustomer_id().getFirstName());
			date.setTitle(rentalTemp.getInventory_id().getFilm().getTitle());
			date.sumarDias();
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			Date tempdate = formatter.parse(fechaSeleccionada);			
			
			date.setDateR(tempdate);
			
			date.comparaFecha();
			final File imgLogo = ResourceUtils.getFile("classpath:static/assets/imgs/LOGOuabc.png");
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("subtotal", date.getMulta());
			parameters.put("Titulo", "Comprobante de devolucion de pelicula");
			parameters.put("iva", date.getIva());
			parameters.put("total", date.getTotal());
			parameters.put("logo", new FileInputStream(imgLogo));
			
			date.setMulta("$"+date.getMulta());
			
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(date), false);

			parameters.put("ds",beanCollectionDataSource );
			
		

			JasperReport compileReport = JasperCompileManager
					.compileReport(new FileInputStream("src/main/resources/ReporteDevolucion.jrxml"));

			
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, new JREmptyDataSource());

			response.setContentType("application/x-pdf");
			 response.setHeader("Content-Disposition","inline; filename=ComprobanteDevolucion.pdf");

			 final OutputStream out = response.getOutputStream();
			 JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		}
		
		
		  
		
		
	}

	
}
