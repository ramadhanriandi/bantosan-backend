package com.blibli.demo.company.service;

import com.blibli.demo.company.entity.User;
import reactor.core.publisher.Flux;

public interface UserService {

	void create(User user);

	Flux<User> bulkInsert(int total);
}
