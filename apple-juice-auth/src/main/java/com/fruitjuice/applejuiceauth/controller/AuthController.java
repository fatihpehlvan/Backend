package com.fruitjuice.applejuiceauth.controller;

import com.fruitjuice.applejuiceauth.model.UserLoginRequest;
import com.fruitjuice.applejuiceauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login (@RequestBody UserLoginRequest userLoginRequest) {
		return userService.login(userLoginRequest);
	}
}
