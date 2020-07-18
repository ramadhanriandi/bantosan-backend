package com.blibli.demo.company.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.blibli.demo.company.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
//	User findFirstByUserIdAndMarkForDeleteFalse(Integer userId);

//	Page<User> findByMarkForDeleteFalse(Pageable pageable);

//	long deleteByUserId(Integer userId);
  User findFirstById(String userId);

  List<User> findAllBy();

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
