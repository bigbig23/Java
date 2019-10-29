package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.dao.CustomerDAO;
import demo.entity.Customer;
import demo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//injectiong CustomerDAO
	//@Autowired
	//private CustomerDAO customerDAO;
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String lsitCustomer(Model theModel) {
		//get Customer from dao
		//List<Customer> thecustomers = customerDAO.getCustomers();
		
		List<Customer> thecustomers = customerService.getCustomers();
		
		//add customer to model
		theModel.addAttribute("customers",thecustomers);
		return "list-customers";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		//create model attribute to bind form data
		Customer theCustomer = new Customer();
		model.addAttribute("customer",theCustomer);
		return "customer-form";
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		//save the customer using service
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model model) {
		//get customer id from service
		Customer theCus = customerService.getCustomer(theId);
		//set cusotmer as model attribute
		model.addAttribute("customer",theCus);
		//send over to our form
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
	
	

}
