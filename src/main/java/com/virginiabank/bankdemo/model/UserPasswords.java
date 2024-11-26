package com.virginiabank.bankdemo.model;

import javax.persistence.*;

@Entity
@Table(name = "User_Passwords")
public class UserPasswords {

    @Id
    @Column(name = "Account_ID", length = 20)
    private String accountId;

    @Column(name = "Password_Hash", nullable = false, length = 256)
    private String passwordHash;
    
    @Column(name = "delflag")
    private Integer delFlag=0;

    @OneToOne
    @JoinColumn(name = "Account_ID", referencedColumnName = "Account_ID", insertable = false, updatable = false)
    private BankAccountInfo bankAccountInfo;
    
    public UserPasswords() {
    	
    }

	public UserPasswords(String accountId, String password) {
		this.accountId=accountId;
		this.passwordHash=password;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public BankAccountInfo getBankAccountInfo() {
		return bankAccountInfo;
	}

	public void setBankAccountInfo(BankAccountInfo bankAccountInfo) {
		this.bankAccountInfo = bankAccountInfo;
	}

    // Getters and setters
    public Integer getDelFlag() {
		return delFlag;
	}
    
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
