package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.Disaster;
import com.blibli.demo.company.repository.DisasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DisasterServiceBean implements DisasterService {
	@Autowired
	private DisasterRepository disasterRepository;

	@Override
	public List<Disaster> find(String display, String userId) {
		List<Disaster> disasters;

		if (display == null && userId == null) {
			disasters = disasterRepository.findAllByMarkForDeleteFalseOrderByUpdatedAtDesc();
		} else {
			if (display != null) {
				disasters = disasterRepository.findAllByMarkForDeleteFalseAndDisplayOrderByUpdatedAtDesc(display);
			}
			else {
				disasters = disasterRepository.findAllByMarkForDeleteFalseAndReporterIdOrderByUpdatedAtDesc(userId);
			}
		}

		return disasters;
	}

	@Override
	public Disaster findByDisasterId(String disasterId) {
		return disasterRepository.findFirstByMarkForDeleteFalseAndId(disasterId);
	}
}
