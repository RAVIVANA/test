package com.nkxgen.spring.jdbc.model;

public class tempRepayment {
	private int loanid;
	private double emi;
	private double interest;
	private double total;
	private double amount;
	private double complete;
	private String Date;
	private int installment_no;
	
	public tempRepayment() {

	}

	 public String getDate() {
		 return Date;
	 }
	 
	 public void setDate(String Date) {
		 this.Date=Date;
	 }
	  
	 public int getinstallment_no() {
		 return installment_no;
	 }
	 
	 public void setinstallment_no(int installment_no) {
		 this.installment_no=installment_no;
	 }
	

	public int getLoanid() {
		return loanid;
	}

	public void setLoanid(int loanid) {
		this.loanid = loanid;
	}

	public double getEMI() {
		return emi;
	}

	public void setEMI(double emi) {
		this.emi = emi;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterset(double interest) {
		this.interest = interest;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getComplete() {
		return complete;
	}

	public void setComplete(double complete) {
		this.complete = complete;
	}

}