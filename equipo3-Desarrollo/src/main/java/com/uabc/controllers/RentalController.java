package com.uabc.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uabc.entities.Customer;
import com.uabc.entities.Film;
import com.uabc.entities.Inventory;
import com.uabc.entities.Payment;
import com.uabc.entities.Rental;
import com.uabc.entities.ReturnDate;
import com.uabc.services.CustomerService;
import com.uabc.services.IFilmService;
import com.uabc.services.IInventoryService;
import com.uabc.services.PaymentService;
import com.uabc.services.RentalService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class RentalController {

	@Autowired
	private IInventoryService inventoryService; 
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private IFilmService filmService;

	@GetMapping("/rental")
	public String rental(Model model) {
			model.addAttribute("inventario", new Inventory());
			model.addAttribute("rental", new Rental());
		return "views/rental";
	}
	
	@ResponseBody
	@RequestMapping(value={"/buscarpeli/{peliId}/{userid}"}, method = RequestMethod.GET)
	public Inventory consultarInventario(@PathVariable Integer peliId,@PathVariable Integer userid) throws ParseException{
		Optional<Customer> customer = customerService.findCustomerById(userid);
		if(customer.get().getActivebool()) {
			
			Inventory inventorys = new Inventory();
			inventorys = inventoryService.findByInventoryId(peliId);
			
			if(inventorys == null) {
				Inventory temp = new Inventory();
				temp.getFilm().setTitle("lost");
				return temp;
				
			}else {
				Integer registro_BD = rentalService.findByReturn_dateNullAndinventoryId(peliId);
				
				if(registro_BD == null) {
					
					return inventoryService.findByInventoryId(peliId);
					
				} else {
					
					return new Inventory();
				}
			}
			
		}else {
			Inventory inventory = new Inventory();
			inventory.getFilm().setTitle("Inactivo");
			return inventory;
		}
		
		
	}
	
	
	@ResponseBody
	@RequestMapping(value={"/cambio/{total}/{pago}"}, method = RequestMethod.GET)
	public String cambioRenta(@PathVariable String total,@PathVariable String pago) {
		String cambio;
		float cambioDinero;
		float totalDinero = Float.parseFloat(total);
		float pagoDinero = Float.parseFloat(pago);
		if(pagoDinero>=totalDinero) 
		{
			cambioDinero = pagoDinero - totalDinero;
			BigDecimal bd = new BigDecimal(cambioDinero);
			bd.setScale(2, BigDecimal.ROUND_HALF_UP); // this does change bd
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			cambio = bd.toString();
			return cambio;
		}else 
		{
			return "Saldo Insuficiente";
		}
		
	
	}
	@ResponseBody
	@RequestMapping(value={"/registrar/{peliId}/{userid}/{total}"}, method = RequestMethod.GET)
	public void insertarRenta(@PathVariable Integer peliId,@PathVariable Integer userid,@PathVariable BigDecimal total) {
		Optional<Customer> customer = customerService.findCustomerById(userid);
		Inventory inventorys = new Inventory();
		inventorys = inventoryService.findByInventoryId(peliId);
		Rental rental = new Rental();
		Payment payment = new Payment();
		Date tempdate = new Date(System.currentTimeMillis());
		java.sql.Timestamp return_date = new Timestamp(tempdate.getTime());
		
		
		rental.setCustomer_id(customer.get());
		rental.setInventory_id(inventorys);
		rental.setStaff_id(22);
		rental.setRental_date(return_date);
		rental.setReturn_date(null);
		
		rental=rentalService.insertRental(rental);
		
		payment.setCustomerId(customer.get().getCustomerId());
		payment.setStaffId(22);
		payment.setRentalId(rental.getRental_id());
		payment.setPaymentDate(return_date);
		payment.setAmount(total);
		
		paymentService.insertPayment(payment);
		
	}
	@RequestMapping("/pdfRental")
	public void downloadInvoice(HttpServletResponse response, Integer peliId, Integer userid, String efectivo) throws JRException, IOException, ParseException{
		
		Optional<Customer> customer = customerService.findCustomerById(userid);
		Inventory inventorys = new Inventory();
		inventorys = inventoryService.findByInventoryId(peliId);
		float subtotal = inventorys.getFilm().getRentalRate();
		float iva = (float) (subtotal*.16);
		float total = iva+subtotal;
		float efect=Float.parseFloat(efectivo);
		float cambio = efect - total;
		final File imgLogo = ResourceUtils.getFile("classpath:static/assets/imgs/LOGOuabc.png");
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("subtotal", Float.toString(subtotal));
		parameters.put("Titulo", "Comprobante de renta de pelicula");
		parameters.put("iva", Float.toString(iva));
		parameters.put("total", Float.toString(total));
		parameters.put("logo", new FileInputStream(imgLogo));
		parameters.put("Efectivo", Float.toString(efect));
		parameters.put("Cambio", Float.toString(cambio));
		parameters.put("Nombre", customer.get().getFirstName()+" "+customer.get().getLastName());
		parameters.put("filmTitle", inventorys.getFilm().getTitle());
		parameters.put("PrecioRenta", Float.toString(inventorys.getFilm().getRentalRate()));
		
		
		
	

		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/ReporteRenta.jrxml"));

		
		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, new JREmptyDataSource());

		response.setContentType("application/x-pdf");
		 response.setHeader("Content-Disposition","inline; filename=ComprobanteRenta.pdf");

		 final OutputStream out = response.getOutputStream();
		 JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	
	
	}

}
