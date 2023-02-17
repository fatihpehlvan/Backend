package com.fruitjuice.applejuiceauth.exception;

public class UserAlreadyExistException extends RuntimeException {

	private static final String USERNAME_EXIST_MESSAGE = "The username %s is already exist.";
	private static final String EMAIL_EXIST_MESSAGE = "The email \"%s\" is already exist.";

	public UserAlreadyExistException(String message){
		super(message);
	}

	public static UserAlreadyExistException throwExistingUsernameException(String username) {
		return new UserAlreadyExistException(String.format(USERNAME_EXIST_MESSAGE, username));
	}

	public static UserAlreadyExistException throwExistingEmailException(String email) {
		return new UserAlreadyExistException(String.format(EMAIL_EXIST_MESSAGE, email));
	}

}
