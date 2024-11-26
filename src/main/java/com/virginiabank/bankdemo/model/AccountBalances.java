package com.virginiabank.bankdemo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Account_Balances")
public class AccountBalances {

    @Id
    @Column(name = "Account_ID", length = 20)
    private String accountId;

    @Column(name = "Balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "Last_Updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @OneToOne
    @JoinColumn(name = "Account_ID", referencedColumnName = "Account_ID", insertable = false, updatable = false)
    private BankAccountInfo bankAccountInfo;

    // Getters and setters
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public BankAccountInfo getBankAccountInfo() {
		return bankAccountInfo;
	}

	public void setBankAccountInfo(BankAccountInfo bankAccountInfo) {
		this.bankAccountInfo = bankAccountInfo;
	}


    
}
