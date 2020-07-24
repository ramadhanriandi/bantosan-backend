package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.Fundraising;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundraisingRepository extends MongoRepository<Fundraising, String> {
  Fundraising findFirstByMarkForDeleteFalseAndId(String fundraisingId);
}
