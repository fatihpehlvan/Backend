package com.arcelik.arcelikApp.repository;

import com.arcelik.arcelikApp.entity.Requests;
import com.arcelik.arcelikApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(Long id);

	User findByRegistrationNumber(Long registrationNumber);

	List<User> findAll();

	User findByRegistrationNumberAndPassword(Long registrationNumber, String password);
}
