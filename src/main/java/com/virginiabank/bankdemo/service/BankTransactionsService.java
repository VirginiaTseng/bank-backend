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

    public List<BankTransactions> getAllTransactions() {
        return bankTransactionsRepository.findAll();
    }

    public Optional<BankTransactions> getTransactionById(Integer transactionId) {
        return bankTransactionsRepository.findById(transactionId);
    }

    public List<BankTransactions> getTransactionsByAccountId(String accountId) {
//        return bankTransactionsRepository.findAll()
//                .stream()
//                .filter(transaction -> transaction.getAccountId().equals(accountId))
//                .toList();
//    	return bankTransactionsRepository.findByAccountId(accountId);
    	return bankTransactionsRepository.findByAccountIdOrderByTransactionDateDesc(accountId);
    }
    
    

    public BankTransactions saveTransaction(BankTransactions transaction) {
        return bankTransactionsRepository.save(transaction);
    }

    public void deleteTransaction(Integer transactionId) {
        bankTransactionsRepository.deleteById(transactionId);
    }

    public BankTransactions updateTransaction(Integer transactionId, BankTransactions updatedTransaction) {
        if (bankTransactionsRepository.existsById(transactionId)) {
            updatedTransaction.setTransactionId(transactionId);
            return bankTransactionsRepository.save(updatedTransaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    @Transactional
	public boolean handleTransaction(String id, int handleType, BigDecimal amount, BigDecimal calcBalance, Date updateTime, String desc) {
		// balance+amount
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
	    	
	    	int res1 = accountBalancesRepository.saveBalanceById(id, calcBalance, updateTime);
	
	    	execRet = true;
    	}catch (Exception e) {
			e.printStackTrace();
			
		}
    	
    	return execRet;
	}
}
