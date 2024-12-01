
package com.virginiabank.bankdemo.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virginiabank.bankdemo.model.AccountBalances;
import com.virginiabank.bankdemo.model.BankTransactions;
import com.virginiabank.bankdemo.repository.AccountBalancesRepository;
import com.virginiabank.bankdemo.repository.BankTransactionsRepository;

@Service
public class BankTransactionsService {

    @Autowired
    private BankTransactionsRepository bankTransactionsRepository;

    @Autowired
    private AccountBalancesRepository accountBalancesRepository;

    /**
     * Retrieves all bank transactions.
     *
     * @return a list of all BankTransactions objects
     */
    public List<BankTransactions> getAllTransactions() {
        return bankTransactionsRepository.findAll();
    }

    /**
     * Retrieves a bank transaction by its ID.
     *
     * @param transactionId the ID of the transaction
     * @return an Optional containing the BankTransactions if found, or an empty Optional if not found
     */
    public Optional<BankTransactions> getTransactionById(Integer transactionId) {
        return bankTransactionsRepository.findById(transactionId);
    }

    /**
     * Retrieves bank transactions by account ID, ordered by transaction date in descending order.
     *
     * @param accountId the ID of the account
     * @return a list of BankTransactions objects for the given account ID
     */
    public List<BankTransactions> getTransactionsByAccountId(String accountId) {
        return bankTransactionsRepository.findByAccountIdOrderByTransactionDateDesc(accountId);
    }

    /**
     * Saves the given bank transaction.
     *
     * @param transaction the BankTransactions object to save
     * @return the saved BankTransactions object
     */
    public BankTransactions saveTransaction(BankTransactions transaction) {
        return bankTransactionsRepository.save(transaction);
    }

    /**
     * Deletes a bank transaction by its ID.
     *
     * @param transactionId the ID of the transaction
     */
    public void deleteTransaction(Integer transactionId) {
        bankTransactionsRepository.deleteById(transactionId);
    }

    /**
     * Updates the bank transaction with the given ID.
     *
     * @param transactionId the ID of the transaction
     * @param updatedTransaction the updated BankTransactions object
     * @return the updated BankTransactions object
     * @throws RuntimeException if the transaction is not found
     */
    public BankTransactions updateTransaction(Integer transactionId, BankTransactions updatedTransaction) {
        if (bankTransactionsRepository.existsById(transactionId)) {
            updatedTransaction.setTransactionId(transactionId);
            return bankTransactionsRepository.save(updatedTransaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    /**
     * Handles a bank transaction by updating the account balance and saving the transaction record.
     *
     * @param id the ID of the account
     * @param handleType the type of transaction (e.g., deposit, withdrawal)
     * @param amount the amount of the transaction
     * @param calcBalance the calculated balance after the transaction
     * @param updateTime the date and time of the transaction
     * @param desc the description of the transaction
     * @return true if the transaction was handled successfully, false otherwise
     */
    @Transactional
    public boolean handleTransaction(String id, int handleType, BigDecimal amount, BigDecimal calcBalance, Date updateTime, String desc) {
        boolean execRet = false;
        try {
            BankTransactions record = new BankTransactions();
            record.setAccountId(id);
            record.setBalanceAfterTransaction(calcBalance);
            record.setAmount(amount);
            record.setTransactionDate(updateTime);
            record.setTransactionType(handleType);
            record.setBranchId("0");
            record.setDescription(desc);
            bankTransactionsRepository.save(record);

            accountBalancesRepository.saveBalanceById(id, calcBalance, updateTime);

            execRet = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return execRet;
    }
}
