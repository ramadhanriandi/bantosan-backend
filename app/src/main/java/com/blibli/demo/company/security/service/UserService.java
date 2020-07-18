package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.User;

public interface UserService {

	void create(User user);

	User update(String userId, User user) throws Exception;

//	Flux<User> bulkInsert(int total);
}
