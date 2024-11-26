package com.virginiabank.bankdemo.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virginiabank.bankdemo.model.UserPasswords;
import com.virginiabank.bankdemo.repository.UserPasswordsRepository;

@Service
public class UserPasswordsService {

    @Autowired
    private UserPasswordsRepository userPasswordsRepository;

    public Optional<UserPasswords> getPasswordByAccountId(String accountId) {
        return userPasswordsRepository.findById(accountId);
    }

    public UserPasswords saveUserPassword(UserPasswords password) {
        return userPasswordsRepository.save(password);
    }

    public void deletePassword(String accountId) {
        userPasswordsRepository.deleteById(accountId);
    }

    public UserPasswords updatePassword(String accountId, UserPasswords updatedPassword) {
        if (userPasswordsRepository.existsById(accountId)) {
            updatedPassword.setAccountId(accountId);
            return userPasswordsRepository.save(updatedPassword);
        } else {
            throw new RuntimeException("Password not found for the account");
        }
    }
}
