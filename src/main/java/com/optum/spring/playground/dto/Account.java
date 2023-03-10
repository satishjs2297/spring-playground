package com.optum.spring.playground.dto;

import lombok.Data;

@Data
public class Account {

	private Long id;
	private String name;
	private Float amount;
	private String bankName;
	private String branchCode;
}
