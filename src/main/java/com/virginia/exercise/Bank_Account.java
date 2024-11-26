package com.virginia.exercise;

public class Bank_Account {
	private String AccountNumber;
	private String AccountName;
	private float  AccountBalance;
	
	public Bank_Account(String accountNumber, String accountName, float accountBalance) {
		super();
		this.AccountNumber = accountNumber;
		this.AccountName = accountName;
		this.AccountBalance = accountBalance;
	}
	
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	public float getAccountBalance() {
		return AccountBalance;
	}
	public void setAccountBalance(float accountBalance) {
		AccountBalance = accountBalance;
	}
	
	public String displayAccountBalance() {
		return this.toString();
	}
	
	public float deposit(float v) {
		this.AccountBalance+=v;
		
		return this.AccountBalance;
	}
	
	public float withdraw(float v) {
		if(v>this.AccountBalance) {
			return -1;
		}
		
		this.AccountBalance-=v;
		return this.AccountBalance;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder(this.AccountNumber)
				.append(" ").append(this.AccountName)
				.append(" balance = ").append(this.AccountBalance).toString();
	}
}
