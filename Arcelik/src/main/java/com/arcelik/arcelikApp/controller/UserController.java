package com.arcelik.arcelikApp.controller;

import com.arcelik.arcelikApp.entity.User;
import com.arcelik.arcelikApp.model.DeleteUser;
import com.arcelik.arcelikApp.model.UpdateUserRole;
import com.arcelik.arcelikApp.model.UserLoginRequest;
import com.arcelik.arcelikApp.model.UserRequest;
import com.arcelik.arcelikApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	UserService userService;

	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<User> login(@RequestBody UserLoginRequest request) {
		return new ResponseEntity<>(userService.getUser(request), HttpStatus.OK);
	}

	@GetMapping("/getAllUsers")
	@CrossOrigin
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/updateUserRole")
	@CrossOrigin
	public ResponseEntity<User> updateUserRole(@RequestBody UpdateUserRole updateUserRole) {

		return new ResponseEntity<>(userService.updateUserRole(updateUserRole), HttpStatus.OK);
	}

	@PostMapping
	public User postUser(@RequestBody UserRequest userRequest){
		return userService.postUser(userRequest);
	}

	@PostMapping("/deleteUser")
	@CrossOrigin
	public User deleteUser(@RequestBody DeleteUser deleteUser) {

		return userService.deleteUser(deleteUser.getUserId());
	}

	/*@PostMapping("/login")
	@CrossOrigin
	public User login(@RequestBody UserLoginRequest request) {
		return userService.getUser(request);
	}*/
}
