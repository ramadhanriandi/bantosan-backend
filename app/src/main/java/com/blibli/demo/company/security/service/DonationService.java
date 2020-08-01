package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Donation;

import java.util.List;

public interface DonationService {
	List<Donation> find(String userId, String fundraisingId);

	void create(Donation donation, String donaturId, String fundraisingId);

	Donation update(String donationId, Donation donation);
}
