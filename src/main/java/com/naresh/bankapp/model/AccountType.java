package com.naresh.bankapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="account_type")
public class AccountType {

	@Id	
	private Integer id;
	
	private String name;
	
}
