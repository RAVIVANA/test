package com.nkxgen.spring.jdbc.Dao;
import com.nkxgen.spring.jdbc.model.*;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class LoanTypesDAO implements LoanTypesInterface {

	@PersistenceContext
	private EntityManager entityManager;

	public List<LoansTypes> getAllLoans() {
		String jpql = "SELECT l FROM LoansTypes l";
		TypedQuery<LoansTypes> query = entityManager.createQuery(jpql, LoansTypes.class);
		return query.getResultList();
	}

	public List<LoansTypes> getAllLoanDetails() {
		String jpql = "SELECT l FROM LoansTypes l";
		TypedQuery<LoansTypes> query = entityManager.createQuery(jpql, LoansTypes.class);
		return query.getResultList();
	}

	public  LoansTypes getSelectedLoanDetails(int loanType) {
		LoansTypes loan = entityManager.find(LoansTypes.class, loanType);
		System.out.println("im in the dao of loan types");
		System.out.println(loan.getLoanType());
		System.out.println(loan.getDescriptionForm());

		return loan;
		
	}
	
	public void saveLoansTypes(LoansTypes LoansTypes) {
		entityManager.merge(LoansTypes);
	}

}
