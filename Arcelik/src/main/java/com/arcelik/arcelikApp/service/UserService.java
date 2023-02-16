package com.arcelik.arcelikApp.service;

import com.arcelik.arcelikApp.entity.Requests;
import com.arcelik.arcelikApp.entity.Roles;
import com.arcelik.arcelikApp.entity.User;
import com.arcelik.arcelikApp.model.UpdateUserRole;
import com.arcelik.arcelikApp.model.UserLoginRequest;
import com.arcelik.arcelikApp.model.UserRequest;
import com.arcelik.arcelikApp.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User getUserById(Long userId) {
		return userRepository.findByUserId(userId);
	}

	public User getUser(UserLoginRequest userLoginRequest){
		User user = userRepository.findByRegistrationNumberAndPassword(userLoginRequest.getRegistrationNumber(), userLoginRequest.getPassword());
		if (Objects.isNull(user)){
			throw new RuntimeException("User not found");
		}
		return user;
	}

	public User postUser(UserRequest userRequest) {
		User user = new User();
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setRegistrationNumber(userRequest.getRegistrationNumber());
		user.setDepartment(userRequest.getDepartment());
		user.setPassword(userRequest.getPassword());
		user.setRole(userRequest.getRole());
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User updateUserRole(UpdateUserRole updateUserRole) {
		User user = userRepository.findByUserId(updateUserRole.getUserId());
		Roles roles = new Roles(updateUserRole.getNewRoleId());
		user.setRole(roles);
		return userRepository.save(user);
	}

	public User deleteUser(Long userId) {
		User user = userRepository.findByUserId(userId);
		userRepository.delete(user);
		return user;
	}
}
