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
import com.nkxgen.spring.jdbc.Dao.AccountApplicationDaoInterface;
import com.nkxgen.spring.jdbc.Dao.AccountProcessingDAO;
import com.nkxgen.spring.jdbc.Dao.AccountProcessingDAOimp;
import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.AccountViewModel;
import com.nkxgen.spring.jdbc.model.LoanTransactions;
import com.nkxgen.spring.jdbc.model.Transaction;

@Controller
public class AccountProcessingController {
	@Autowired
	private AccountApplicationDaoInterface ac;
	@Autowired
	private ViewInterface v;
	@Autowired
	private Accounts ac1;
	@Autowired
	private AccountProcessingDAO interestCalDao;

	@RequestMapping(value = "/intrest", method = RequestMethod.GET)
	public String calculateInterest(Model model) {
		List<Account> list = ac.getall();
		List<Account> list1 = ac1.calcIntrst(list);
		for (Account aa : list1) {
			ac.mergeAccount(aa);
		}
		List<Account> list3 = ac.getall();
		model.addAttribute("accounts", list3);
		return "New2";
	}

	@RequestMapping(value = "/getsavingsaccounts", method = RequestMethod.GET)
	public String getIntrest(Model model) {
		return "Accounttypes";
	}

	@RequestMapping(value = "/getaccounts", method = RequestMethod.GET)
	public String getAccounts(@RequestParam("accountType") String accountType, Model model) {
		List<Account> l = ac.getAccountssByType(accountType);
		List<Account> l1 = v.checkdate(l);
		model.addAttribute("accounts", l1);
		return "New2";
	}

	@RequestMapping(value = "/getsavings", method = RequestMethod.GET)
	public String getSavingsAccounts(@RequestParam("accountType") String accountType, Model model) {
		List<Account> l = ac.getAccountssByType(accountType);
		List<Account> l1 = v.checkdates(l);
		List<AccountViewModel> l2 = v.setAccountEntityModelToViewModel(l1);
		model.addAttribute("accounts", l2);
		return "New2";
	}

	@RequestMapping(value = "/caluclateintrest", method = RequestMethod.GET)
	public String getAccountsData(@RequestParam("accountType") String accountType, Model model) {
		if (accountType.equals("savings")) {
			List<Account> l = ac.getAccountssByType(accountType);
			List<Account> l1 = v.checkdates(l);
			List<Account> l2 = interestCalDao.getSavingsAcc(l1);
			List<AccountViewModel> l3 = v.setAccountEntityModelToViewModel(l1);
			model.addAttribute("accounts", l3);

		} else {
			List<Account> l = ac.getAccountssByType(accountType);
			List<Account> l1 = v.checkdate(l);
			List<Account> list1 = ac1.calcIntrst(l1);
			for (Account aa : list1) {
				ac.mergeAccount(aa);
			}
			List<Account> list3 = ac.getall();
			model.addAttribute("accounts", list3);
		}
		return "New2";
	}

	@RequestMapping(value = "/statementpage", method = RequestMethod.POST)
	public String getStatementPage() {
		return "statementpage";
	}

	@RequestMapping(value = "/statement", method = RequestMethod.GET)
	public String generateStatement(@RequestParam("accountId") int accountId,@RequestParam("type") String type, Model model) {
		// Generate a statement for the specific account
		// Implementation logic goes here
		String statement = "Statement for Account " + accountId;
		System.out.println(statement);
		// Generate the statement contents
		// ...
		if(type.equals("accounts")) {
		List<Transaction> st = interestCalDao.statementofAccounts(accountId);
		System.out.println(st);
		model.addAttribute("statementlist", st);
		return "stlist";
		}
		else  {
			List<LoanTransactions> st = interestCalDao.statementofLoan(accountId);
			System.out.println(st);
			model.addAttribute("loanTransactions", st);
		    return "Stmt2";
		}
	}

}