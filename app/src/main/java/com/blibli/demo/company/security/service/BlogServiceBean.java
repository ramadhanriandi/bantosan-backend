package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Blog;
import com.blibli.demo.company.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceBean implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Override
	public void create(Blog blog) throws Exception {
		blogRepository.save(blog);
	}

	@Override
	public List<Blog> find() {
		return blogRepository.findAllBy();
	}

	@Override
	public Blog findByBlogId(Integer blogId) {
		return blogRepository.findFirstByBlogId(blogId);
	}

	@Override
	public void update(Integer blogId, Blog blog) throws Exception {
		Blog updatedBlog = blogRepository.findFirstByBlogId(blogId);
		updatedBlog.setTitle(blog.getTitle());
		updatedBlog.setImages(blog.getImages());
		updatedBlog.setContent(blog.getContent());
		updatedBlog.setTags(blog.getTags());
		updatedBlog.setRelatedPosts(blog.getRelatedPosts());
		updatedBlog.setAuthor(blog.getAuthor());

		blogRepository.save(updatedBlog);
	}

	@Override
	public boolean delete(Integer blogId) {
		return blogRepository.deleteByBlogId(blogId) > 0;
	}
}
