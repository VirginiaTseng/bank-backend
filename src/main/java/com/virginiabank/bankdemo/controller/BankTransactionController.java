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

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class BankTransactionController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountBalancesService accountBalancesService;
	@Autowired
	private BankTransactionsService bankTransactionsService;

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
			return ResponseEntity.ok(0.0d);
		}
	}

	// TODO:
	@PutMapping("/{id}/deposit")
//    @PostMapping("/{id}/deposit")
	public ResponseEntity<Map<String, Object>> deposit(@PathVariable String id,
			@RequestBody Map<String, Object> request) {// @RequestParam Double amount
		logger.info("deposit for account_id: {}", id);

		Map<String, Object> response = new HashMap<>();

		BigDecimal amount = ConversionUtils.toBigDecimal(request.get("amount"));
		BigDecimal balance = ConversionUtils.toBigDecimal(request.get("balance"));
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "Invalid deposit amount");
			return ResponseEntity.badRequest().body(errorResponse);
		}

		String desc = (String) request.get("desc");
		// check balance from internet
		Optional<AccountBalances> accbalance = accountBalancesService.getBalanceByAccountId(id);
		if (accbalance.isPresent()) {
			AccountBalances accBalance = accbalance.get();
			BigDecimal restoredBalance = accBalance.getBalance();

			if (balance.compareTo(restoredBalance) == 0) {
				BigDecimal calcBalance = balance.add(amount);
				Date updateTime = new Date();

				logger.info("deposit for account_id: {}", id, amount);
				boolean res = bankTransactionsService.handleTransaction(id, TransactionType.DEPOSIT.getCode(), amount,
						calcBalance, updateTime, desc + "");

				if(res) {
					response.put("success", true);
					response.put("balance", calcBalance);
				}else {
					response.put("success", false);
					response.put("balance", restoredBalance);
				}
			} else {
				response.put("success", false);
				response.put("balance", restoredBalance);
			}
		} else {
			// no balance account -- create on for it.
		}

		return ResponseEntity.ok(response);
	}

	// TODO:
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<Map<String, Object>> withdraw(@PathVariable String id,
			@RequestBody Map<String, Object> request) {// @RequestParam Double amount
		Map<String, Object> response = new HashMap<>();

		BigDecimal amount = ConversionUtils.toBigDecimal(request.get("amount"));
		BigDecimal balance = ConversionUtils.toBigDecimal(request.get("balance"));
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "Invalid deposit amount");
			return ResponseEntity.badRequest().body(errorResponse);
		}

//		String desc = (String) request.get("desc");

		// check balance from internet
		Optional<AccountBalances> accbalance = accountBalancesService.getBalanceByAccountId(id);
		if (accbalance.isPresent()) {
			AccountBalances accBalance = accbalance.get();
			BigDecimal restoredBalance = accBalance.getBalance();

			if (balance.compareTo(restoredBalance) == 0) {
//				// Keep 2 decimal point
//				amount = Math.round(amount * 100.0) / 100.0;
//				balance = Math.round(balance * 100.0) / 100.0;

				// check amout <= balance;
				if (balance.compareTo(restoredBalance) < 0) {
					response.put("success", false);
					response.put("balance", balance);
				} else {
					BigDecimal calcBalance = balance.subtract(amount);
					Date updateTime = new Date();

					System.out.println("withdraw " + id + " " + amount);
					boolean res = bankTransactionsService.handleTransaction(id, TransactionType.WITHDRAW.getCode(),
							amount, calcBalance, updateTime,  "");

					response.put("success", true);
					response.put("balance", calcBalance);
				}

			} else {
				response.put("success", false);
				response.put("balance", restoredBalance);
			}
		} else {
			// no balance account -- create on for it.
		}

		return ResponseEntity.ok(response);
	}

}
