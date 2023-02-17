package com.fruitjuice.applejuiceauth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fruitjuice.applejuiceauth.entity.User;
import com.fruitjuice.applejuiceauth.exception.UserAlreadyExistException;
import com.fruitjuice.applejuiceauth.exception.UserNotFoundException;

import com.fruitjuice.applejuiceauth.model.UserLoginRequest;
import com.fruitjuice.applejuiceauth.model.UserRequest;
import com.fruitjuice.applejuiceauth.model.UserUpdateRequest;
import com.fruitjuice.applejuiceauth.repository.UserRepository;
import com.fruitjuice.applejuiceauth.util.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	public User saveUser(UserRequest userRequest) {

		if (userRepository.findAllByEmail(userRequest.getEmail()).size() > 0) {
			throw UserAlreadyExistException.throwExistingEmailException(userRequest.getEmail());
		}

		if (userRepository.findAllByUsername(userRequest.getUsername()).size() > 0) {
			throw UserAlreadyExistException.throwExistingUsernameException(userRequest.getUsername());
		}

		return userRepository.save(userMapper.userRequestToUser(userRequest));
	}

	public User updateUser(Long id, UserUpdateRequest userUpdateRequest) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException(id);
		}
		User updatedUser = userMapper.userUpdateRequestToUser(userOptional.get(), userUpdateRequest);
		return userRepository.save(updatedUser);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void deleteUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException(id);
		}
		userRepository.delete(userOptional.get());
	}

	public User findUserById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException(id);
		}
		return userOptional.get();
	}

	public String login(UserLoginRequest userLoginRequest) {
		Optional<User> userOptional = userRepository.findByEmailAndPassword(userLoginRequest.getEmail(), userLoginRequest.getPassword());
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException(userLoginRequest);
		}
		Algorithm algorithm = Algorithm.HMAC256("aPPlEjuIce".getBytes());
		String token = JWT.create()
				.withSubject(userLoginRequest.getEmail())
				.withExpiresAt(new Date(System.currentTimeMillis()+ 10 * 60 * 1000))
				.sign(algorithm);
		return token;
	}
}