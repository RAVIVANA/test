
package com.nkxgen.spring.jdbc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nkxgen.spring.jdbc.Bal.View;
import com.nkxgen.spring.jdbc.Dao.BankUserInterface;
import com.nkxgen.spring.jdbc.events.BankUserCreationEvent;
import com.nkxgen.spring.jdbc.events.BankUserDetailsModificationEvent;
import com.nkxgen.spring.jdbc.model.BankUser;
import com.nkxgen.spring.jdbc.model.BankUserInput;
import com.nkxgen.spring.jdbc.model.BankUserViewModel;

@Controller
public class UserController {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser() {
		return "addUser";
	}

	private final BankUserInterface bankUserService;
	@Autowired
	View v;

	@Autowired
	public UserController(BankUserInterface bankUserService) {
		this.bankUserService = bankUserService;
	}

	@RequestMapping(value = "/submitform", method = RequestMethod.POST)
	public ResponseEntity<String> submitForm(BankUserInput bankUser, HttpServletRequest request) {
		BankUser b = new BankUser();
		b.setInputModelValues(bankUser);
		bankUserService.saveBankUser(b);
		System.out.println("Submitted Form");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		applicationEventPublisher.publishEvent(new BankUserCreationEvent("Bank User Created ", username));
		return ResponseEntity.ok("Data saved successfully!");
	}

	@RequestMapping(value = "/mainUser", method = RequestMethod.GET)
	public String getBankUsers(Model model) {
		List<BankUserViewModel> bankUsers = v.getAllBankUsers();
		model.addAttribute("bankUsers", bankUsers);
		return "mainUser";
	}

	@RequestMapping("/saveUserData")
	@ResponseBody
	public String saveUserData(BankUserInput bankUser, HttpServletRequest request) {
		BankUser b = new BankUser();
		System.out.println(bankUser.getBusr_id());
		b.setInputModelValues(bankUser);
		System.out.println(b.getBusr_id());
		bankUserService.saveUser(b);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		applicationEventPublisher
				.publishEvent(new BankUserDetailsModificationEvent("Bank User Details Modified", username));
		return "User data updated successfully";
	}

	@RequestMapping(value = "/fetchData", method = RequestMethod.POST)
	public String getBankuserDataByDesignation(@ModelAttribute("BankUser") BankUserInput bankUser, Model model) {
		BankUser b = new BankUser();
		b.setInputModelValues(bankUser);
		List<BankUserViewModel> bankUsers;
		if (bankUser.getBusr_desg().equals("All")) {
			bankUsers = v.getAllBankUsers();
		} else {
			bankUsers = v.getBankUserByDesignation(b);
		}
		model.addAttribute("bankUsers", bankUsers);
		return "bankUsers"; // Return the name of the HTML page as the view
	}

}