package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.User;

import java.util.List;

public interface UserService {

	void create(User user);

	List<User> find();

	User findByUserId(String userId);

	User update(String userId, User user) throws Exception;
}
