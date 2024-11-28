package com.virginiabank.bankdemo.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virginiabank.bankdemo.model.AccountBalances;
import com.virginiabank.bankdemo.model.BankTransactions;
import com.virginiabank.bankdemo.model.TransactionType;
import com.virginiabank.bankdemo.service.AccountBalancesService;
import com.virginiabank.bankdemo.service.BankTransactionsService;
import com.virginiabank.bankdemo.tools.ConversionUtils;

/**
 * 
 */
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class BankTransactionController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountBalancesService accountBalancesService;
	@Autowired
	private BankTransactionsService bankTransactionsService;

	/**
	 * Retrieve User's Whole Transaction History
	 * @param id
	 * @return ResponseEntity<Map<String, Object>> for web request
	 */
	@PostMapping("/{id}/transactions")
	public ResponseEntity<Map<String, Object>> getTransactionHistory(@PathVariable String id) {
		logger.info("getTransactionHistory for account_id: {}", id);
		List<BankTransactions> result = bankTransactionsService.getTransactionsByAccountId(id);

		// previous [BankTransactions] contains [BankAccountInfo], so should be set to
		// null. Now the BankAccountInfo has been removed.
//    	for(BankTransactions bt:result) {
//    		bt.setBankAccountInfo(null);
//    	}

		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("transactions", result);

		return ResponseEntity.ok(response);
	}


	/**
	 * Retrieve User's Balance Amount stored in the database
	 * @param id  Account ID
	 * @return Balance Amount in the form of ResponseEntity<Map<String, Object>>
	 */
	@PostMapping("/{id}/balance")
	public ResponseEntity<Double> balance(@PathVariable String id) {
		Optional<AccountBalances> accountbalance = accountBalancesService.getBalanceByAccountId(id);
		if (accountbalance == null) {
			return ResponseEntity.notFound().build();
		}

		if (accountbalance.isPresent()) {
			AccountBalances instance = accountbalance.get();
			return ResponseEntity.ok(Double.valueOf(instance.getBalance().toString()));
		} else {
			// initial balance = 0.0 if no record in the table
			return ResponseEntity.ok(-1.0d);
		}
	}

	/**
	 * Deposit money according to the request from the browser
	 * 
	 * @param id Account ID
	 * @param request   request from the browser
	 * @return Exe Returned to Front-end in the form of ResponseEntity<Map<String, Object>>
	 */
	@PutMapping("/{id}/deposit")
//    @PostMapping("/{id}/deposit")
	public ResponseEntity<Map<String, Object>> deposit(@PathVariable String id,
			@RequestBody Map<String, Object> request) {// @RequestParam Double amount
		logger.info("deposit request received for account_id: {}", id);
		
		BigDecimal amount = ConversionUtils.toBigDecimal(request.get("amount"));
		BigDecimal balance = ConversionUtils.toBigDecimal(request.get("balance"));
		
		Map<String, Object> response = checkAccountAndTransactionAmount(id, amount, balance, false);
		if(response != null) {
			return ResponseEntity.ok(response);
		}
		
		response = new HashMap<>();
		
		BigDecimal calcBalance = balance.add(amount);
		Date updateTime = new Date();
		String desc = (String) request.get("desc");

		logger.info("deposit for account_id: {}", id, amount);
		boolean res = bankTransactionsService.handleTransaction(id, TransactionType.DEPOSIT.getCode(), amount,
				calcBalance, updateTime, desc + "");

		if(res) {
			response.put("success", true);
			response.put("balance", calcBalance);
		}else {
			response.put("success", false);
			response.put("error", "deposit transaction failed!");
		}

		return ResponseEntity.ok(response);
		
	}

	
	/**
	 * Withdraw money according to the request from the browser
	 * 
	 * @param id Account ID
	 * @param request   request from the browser
	 * @return Exe Returned to Front-end in the form of ResponseEntity<Map<String, Object>>
	 */
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<Map<String, Object>> withdraw(@PathVariable String id,
			@RequestBody Map<String, Object> request) {// @RequestParam Double amount
		logger.info("withdraw request received for account_id: {}", id);
		
		BigDecimal amount = ConversionUtils.toBigDecimal(request.get("amount"));
		BigDecimal balance = ConversionUtils.toBigDecimal(request.get("balance"));
		Map<String, Object> response = checkAccountAndTransactionAmount(id, amount, balance, true);
		if(response != null) {
			return ResponseEntity.ok(response);
		}
		
		response = new HashMap<>();

		String desc = (String) request.get("desc");	//remark

		BigDecimal calcBalance = balance.subtract(amount);
		Date updateTime = new Date();

		logger.info(" exec withdraw transaction for " + id + " $" + amount);
		boolean res = bankTransactionsService.handleTransaction(id, TransactionType.WITHDRAW.getCode(),
				amount, calcBalance, updateTime,  desc+"");

		if(res) {
			response.put("success", true);
			response.put("balance", calcBalance);
		}else {
			response.put("success", false);
			response.put("error", "deposit transaction failed!");
		}

		return ResponseEntity.ok(response);
	}
	
	/**
	 * Check if user could execute Deposit/Withdraw action:
	 * 1) Check deposit/withdraw amount is Valid;
	 * 2) Check Account is Valid;
	 * 3) Check account balance is corresponding to the restored amount
	 * 4) If it is withdraw transaction, check Account balance is enough for the amount to withdraw
	 * 
	 * @param accountId Account ID
	 * @param amount    Deposit/Withdraw amount
	 * @param balance   Account Balance from Internet
	 * @return Map<String, Object> Error Information would be returned, if action is allowed, null will be returned
	 */
	private Map<String, Object> checkAccountAndTransactionAmount(String accountId, BigDecimal amount, BigDecimal balance, boolean isWithdraw) {
		//Check deposit/withdraw amount is Valid;
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "Invalid deposit/withdraw amount");
			return errorResponse;//.badRequest().body(errorResponse);
		}
		
		//Check Account is Valid;
		Optional<AccountBalances> accbalance = accountBalancesService.getBalanceByAccountId(accountId);
		if (!accbalance.isPresent()) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "User's account balance does not exist!");
			return errorResponse;
		}
		
		// check balance from internet
		AccountBalances accBalance = accbalance.get();
		BigDecimal restoredBalance = accBalance.getBalance();

		if ( ! (balance.compareTo(restoredBalance) == 0) ) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "User's balance does not match with stored balance!");
			return errorResponse;
		}
		
		// check the balance is enough for the amount to withdraw
		if(isWithdraw) {
			if (restoredBalance.compareTo(amount) < 0) {
				Map<String, Object> errorResponse = new HashMap<>();
				errorResponse.put("error", "Account balance is not enough for the amount to withdraw!");
				return errorResponse;
			}
		}
		
		return null;
	}

}
