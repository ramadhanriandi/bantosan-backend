package com.blibli.demo.company.security.service;

import com.blibli.demo.company.constant.FundraisingStatus;
import com.blibli.demo.company.entity.Fundraising;
import com.blibli.demo.company.entity.User;
import com.blibli.demo.company.repository.FundraisingRepository;
import com.blibli.demo.company.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FundraisingServiceBean implements FundraisingService {
	@Autowired
	private FundraisingRepository fundraisingRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void create(Fundraising fundraising, String organizerId) {
		User organizer = userRepository.findFirstById(organizerId);
		fundraising.setStatus(FundraisingStatus.PENDING);
		fundraising.setOrganizer(organizer);

		fundraisingRepository.save(fundraising);
	}
}
