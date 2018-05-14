package com.naresh.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.bankapp.dto.TransactionDTO;
import com.naresh.bankapp.model.Transaction;
import com.naresh.bankapp.service.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/{id}/withdraw")
	public ResponseEntity<?> withdraw(@PathVariable("id") Long accountId, @RequestBody TransactionDTO transactionDTO) {

		Transaction tx = accountService.withdraw( accountId, transactionDTO.getTransactionAmount(),
				transactionDTO.getMode());
		return new ResponseEntity<>(tx, HttpStatus.OK);
	}

	@GetMapping("/{id}/fundTransfer")
	public ResponseEntity<?> fundTransfer(@PathVariable("id") Long accountId, @RequestBody TransactionDTO transactionDTO) {

		Transaction tx = accountService.fundTransfer( accountId, transactionDTO.getToAccount(),
				transactionDTO.getTransactionAmount(), transactionDTO.getMode());
		return new ResponseEntity<>(tx, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/balance")
	public ResponseEntity<?> getBalance(@PathVariable("id") Long accountId) {

		Long balance = accountService.getBalance(accountId);
		return new ResponseEntity<>(balance, HttpStatus.OK);
	}
	

}
