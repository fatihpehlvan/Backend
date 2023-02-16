package com.arcelik.arcelikApp.model;

import lombok.Data;

@Data
public class UserLoginRequest {

	private Long registrationNumber;

	private String password;

}
