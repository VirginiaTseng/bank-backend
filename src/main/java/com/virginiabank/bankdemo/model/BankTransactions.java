
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

    // Getters and setters

    /**
     * Gets the transaction ID.
     *
     * @return the transaction ID
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID.
     *
     * @param transactionId the new transaction ID
     */
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the account ID.
     *
     * @return the account ID
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the account ID.
     *
     * @param accountId the new account ID
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the transaction date.
     *
     * @return the transaction date
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the transaction date.
     *
     * @param transactionDate the new transaction date
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the amount of the transaction
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount the new amount of the transaction
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the balance after the transaction.
     *
     * @return the balance after the transaction
     */
    public BigDecimal getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    /**
     * Sets the balance after the transaction.
     *
     * @param balanceAfterTransaction the new balance after the transaction
     */
    public void setBalanceAfterTransaction(BigDecimal balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    /**
     * Gets the transaction type.
     *
     * @return the transaction type
     */
    public Integer getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the transaction type.
     *
     * @param transactionType the new transaction type
     */
    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets the branch ID.
     *
     * @return the branch ID
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * Sets the branch ID.
     *
     * @param branchId the new branch ID
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return the description of the transaction
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description the new description of the transaction
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
