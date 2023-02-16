package com.arcelik.arcelikApp.model;

import lombok.Data;

@Data
public class UpdateRequestStatus {
	private Long requestId;
	private Integer status;
	private String reasonOfReject;
}
