package com.arcelik.arcelikApp.repository;

import com.arcelik.arcelikApp.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Requests,Long> {
	List<Requests> findAll();
	Optional<Requests> findByRequestId(Long id);
	@Query("select u from Requests u where u.status = 1")
	List<Requests> findAllStatusEqualPending();
}
