package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Fundraising;

public interface FundraisingService {
	void create(Fundraising fundraising, String organizerId);

	Fundraising update(String fundraisingId, Fundraising fundraising);

	boolean delete(String fundraisingId);
}
