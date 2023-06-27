package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.LoanTransactions;
import com.nkxgen.spring.jdbc.model.Transaction;

public interface AccountProcessingDAO {

	public List<Account> getSavingsAcc(List<Account> acctype);

	public void executeProcedure(int accno);

	public List<Transaction> statementofAccounts(int accno);
	public List<LoanTransactions> statementofLoan(int accno);
}
