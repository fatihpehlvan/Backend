package com.fruitjuice.applejuiceauth.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

	@NotNull
	private String username;

	@NotNull
	@Size(min = 5, max = 20)
	private String password;

	@Email
	@NotNull
	private String email;

	private String firstName;

	private String secondName;

	private String lastName;

	public static UserRequest getInstance() {
		UserRequest userRequest = new UserRequest();
		userRequest.setUsername("test-username");
		userRequest.setPassword("test-password");
		userRequest.setFirstName("test-firstname");
		userRequest.setSecondName("test-secondname");
		userRequest.setLastName("test-lastname");
		userRequest.setEmail("test-email@gmail.com");
		return userRequest;
	}
}
