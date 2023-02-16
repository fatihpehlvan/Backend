package com.arcelik.arcelikApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity

@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Requests extends EntityBase{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long requestId;

	@Column
	private Long inkNumber;

	@Column(nullable = false)
	private Long skuList;

	@Column(nullable = false)
	private String projectName;

	@Column(nullable = false)
	private String companyName;

	@Column
	private String requestReason;

	@Column
	private String requestDescription;

	@Column(nullable = false)
	private Integer numberOfProduct;

	@Column(nullable = false)
	private String budgetNumber;

	@Column
	private Date deadline;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	User user;

	@Column
	private Integer status = 1;

	@Column
	private String reasonOfReject;
}
