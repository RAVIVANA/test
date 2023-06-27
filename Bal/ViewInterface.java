package com.nkxgen.spring.jdbc.Bal;

import java.util.List;

import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.AccountApplicationViewModel;
import com.nkxgen.spring.jdbc.model.AccountTypeView;
import com.nkxgen.spring.jdbc.model.AccountViewModel;
import com.nkxgen.spring.jdbc.model.BankUser;
import com.nkxgen.spring.jdbc.model.BankUserInput;
import com.nkxgen.spring.jdbc.model.BankUserViewModel;
import com.nkxgen.spring.jdbc.model.Customer;
import com.nkxgen.spring.jdbc.model.LoanAccountViewModel;
import com.nkxgen.spring.jdbc.model.LoanApplicationViewModel;
import com.nkxgen.spring.jdbc.model.LoanViewModel;

public interface ViewInterface {

	 List<LoanAccountViewModel> setLoanAccountEntityModelToViewModel(String typee);

	    AccountViewModel getAccountById(int act);

	    List<AccountViewModel> setAccountEntityModelToViewModel(List<Account> typee);

	    List<LoanApplicationViewModel> getLoanAppplicationByStatus(String typee);

	    List<AccountApplicationViewModel> getAccountAppplicationByType(String typee);

	    List<LoanApplicationViewModel> getLoanAppplicationByType(String typee);

	    List<AccountViewModel> getAccountByType(String typee);

	    List<BankUserViewModel> getAllBankUsers();

	    BankUser getBankUserById(BankUserInput status);

	    List<BankUserViewModel> getBankUserByDesignation(BankUser status);

	    List<BankUserViewModel> getBankUserByDesignation(String status);

	    LoanViewModel getSelectedLoanDetails(int status);

	    List<LoanViewModel> getAllLoans();

	    AccountTypeView getSelectedAccountDetails(int status);

	    List<AccountTypeView> getAllAccounts();

	    List<Account> checkdate(List<Account> l);

	    List<Account> checkdates(List<Account> l);

	    List<Customer> getAllCustomers();

	    LoanAccountViewModel getLoanAccountById(int accountnumber);
}
