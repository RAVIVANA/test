package com.nkxgen.spring.jdbc.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nkxgen.spring.jdbc.model.UserCredentials;

@Component
public class UserCredentialsDAOImpl implements UserCredentialsDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public boolean userCredentialsCheck(String username, String password) {
		String queryString = "SELECT uc FROM UserCredentials uc WHERE uc.username = :username AND uc.password = :password";
		TypedQuery<UserCredentials> query = entityManager.createQuery(queryString, UserCredentials.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		return query.getResultList().size() > 0;
	}
}
