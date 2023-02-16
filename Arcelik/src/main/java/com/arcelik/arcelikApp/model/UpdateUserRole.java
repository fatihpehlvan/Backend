package com.arcelik.arcelikApp.model;

import lombok.Data;

@Data
public class UpdateUserRole {
	private Long userId;
	private Long newRoleId;
}
