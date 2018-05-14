package com.naresh.bankapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naresh.bankapp.dao.AccountRepository;
import com.naresh.bankapp.dao.TransactionRepository;
import com.naresh.bankapp.exception.ServiceException;
import com.naresh.bankapp.model.Account;
import com.naresh.bankapp.model.Transaction;
import com.naresh.bankapp.model.TransactionModeEnum;
import com.naresh.bankapp.model.TransactionStatusEnum;
import com.naresh.bankapp.model.TransactionTypeEnum;
import com.naresh.bankapp.service.AccountService;
import com.naresh.bankapp.validation.AccountValidator;
import com.naresh.bankapp.validation.TransactionValidator;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountValidator accountValidator;

	@Autowired
	private TransactionValidator transactionValidator;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private TransactionRepository transactionRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Account> findMyAccounts(Long userId) {
		return accountRepo.findByUser(userId);
	}

	private Account getAccount(Long accountId) {

		Account account;
		try {
			account = accountRepo.getOne(accountId);
		} catch (EmptyResultDataAccessException e) {
			account = null;
		}
		return account;
	}

	/**
	 * If account has sufficient balance, make a transaction entry and update
	 * the balance
	 */
	@Override
	@Transactional
	public Transaction withdraw(Long accountId, Long amount, String txMode) {

		Account account = getAccount(accountId);
		accountValidator.validateBalanceForWithdraw(account, amount);
		if (txMode.equals(TransactionModeEnum.ATM.toString())) {
			transactionValidator.validateATMTransactionLimit(account, amount);
		}
		Transaction transaction = null;
		try {
			transaction = creditTransaction(account, amount, TransactionModeEnum.valueOf(txMode));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException("Unable to do withdrawal transaction");
		}
		return transaction;

	}

	private Transaction debitTransaction(Account account, Long amount, TransactionModeEnum txMode) {
		Transaction transaction;
		Transaction tx = new Transaction();
		tx.setAccount(account);
		tx.setTransactionDate(LocalDateTime.now());
		tx.setStatus(TransactionStatusEnum.SUCCESS.toString());
		tx.setTransactionType(TransactionTypeEnum.DEBIT.toString());
		tx.setActive(true);

		transaction = transactionRepo.save(tx);

		account.setBalance(account.getBalance() + amount);
		account.setModifiedDate(LocalDateTime.now());
		accountRepo.save(account);
		return transaction;
	}

	private Transaction creditTransaction(Account account, Long amount, TransactionModeEnum txMode) {
		Transaction transaction;
		Transaction tx = new Transaction();
		tx.setAccount(account);
		tx.setTransactionDate(LocalDateTime.now());
		tx.setStatus(TransactionStatusEnum.SUCCESS.toString());
		tx.setTransactionType(TransactionTypeEnum.DEBIT.toString());
		tx.setActive(true);
		tx.setMode(tx.toString());

		transaction = transactionRepo.save(tx);

		account.setBalance(account.getBalance() - amount);
		account.setModifiedDate(LocalDateTime.now());
		accountRepo.save(account);
		return transaction;
	}

	@Transactional
	@Override
	public Transaction fundTransfer(Long accountId, Long toAccountId, Long transactionAmount, String mode) {

		Account fromAccount = getAccount(accountId);
		Account toAccount = getAccount(toAccountId);
		accountValidator.validateBalanceForFundTransfer(fromAccount, transactionAmount);
		accountValidator.validateAccount(toAccount);

		Transaction transaction = null;
		try {
			transaction = debitTransaction(fromAccount, transactionAmount, TransactionModeEnum.valueOf(mode));
			creditTransaction(toAccount, transactionAmount, TransactionModeEnum.ONLINE);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException("Unable to do fundtransfer transaction");
		}
		return transaction;
	}

	@Override
	public Long getBalance(Long accountId) {
		return accountRepo.getBalance(accountId);
	}

	@Override
	public List<Transaction> getTransactionList(Long accountId) {
		return transactionRepo.findTransactionsByAccount(accountId);
	}

}
