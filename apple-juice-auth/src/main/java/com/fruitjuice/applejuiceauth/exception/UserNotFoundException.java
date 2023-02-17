package com.fruitjuice.applejuiceauth.exception;

import com.fruitjuice.applejuiceauth.model.UserLoginRequest;

public class UserNotFoundException extends RuntimeException {

    private static final String USER_NOT_FOUND_MESSAGE = "User with id %d is not found!";
    private static final String USER_NOT_FOUND_MESSAGE_BY_EMAIL_AND_PASSWORD = "User with email %s and password %s is not found!";
    public UserNotFoundException(Long id) {
        super(String.format(USER_NOT_FOUND_MESSAGE, id));
    }
    public UserNotFoundException (UserLoginRequest userLoginRequest) {
        super(String.format(USER_NOT_FOUND_MESSAGE_BY_EMAIL_AND_PASSWORD, userLoginRequest.getEmail(), userLoginRequest.getPassword()));
    }
}
