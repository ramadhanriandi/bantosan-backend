package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.About;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AboutRepository extends MongoRepository<About, String> {

	List<About> findAllBy();
}
