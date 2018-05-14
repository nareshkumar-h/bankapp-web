package com.naresh.bankapp.validation;

import org.springframework.stereotype.Component;

import com.naresh.bankapp.exception.ServiceException;
import com.naresh.bankapp.model.Account;

@Component
public class AccountValidator {

	public  void validateAccount(Account account){
		
		if (account == null) {
			throw new ServiceException("Invalid Account");
		}
	}
	
	public  void validateBalanceForWithdraw(Account account, Long withdrawAmount){
		if (account.getBalance() < withdrawAmount){
			throw new ServiceException("Insufficient Balance to withdraw");
		}
	}
	
	public  void validateBalanceForFundTransfer(Account account1, Long amount){
		if (account1.getBalance() < amount){
			throw new ServiceException("Insufficient Balance");
		}
	}
}
