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
public class Post {
	public static final String FIELD_POST_ID = "postId";
	public static final String FIELD_TITLE = "title";
	public static final String FIELD_IMAGE = "image";
	public static final String FIELD_URL = "url";

	@Field(value = Post.FIELD_POST_ID)
	private Integer postId;

	@Field(value = Post.FIELD_TITLE)
	private String title;

	@Field(value = Post.FIELD_IMAGE)
	private String image;

	@Field(value = Post.FIELD_URL)
	private String url;
}
