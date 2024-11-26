package com.virginiabank.bankdemo.repository;

import com.virginiabank.bankdemo.model.UserPasswords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPasswordsRepository extends JpaRepository<UserPasswords, String> {
}
