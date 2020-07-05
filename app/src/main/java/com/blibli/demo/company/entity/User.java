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

	public static final String FIELD_USERNAME = "username";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_PASSWORD = "password";
	public static final String FIELD_FULLNAME = "fullname";
	public static final String FIELD_PHONE = "phone";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_ROLE = "role";
	public static final String FIELD_AVATAR = "avatar";
	public static final String FIELD_IDENTITY = "identity";

	@Field(value = User.FIELD_USERNAME)
	private String username;

	@Field(value = User.FIELD_EMAIL)
	private String email;

	@Field(value = User.FIELD_PASSWORD)
	private String password;

	@Field(value = User.FIELD_FULLNAME)
	private String fullname;

	@Field(value = User.FIELD_PHONE)
	private String phone;

	@Field(value = User.FIELD_STATUS)
	private String status;

	@Field(value = User.FIELD_ROLE)
	private String role;

	@Field(value = User.FIELD_AVATAR)
	private String avatar;

	@Field(value = User.FIELD_IDENTITY)
	private String identity;
}
