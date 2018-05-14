package com.naresh.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.bankapp.dto.UserDTO;
import com.naresh.bankapp.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
		
		UserDTO user = userService.login(userDTO.getEmail(), userDTO.getPassword());
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	
}
