package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.Disaster;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DisasterRepository extends MongoRepository<Disaster, String> {
  Disaster findFirstByMarkForDeleteFalseAndId(String disasterId);

  List<Disaster> findAllByMarkForDeleteFalseOrderByUpdatedAtDesc();

  List<Disaster> findAllByMarkForDeleteFalseAndDisplayOrderByUpdatedAtDesc(String display);

  List<Disaster> findAllByMarkForDeleteFalseAndReporterIdOrderByUpdatedAtDesc(String userId);
}
