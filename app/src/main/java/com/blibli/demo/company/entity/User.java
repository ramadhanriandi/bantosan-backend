package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = User.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends MongoBaseEntity {

	public static final String COLLECTION_NAME = "users";

	public static final String FIELD_USER_ID = "userId";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_BIRTHDATE = "birthdate";
	public static final String FIELD_ADDRESS = "address";
	public static final String FIELD_GENDER = "gender";

	@Field(value = User.FIELD_USER_ID)
	private Integer userId;

	@Field(value = User.FIELD_NAME)
	private String name;

	@Field(value = User.FIELD_BIRTHDATE)
	private long birthdate;

	@Field(value = User.FIELD_ADDRESS)
	private String address;

	@Field(value = User.FIELD_GENDER)
	private String gender;
}
