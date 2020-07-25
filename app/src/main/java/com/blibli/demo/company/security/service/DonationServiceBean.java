package com.blibli.demo.company.security.service;

import com.blibli.demo.company.constant.DonationStatus;
import com.blibli.demo.company.entity.Donation;
import com.blibli.demo.company.entity.Fundraising;
import com.blibli.demo.company.entity.User;
import com.blibli.demo.company.repository.DonationRepository;
import com.blibli.demo.company.repository.FundraisingRepository;
import com.blibli.demo.company.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DonationServiceBean implements DonationService {
	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FundraisingRepository fundraisingRepository;

	@Override
	public void create(Donation donation, String donaturId, String fundraisingId) {
		User donatur = userRepository.findFirstById(donaturId);
		Fundraising fundraising = fundraisingRepository.findFirstByMarkForDeleteFalseAndId(fundraisingId);
		donation.setStatus(DonationStatus.PENDING);
		donation.setDonatur(donatur);
		donation.setFundraising(fundraising);

		donationRepository.save(donation);
	}

	@Override
	public Donation update(String donationId, Donation donation) {
		Donation updatedDonation = donationRepository.findFirstById(donationId);

		if (donation.getStatus() != null) updatedDonation.setStatus(donation.getStatus());

		donationRepository.save(updatedDonation);

		return updatedDonation;
	}
}
