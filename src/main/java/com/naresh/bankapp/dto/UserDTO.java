package com.naresh.bankapp.dto;

import lombok.Data;

@Data
public class UserDTO {

	public UserDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	private Long id;

	private String name;

	private String email;
	
	private String password;

}
