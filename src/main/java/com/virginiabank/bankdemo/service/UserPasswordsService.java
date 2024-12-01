
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

    /**
     * Retrieves the password for a given account ID.
     *
     * @param accountId the ID of the account
     * @return an Optional containing the UserPasswords if found, or an empty Optional if not found
     */
    public Optional<UserPasswords> getPasswordByAccountId(String accountId) {
        return userPasswordsRepository.findById(accountId);
    }

    /**
     * Saves the given user password.
     *
     * @param password the UserPasswords object to save
     * @return the saved UserPasswords object
     */
    public UserPasswords saveUserPassword(UserPasswords password) {
        return userPasswordsRepository.save(password);
    }

    /**
     * Deletes the password for a given account ID.
     *
     * @param accountId the ID of the account
     */
    public void deletePassword(String accountId) {
        userPasswordsRepository.deleteById(accountId);
    }

    /**
     * Updates the password for a given account ID.
     *
     * @param accountId the ID of the account
     * @param updatedPassword the updated UserPasswords object
     * @return the updated UserPasswords object
     * @throws RuntimeException if the password record is not found for the account
     */
    public UserPasswords updatePassword(String accountId, UserPasswords updatedPassword) {
        if (userPasswordsRepository.existsById(accountId)) {
            updatedPassword.setAccountId(accountId);
            return userPasswordsRepository.save(updatedPassword);
        } else {
            throw new RuntimeException("Password not found for the account");
        }
    }
}
