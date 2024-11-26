package com.virginiabank.bankdemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.virginiabank.bankdemo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {}
