package com.virginiabank.bankdemo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virginiabank.bankdemo.model.AccountBalances;
import com.virginiabank.bankdemo.model.BankAccountInfo;
import com.virginiabank.bankdemo.model.BankTransactions;
import com.virginiabank.bankdemo.model.RegisteredAccount;
import com.virginiabank.bankdemo.model.UserPasswords;
import com.virginiabank.bankdemo.service.AccountBalancesService;
import com.virginiabank.bankdemo.service.BankAccountInfoService;
import com.virginiabank.bankdemo.service.BankTransactionsService;
import com.virginiabank.bankdemo.service.UserPasswordsService;
import com.virginiabank.bankdemo.tools.TransactionType;

/**
 * Account Management Controller
 * 
 * @author virginiatseng
 */
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

	/**
	 * register new account
	 * 
	 * @param request web request
	 * @return web return
	 */
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody RegisteredAccount request) {
		// log record
		logger.info("Register request received for username: {}", request.getUsername());

		Map<String, Object> response = new HashMap<>();

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

	/**
	 * Get Bank Account Information by AccountID
	 * 
	 * @param id accountID
	 * @return Bank Account Information
	 */
	@GetMapping("/{id}/info")
	public ResponseEntity<BankAccountInfo> getAccountInfo(@PathVariable String id) {
		logger.info("account info request {}", id);

		ResponseEntity<BankAccountInfo> result = bankAccountInfoService.getAccountById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

		return result;
	}

	/**
	 * Update account information
	 * 
	 * @param id           accountID
	 * @param toUpdateInfo Account Information to be updated
	 * @return
	 */
	@PutMapping("/{id}/update")
	public ResponseEntity<String> update(@PathVariable String id, @RequestBody BankAccountInfo toUpdateInfo) {
		logger.info("account update request {}", id);

		String accountId = id;
		BankAccountInfo info = toUpdateInfo;
		info.setId(Integer.valueOf(id.substring(3)));

		bankAccountInfoService.saveAccount(info);

		return ResponseEntity.ok("Update Account successful");
	}

	/**
	 * Delete an account (put the delflag of this account into 1 )
	 * 
	 * @param id accountID passed through internet
	 * @return Return back to web browser
	 */
	@PutMapping("/{id}/delete")
	public ResponseEntity<String> delete(@PathVariable String id) {
		logger.info("account update request {}", id);

		// check user password existed or not
		Optional<UserPasswords> pws = userPasswordsService.getPasswordByAccountId(id);
		if (pws.isPresent()) {
			UserPasswords up = pws.get();
			if (up.getDelFlag().equals(1)) {
				logger.error("Account_id {}  has been deleted!", id);
				return ResponseEntity.ok("Account has already been deleted");
			}
			up.setDelFlag(1);
			userPasswordsService.saveUserPassword(up);
		}

		return ResponseEntity.ok("Delete Account successful");
	}

}
