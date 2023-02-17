package com.fruitjuice.applejuiceauth.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserLoginRequest {
	@Email
	@NotNull
	private String email;

	@NotNull
	@Size(min = 5, max = 20)
	private String password;
}
