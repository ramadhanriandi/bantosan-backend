package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Fundraising;

public interface FundraisingService {
	void create(Fundraising fundraising, String organizerId);
}
