package com.naresh.bankapp.dto;

import lombok.Data;

@Data
public class TransactionDTO {

	private Long accountId;
	
	private String transactionType;
	
	private Long toAccount;
	
	private Long transactionAmount;
}
