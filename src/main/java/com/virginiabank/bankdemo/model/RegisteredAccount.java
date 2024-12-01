
package com.virginiabank.bankdemo.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * RegisteredAccount
 *    POJO used for convert Registered Account data from web, used in controllers
 * 
 * This class represents a registered account with various attributes such as username, password, 
 * gender, address, phone number, email, account type, initial deposit, ID proof type, ID proof number, 
 * occupation, annual income, and date of birth.
 * 
 * @author 
 *    virginia.zane
 */
public class RegisteredAccount {

    private String username;
    private String password;
    private String confirmPassword;
    private Integer gender;
    private String address;
    private String phoneNumber;
    private String email;
    private Integer accountType;
    private BigDecimal initialDeposit;
    private String idProofType;
    private String idProofNumber;
    private String occupation;
    private BigDecimal annualIncome;
    private Date dateOfBirth;

    /**
     * Sets the password for the account.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the date of birth of the account holder.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the account holder.
     *
     * @param dateOfBirth the new date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the password for the account.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the gender of the account holder.
     *
     * @return the gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * Sets the gender of the account holder.
     *
     * @param gender the new gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * Gets the address of the account holder.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the account holder.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phone number of the account holder.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the account holder.
     *
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the email of the account holder.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the account holder.
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
     * Gets the initial deposit amount.
     *
     * @return the initial deposit
     */
    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    /**
     * Sets the initial deposit amount.
     *
     * @param initialDeposit the new initial deposit
     */
    public void setInitialDeposit(BigDecimal initialDeposit) {
        this.initialDeposit = initialDeposit;
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
     * Gets the occupation of the account holder.
     *
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the occupation of the account holder.
     *
     * @param occupation the new occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Gets the annual income of the account holder.
     *
     * @return the annual income
     */
    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    /**
     * Sets the annual income of the account holder.
     *
     * @param annualIncome the new annual income
     */
    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    /**
     * Gets the username for the account.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the account.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the confirmation password for the account.
     *
     * @return the confirmation password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirmation password for the account.
     *
     * @param confirmPassword the new confirmation password
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
