package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nkxgen.spring.jdbc.model.Customer;
import com.nkxgen.spring.jdbc.model.CustomerSub;
import com.nkxgen.spring.jdbc.model.Customertrail;
import com.nkxgen.spring.jdbc.model.service;

@Repository
@Transactional
public class CustomerDao implements CustomerDaoInterface {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private service s;

	@Override
	public void save_Trail(Customertrail customer) {
		entityManager.merge(customer);

	}

	public Customertrail getCustomerById(Long id) {
		// Retrieve customer data from the table based on the ID
		Customertrail customer = entityManager.find(Customertrail.class, id);
		return customer;
	}

	public void saveCustomer(Customer customer) {
		entityManager.persist(customer);
	}

	public List<Customertrail> getAllCustomers() {
		// TODO Auto-generated method stub
		String jpql = "SELECT la FROM Customertrail la";
		TypedQuery<Customertrail> query = entityManager.createQuery(jpql, Customertrail.class);
		return query.getResultList();
	}

	public void updateCustomerDataById(Customertrail updatedCustomer) {

		entityManager.merge(updatedCustomer);

	}

	public void deleteCustomerById(Customertrail cus) {
		// TODO Auto-generated method stub
		// Assuming Customertrail class has an 'id' property

		long customerId = cus.getId();

		Customertrail customer = entityManager.find(Customertrail.class, customerId);

		if (customer != null) {
			entityManager.remove(customer);
			System.out.println("Customer with ID " + customerId + " has been deleted successfully.");
		} else {
			System.out.println("Customer with ID " + customerId + " does not exist.");
		}
	}

	public Customertrail getRealCustomerById(Long customerId) {
		Customertrail customer = entityManager.find(Customertrail.class, customerId);
		return customer;
	}

	@Override
	public void changethese(Customertrail customer2, CustomerSub customerSub) {
		Customertrail customer = s.changing(customer2, customerSub);
		entityManager.merge(customer);

	}

}