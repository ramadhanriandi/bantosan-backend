package com.blibli.demo.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
	public static final String FIELD_BANK_ID = "bankId";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_ACCOUNT_NUMBER = "accountNumber";
	public static final String FIELD_ACCOUNT_HOLDER = "accountHolder";

	@Field(value = Bank.FIELD_BANK_ID)
	private Integer bankId;

	@Field(value = Bank.FIELD_NAME)
	private String name;

	@Field(value = Bank.FIELD_ACCOUNT_NUMBER)
	private String accountNumber;

	@Field(value = Bank.FIELD_ACCOUNT_HOLDER)
	private String accountHolder;
}
