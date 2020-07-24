package com.blibli.demo.company.controller;

import com.blibli.demo.base.BaseResponse;
import com.blibli.demo.company.entity.Donation;
import com.blibli.demo.company.model.command.CreateDonationRequest;
import com.blibli.demo.company.security.service.DonationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = DonationControllerPath.BASE_PATH)
public class DonationController {
  @Autowired
  private DonationService donationService;

  @RequestMapping(
          method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> createDonation(@Valid @RequestBody CreateDonationRequest createDonationRequest) {
    Donation donation = Donation.builder().build();
    BeanUtils.copyProperties(createDonationRequest, donation);
    this.donationService.create(donation, createDonationRequest.getDonatur(), createDonationRequest.getFundraising());

    return ResponseEntity.ok(new BaseResponse(null, null, true, null));
  }
}