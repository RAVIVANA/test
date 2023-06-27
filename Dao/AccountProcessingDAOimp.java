package com.nkxgen.spring.jdbc.Dao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.LoanTransactions;
import com.nkxgen.spring.jdbc.model.Transaction;

// ...

@Repository
@Transactional
public class AccountProcessingDAOimp implements AccountProcessingDAO {

	@PersistenceContext
	public EntityManager em;

	@Transactional
	public List<Account> getSavingsAcc(List<Account> acctype) {

		for (Account i : acctype) {
			System.out.println("inside");
			long acc = i.getApplicationId();
			String sql = "CALL smi(:acc)";
			Query query1 = em.createNativeQuery(sql);
			query1.setParameter("acc", acc);

			query1.executeUpdate();

			// System.out.println(acc);
			// StoredProcedureQuery query1 = em.createStoredProcedureQuery("smi");
			// query1.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			// query1.setParameter(1, acc);
			//
			// query1.execute();
		}
		LocalDate currentDate = LocalDate.now();

		String dateString1 = currentDate.toString();
		for (Account a : acctype) {
			a.setLastUpdate(dateString1);
		}

		return acctype;
	}

	@Override
	@Transactional
	public void executeProcedure(int acc) {
		System.out.println("procedure");
		System.out.println(acc);

		StoredProcedureQuery query = em.createStoredProcedureQuery("smi");
		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		query.setParameter(1, acc);

		query.execute();

	}

	@Override
	public List<Transaction> statementofAccounts(int accno) {
		LocalDateTime endDate = LocalDateTime.now();
		Timestamp tran_date1 = Timestamp.valueOf(endDate);
		String tran_date = tran_date1.toString();
		String jpql = "SELECT bt FROM Transaction bt WHERE bt.tran_anct_id = :accno";
		TypedQuery<Transaction> query = em.createQuery(jpql, Transaction.class);
		query.setParameter("accno", accno);
		return query.getResultList();
	}

	@Override
	public List<LoanTransactions> statementofLoan(int accno) {
		LocalDateTime endDate = LocalDateTime.now();
		Timestamp tran_date1 = Timestamp.valueOf(endDate);
		String tran_date = tran_date1.toString();
		String jpql = "SELECT bt FROM LoanTransactions bt WHERE bt.loanId = :accno";
		TypedQuery<LoanTransactions> query = em.createQuery(jpql, LoanTransactions.class);
		query.setParameter("accno", accno);		
		return  query.getResultList();
	}

}
