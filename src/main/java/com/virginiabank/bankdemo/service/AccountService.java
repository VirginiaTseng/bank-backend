
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

    /**
     * Creates a new account with the given customer name and initial balance.
     *
     * @param customerName the name of the customer
     * @param initialBalance the initial balance of the account
     * @return the created Account object
     */
    public Account createAccount(String customerName, Double initialBalance) {
        Account account = new Account(customerName, initialBalance);
        return accountRepository.save(account);
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param id the ID of the account
     * @return an Optional containing the Account if found, or an empty Optional if not found
     */
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    /**
     * Retrieves bank account information by its ID.
     *
     * @param id the ID of the bank account information
     * @return an Optional containing the BankAccountInfo if found, or an empty Optional if not found
     */
    public Optional<BankAccountInfo> getAccount(String id) {
        return bankAccountInfoRepository.findById(id);
    }

    /**
     * Deposits a specified amount into the account with the given ID.
     *
     * @param id the ID of the account
     * @param amount the amount to deposit
     */
    @Transactional
    public void deposit(Long id, Double amount) {
//        Account account = accountRepository.findById(id).orElseThrow();
//        account.setBalance(account.getBalance() + amount);
//        accountRepository.save(account);
    }

    /**
     * Withdraws a specified amount from the account with the given ID.
     *
     * @param id the ID of the account
     * @param amount the amount to withdraw
     */
    @Transactional
    public void withdraw(Long id, Double amount) {
//        Account account = accountRepository.findById(id).orElseThrow();
//        account.setBalance(account.getBalance() - amount);
//        accountRepository.save(account);
    }
}
