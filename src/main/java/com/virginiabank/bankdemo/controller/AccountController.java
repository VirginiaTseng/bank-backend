package com.virginiabank.bankdemo.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virginiabank.bankdemo.model.AccountBalances;
import com.virginiabank.bankdemo.model.BankAccountInfo;
import com.virginiabank.bankdemo.model.BankTransactions;
import com.virginiabank.bankdemo.model.RegisteredAccount;
import com.virginiabank.bankdemo.model.TransactionType;
import com.virginiabank.bankdemo.model.UserPasswords;
import com.virginiabank.bankdemo.service.AccountBalancesService;
import com.virginiabank.bankdemo.service.BankAccountInfoService;
import com.virginiabank.bankdemo.service.BankTransactionsService;
import com.virginiabank.bankdemo.service.UserPasswordsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private BankAccountInfoService bankAccountInfoService;
	@Autowired
	private UserPasswordsService userPasswordsService;
	@Autowired
	private AccountBalancesService accountBalancesService;
	@Autowired
	private BankTransactionsService bankTransactionsService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String userId, @RequestParam String password) {
		Map<String, Object> response = new HashMap<>();


		//check user password existed or not
		Optional<UserPasswords> pws = userPasswordsService.getPasswordByAccountId(userId);
		if (pws == null) {
			System.out.println("Account not found");
			response.put("success", false);
			response.put("error", "Account not found!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		if (!(pws.isPresent())) {
			System.out.println("Account may not be exist!");
			response.put("success", false);
			response.put("error", "Account not exist!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		UserPasswords up = pws.get();
		if (!up.getDelFlag().equals(0)) {
			response.put("success", false);
			response.put("error", "Account has been deleted");
			return ResponseEntity.status(HttpStatus.GONE).body(response);
		}
		
		String storedPasswordHash = up.getPasswordHash();
		String inputHash = hashPassword(password);
	    if (!inputHash.equals(storedPasswordHash)) {
			response.put("success", false);
			response.put("error", "Incorrect account or password");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		Optional<BankAccountInfo> account = bankAccountInfoService.getAccountById(userId);

		if (!account.isPresent()) {
			response.put("success", false);
			response.put("error", "Account information not found");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} 
		
		String username = ((BankAccountInfo) (account.get())).getCustomerName();
		System.out.println("Username: " + username);

		response.put("username", username);
		response.put("success", true);

		return ResponseEntity.ok(response);

	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody RegisteredAccount request) {
		Map<String, Object> response = new HashMap<>();
		
		 // log record
	    logger.info("Register request received for username: {}", request.getUsername());
	    
		// input check ..... first deposit
		request.getInitialDeposit();

		// idProofNumber repeated or not

		try {
			// Generate a new account ID
			String accountId = bankAccountInfoService.getANewAccountId();
			logger.debug("Generated account ID: {}", accountId);
			
			// bankaccountInfo
			BankAccountInfo accountInfo = new BankAccountInfo();
			accountInfo.setId(Integer.valueOf(accountId.substring(3)));
			accountInfo.setAccountId(accountId);
			accountInfo.setCustomerName(request.getUsername());
			accountInfo.setDateOfBirth(new Date(0));
			accountInfo.setGender(request.getGender());
			accountInfo.setAddress(request.getAddress());
			accountInfo.setPhoneNumber(request.getPhoneNumber());
			accountInfo.setEmail(request.getEmail());
			accountInfo.setAccountType(request.getAccountType());
			accountInfo.setInitialDeposit(request.getInitialDeposit());
			accountInfo.setDateOpened(new Date(0));
			accountInfo.setBranchId("0");
			accountInfo.setIdProofType(request.getIdProofType());
			accountInfo.setIdProofNumber(request.getIdProofNumber());
			accountInfo.setOccupation(request.getOccupation());
			accountInfo.setIdProofType(request.getIdProofType());
			accountInfo.setAnnualIncome(request.getAnnualIncome());

			bankAccountInfoService.saveAccount(accountInfo);

			// user-password
			UserPasswords up = new UserPasswords(accountId, request.getPassword());
			up.setBankAccountInfo(accountInfo);
			userPasswordsService.saveUserPassword(up);

			// BALANCE INIT
			//
			Date date = new Date();

			// HISTORY ?
			BankTransactions bts = new BankTransactions();
			bts.setAccountId(accountId);
			bts.setAmount(request.getInitialDeposit());
			bts.setBalanceAfterTransaction(request.getInitialDeposit());
			bts.setDescription("New User Initial Deposit");
			bts.setBranchId("0");
			bts.setTransactionDate(date);
			bts.setTransactionType(TransactionType.DEPOSIT.getCode());

			bankTransactionsService.saveTransaction(bts);

			AccountBalances ab = new AccountBalances();
			ab.setAccountId(accountId);
			ab.setBalance(request.getInitialDeposit());
			ab.setBankAccountInfo(accountInfo);
			ab.setLastUpdated(date);
			accountBalancesService.saveBalance(ab);

			response.put("username", request.getUsername());
			response.put("success", true);
		} catch (Exception e) {
			// response.put("username", request.getUsername());
			response.put("success", false);
			response.put("error", e);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}/info")
	public ResponseEntity<BankAccountInfo> getAccountInfo(@PathVariable String id) {
		System.out.println(id);
		ResponseEntity<BankAccountInfo> result = bankAccountInfoService.getAccountById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

		return result;
	}

	@PutMapping("/{id}/logout")
	public ResponseEntity<String> logout(@PathVariable Long id) {
//      accountService.delete(id);
		return ResponseEntity.ok("Account logout successful");
	}

	@PutMapping("/{id}/delete")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		// .delete(id);
		return ResponseEntity.ok("Delete Account successful");
	}

	
	private String hashPassword(String password) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5"); // 替换为实际的加密算法
	        byte[] hashBytes = md.digest(password.getBytes());
	        StringBuilder sb = new StringBuilder();
	        for (byte b : hashBytes) {
	            sb.append(String.format("%02x", b)); // 转为16进制字符串
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Error hashing password", e);
	    }
	}
}
