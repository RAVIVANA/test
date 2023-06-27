package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import com.nkxgen.spring.jdbc.model.LoanAccount;
import com.nkxgen.spring.jdbc.model.LoanApplication;
import com.nkxgen.spring.jdbc.model.LoanApplicationInput;

public interface LoanApplicationDaoInterface {
	void saveLoanApplication(LoanApplication loanApplication);

	void updateLoanApplication(LoanApplicationInput loanApplication);

	List<LoanApplication> findByType(String value);

	List<LoanApplication> getLoanApplicationsByStatus(String status);

	List<LoanAccount> getLoanAccountsByLoanType(String loanType);

	void delete_Application(int id);

	List<LoanAccount> getAllLoans();

	void approve_Application(int loanId, long custId);
}
