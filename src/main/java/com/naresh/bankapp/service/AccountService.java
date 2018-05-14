package com.naresh.bankapp.service;

import java.util.List;

import com.naresh.bankapp.model.Account;

public interface AccountService {

	List<Account> findMyAccounts(Long userId);
	
	void withdraw(Long accountId, Long amount);
}
