package com.virginia.exercise;

public class BankAccount_Main {
	public static void main(String[] args) {
		Bank_Account account = new Bank_Account("Account01", "Jason",1000.0f);
		System.out.println(account.displayAccountBalance());
		
		account.deposit(100.0f);
		System.out.println(account.displayAccountBalance());
		
		float ret = account.withdraw(500.0f);
		if(ret<0) {
			System.out.println("*** with draw error!");
		}
		System.out.println(account.displayAccountBalance());
		
		ret = account.withdraw(500.0f);
		if(ret<0) {
			System.out.println("*** with draw error!");
		}
		System.out.println(account.displayAccountBalance());
		
		ret = account.withdraw(500.0f);
		if(ret<0) {
			System.out.println("*** with draw error!");
		}
		System.out.println(account.displayAccountBalance());
		
	}
}
