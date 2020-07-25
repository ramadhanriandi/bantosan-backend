package com.blibli.demo.company.security.service;

import com.blibli.demo.company.constant.DonationStatus;
import com.blibli.demo.company.constant.FundraisingStatus;
import com.blibli.demo.company.entity.*;
import com.blibli.demo.company.repository.DonationRepository;
import com.blibli.demo.company.repository.FundraisingRepository;
import com.blibli.demo.company.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class FundraisingServiceBean implements FundraisingService {
	@Autowired
	private FundraisingRepository fundraisingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DonationRepository donationRepository;

	@Override
	public List<Fundraising> find(Integer limit, String status, String userId) {
		List<Fundraising> fundraisings = null;

		if (limit == null && status == null && userId == null) {
			fundraisings = fundraisingRepository.findAllByMarkForDeleteFalseOrderByUpdatedAtDesc();
		} else {
			if (status != null) {
				if (limit == null) {
					fundraisings = fundraisingRepository.findAllByMarkForDeleteFalseAndStatusOrderByUpdatedAtDesc(status);
				} else {
					fundraisings = fundraisingRepository.findAllByMarkForDeleteFalseOrderByUpdatedAtDesc().subList(0, limit);
				}
			} else if (userId != null) {
				fundraisings = fundraisingRepository.findAllByMarkForDeleteFalseAndOrganizerIdOrderByUpdatedAtDesc(userId);
			}
		}

		return fundraisings;
	}

	@Override
	public Fundraising findByFundraisingId(String fundraisingId) {
		return fundraisingRepository.findFirstByMarkForDeleteFalseAndId(fundraisingId);
	}

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

	@Override
	public Double findTotalDonation(String fundraisingId) {
		List<Donation> donations = donationRepository.findAllByFundraisingIdAndStatusOrderByUpdatedAtDesc(
						fundraisingId, DonationStatus.VERIFIED
		);

		Double totalDonation = 0.0;

		for (Donation donation : donations) {
			totalDonation += donation.getNominal();
		}

		return totalDonation;
	}

	@Override
	public DonationBank[] findDonationByBank(String fundraisingId) {
		List<Donation> donations = donationRepository.findAllByFundraisingIdAndStatusOrderByUpdatedAtDesc(
						fundraisingId, DonationStatus.VERIFIED
		);
		Fundraising fundraising = fundraisingRepository.findFirstByMarkForDeleteFalseAndId(fundraisingId);

		Bank[] banks = fundraising.getBanks();
		DonationBank[] donationByBank = new DonationBank[banks.length];

		if (banks.length > 0) {
			for (int i = 0; i < banks.length; i++) {
				donationByBank[i] = new DonationBank(banks[i].getBankId(), 0.0);
			}

			for (Donation donation : donations) {
				DonationBank donationBank = donationByBank[donation.getBank()];
				donationByBank[donation.getBank()].setTotal(
								donationBank.getTotal() + donation.getNominal()
				);
			}
		}

		return donationByBank;
	}

	@Override
	public Integer findDonaturs(String fundraisingId) {
		return donationRepository.findAllByFundraisingIdAndStatusOrderByUpdatedAtDesc(
						fundraisingId, DonationStatus.VERIFIED
		).size();
	}
}
