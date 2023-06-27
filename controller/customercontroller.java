package com.nkxgen.spring.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nkxgen.spring.jdbc.Bal.ViewInterface;
import com.nkxgen.spring.jdbc.Dao.CustomerDaoInterface;
import com.nkxgen.spring.jdbc.model.Customer;
import com.nkxgen.spring.jdbc.model.CustomerSub;
import com.nkxgen.spring.jdbc.model.CustomerViewModel;
import com.nkxgen.spring.jdbc.model.Customertrail;
import com.nkxgen.spring.jdbc.model.service;

@Controller
public class customercontroller {
	@Autowired
	private CustomerDaoInterface cd;
	@Autowired
	ViewInterface v;

	@RequestMapping(value = "/customerdatatrailsave", method = RequestMethod.POST)
	public String customerDataSaveToDB(@Validated CustomerViewModel customer, Model model) {
		System.out.println("this is my new customer id : "+customer.getId());
		Customertrail cus = service.dotheservice2(customer);
		//Customertrail cus1 = cd.getCustomerById(customer.getId());
		if (cus.getId() != null) {
			//cus1 = cus1.dotheservice1(customer);
			cd.save_Trail(cus);

		} else {
			cd.save_Trail(cus);
		}
		return "Account_new_application_form";
	}

	// =================================================
	// real saving
//	@RequestMapping(value = "/save_to_customer_database", method = RequestMethod.POST)
//	public String save_to_customer_database(@RequestParam("CustomerId") Long customerId) {
//		long customerid = customerId;
//		System.out.println("print the value: " + customerid);
//		Customertrail cus = cd.getCustomerById(customerid);
//		System.out.println("print the value returning: " + cus.getId());
//
//		Customer customer = service.dotheservice(cus);
//
//		cd.save(customer);
//		// change the return
//		return "Account_new_application_form";
//	}

	// =========================================================
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public String getCustomers(Model model) {

		List<Customer> customerList = v.getAllCustomers();
		model.addAttribute("customerList", customerList);
		return "customer_edit_details_form";

	}

	// ===============================================================
	// editing the existing data
	// =========================================================
	@RequestMapping(value = "/customerdataupdation", method = RequestMethod.POST)
	public String CustomerDataUpdation(Customertrail updatedCustomer) {

		cd.updateCustomerDataById(updatedCustomer);
		return "customer_edit_details_form";

	}

	// =========================================================================================
	@RequestMapping(value = "/saveToCustomersubDatabase", method = RequestMethod.POST)
	public String save_to_customersub_database(@Validated CustomerSub CustomerSub, Model model) {

		Customertrail customer2 = cd.getRealCustomerById(CustomerSub.getCustomerId());
		cd.changethese(customer2, CustomerSub);
		return "Any_Type_account_info";

	}
}