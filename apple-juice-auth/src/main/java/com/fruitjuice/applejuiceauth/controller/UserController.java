package com.fruitjuice.applejuiceauth.controller;

import com.fruitjuice.applejuiceauth.entity.User;
import com.fruitjuice.applejuiceauth.model.UserRequest;
import com.fruitjuice.applejuiceauth.model.UserUpdateRequest;
import com.fruitjuice.applejuiceauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public User saveUser(@RequestBody UserRequest userRequest) {
		return userService.saveUser(userRequest);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@GetMapping("/all")
	public List<User> findAllUsers() {
		return userService.findAll();
	}

	@GetMapping("{id}")
	public User findUserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
		return userService.updateUser(id, userUpdateRequest);
	}

}
