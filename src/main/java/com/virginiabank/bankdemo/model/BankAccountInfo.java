package com.virginiabank.bankdemo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Bank_Account_Info")
public class BankAccountInfo {

    @Id
    @Column(name = "Account_ID", length = 20)
    private String accountId;
    
    @Column(name = "id")
    private Integer id;

    @Column(name = "Customer_Name", nullable = false, length = 50)
    private String customerName;

    @Column(name = "Date_of_Birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "Gender")
    private Integer gender;

    @Column(name = "Address", length = 256)
    private String address;

    @Column(name = "Phone_Number", length = 15)
    private String phoneNumber;

    @Column(name = "Email", length = 50)
    private String email;

    @Column(name = "Account_Type")
    private Integer accountType;

    @Column(name = "Initial_Deposit", precision = 15, scale = 2)
    private BigDecimal initialDeposit;

    @Column(name = "Date_Opened")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOpened;

    @Column(name = "Branch_ID", length = 10)
    private String branchId;

    @Column(name = "ID_Proof_Type", length = 20)
    private String idProofType;

    @Column(name = "ID_Proof_Number", length = 30)
    private String idProofNumber;

    @Column(name = "Occupation", length = 30)
    private String occupation;

    @Column(name = "Annual_Income", precision = 15, scale = 2)
    private BigDecimal annualIncome;

    // Getters and setters
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(BigDecimal initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}

	public String getIdProofNumber() {
		return idProofNumber;
	}

	public void setIdProofNumber(String idProofNumber) {
		this.idProofNumber = idProofNumber;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}

	public void setId(Integer id) {
		this.id=id;
	}

   
}
