package com.fruitjuice.applejuiceauth.repository;

import com.fruitjuice.applejuiceauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByUsername(String username);

	List<User> findAllByEmail(String email);

	Optional<User> findById(Long id);

	Optional<User> findByEmailAndPassword(String email ,String password);
}
