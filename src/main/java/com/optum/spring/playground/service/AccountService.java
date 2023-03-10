package com.optum.spring.playground.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.optum.spring.playground.dto.Account;
import com.optum.spring.playground.model.AccountEntity;
import com.optum.spring.playground.repository.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {

	private AccountRepository accountRepository;
	private ModelMapper modelMapper;

	public List<Account> getAccounts() {
		List<AccountEntity> accounts = accountRepository.findAll();
		log.debug("accounts: {}", accounts);
		return accounts.stream().map(this::buildAccount).collect(Collectors.toList());
	}

	public Account getAccountById(Long id) {
		Optional<AccountEntity> accountEntity = accountRepository.findById(id);
		return accountEntity.map(this::buildAccount).orElse(null);
	}

	public Long addAccount(Account account) {
		AccountEntity accountEntity = modelMapper.map(account, AccountEntity.class);
		return accountRepository.save(accountEntity).getId();
	}

	private Account buildAccount(AccountEntity accountEntity) {
		return modelMapper.map(accountEntity, Account.class);
	}
}
