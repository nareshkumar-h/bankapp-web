package com.naresh.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.bankapp.model.Account;
import com.naresh.bankapp.service.AccountService;

@RestController
@RequestMapping("users")
public class UserController {

	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/{userId}/accounts")
	public ResponseEntity<List<Account>> listAccounts(@PathVariable("userId") Long userId){
		
		List<Account> accounts = accountService.findMyAccounts(userId);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}
	
}
