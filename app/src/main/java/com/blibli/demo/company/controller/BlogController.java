package com.blibli.demo.company.controller;

import com.blibli.demo.base.BaseResponse;
import com.blibli.demo.base.ListBaseResponse;
import com.blibli.demo.base.SingleBaseResponse;
import com.blibli.demo.company.entity.Blog;
import com.blibli.demo.company.service.BlogService;
import com.blibli.demo.dto.BlogRequest;
import com.blibli.demo.dto.BlogResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = BlogControllerPath.BASE_PATH)
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse create(@RequestBody BlogRequest request) throws Exception {
		Blog blog = Blog.builder().build();
		BeanUtils.copyProperties(request, blog);
		this.blogService.create(blog);
		return new BaseResponse(null, null, true, null);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ListBaseResponse<BlogResponse> find() throws Exception {
		List<Blog> blogs = this.blogService.find();
		List<BlogResponse> blogResponses = new ArrayList<>();

		for (Blog blog : blogs) {
			BlogResponse blogResponse = BlogResponse.builder().build();
			BeanUtils.copyProperties(blog, blogResponse);
			blogResponses.add(blogResponse);
		}
		return new ListBaseResponse<>(null, null, true, null, blogResponses, null);
	}

	@RequestMapping(value = BlogControllerPath.FIND_BY_BLOG_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleBaseResponse<BlogResponse> findByBlogId(@PathVariable Integer blogId) throws Exception {
		Blog blog = this.blogService.findByBlogId(blogId);
		BlogResponse blogResponse = BlogResponse.builder().build();
		BeanUtils.copyProperties(blog, blogResponse);
		return new SingleBaseResponse<>(null, null, true, null, blogResponse);
	}

	@RequestMapping(value = BlogControllerPath.FIND_BY_BLOG_ID, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse update(@PathVariable Integer blogId, @RequestBody BlogRequest request) throws Exception {
		Blog blog = Blog.builder().build();
		BeanUtils.copyProperties(request, blog);
		this.blogService.update(blogId, blog);
		return new BaseResponse(null, null, true, null);
	}

	@RequestMapping(value = BlogControllerPath.FIND_BY_BLOG_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse delete(@PathVariable Integer blogId) throws Exception {
		this.blogService.delete(blogId);
		return new BaseResponse(null, null, true, null);
	}
}
