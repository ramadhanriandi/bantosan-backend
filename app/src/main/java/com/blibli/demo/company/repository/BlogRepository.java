package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String> {

	Blog findFirstByBlogId(Integer blogId);

	List<Blog> findAllBy();
	
	long deleteByBlogId(Integer blogId);
}
