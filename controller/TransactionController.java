package com.nkxgen.spring.jdbc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nkxgen.spring.jdbc.Dao.TransactionsInterface;
import com.nkxgen.spring.jdbc.events.TransactionEvent;
import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.Customertrail;
import com.nkxgen.spring.jdbc.model.EMIpay;
import com.nkxgen.spring.jdbc.model.LoanAccount;
import com.nkxgen.spring.jdbc.model.LoanTransactions;
import com.nkxgen.spring.jdbc.model.Transaction;
import com.nkxgen.spring.jdbc.model.service;
import com.nkxgen.spring.jdbc.model.tempRepayment;
import com.nkxgen.spring.jdbc.model.transactioninfo;;

@Controller
public class TransactionController {

	@Autowired
	TransactionsInterface ti;

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private service s;

	@RequestMapping(value = "/moneydeposit", method = RequestMethod.GET)
	public String getMoneyDepositeForm(Model model) {
		return "Money_Deposit";
	}

	@RequestMapping(value = "/loanrepay", method = RequestMethod.GET)
	public String getLoanRepayMentForm(Model model) {
		return "Loan_Repayment";
	}

	@RequestMapping(value = "/interest", method = RequestMethod.GET)
	public String getIntrestDepositeForm(Model model) {
		return "Interest_Deposit";
	}

	@RequestMapping(value = "/withdrawl", method = RequestMethod.GET)
	public String geMoneyWithDrawForm(Model model) {
		return "money_withdrawl_form";
	}

	@RequestMapping(value = "/lowid", method = RequestMethod.GET)
	public String getLoanWithDrawForm(Model model) {
		return "loan_withdrawl_form";
	}
	// =================================================================================

	// money_deposit
	@RequestMapping(value = "/getaccountdetails", method = RequestMethod.POST)
	public String getAccountDetails(@RequestParam("accountNumber") int Acnt_id, Model model) {
		Account account = ti.getAccountById(Acnt_id);
		model.addAttribute("account", account);

		return "sub_money_deposit";
	}

	// sub_money_deposit
	@RequestMapping(value = "/moneyDepositurl")
	public ResponseEntity<String> getDepositMoney(@Validated transactioninfo tarn, Model model,
			HttpServletRequest request) {
		ti.moneyDeposit(tarn);
		Transaction t = ti.transactionSave1(tarn);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		applicationEventPublisher.publishEvent(new TransactionEvent("Money Deposited ", username));
		ti.saveTransaction(t);

		return ResponseEntity.ok("deposit sucessfully");
	}

	// money_withdrawl
	@RequestMapping(value = "/getaccountdetailsmoneyWithdrawl", method = RequestMethod.POST)
	public String getaccountdetailsmoneyWithdrawl(@RequestParam("accountNumber") int Acnt_id, Model model) {
		Account account = ti.getAccountById(Acnt_id);
		model.addAttribute("account", account);
		return "sub_money_withdrawl";
	}

	// sub_money_withdrawl
	@RequestMapping(value = "/moneywithdrawlurl")
	public ResponseEntity<String> getwithdrawlAmount(@Validated transactioninfo tarn, Model model,
			HttpServletRequest request) {
		ti.moneyWithdrawl(tarn);
		Transaction t = ti.transactionSave(tarn);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		applicationEventPublisher.publishEvent(new TransactionEvent("Money Withdrawed ", username));
		ti.saveTransaction(t);
		return ResponseEntity.ok("Money withdrawl Successfully");
	}

	// =====================================================================================================================

	// loan_withdrawl

	// loan_withdrawl
	@RequestMapping(value = "/getloandetails", method = RequestMethod.POST)
	public String getloandetails(@RequestParam("accountNumber") long loan_id, Model model) {
		long acnt_id = loan_id;
		LoanAccount account = ti.getLoanAccountById(acnt_id);
		Customertrail customer = ti.getCustomerByLoanID(account.getCustomerId().getId());
		System.out.println("the value is" + account.getdeductionAmt());
		if (account.getdeductionAmt() == 0) {
			model.addAttribute("account", account);
			model.addAttribute("customerss", customer);
			return "sub_loan_withdrawl";
		} else {
			return null;
		}
	}

	// // sub_loan_withdrawl
	//
	@RequestMapping(value = "/loanwithdrawlurl", method = RequestMethod.POST)
	public ResponseEntity<String> getloanwithdrawlAmount(@Validated transactioninfo tarn, Model model,
			HttpServletRequest request) {
		ti.loanWithdrawl(tarn.getAccountNumber());
		tempRepayment temp = s.setthistarn(tarn);
		LoanTransactions t = ti.LoanTransactionw(temp);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		applicationEventPublisher.publishEvent(new TransactionEvent("Loan Withdrawed ", username));
		ti.saveLoanTransaction(t);
		return ResponseEntity.ok("Loan withdrawl Successfully");
	}

	// loan_repayment
	@RequestMapping(value = "/getloanrepaytdetails", method = RequestMethod.POST)
	public String getloanrepaytdetails(@RequestParam("accountNumber") long loan_id, Model model) {

		LoanAccount account = ti.getLoanAccountById(loan_id);
		System.out.println("print the value: " + account.getInterestRate());
		if (account.getdeductionAmt() != 0) {
			EMIpay emiobj = ti.changeToEMI(account);
			model.addAttribute("account", emiobj);
			return "sub_loan_repayment.html";
		} else {
			return null;
		}

	}

	// sub_loan_repayment
	@RequestMapping(value = "/loanrepaymenturl")
	public ResponseEntity<String> loanrepaymenturl(@Validated tempRepayment tarn, Model model,
			HttpServletRequest request) {
		System.out.println("the value of tar complete :" + tarn.getComplete());
		ti.loanRepayment(tarn);
		LoanTransactions t = ti.LoanTransaction(tarn);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		applicationEventPublisher.publishEvent(new TransactionEvent("Loan repayed ", username));
		ti.saveLoanTransaction(t);
		return ResponseEntity.ok("Loan Repayed Successfully ");
	}

}