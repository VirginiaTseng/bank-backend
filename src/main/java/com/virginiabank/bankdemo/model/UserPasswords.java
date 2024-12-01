
package com.virginiabank.bankdemo.model;

import javax.persistence.*;

/**
 * Represents the user passwords associated with a bank account.
 * This entity is mapped to the "User_Passwords" table in the database.
 */
@Entity
@Table(name = "User_Passwords")
public class UserPasswords {

    /**
     * The account ID associated with the user password.
     */
    @Id
    @Column(name = "Account_ID", length = 20)
    private String accountId;

    /**
     * The hashed password of the user.
     */
    @Column(name = "Password_Hash", nullable = false, length = 256)
    private String passwordHash;

    /**
     * The delete flag indicating whether the password is marked for deletion.
     * Default value is 0.
     */
    @Column(name = "delflag")
    private Integer delFlag = 0;

    /**
     * The bank account information associated with this user password.
     */
    @OneToOne
    @JoinColumn(name = "Account_ID", referencedColumnName = "Account_ID", insertable = false, updatable = false)
    private BankAccountInfo bankAccountInfo;

    /**
     * Default constructor.
     */
    public UserPasswords() {

    }

    /**
     * Constructs a new UserPasswords instance with the specified account ID and password hash.
     *
     * @param accountId the account ID
     * @param password the password hash
     */
    public UserPasswords(String accountId, String password) {
        this.accountId = accountId;
        this.passwordHash = password;
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
     * Gets the password hash.
     *
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the password hash.
     *
     * @param passwordHash the new password hash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gets the bank account information.
     *
     * @return the bank account information
     */
    public BankAccountInfo getBankAccountInfo() {
        return bankAccountInfo;
    }

    /**
     * Sets the bank account information.
     *
     * @param bankAccountInfo the new bank account information
     */
    public void setBankAccountInfo(BankAccountInfo bankAccountInfo) {
        this.bankAccountInfo = bankAccountInfo;
    }

    /**
     * Gets the delete flag.
     *
     * @return the delete flag
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * Sets the delete flag.
     *
     * @param delFlag the new delete flag
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
