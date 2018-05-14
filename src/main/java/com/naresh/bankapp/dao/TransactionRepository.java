package com.naresh.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naresh.bankapp.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	
}
