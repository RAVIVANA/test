package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkxgen.spring.jdbc.model.BankUser;

@Service
public class BankUserService implements BankUserInterface {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public boolean saveBankUser(BankUser bankUser) {
		entityManager.persist(bankUser);
		return true;
	}

	public BankUser getBankUserById(long busr_id) {
		return entityManager.find(BankUser.class, busr_id);
	}

	public List<BankUser> getAllBankUsers() {
		return entityManager.createQuery("SELECT u FROM BankUser u ORDER BY u.busr_id ASC").getResultList();
	}

	@Transactional
	public BankUser saveUser(BankUser bankUser) {
		return entityManager.merge(bankUser);
	}

	@Transactional
	public List<BankUser> getBankUsersByDesignation(String designation) {
		return entityManager.createQuery("SELECT bu FROM BankUser bu WHERE bu.busr_desg = :designation", BankUser.class)
				.setParameter("designation", designation).getResultList();
	}

	@Transactional
	public List<BankUser> getBankUsersByDesignation(BankUser bankUser) {
		return entityManager.createQuery("SELECT bu FROM BankUser bu WHERE bu.busr_desg = :designation", BankUser.class)
				.setParameter("designation", bankUser.getBusr_desg()).getResultList();
	}

}