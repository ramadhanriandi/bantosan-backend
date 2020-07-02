package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	public static final String FIELD_AUTHOR_ID = "authorId";
	public static final String FIELD_NAME = "name";

	@Field(value = Author.FIELD_AUTHOR_ID)
	private Integer authorId;

	@Field(value = Author.FIELD_NAME)
	private String name;
}
