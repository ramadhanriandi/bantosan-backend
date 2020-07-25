package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Fundraising;

import java.util.List;

public interface FundraisingService {
	List<Fundraising> find(Integer limit, String status, String userId);

	void create(Fundraising fundraising, String organizerId);

	Fundraising update(String fundraisingId, Fundraising fundraising);

	boolean delete(String fundraisingId);

	Double findTotalDonation(String fundraisingId);
}
