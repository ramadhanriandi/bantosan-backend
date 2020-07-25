package com.blibli.demo.company.controller;

import com.blibli.demo.base.BaseResponse;
import com.blibli.demo.base.ListBaseResponse;
import com.blibli.demo.base.SingleBaseResponse;
import com.blibli.demo.company.entity.Donation;
import com.blibli.demo.company.model.command.CreateDonationRequest;
import com.blibli.demo.company.model.command.UpdateDonationRequest;
import com.blibli.demo.company.model.web.DonaturResponse;
import com.blibli.demo.company.model.web.FundraisingResponse;
import com.blibli.demo.company.model.web.GetAllDonationsResponse;
import com.blibli.demo.company.model.web.UpdateDonationResponse;
import com.blibli.demo.company.security.service.DonationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = DonationControllerPath.BASE_PATH)
public class DonationController {
  @Autowired
  private DonationService donationService;

  @RequestMapping(
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity getAllDonation(
          @RequestParam(value = "userId", required = false) String userId,
          @RequestParam(value = "fundraisingId", required = false) String fundraisingId
  ) throws Exception {
    List<Donation> donations = this.donationService.find(userId, fundraisingId);
    List<GetAllDonationsResponse> donationsResponses = new ArrayList<>();

    for (Donation donation : donations) {
      GetAllDonationsResponse donationsResponse = GetAllDonationsResponse.builder().build();
      BeanUtils.copyProperties(donation, donationsResponse);

      DonaturResponse donaturResponse = DonaturResponse.builder().build();
      BeanUtils.copyProperties(donation.getDonatur(), donaturResponse);
      donationsResponse.setDonatur(donaturResponse);

      FundraisingResponse fundraisingResponse = FundraisingResponse.builder().build();
      BeanUtils.copyProperties(donation.getFundraising(), fundraisingResponse);
      donationsResponse.setFundraising(fundraisingResponse);

      donationsResponses.add(donationsResponse);
    }

    return ResponseEntity.ok(
            new ListBaseResponse<>(
                    null,
                    null,
                    true,
                    null,
                    donationsResponses,
                    null
            )
    );
  }


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

  @RequestMapping(
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          value = DonationControllerPath.UPDATE_BY_ID
  )
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> updateDonation(
          @PathVariable String donationId,
          @Valid @RequestBody UpdateDonationRequest updateDonationRequest
  ) {
    Donation donation = Donation.builder().build();
    BeanUtils.copyProperties(updateDonationRequest, donation);

    Donation updatedDonation = this.donationService.update(donationId, donation);

    UpdateDonationResponse updateDonationResponse = UpdateDonationResponse.builder().build();
    BeanUtils.copyProperties(updatedDonation, updateDonationResponse);

    return ResponseEntity.ok(new SingleBaseResponse<>(
            null,
            null,
            true,
            null,
            updateDonationResponse
    ));
  }
}