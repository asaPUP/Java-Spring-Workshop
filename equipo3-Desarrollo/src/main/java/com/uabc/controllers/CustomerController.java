package com.uabc.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uabc.entities.Address;
import com.uabc.entities.CatalagoCustomers;
import com.uabc.entities.CatalogoIndex;
import com.uabc.entities.City;
import com.uabc.entities.Country;
import com.uabc.entities.Customer;
import com.uabc.entities.Store;
import com.uabc.services.AddressService;
import com.uabc.services.CategoryService;
import com.uabc.services.CityService;
import com.uabc.services.CountryService;
import com.uabc.services.CustomerService;
import com.uabc.services.FilmService;
import com.uabc.services.LanguageService;
import com.uabc.services.StoreService;





@Controller
public class CustomerController {

	@Autowired
	private FilmService filmService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CityService cityService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private AddressService addressService;

	@RequestMapping(value = {"/customers"})
	public String index(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {

		List<Country> countries= new ArrayList<Country>();


		List<CatalagoCustomers> customers;

	    customers= customerService.findAll();


		countries = countryService.findAll();
		
		List<Store> stores = storeService.findAll();

		model.addAttribute("Store", stores);
		model.addAttribute("Country", countries);
		model.addAttribute("CatalagoCustomers", customers);

		return "views/registro_clientes";
	}



	@RequestMapping(value = {"/InsertCustome"}, method = RequestMethod.POST)
	public String insert(Customer customer, Model model, HttpServletRequest request, HttpServletResponse response, Principal principal,RedirectAttributes redirectAttrs) {
		Boolean xd = true;
		Address address = customer.getAddress();
		String email = customerService.emailCustomer(customer.getEmail());

		if(customer.getEmail().equalsIgnoreCase(email)) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Error, no se puede repetir al mismo cliente")
            .addFlashAttribute("clase", "danger");

			return "redirect:/customers";
		}
		else {

			address.setLastUpdate(new Timestamp(System.currentTimeMillis()));
			addressService.InsertAddress(address);
			customer.setAddress(address);
			customer.setActive(1);
			customer.setActivebool(true);
			customer.setLastUpdate(new Timestamp(System.currentTimeMillis()));
			customerService.InsertCustomer(customer);
			redirectAttrs
            .addFlashAttribute("mensaje", "Cliente Agregado correctamente")
            .addFlashAttribute("clase", "success");

			return "redirect:/customers";
		}

	}

	@RequestMapping(value = "insertarCustomer" , method = RequestMethod.POST)
	public String filtroTitulo(Model model,
    @RequestParam(name = "Nombre") String Nombre, @RequestParam(name = "Apellido") String Apellido,
    @RequestParam(name = "Email") String Email, @RequestParam(name = "Numero") String Numero,
    @RequestParam(name = "Direccion") String Direccion, @RequestParam(name = "selectCountry") Integer selectCountry,
    @RequestParam(name = "selectCity") Integer selectCity, @RequestParam(name = "storeId") Integer storeId,
    RedirectAttributes redirectAtt) {
		Address address = new Address();
		Customer customer = new Customer();
		customer.setFirstName(Nombre);
		customer.setLastName(Apellido);
		customer.setEmail(Email);
		address.setPhone(Numero);
		address.setAddress(Direccion);
		address.setCity_id(selectCity);
		customer.setActivebool(true);
		customer.setStoreId(storeId);
		customer.setAddress(address);
		String email = customerService.emailCustomer(customer.getEmail());

		if(customer.getEmail().equalsIgnoreCase(email)) {
			redirectAtt
            .addFlashAttribute("mensaje", "Error, no se puede repetir al mismo cliente")
            .addFlashAttribute("clase", "danger");

			return "redirect:/customers";
		}
		else {

			address.setLastUpdate(new Timestamp(System.currentTimeMillis()));
			addressService.InsertAddress(address);
			customer.setAddress(address);
			customer.setActive(1);
			customer.setActivebool(true);
			customer.setLastUpdate(new Timestamp(System.currentTimeMillis()));
			customerService.InsertCustomer(customer);
			redirectAtt
            .addFlashAttribute("mensaje", "Cliente Agregado correctamente")
            .addFlashAttribute("clase", "success");

			return "redirect:/customers";
		}



	}

	@ResponseBody
	@RequestMapping(value={"/ciudadesPais/{id}"}, method = RequestMethod.GET)
	public List<Map> consultarCuidadesPorPais(@PathVariable Integer id){
		return cityService.cuidadesPorPais(id);
	}

	

}
