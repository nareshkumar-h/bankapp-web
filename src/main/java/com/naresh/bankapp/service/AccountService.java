package com.naresh.bankapp.service;

import java.util.List;

import com.naresh.bankapp.model.Account;
import com.naresh.bankapp.model.Transaction;

public interface AccountService {

	List<Account> findMyAccounts(Long userId);
	
	Transaction withdraw(Long accountId, Long amount, String mode);

	Transaction fundTransfer(Long accountId, Long toAccount, Long transactionAmount, String mode);
	
	Long getBalance(Long accountId);
	
	List<Transaction> getTransactionList(Long accountId);
}
