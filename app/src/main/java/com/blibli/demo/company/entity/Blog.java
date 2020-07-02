package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = Blog.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blog extends MongoBaseEntity {

	public static final String COLLECTION_NAME = "blogs";

	public static final String FIELD_BLOG_ID = "blogId";
	public static final String FIELD_TITLE = "title";
	public static final String FIELD_IMAGES = "images";
	public static final String FIELD_CONTENT = "content";
	public static final String FIELD_TAGS = "tags";
	public static final String FIELD_RELATEDPOSTS = "relatedPosts";
	public static final String FIELD_AUTHOR = "author";

	@Field(value = Blog.FIELD_BLOG_ID)
	private Integer blogId;

	@Field(value = Blog.FIELD_TITLE)
	private String title;

	@Field(value = Blog.FIELD_IMAGES)
	private List<String> images;

	@Field(value = Blog.FIELD_CONTENT)
	private String content;

	@Field(value = Blog.FIELD_TAGS)
	private List<String> tags;

	@Field(value = Blog.FIELD_RELATEDPOSTS)
	private List<Post> relatedPosts;

	@Field(value = Blog.FIELD_AUTHOR)
	private Author author;
}
