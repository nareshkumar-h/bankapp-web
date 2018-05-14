package com.naresh.bankapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naresh.bankapp.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	@Query("select a from Account a where a.user.id = ?1")
	List<Account> findByUser(Long userId);

	@Query("select balance from Account a where a.id = ?1")
	Long getBalance(Long accountId);
}
