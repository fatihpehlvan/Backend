package com.fruitjuice.applejuiceauth.util;

import com.fruitjuice.applejuiceauth.entity.User;

import com.fruitjuice.applejuiceauth.model.UserLoginRequest;
import com.fruitjuice.applejuiceauth.model.UserRequest;
import com.fruitjuice.applejuiceauth.model.UserUpdateRequest;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User userRequestToUser(UserRequest userRequest);

	User userLoginToUser(UserLoginRequest userLoginRequest);

	default User userUpdateRequestToUser(User user, UserUpdateRequest userUpdateRequest) {
		if (Objects.nonNull(userUpdateRequest.getFirstName()))
			user.setFirstName(userUpdateRequest.getFirstName());
		if (Objects.nonNull(userUpdateRequest.getSecondName()))
			user.setSecondName(userUpdateRequest.getSecondName());
		if (Objects.nonNull(userUpdateRequest.getLastName()))
			user.setLastName(userUpdateRequest.getLastName());
		if (Objects.nonNull(userUpdateRequest.getPassword()))
			user.setPassword(userUpdateRequest.getPassword());
		return user;
	}

}
