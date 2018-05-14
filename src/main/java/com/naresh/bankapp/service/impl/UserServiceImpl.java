package com.naresh.bankapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.naresh.bankapp.dao.UserRepository;
import com.naresh.bankapp.dto.UserDTO;
import com.naresh.bankapp.exception.ServiceException;
import com.naresh.bankapp.model.User;
import com.naresh.bankapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> list() {

		List<User> list = userRepository.findAll();
		return list;
	}

	@Override
	public User findByEmail(String email) {

		User user;
		try {
			user = userRepository.findByEmail(email);
		} catch (EmptyResultDataAccessException e) {
			user = null;
		}

		return user;
	}

	@Override
	public UserDTO login(String email, String password) {

		User user = null;
		user = findByEmail(email);
		if (user == null) {
			throw new ServiceException("Email ID not found");
		} 
		else if ( !user.getActive() ){
			throw new ServiceException("Account is disabled");
		}
		else if (!user.getPassword().equals(password)) {
			throw new ServiceException("Invalid Email/Password");
		}

		return new UserDTO(user.getId(), user.getName(), user.getEmail());
	}

}
