package com.blibli.demo.company.repository;

import com.blibli.demo.company.entity.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DonationRepository extends MongoRepository<Donation, String> {
  Donation findFirstById(String donationId);

  List<Donation> findAllByOrderByUpdatedAtDesc();

  List<Donation> findAllByDonaturIdOrderByUpdatedAtDesc(String userId);

  List<Donation> findAllByFundraisingIdOrderByUpdatedAtDesc(String fundraisingId);

  List<Donation> findAllByDonaturIdAndFundraisingIdOrderByUpdatedAtDesc(String userId, String fundraisingId);
}
