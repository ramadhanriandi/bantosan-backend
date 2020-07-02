package com.blibli.demo.dto;

import com.blibli.demo.company.entity.Author;
import com.blibli.demo.company.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequest implements Serializable {

	private Integer blogId;
	private String title;
	private List<String> images;
	private String content;
	private List<String> tags;
	private List<Post> relatedPosts;
	private Author author;
}
