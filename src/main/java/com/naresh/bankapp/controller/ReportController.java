package com.naresh.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.bankapp.model.Transaction;
import com.naresh.bankapp.service.AccountService;

@RestController
@RequestMapping("reports")
public class ReportController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/{accountId}/transactions")
	public ResponseEntity<?> listTransactions(@PathVariable("id") Long accountId) {

		List<Transaction> transactionList = accountService.getTransactionList(accountId);
		return new ResponseEntity<>(transactionList, HttpStatus.OK);
	}

}
