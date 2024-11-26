package com.virginiabank.bankdemo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Bank_Transactions")
public class BankTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Transaction_ID")
    private Integer transactionId;

    @Column(name = "Account_ID", length = 20, nullable = false)
    private String accountId;

    @Column(name = "Transaction_Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(name = "Amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "Balance_After_Transaction", nullable = false, precision = 15, scale = 2)
    private BigDecimal balanceAfterTransaction;
    

    @Column(name = "Transaction_Type")
    private Integer transactionType;

    @Column(name = "Branch_ID", length = 10)
    private String branchId;

    @Column(name = "Description")
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "Account_ID", referencedColumnName = "Account_ID", insertable = false, updatable = false)
//    private BankAccountInfo bankAccountInfo;

 // Getters and setters
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public BankAccountInfo getBankAccountInfo() {
//		return bankAccountInfo;
//	}
//
//	public void setBankAccountInfo(BankAccountInfo bankAccountInfo) {
//		this.bankAccountInfo = bankAccountInfo;
//	}

	public BigDecimal getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}

	public void setBalanceAfterTransaction(BigDecimal balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

    
}
