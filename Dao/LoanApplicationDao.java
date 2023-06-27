package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nkxgen.spring.jdbc.model.Customer;
import com.nkxgen.spring.jdbc.model.Customertrail;
import com.nkxgen.spring.jdbc.model.LoanAccount;
import com.nkxgen.spring.jdbc.model.LoanApplication;
import com.nkxgen.spring.jdbc.model.LoanApplicationInput;
import com.nkxgen.spring.jdbc.model.service;

@Repository
@Transactional
public class LoanApplicationDao implements LoanApplicationDaoInterface {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	service s;

	@Override
	public List<LoanApplication> findByType(String value) {
		String jpql = "SELECT la FROM LoanApplication la WHERE la.loanTypeId = :value and la.status=:val";
		TypedQuery<LoanApplication> query = entityManager.createQuery(jpql, LoanApplication.class);
		query.setParameter("value", value);
		query.setParameter("val", "waiting");

		return query.getResultList();
	}

	@Override
	public void saveLoanApplication(LoanApplication loanApplication) {
		entityManager.persist(loanApplication);
	}

	@Override
	public void updateLoanApplication(LoanApplicationInput loanApplication) {
		LoanApplication la = entityManager.find(LoanApplication.class, loanApplication.getId());
		la.LoanApplication(loanApplication);
		entityManager.merge(la);
	}

	@Override
	public List<LoanApplication> getLoanApplicationsByStatus(String status) {
		String jpql = "SELECT la FROM LoanApplication la WHERE la.status = :status";
		TypedQuery<LoanApplication> query = entityManager.createQuery(jpql, LoanApplication.class);
		query.setParameter("status", status);
		return query.getResultList();
	}

	@Override
	public List<LoanAccount> getLoanAccountsByLoanType(String loanType) {
		String queryStr = "SELECT la FROM LoanAccount la WHERE la.loanType = :loanType";
		TypedQuery<LoanAccount> query = entityManager.createQuery(queryStr, LoanAccount.class);
		query.setParameter("loanType", loanType);
		return query.getResultList();
	}

	@Override
	public void delete_Application(int loanId) {
		LoanApplication loanApplication = entityManager.find(LoanApplication.class, loanId);
		if (loanApplication != null) {
			entityManager.remove(loanApplication);
		}

	}

	@Override
	public void approve_Application(int loanId, long custId) {
		LoanApplication loanApplication = entityManager.find(LoanApplication.class, loanId);
		Customertrail customer = entityManager.find(Customertrail.class, custId);
		LoanAccount l = new LoanAccount();
		l.setValuesFromLoanAccount(loanApplication);
		entityManager.persist(l);
	}
	
	public List<LoanAccount> getAllLoans() {
		String jpql = "SELECT l FROM LoanAccount l";
		TypedQuery<LoanAccount> query = entityManager.createQuery(jpql, LoanAccount.class);
		return query.getResultList();
	}
}
