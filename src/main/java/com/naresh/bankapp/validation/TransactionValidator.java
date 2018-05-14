package com.naresh.bankapp.validation;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naresh.bankapp.dao.TransactionRepository;
import com.naresh.bankapp.exception.ServiceException;
import com.naresh.bankapp.model.Account;

@Component
public class TransactionValidator {

	private static final Integer ATM_WITHDRAWAL_LIMIT_PER_DAY =  10000;
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	public void validateATMTransactionLimit(Account account, Long withDrawalAmount){
		
		LocalDate currentDate = LocalDate.now();
		Long totalAmount = transactionRepo.findTransactedAmountOnThatDay(account.getId(),currentDate);
		if ( (totalAmount + withDrawalAmount ) > ATM_WITHDRAWAL_LIMIT_PER_DAY ){
			throw new ServiceException("Withdrawal Amount exceeds per day Limit");
		}
		
		
	}
}
