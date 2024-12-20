package com.virginiabank.bankdemo.repository;

import com.virginiabank.bankdemo.model.BankAccountInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankAccountInfoRepository extends JpaRepository<BankAccountInfo, String> {
	
//	@Query("SELECT MAX(CAST(SUBSTRING(accountId, 4) AS UNSIGNED)) \n"
//			+ "FROM BankAccountInfo\n"
//			+ "WHERE accountId LIKE 'ACC%'")
	
	 /**
     * Finds the maximum account ID in the BankAccountInfo table.
     *
     * @return the maximum account ID as an Integer
     */
	@Query("SELECT MAX(id) \n"
			+ "FROM BankAccountInfo\n")
	Integer findMaxAccountId();
}
