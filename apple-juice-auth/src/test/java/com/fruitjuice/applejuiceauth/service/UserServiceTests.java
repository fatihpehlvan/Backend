package com.fruitjuice.applejuiceauth.service;

import com.fruitjuice.applejuiceauth.entity.User;
import com.fruitjuice.applejuiceauth.exception.UserAlreadyExistException;
import com.fruitjuice.applejuiceauth.exception.UserNotFoundException;
import com.fruitjuice.applejuiceauth.model.UserRequest;
import com.fruitjuice.applejuiceauth.model.UserUpdateRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class UserServiceTests {

	@Autowired
	UserService userService;

	@Test
	void should_save_user() {
		UserRequest userRequest = UserRequest.getInstance();
		User savedUser = userService.saveUser(userRequest);

		User expectedUser = User.getInstance();

		// assertion 1: expectedUser and savedUser Comparison
		Assertions.assertThat(savedUser).usingRecursiveComparison().ignoringExpectedNullFields()
				.isEqualTo(expectedUser);

		// assertion 2: database size and comparison with entity
		List<User> allUsers = userService.findAll();
		Assertions.assertThat(allUsers.size()).isEqualTo(1);
		Assertions.assertThat(allUsers.get(0)).usingRecursiveComparison().ignoringExpectedNullFields()
				.isEqualTo(expectedUser);
	}

	@Test
	void should_throw_exception_when_saving_existing_username() {
		UserRequest userRequest = UserRequest.getInstance();
		userService.saveUser(userRequest);
		UserRequest userRequestWithExistingUsername = new UserRequest();
		userRequestWithExistingUsername.setUsername(userRequest.getUsername());
		userRequestWithExistingUsername.setPassword("test-password");
		userRequestWithExistingUsername.setEmail("test-email1@gmail.com");
		Assertions.assertThatExceptionOfType(UserAlreadyExistException.class)
				.isThrownBy(() -> userService.saveUser(userRequestWithExistingUsername));
	}

	@Test
	void should_throw_exception_when_saving_existing_email() {
		UserRequest userRequest = UserRequest.getInstance();
		userService.saveUser(userRequest);
		UserRequest userRequestWithExistingEmail = new UserRequest();
		userRequestWithExistingEmail.setUsername("test-username1");
		userRequestWithExistingEmail.setPassword("test-password");
		userRequestWithExistingEmail.setEmail(userRequest.getEmail());
		Assertions.assertThatExceptionOfType(UserAlreadyExistException.class)
				.isThrownBy(() -> userService.saveUser(userRequestWithExistingEmail));
	}

	@Test
	void should_throw_exception_when_username_is_null() {
		UserRequest userRequest = UserRequest.getInstance();
		userRequest.setUsername(null);
		Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class)
				.isThrownBy(() -> userService.saveUser(userRequest));
	}

	@Test
	void should_throw_exception_when_password_is_null() {
		UserRequest userRequest = UserRequest.getInstance();
		userRequest.setPassword(null);
		Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class)
				.isThrownBy(() -> userService.saveUser(userRequest));
	}

	@Test
	void should_delete_existing_user() {
		UserRequest userRequest = UserRequest.getInstance();
		User savedUser = userService.saveUser(userRequest);
		Assertions.assertThat(userService.findAll().size()).isEqualTo(1);
		userService.deleteUser(savedUser.getId());
		Assertions.assertThat(userService.findAll().size()).isEqualTo(0);
	}

	@Test
	void should_throw_exception_when_delete_not_found_user() {
		Assertions.assertThatExceptionOfType(UserNotFoundException.class)
				.isThrownBy(() -> userService.deleteUser(1L));
	}

	@Test
	void should_update_existing_user() {
		UserRequest userRequest = UserRequest.getInstance();
		User user = userService.saveUser(userRequest);
		UserUpdateRequest userUpdateRequest = UserUpdateRequest.getInstance();
		userUpdateRequest.setPassword(null);

		User expectedUser = new User();
		if (Objects.nonNull(userUpdateRequest.getPassword()))
			expectedUser.setPassword(userUpdateRequest.getPassword());
		if (Objects.nonNull(userUpdateRequest.getLastName()))
			expectedUser.setLastName(userUpdateRequest.getLastName());
		if (Objects.nonNull(userUpdateRequest.getSecondName()))
			expectedUser.setSecondName(userUpdateRequest.getSecondName());
		if (Objects.nonNull(userUpdateRequest.getFirstName()))
			expectedUser.setFirstName(userUpdateRequest.getFirstName());
		expectedUser.setEmail(user.getEmail());
		expectedUser.setId(user.getId());

		user = userService.updateUser(user.getId(), userUpdateRequest);

		Assertions.assertThat(user).usingRecursiveComparison().ignoringExpectedNullFields()
				.isEqualTo(expectedUser);

	}

	@Test
	void should_throw_exception_when_update_not_found_user() {
		Assertions.assertThatExceptionOfType(UserNotFoundException.class)

				.isThrownBy(() -> userService.updateUser(1L, UserUpdateRequest.getInstance()));
	}

}
