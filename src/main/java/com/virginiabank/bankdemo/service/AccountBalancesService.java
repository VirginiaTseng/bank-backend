package com.virginiabank.bankdemo.service;


import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virginiabank.bankdemo.model.AccountBalances;
import com.virginiabank.bankdemo.repository.AccountBalancesRepository;

@Service
public class AccountBalancesService {

    @Autowired
    private AccountBalancesRepository accountBalancesRepository;

    public Optional<AccountBalances> getBalanceByAccountId(String accountId) {
        return accountBalancesRepository.findById(accountId);
    }


    public AccountBalances saveBalance(AccountBalances balance) {
        return accountBalancesRepository.save(balance);
    }

    public void updateBalance(String accountId, BigDecimal newBalance) {
        Optional<AccountBalances> accountBalance = accountBalancesRepository.findById(accountId);
        if (accountBalance.isPresent()) {
            AccountBalances balance = accountBalance.get();
            balance.setBalance(newBalance);
            balance.setLastUpdated(new java.util.Date());
            accountBalancesRepository.save(balance);
        } else {
            throw new RuntimeException("Balance record not found for the account");
        }
    }

    public void deleteBalance(String accountId) {
        accountBalancesRepository.deleteById(accountId);
    }


	public Boolean saveBalanceById(AccountBalances ab) {
		int ret = accountBalancesRepository.saveBalanceById(ab.getAccountId(), ab.getBalance(), ab.getLastUpdated());
		
		if(ret >0 )
			return true;
		else {
			return false;
		}
	}
}
