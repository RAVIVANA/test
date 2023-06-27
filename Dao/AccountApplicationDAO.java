package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nkxgen.spring.jdbc.model.Account;
import com.nkxgen.spring.jdbc.model.AccountApplication;
import com.nkxgen.spring.jdbc.model.Accountdocument;

@Repository
@Transactional
public class AccountApplicationDAO implements AccountApplicationDaoInterface {
	AccountApplicationDAO() {

	}

	@PersistenceContext
	private EntityManager entityManager;

	public void save(AccountApplication accountApplication) {
		entityManager.persist(accountApplication);
	}

	public List<AccountApplication> getAccountsappByType(String value) {
		System.out.println("neeraj function loki vacahav repository lo : " + value);

		String jpql = "SELECT la FROM AccountApplication la WHERE la.acap_acty_id = :value";
		TypedQuery<AccountApplication> query = entityManager.createQuery(jpql, AccountApplication.class);
		query.setParameter("value", value);

		List<AccountApplication> list1 = query.getResultList();

		if (!list1.isEmpty()) {
			AccountApplication firstAccount = list1.get(0);
			String acapActyId = firstAccount.getAcap_acty_id();
			System.out.println("acap_acty_id of first object: " + acapActyId);
		}

		return list1;
	}

	public List<Account> getAccountsByType(String acnt_acty_id) {
		String jpql = "SELECT la FROM Account la WHERE la.accountTypeId = :acnt_acty_id";
		TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
		query.setParameter("acnt_acty_id", acnt_acty_id);
		return query.getResultList();
	}

	public List<Account> getAccountssByType(String acnt_acty_id) {
		String jpql = "SELECT la FROM Account la WHERE la.accountTypeId = :acnt_acty_id";
		TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
		query.setParameter("acnt_acty_id", acnt_acty_id);
		return query.getResultList();
	}

	public void saveAccount(Account account) {
		Account mergedAccount = entityManager.merge(account);
		entityManager.persist(mergedAccount);

	}

	public void saveAccountdocument(Accountdocument accountdocument) {
		Accountdocument mergedAccount = entityManager.merge(accountdocument);
		entityManager.persist(mergedAccount);
	}

	@Override
	public List<Account> getall() {
		String query = "SELECT l FROM Account l";
		TypedQuery<Account> query1 = entityManager.createQuery(query, Account.class);
		return query1.getResultList();
	}

	public Account mergeAccount(Account account) {
		return entityManager.merge(account);
	}
	// public String gettheid(String inputValue) {
	// String jpql = "SELECT e.id FROM AccountType e WHERE e.value = :inputValue";
	// TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
	// query.setParameter("inputValue", inputValue);
	// return query.getSingleResult();
	// }

}
