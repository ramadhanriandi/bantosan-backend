package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.User;

public interface UserMongoService {

	void create(User user);

//	Flux<User> bulkInsert(int total);
}
