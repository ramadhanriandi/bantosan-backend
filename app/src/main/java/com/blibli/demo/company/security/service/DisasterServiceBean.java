package com.blibli.demo.company.security.service;

import com.blibli.demo.company.constant.DisasterDisplay;
import com.blibli.demo.company.constant.DisasterStatus;
import com.blibli.demo.company.entity.Disaster;
import com.blibli.demo.company.entity.User;
import com.blibli.demo.company.repository.DisasterRepository;
import com.blibli.demo.company.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DisasterServiceBean implements DisasterService {
	@Autowired
	private DisasterRepository disasterRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Disaster> find(String display, String userId) {
		List<Disaster> disasters = null;

		if (display == null && userId == null) {
			disasters = disasterRepository.findAllByMarkForDeleteFalseOrderByUpdatedAtDesc();
		}
		if (display != null && userId == null) {
			disasters = disasterRepository.findAllByMarkForDeleteFalseAndDisplayOrderByUpdatedAtDesc(display);
		}
		if (display == null && userId != null) {
			disasters = disasterRepository.findAllByMarkForDeleteFalseAndReporterIdOrderByUpdatedAtDesc(userId);
		}
		if (display != null && userId != null) {
			disasters = disasterRepository.findAllByMarkForDeleteFalseAndDisplayAndReporterIdOrderByUpdatedAtDesc(display, userId);
		}

		return disasters;
	}

	@Override
	public Disaster findByDisasterId(String disasterId) {
		return disasterRepository.findFirstByMarkForDeleteFalseAndId(disasterId);
	}

	@Override
	public void create(Disaster disaster, String reportedId) {
		User reporter = userRepository.findFirstById(reportedId);
		disaster.setDisplay(DisasterDisplay.SHOW);
		disaster.setStatus(DisasterStatus.PENDING);
		disaster.setReporter(reporter);

		disasterRepository.save(disaster);
	}

	@Override
	public Disaster update(String disasterId, Disaster disaster) {
		Disaster updatedDisaster = disasterRepository.findFirstByMarkForDeleteFalseAndId(disasterId);

		if (disaster.getName() != null) updatedDisaster.setName(disaster.getName());
		if (disaster.getCategory() != null) updatedDisaster.setCategory(disaster.getCategory());
		if (disaster.getStatus() != null) updatedDisaster.setStatus(disaster.getStatus());
		if (disaster.getDescription() != null) updatedDisaster.setDescription(disaster.getDescription());
		if (disaster.getLocation() != null) updatedDisaster.setLocation(disaster.getLocation());
		if (disaster.getDisplay() != null) updatedDisaster.setDisplay(disaster.getDisplay());
		if (disaster.getEvidence() != null) updatedDisaster.setEvidence(disaster.getEvidence());

		disasterRepository.save(updatedDisaster);

		return updatedDisaster;
	}

	@Override
	public boolean delete(String disasterId) {
		Disaster deletedDisaster = disasterRepository.findFirstByMarkForDeleteFalseAndId(disasterId);

		if (deletedDisaster == null) return false;

		deletedDisaster.setMarkForDelete(true);
		disasterRepository.save(deletedDisaster);

		return true;
	}
}
