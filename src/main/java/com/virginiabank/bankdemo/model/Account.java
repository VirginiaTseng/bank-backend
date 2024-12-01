
package com.virginiabank.bankdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private Double balance;

    /**
     * Default constructor for the Account class.
     */
    public Account() {}

    /**
     * Constructor for the Account class.
     *
     * @param customerName the name of the customer
     * @param balance the initial balance of the account
     */
    public Account(String customerName, Double balance) {
        this.customerName = customerName;
        this.balance = balance;
    }

    /**
     * Gets the ID of the account.
     *
     * @return the ID of the account
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the account.
     *
     * @param id the new ID of the account
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the customer.
     *
     * @return the name of the customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the name of the customer.
     *
     * @param customerName the new name of the customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the balance of the account.
     *
     * @return the balance of the account
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account.
     *
     * @param balance the new balance of the account
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
