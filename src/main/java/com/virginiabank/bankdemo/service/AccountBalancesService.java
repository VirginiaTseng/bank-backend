
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

    /**
     * Retrieves the balance for a given account ID.
     *
     * @param accountId the ID of the account
     * @return an Optional containing the AccountBalances if found, or an empty Optional if not found
     */
    public Optional<AccountBalances> getBalanceByAccountId(String accountId) {
        return accountBalancesRepository.findById(accountId);
    }

    /**
     * Saves the given account balance.
     *
     * @param balance the AccountBalances object to save
     * @return the saved AccountBalances object
     */
    public AccountBalances saveBalance(AccountBalances balance) {
        return accountBalancesRepository.save(balance);
    }

    /**
     * Updates the balance for a given account ID.
     *
     * @param accountId the ID of the account
     * @param newBalance the new balance to set
     * @throws RuntimeException if the balance record is not found for the account
     */
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

    /**
     * Deletes the balance for a given account ID.
     *
     * @param accountId the ID of the account
     */
    public void deleteBalance(String accountId) {
        accountBalancesRepository.deleteById(accountId);
    }

    /**
     * Saves the balance for a given account ID.
     *
     * @param ab the AccountBalances object to save
     * @return true if the balance was saved successfully, false otherwise
     */
    public Boolean saveBalanceById(AccountBalances ab) {
        int ret = accountBalancesRepository.saveBalanceById(ab.getAccountId(), ab.getBalance(), ab.getLastUpdated());

        if (ret > 0)
            return true;
        else {
            return false;
        }
    }
}
