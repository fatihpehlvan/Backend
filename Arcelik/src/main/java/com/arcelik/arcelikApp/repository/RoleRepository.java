package com.arcelik.arcelikApp.repository;

import com.arcelik.arcelikApp.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {

}
