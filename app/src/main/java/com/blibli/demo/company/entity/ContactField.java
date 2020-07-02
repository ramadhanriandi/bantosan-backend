package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = ContactField.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactField extends MongoBaseEntity {

	public static final String COLLECTION_NAME = "contactFields";

	public static final String FIELD_CONTACTFIELD_ID = "contactFieldId";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_PLACEHOLDER = "placeholder";

	@Field(value = ContactField.FIELD_CONTACTFIELD_ID)
	private Integer contactFieldId;

	@Field(value = ContactField.FIELD_NAME)
	private String name;

	@Field(value = ContactField.FIELD_TYPE)
	private String type;

	@Field(value = ContactField.FIELD_PLACEHOLDER)
	private String placeholder;
}
