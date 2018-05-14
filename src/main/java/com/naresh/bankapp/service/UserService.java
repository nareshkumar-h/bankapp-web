package com.naresh.bankapp.service;

import java.util.List;

import com.naresh.bankapp.dto.UserDTO;
import com.naresh.bankapp.model.User;


public interface UserService {

	List<User> list();
	
	UserDTO login(String email, String password);
	
	User findByEmail(String email);
	
}
