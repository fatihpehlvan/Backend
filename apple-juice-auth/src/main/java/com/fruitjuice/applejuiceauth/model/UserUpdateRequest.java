package com.fruitjuice.applejuiceauth.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserUpdateRequest {

	@Size(min = 5, max = 20)
	private String password;

	private String firstName;

	private String secondName;

	private String lastName;

	public static UserUpdateRequest getInstance() {
		UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
		userUpdateRequest.setPassword("test-update-password");
		userUpdateRequest.setFirstName("test-update-firstname");
		userUpdateRequest.setSecondName("test-update-secondname");
		userUpdateRequest.setLastName("test-update-lastname");
		return userUpdateRequest;
	}
}
