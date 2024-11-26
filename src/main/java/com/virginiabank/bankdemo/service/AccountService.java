package com.virginiabank.bankdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virginiabank.bankdemo.model.Account;
import com.virginiabank.bankdemo.model.BankAccountInfo;
import com.virginiabank.bankdemo.repository.AccountRepository;
import com.virginiabank.bankdemo.repository.BankAccountInfoRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankAccountInfoRepository bankAccountInfoRepository;

    public Account createAccount(String customerName, Double initialBalance) {
        Account account = new Account(customerName, initialBalance);
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
    	return accountRepository.findById(id);
    }
    
    public Optional<BankAccountInfo> getAccount(String id) {
          return bankAccountInfoRepository.findById(id);
      }

    @Transactional
    public void deposit(Long id, Double amount) {
//        Account account = accountRepository.findById(id).orElseThrow();
//        account.setBalance(account.getBalance() + amount);
//        accountRepository.save(account);
    }

    @Transactional
    public void withdraw(Long id, Double amount) {
//        Account account = accountRepository.findById(id).orElseThrow();
//        account.setBalance(account.getBalance() - amount);
//        accountRepository.save(account);
    }
}
