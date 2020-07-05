package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
//	User findFirstByUserIdAndMarkForDeleteFalse(Integer userId);

//	Page<User> findByMarkForDeleteFalse(Pageable pageable);

//	long deleteByUserId(Integer userId);
}
