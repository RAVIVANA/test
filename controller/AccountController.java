package com.nkxgen.spring.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nkxgen.spring.jdbc.Bal.ViewInterface;
import com.nkxgen.spring.jdbc.Dao.AccountApplicationDaoInterface;
import com.nkxgen.spring.jdbc.Dao.CustomerDaoInterface;
import com.nkxgen.spring.jdbc.events.AccountAppApprovalEvent;
import com.nkxgen.spring.jdbc.events.AccountAppRequestEvent;
import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.AccountApplication;
import com.nkxgen.spring.jdbc.model.AccountApplicationInput;
import com.nkxgen.spring.jdbc.model.AccountApplicationViewModel;
import com.nkxgen.spring.jdbc.model.AccountDocumentInput;
import com.nkxgen.spring.jdbc.model.AccountInput;
import com.nkxgen.spring.jdbc.model.AccountViewModel;
import com.nkxgen.spring.jdbc.model.Accountdocument;
import com.nkxgen.spring.jdbc.model.Customertrail;
import com.nkxgen.spring.jdbc.model.Types;

@Controller
public class AccountController {

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private AccountApplicationDaoInterface ac;
	@Autowired
	ViewInterface v;

	AccountController(AccountApplicationDaoInterface ac) {
		this.ac = ac;
	}

	@Autowired
	private CustomerDaoInterface cd;

	@RequestMapping("/masteraccount")
	public String masterAccount(Model model) {
		return "account_master_entry";
	}

	@RequestMapping(value = "/Newaccountapplication", method = RequestMethod.POST)
	public String newAccountApplication(@Validated Types t, Model model) {
		String value = t.gethrefvalue();
		List<AccountApplicationViewModel> list1 = v.getAccountAppplicationByType(value);

		// Check if the list is not empty before accessing the first object
		if (!list1.isEmpty()) {
			AccountApplicationViewModel firstAccount = list1.get(0);
			String acapActyId = firstAccount.getAcap_acty_id();
			System.out.println("acap_acty_id of first object: " + acapActyId);
		}
		System.out.println("hello");
		model.addAttribute("list_of_account_applications", list1);
		return "New_account_application";
	}

	// =====================================================================================
	@RequestMapping(value = "/AnyTypeaccountinfo", method = RequestMethod.POST)
	public String view_accounts(@Validated Types t, Model model) {
		String value = t.gethrefvalue();
		System.out.println(value);
		List<AccountViewModel> list1 = v.getAccountByType(value);
		List<Customertrail> list2 = new ArrayList<>();
		for (AccountViewModel account : list1) {
			Customertrail customer = cd.getRealCustomerById(account.getCustomerId());
			list2.add(customer);
		}
		model.addAttribute("list_of_account", list1);
		model.addAttribute("list_of_customer", list2);
		return "Any_Type_account_info";
	}

	// ===============================================================================================
	@RequestMapping("/result")
	public String ne(Model model) {
		return "New_account_application";
	}

	// ===========================================================================================
	@RequestMapping("/Accountnewapplicationform")
	public String accountNewApplicationForm(Model model) {
		return "Account_new_application_form";
	}

	// ===========================================================================================
	@RequestMapping("/AnyTypeaccountinfo")
	public String anyTypeAccountInfo(Model model) {
		return "Any_Type_account_info";
	}
	// ===========================================================================================

	@RequestMapping(value = "/accountapplicationsave", method = RequestMethod.POST)
	public String saveAccountApplication(@Validated AccountApplicationInput accountApplication,
			HttpServletRequest request) {
		AccountApplication account = new AccountApplication();
		account.setInputModelValues(accountApplication);
		ac.save(account);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		applicationEventPublisher.publishEvent(new AccountAppRequestEvent("New Application Form Filled", username));
		return "Account_new_application_form";
	}
	// ===========================================================================================

	// ===================================================================================================
	@RequestMapping(value = "/savetoaccountdatabase", method = RequestMethod.POST)
	public String saveToAccountDatabase(@Validated AccountInput account, Model model, HttpServletRequest request) {
		Account a = new Account();
		a.setInputModelValues(account);
		ac.saveAccount(a);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		applicationEventPublisher.publishEvent(new AccountAppApprovalEvent("Account Application Approved", username));
		// change the return
		return "Account_new_application_form";
	}

	@RequestMapping(value = "/savetoaccountdocumentsdatabase", method = RequestMethod.POST)
	public String saveToAccountDocumentsDatabase(@Validated AccountDocumentInput accountdocument, Model model) {
		Accountdocument ad = new Accountdocument();
		ad.setInputModelValues(accountdocument);

		ac.saveAccountdocument(ad);
		// change the return
		return "Account_new_application_form";
	}
	// // ======================================================================================================

}
