package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Donation;

public interface DonationService {
	void create(Donation donation, String donaturId, String fundraisingId);
}
