package com.arcelik.arcelikApp.model;

import com.arcelik.arcelikApp.entity.Department;
import com.arcelik.arcelikApp.entity.Roles;
import lombok.Data;

@Data
public class UserRequest {

	private String firstName;

	private String lastName;

	private String password;

	private Long registrationNumber;

	private Roles role;

	private Department department;

	public static UserRequest getInstance() {
		UserRequest userRequest = new UserRequest();
		userRequest.setFirstName("test-firstname");
		userRequest.setLastName("test-lastname");
		userRequest.setRegistrationNumber(-1L);
		return userRequest;
	}
}
