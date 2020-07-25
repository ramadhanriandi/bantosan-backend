package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Fundraising;
import com.blibli.demo.company.entity.DonationBank;

import java.util.List;

public interface FundraisingService {
	List<Fundraising> find(Integer limit, String status, String userId);

	Fundraising findByFundraisingId(String fundraisingId);

	void create(Fundraising fundraising, String organizerId);

	Fundraising update(String fundraisingId, Fundraising fundraising);

	boolean delete(String fundraisingId);

	Double findTotalDonation(String fundraisingId);

	DonationBank[] findDonationByBank(String fundraisingId);

	Integer findDonaturs(String fundraisingId);
}
