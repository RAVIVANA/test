package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import com.nkxgen.spring.jdbc.model.accountTypes;
import com.nkxgen.spring.jdbc.model.cashChest;

public interface AccountTypeInterface {
	List<accountTypes> getAllAccounts();

	List<accountTypes> getAllAccountDetails();

	accountTypes getSelectedAccountDetails(int accountType);

	void saveAccountTypes(accountTypes accountTypes);

	cashChest getAllAmount();

	// void setTheLoanRepay(tempRepayment amount, LoanAccount loan);
}
