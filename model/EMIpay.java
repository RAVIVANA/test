package com.nkxgen.spring.jdbc.model;

public class EMIpay {
	private long loanid;
	private String accountHolder;
	private String loanType;
	private long loanamount;
	private int loanduration;
	private int NOI;
	private double interestRate;
	private int loan_pending;
	private int paidMonths;
	private double emi;
	private double interest;
	private double total;
	private double complete;

	public EMIpay() {

	}

	public long getLoanId() {
		return loanid;
	}

	public void setLoanId(long loanid) {
		this.loanid = loanid;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public int getLoanduration() {
		return loanduration;
	}

	public void setLoanduration(int loanduration) {
		this.loanduration = loanduration;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public long getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(long loanamount) {
		this.loanamount = loanamount;
	}

	public int getNOI() {
		return NOI;
	}

	public void setNOI(int NOI) {
		this.NOI = NOI;
	}

	public int getLoan_pending() {
		return loan_pending;
	}

	public void setLoan_pending(int loan_pending) {
		this.loan_pending = loan_pending;
	}

	public int getPaidMonths() {
		return paidMonths;
	}

	public void setPaidMonths(int paidMonths) {
		this.paidMonths = paidMonths;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getComplete() {
		return complete;
	}

	public void setComplete(double complete) {
		this.complete = complete;
	}
}