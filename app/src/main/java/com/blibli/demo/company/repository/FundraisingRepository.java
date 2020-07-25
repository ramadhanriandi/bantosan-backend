package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.Fundraising;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FundraisingRepository extends MongoRepository<Fundraising, String> {
  Fundraising findFirstByMarkForDeleteFalseAndId(String fundraisingId);

  List<Fundraising> findAllByMarkForDeleteFalseOrderByUpdatedAtDesc();

  List<Fundraising> findAllByMarkForDeleteFalseAndStatusOrderByUpdatedAtDesc(String status);

  List<Fundraising> findAllByMarkForDeleteFalseAndOrganizerIdOrderByUpdatedAtDesc(String userId);
}
