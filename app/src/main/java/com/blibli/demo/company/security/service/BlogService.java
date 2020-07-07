package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Blog;

import java.util.List;

public interface BlogService {

	void create(Blog blog) throws Exception;

	List<Blog> find();

	Blog findByBlogId(Integer blogId);

	void update(Integer blogId, Blog blog) throws Exception;

	boolean delete(Integer blogId);
}
