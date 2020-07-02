package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = Contact.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends MongoBaseEntity {

	public static final String COLLECTION_NAME = "contacts";

	public static final String FIELD_CONTACT_ID = "contactId";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_PHONE = "phone";
	public static final String FIELD_CONTENT = "content";

	@Field(value = Contact.FIELD_CONTACT_ID)
	private Integer contactId;

	@Field(value = Contact.FIELD_NAME)
	private String name;

	@Field(value = Contact.FIELD_EMAIL)
	private String email;

	@Field(value = Contact.FIELD_PHONE)
	private String phone;

	@Field(value = Contact.FIELD_CONTENT)
	private String content;
}
