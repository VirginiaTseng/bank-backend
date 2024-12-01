package com.virginiabank.bankdemo.repository;


import com.virginiabank.bankdemo.model.AccountBalances;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AccountBalancesRepository extends JpaRepository<AccountBalances, String> {

	/**
     * Updates the balance and last updated date for the account with the given ID.
     *
     * @param accountId the ID of the account
     * @param balance the new balance of the account
     * @param lastUpdated the date and time when the balance was last updated
     * @return the number of rows affected by the update
     */
    @Modifying // 表示此查询是数据修改操作
    @Transactional // 需要事务支持
    @Query("UPDATE AccountBalances a SET a.balance = :balance, a.lastUpdated=:lastUpdated  WHERE a.accountId = :accountId")
	int saveBalanceById(@Param("accountId") String accountId,
	        @Param("balance") BigDecimal balance,
	        @Param("lastUpdated") Date lastUpdated);
}
