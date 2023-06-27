package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import com.nkxgen.spring.jdbc.model.Customer;
import com.nkxgen.spring.jdbc.model.CustomerSub;
import com.nkxgen.spring.jdbc.model.Customertrail;

public interface CustomerDaoInterface {

	void save_Trail(Customertrail customer);

	Customertrail getCustomerById(Long id);

	void saveCustomer(Customer customer);

	List<Customertrail> getAllCustomers();

	void updateCustomerDataById(Customertrail updatedCustomer);

	void deleteCustomerById(Customertrail cus);

	Customertrail getRealCustomerById(Long id);

	void changethese(Customertrail customer2, CustomerSub customerSub);

}
