
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
     * Gets the customer name.
     *
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer name.
     *
     * @param customerName the new customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth.
     *
     * @param dateOfBirth the new date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the account type.
     *
     * @return the account type
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type.
     *
     * @param accountType the new account type
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the initial deposit.
     *
     * @return the initial deposit
     */
    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    /**
     * Sets the initial deposit.
     *
     * @param initialDeposit the new initial deposit
     */
    public void setInitialDeposit(BigDecimal initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    /**
     * Gets the date opened.
     *
     * @return the date opened
     */
    public Date getDateOpened() {
        return dateOpened;
    }

    /**
     * Sets the date opened.
     *
     * @param dateOpened the new date opened
     */
    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
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
     * Gets the ID proof type.
     *
     * @return the ID proof type
     */
    public String getIdProofType() {
        return idProofType;
    }

    /**
     * Sets the ID proof type.
     *
     * @param idProofType the new ID proof type
     */
    public void setIdProofType(String idProofType) {
        this.idProofType = idProofType;
    }

    /**
     * Gets the ID proof number.
     *
     * @return the ID proof number
     */
    public String getIdProofNumber() {
        return idProofNumber;
    }

    /**
     * Sets the ID proof number.
     *
     * @param idProofNumber the new ID proof number
     */
    public void setIdProofNumber(String idProofNumber) {
        this.idProofNumber = idProofNumber;
    }

    /**
     * Gets the occupation.
     *
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the occupation.
     *
     * @param occupation the new occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Gets the annual income.
     *
     * @return the annual income
     */
    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    /**
     * Sets the annual income.
     *
     * @param annualIncome the new annual income
     */
    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    /**
     * Sets the ID.
     *
     * @param id the new ID
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
