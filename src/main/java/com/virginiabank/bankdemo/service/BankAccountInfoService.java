package com.virginiabank.bankdemo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virginiabank.bankdemo.model.BankAccountInfo;
import com.virginiabank.bankdemo.repository.BankAccountInfoRepository;

@Service
public class BankAccountInfoService {

    @Autowired
    private BankAccountInfoRepository bankAccountInfoRepository;

    public List<BankAccountInfo> getAllAccounts() {
        return bankAccountInfoRepository.findAll();
    }

    public Optional<BankAccountInfo> getAccountById(String accountId) {
        return bankAccountInfoRepository.findById(accountId);
    }

    public BankAccountInfo saveAccount(BankAccountInfo account) {
        return bankAccountInfoRepository.save(account);
    }

    public void deleteAccount(String accountId) {
        bankAccountInfoRepository.deleteById(accountId);
    }

    public BankAccountInfo updateAccount(String accountId, BankAccountInfo updatedAccount) {
        if (bankAccountInfoRepository.existsById(accountId)) {
            updatedAccount.setAccountId(accountId);
            return bankAccountInfoRepository.save(updatedAccount);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    //synchroniezed
	public synchronized String getANewAccountId() {
		  Integer maxAccountId = bankAccountInfoRepository.findMaxAccountId();
		    if (maxAccountId!=null) {
		        int newId = maxAccountId + 1;
		        return "ACC" + String.format("%04d", newId); // 格式化为 3 位数字
		    } else {
		        return "ACC0001"; // 初始 ID
		    }
	}
}
