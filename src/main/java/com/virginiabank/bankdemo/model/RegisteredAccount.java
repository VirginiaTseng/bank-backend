package com.virginiabank.bankdemo.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * RegisteredAccount
 *    POJO used for convert Registered Account data from web, used in controllers
 * @author virginia.zane
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

//	    private Date dateOpened;
//
//	    private String branchId;


	    
	    public void setPassword(String password) {
			this.password = password;
		}
	    
	    public Date getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getPassword() {
			return password;
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

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

}
