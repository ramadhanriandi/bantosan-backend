package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = About.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class About extends MongoBaseEntity {

	public static final String COLLECTION_NAME = "about";

	public static final String FIELD_ABOUT_ID = "aboutId";
	public static final String FIELD_CONTENT = "content";
	public static final String FIELD_AUTHOR = "author";

	@Field(value = About.FIELD_ABOUT_ID)
	private Integer aboutId;

	@Field(value = About.FIELD_CONTENT)
	private String content;

	@Field(value = About.FIELD_AUTHOR)
	private Author author;
}
