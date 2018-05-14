package com.naresh.bankapp.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.naresh.bankapp.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("select sum(t.amount) as transaction_amount from Transaction t where t.account.id = ?1 and t.transactionDate = ?2 and t.status='SUCCESS' and t.active = true")
	public Long findTransactedAmountOnThatDay(Long accountId, LocalDate date);

	@Query("select t from Transaction t where t.account.id = ?1 order by t.transactionDate desc")
	public List<Transaction> findTransactionsByAccount(Long accountId);
	
}
