
package com.virginiabank.bankdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virginiabank.bankdemo.model.BankAccountInfo;
import com.virginiabank.bankdemo.repository.BankAccountInfoRepository;

@Service
public class BankAccountInfoService {

    @Autowired
    private BankAccountInfoRepository bankAccountInfoRepository;

    /**
     * Retrieves all bank accounts.
     *
     * @return a list of all BankAccountInfo objects
     */
    public List<BankAccountInfo> getAllAccounts() {
        return bankAccountInfoRepository.findAll();
    }

    /**
     * Retrieves a bank account by its ID.
     *
     * @param accountId the ID of the bank account
     * @return an Optional containing the BankAccountInfo if found, or an empty Optional if not found
     */
    public Optional<BankAccountInfo> getAccountById(String accountId) {
        return bankAccountInfoRepository.findById(accountId);
    }

    /**
     * Saves the given bank account.
     *
     * @param account the BankAccountInfo object to save
     * @return the saved BankAccountInfo object
     */
    public BankAccountInfo saveAccount(BankAccountInfo account) {
        return bankAccountInfoRepository.save(account);
    }

    /**
     * Deletes a bank account by its ID.
     *
     * @param accountId the ID of the bank account
     */
    public void deleteAccount(String accountId) {
        bankAccountInfoRepository.deleteById(accountId);
    }

    /**
     * Updates the bank account with the given ID.
     *
     * @param accountId the ID of the bank account
     * @param updatedAccount the updated BankAccountInfo object
     * @return the updated BankAccountInfo object
     * @throws RuntimeException if the account is not found
     */
    public BankAccountInfo updateAccount(String accountId, BankAccountInfo updatedAccount) {
        if (bankAccountInfoRepository.existsById(accountId)) {
            updatedAccount.setAccountId(accountId);
            return bankAccountInfoRepository.save(updatedAccount);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    /**
     * Generates a new unique account ID.
     *
     * @return a new unique account ID
     */
    public synchronized String getANewAccountId() {
        Integer maxAccountId = bankAccountInfoRepository.findMaxAccountId();
        if (maxAccountId != null) {
            int newId = maxAccountId + 1;
            return "ACC" + String.format("%04d", newId); // Format as a 4-digit number
        } else {
            return "ACC0001"; // Initial ID
        }
    }
}
