package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DonationRepository extends MongoRepository<Donation, String> {
  Donation findFirstById(String donationId);
}
