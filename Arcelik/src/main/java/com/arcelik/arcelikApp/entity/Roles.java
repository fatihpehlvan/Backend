package com.arcelik.arcelikApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor

public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column(nullable = false)
	private String roleName;


	public Roles(Long roleId) {
		this.roleId = roleId;
		if (roleId == 1) roleName = "ADMIN";
		else if(roleId == 2) roleName ="UPY";
		else roleName = "USER";
	}
}
