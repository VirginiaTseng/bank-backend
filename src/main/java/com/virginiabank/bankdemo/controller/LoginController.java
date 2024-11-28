package com.virginiabank.bankdemo.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virginiabank.bankdemo.model.BankAccountInfo;
import com.virginiabank.bankdemo.model.UserPasswords;
import com.virginiabank.bankdemo.service.BankAccountInfoService;
import com.virginiabank.bankdemo.service.UserPasswordsService;
import com.virginiabank.bankdemo.tools.EncryptionUtils;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private BankAccountInfoService bankAccountInfoService;
	@Autowired
	private UserPasswordsService userPasswordsService;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String userId, @RequestParam String password) {
		Map<String, Object> response = new HashMap<>();


		//check user password existed or not
		Optional<UserPasswords> pws = userPasswordsService.getPasswordByAccountId(userId);
		if (pws == null) {
			logger.error("Account_id {} not found", userId);
			response.put("success", false);
			response.put("error", "Account not found!");
			return ResponseEntity.ok(response);
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		if (!(pws.isPresent())) {
			logger.error("Account_id {}  may not be exist!", userId);
			response.put("success", false);
			response.put("error", "Account not exist!");
			return ResponseEntity.ok(response);
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		UserPasswords up = pws.get();
		if (!up.getDelFlag().equals(0)) {
			logger.error("Account_id {}  has been deleted!", userId);
			response.put("success", false);
			response.put("error", "Account has been deleted");
			return ResponseEntity.ok(response);
			//return ResponseEntity.status(HttpStatus.GONE).body(response);
		}
		
		String storedPasswordHash = up.getPasswordHash();
		String inputHash = EncryptionUtils.hashPassword(password);
	    if (!inputHash.equals(storedPasswordHash)) {
	    	logger.error("Account_id {} login attempt:  Incorrect account or password!", userId);
			response.put("success", false);
			response.put("error", "Incorrect account or password");
			return ResponseEntity.ok(response);
			//return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		Optional<BankAccountInfo> account = bankAccountInfoService.getAccountById(userId);

		if (!account.isPresent()) {
			logger.error("Account_id {} login attempt:  Account information not found!", userId);
			response.put("success", false);
			response.put("error", "Account information not found");
			return ResponseEntity.ok(response);
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} 
		
		String username = ((BankAccountInfo) (account.get())).getCustomerName();
		logger.info("Account_id {} login succeed: username ={}", username);

		response.put("username", username);
		response.put("success", true);

		return ResponseEntity.ok(response);

	}
	
	@PutMapping("/{id}/logout")
	public ResponseEntity<String> logout1(@RequestParam String userId) {
		logger.info("Account_id {} log1 out [Put]", userId);
		return ResponseEntity.ok("Account logout successful");
	}

	@PostMapping("/{id}/logout")
	public ResponseEntity<String> logout(@PathVariable String id) {
		logger.info("Account_id {} log out [Post]", id);
		return ResponseEntity.ok("Account logout successful");
	}
	
	@GetMapping("/{id}/logout")
	public ResponseEntity<String> logout2(@PathVariable String id) {
		logger.info("Account_id {} log out [Get]", id);
		return ResponseEntity.ok("Account logout successful");
	}
}
