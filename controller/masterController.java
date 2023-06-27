package com.nkxgen.spring.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nkxgen.spring.jdbc.Bal.Accounts;
import com.nkxgen.spring.jdbc.Bal.ViewInterface;
import com.nkxgen.spring.jdbc.Dao.AccountTypeInterface;
import com.nkxgen.spring.jdbc.Dao.LoanTypesInterface;
import com.nkxgen.spring.jdbc.model.AccountTypeInput;
import com.nkxgen.spring.jdbc.model.AccountTypeView;
import com.nkxgen.spring.jdbc.model.LoanTypeInput;
import com.nkxgen.spring.jdbc.model.LoanViewModel;
import com.nkxgen.spring.jdbc.model.LoansTypes;
import com.nkxgen.spring.jdbc.model.accountTypes;
import com.nkxgen.spring.jdbc.model.cashChest;

@Controller
public class masterController {

	@Autowired
	private AccountTypeInterface account;
	@Autowired
	ViewInterface v;
	@Autowired
	private Accounts ac1;
	@Autowired
	private LoanTypesInterface loan;

	@RequestMapping(value = "/accountdatasave", method = RequestMethod.POST)
	public String accountTypeSaveToDb(AccountTypeInput accountTypes, Model model) {
		accountTypes at = new accountTypes();
		at.setInputModelValues(accountTypes);
		account.saveAccountTypes(at);
		return "account_master_entry";
	}

	@RequestMapping(value = "/getaccounttypes", method = RequestMethod.GET)
	public String getAccountsTypes(Model model) {
		List<AccountTypeView> list = v.getAllAccounts();
		for (AccountTypeView ll : list) {
			System.out.println(ll.getAccountType());
		}
		model.addAttribute("accountTypes", list);
		return "getaccounts";
	}

	@RequestMapping(value = "/getselectedaccountdetails", method = RequestMethod.GET)
	public String getSelectedAccountDetails(@RequestParam("accountType") int accountType, Model model) {

		AccountTypeView accountT = v.getSelectedAccountDetails(accountType);
		System.out.println("the iid value is :" + accountT.getAccountType());
		System.out.println("the iid value is :" + accountT.getDescriptionForm());
		model.addAttribute("accounts", accountT);
		return "accountdetails";
	}
	// =============================================================================================

	@RequestMapping(value = "/loandatasave", method = RequestMethod.POST)
	public String loanApplicationSaveToDb(LoanTypeInput loans, Model model) {
		LoansTypes lt = new LoansTypes();
		lt.setInputModelValues(loans);
		System.out.println("hello");
		loan.saveLoansTypes(lt);
		return "loan_master_entry";
	}

	@RequestMapping(value = "/getloantypes", method = RequestMethod.GET)
	public String getLoansTypes(Model model) {
		List<LoanViewModel> list = v.getAllLoans();
		for (LoanViewModel ll : list) {
			System.out.println(ll.getLoanType());
		}
		model.addAttribute("loans", list);
		return "getloans";
	}

	@RequestMapping(value = "/getselectedloandetails", method = RequestMethod.GET)
	public String getSelectedLoanDetails(@RequestParam("loanType") int loanType, Model model) {
		System.out.println("the value is" + loanType);

		LoanViewModel loank = v.getSelectedLoanDetails(loanType);

		System.out.println("the iid value is :" + loank.getLoanId());
		model.addAttribute("loans", loank);
		return "loandetails";
	}

	// =================================================================================================================

	@RequestMapping("/masterloan")
	public String getLoanDashoard(Model model) {
		return "loan_master_entry";
	}

	@RequestMapping(value = "/cashchest", method = RequestMethod.GET)
	public String setMoneyToCashChest(Model model) {
		cashChest cashchest = account.getAllAmount();
		ac1.setcashChest(cashchest);
		model.addAttribute("cashChest", cashchest);
		return "cashchest";
		
	}

}