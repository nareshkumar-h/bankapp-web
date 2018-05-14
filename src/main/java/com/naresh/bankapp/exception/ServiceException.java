package com.naresh.bankapp.exception;

public class ServiceException extends RuntimeException {

	public ServiceException(String message, Throwable th){
		super(message, th);
	}
	
	public ServiceException(String message){
		super(message);
	}
}
