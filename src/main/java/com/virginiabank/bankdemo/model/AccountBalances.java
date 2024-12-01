
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
     * Gets the balance of the account.
     *
     * @return the balance of the account
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account.
     *
     * @param balance the new balance of the account
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets the last updated date of the account balance.
     *
     * @return the last updated date
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the last updated date of the account balance.
     *
     * @param lastUpdated the new last updated date
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Gets the bank account information associated with this account balance.
     *
     * @return the bank account information
     */
    public BankAccountInfo getBankAccountInfo() {
        return bankAccountInfo;
    }

    /**
     * Sets the bank account information associated with this account balance.
     *
     * @param bankAccountInfo the new bank account information
     */
    public void setBankAccountInfo(BankAccountInfo bankAccountInfo) {
        this.bankAccountInfo = bankAccountInfo;
    }
}
