package com.blibli.demo.company.repository;

import java.util.Optional;

import com.blibli.demo.company.entity.ERole;
import com.blibli.demo.company.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}