package com.naresh.bankapp.validation;

import com.naresh.bankapp.exception.ServiceException;
import com.naresh.bankapp.model.Account;

public class AccountValidation {

	public static void validateAccount(Account account){
		
		if (account == null) {
			throw new ServiceException("Invalid Account");
		}
	}
	
	public static void validateBalanceForWithdraw(Account account, Long withdrawAmount){
		if (account.getBalance() < withdrawAmount){
			throw new ServiceException("Insufficient Balance to withdraw");
		}
	}
	
	public static void validateBalanceForFundTransfer(Account account1, Long amount){
		if (account1.getBalance() < amount){
			throw new ServiceException("Insufficient Balance");
		}
	}
}
