package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Disaster;

import java.util.List;

public interface DisasterService {
	List<Disaster> find(String display, String userId);

	Disaster findByDisasterId(String disasterId);
}
