package com.naresh.bankapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.naresh.bankapp.dao.AccountRepository;
import com.naresh.bankapp.dao.TransactionRepository;
import com.naresh.bankapp.exception.ServiceException;
import com.naresh.bankapp.model.Account;
import com.naresh.bankapp.model.Transaction;
import com.naresh.bankapp.model.TransactionStatusEnum;
import com.naresh.bankapp.model.TransactionTypeEnum;
import com.naresh.bankapp.service.AccountService;
import com.naresh.bankapp.validation.AccountValidation;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;

	@Override
	public List<Account> findMyAccounts(Long userId) {
		return accountRepo.findByUser(userId);
	}
	
	private Account getAccount(Long accountId){
		
		Account account;
		try {
			account = accountRepo.getOne(accountId);
		} catch (EmptyResultDataAccessException e) {
			account =  null;
		}
		return account;
	}

	@Override
	/**
	 *  If account has sufficient balance, make a transaction entry and update the balance
	 */
	public void withdraw(Long accountId, Long amount) {

		Account account = getAccount(accountId);
		AccountValidation.validateBalanceForWithdraw(account, amount);
			
		try {
			Transaction tx = new Transaction();
			tx.setAccount(account);
			tx.setTransactionDate(LocalDateTime.now());
			tx.setStatus(TransactionStatusEnum.SUCCESS.toString());
			tx.setTransactionType(TransactionTypeEnum.DEBIT.toString());
			tx.setActive(true);
			
			transactionRepo.save(tx);		
			
			account.setBalance(account.getBalance() - amount);
			account.setModifiedDate(LocalDateTime.now());
			accountRepo.save(account);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException("Unable to do withdrawal transaction");
		}

	}

}
