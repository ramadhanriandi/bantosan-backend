package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.ContactField;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactFieldRepository extends MongoRepository<ContactField, String> {

	List<ContactField> findByMarkForDeleteFalse();
}
