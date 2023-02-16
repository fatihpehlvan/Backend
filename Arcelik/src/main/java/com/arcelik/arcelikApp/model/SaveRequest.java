package com.arcelik.arcelikApp.model;

import lombok.Data;

@Data
public class SaveRequest {
	private Long userId;

	private Long inkNumber;

	private Long skuList;

	private String projectName;

	private String companyName;

	private String requestReason;

	private String requestDescription;

	private Integer numberOfProduct;

	private String budgetNumber;

	private String deadline;
}
