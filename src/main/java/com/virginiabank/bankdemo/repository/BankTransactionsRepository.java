package com.virginiabank.bankdemo.repository;

import com.virginiabank.bankdemo.model.BankTransactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;



public interface BankTransactionsRepository extends JpaRepository<BankTransactions, Integer> {

	 /**
     * Finds all bank transactions for a given account ID.
     *
     * @param accountId the ID of the account
     * @return a list of BankTransactions objects for the given account ID
     */
	public List<BankTransactions> findByAccountId(String accountId);
	
	//Spring Data JPA’s method query derivation. This feature allows Spring to automatically generate query implementations based on the method name's structure, which follows specific conventions.
    /**
     * Finds all bank transactions for a given account ID, ordered by transaction date in descending order.
     *
     * @param accountId the ID of the account
     * @return a list of BankTransactions objects for the given account ID, ordered by transaction date in descending order
     */
	public List<BankTransactions> findByAccountIdOrderByTransactionDateDesc(String accountId);
	
	
	// Fetch paginated transactions
//    public Page<BankTransactions> findByAccountIdOrderByTransactionDateDesc(String accountId, Pageable pageable);

}
