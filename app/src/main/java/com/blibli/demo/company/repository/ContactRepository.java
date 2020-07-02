package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {

}
