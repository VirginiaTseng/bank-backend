package com.virginiabank.bankdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.virginiabank.bankdemo.model.UserPasswords;

public interface UserPasswordsRepository extends JpaRepository<UserPasswords, String> {
	
	  @Query("SELECT u.accountId FROM UserPasswords u WHERE u.bankAccountInfo.customerName = :username")
	    Optional<String> findUserIdByUsername(String username);
}
