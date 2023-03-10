package com.optum.spring.playground.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optum.spring.playground.dto.Account;
import com.optum.spring.playground.service.AccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@AllArgsConstructor
@Slf4j
public class AccountController {

	private AccountService accountService;

	@GetMapping
	public ResponseEntity<List<Account>> getAccount() {
		log.info("Accounts were fetched successfully");
		return ResponseEntity.ok(accountService.getAccounts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
		Account account = accountService.getAccountById(id);
		log.info("account: {}", account);
		ResponseEntity<Account> responseEntity = Objects.nonNull(account) ? ResponseEntity.ok(account)
				: ResponseEntity.notFound().build();
		return responseEntity;
	}

	@PostMapping
	public ResponseEntity<String> createAccount(@RequestBody Account account) {
		accountService.addAccount(account);
		log.info("Account was successfully created.");
		return ResponseEntity.ok("Account was successfully created.");
	}

}
