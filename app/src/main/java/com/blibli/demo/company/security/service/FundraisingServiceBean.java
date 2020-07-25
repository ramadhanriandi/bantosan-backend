package com.blibli.demo.company.security.service;

import com.blibli.demo.company.constant.FundraisingStatus;
import com.blibli.demo.company.entity.Fundraising;
import com.blibli.demo.company.entity.User;
import com.blibli.demo.company.repository.FundraisingRepository;
import com.blibli.demo.company.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

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

	@Override
	public Fundraising update(String fundraisingId, Fundraising fundraising) {
		Fundraising updatedFundraising = fundraisingRepository.findFirstByMarkForDeleteFalseAndId(fundraisingId);

		if (fundraising.getTitle() != null) updatedFundraising.setTitle(fundraising.getTitle());
		if (fundraising.getDescription() != null) updatedFundraising.setDescription(fundraising.getDescription());
		if (fundraising.getDay() != null) updatedFundraising.setDay(fundraising.getDay());
		if (fundraising.getTarget() != null) updatedFundraising.setTarget(fundraising.getTarget());
		if (fundraising.getStatus() != null) updatedFundraising.setStatus(fundraising.getStatus());
		if (fundraising.getBanks() != null) updatedFundraising.setBanks(fundraising.getBanks());

		if (fundraising.getStatus().equals(FundraisingStatus.ONGOING) && updatedFundraising.getStartDate() == null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + (updatedFundraising.getDay()-1));
			Date endDate = cal.getTime();

			updatedFundraising.setStartDate(new Date());
			updatedFundraising.setEndDate(endDate);
		}

		fundraisingRepository.save(updatedFundraising);

		return updatedFundraising;
	}

	@Override
	public boolean delete(String fundraisingId) {
		Fundraising deletedFundraising = fundraisingRepository.findFirstByMarkForDeleteFalseAndId(fundraisingId);
	
		if (deletedFundraising == null) return false;

		deletedFundraising.setMarkForDelete(true);
		fundraisingRepository.save(deletedFundraising);

		return true;
	}
}
