package com.blibli.demo.company.controller;

import com.blibli.demo.base.BaseResponse;
import com.blibli.demo.base.SingleBaseResponse;
import com.blibli.demo.company.entity.Fundraising;
import com.blibli.demo.company.model.command.CreateFundraisingRequest;
import com.blibli.demo.company.model.command.UpdateFundraisingRequest;
import com.blibli.demo.company.security.service.FundraisingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = FundraisingControllerPath.BASE_PATH)
public class FundraisingController {
  @Autowired
  private FundraisingService fundraisingService;

  @RequestMapping(
          method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> createFundraising(@Valid @RequestBody CreateFundraisingRequest createFundraisingRequest) {
    Fundraising fundraising = Fundraising.builder().build();
    BeanUtils.copyProperties(createFundraisingRequest, fundraising);
    this.fundraisingService.create(fundraising, createFundraisingRequest.getOrganizer());

    return ResponseEntity.ok(new BaseResponse(null, null, true, null));
  }

  @RequestMapping(
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          value = FundraisingControllerPath.UPDATE_BY_ID
  )
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> updateFundraising(
          @PathVariable String fundraisingId,
          @Valid @RequestBody UpdateFundraisingRequest updateFundraisingRequest
  ) {
    Fundraising fundraising = Fundraising.builder().build();
    BeanUtils.copyProperties(updateFundraisingRequest, fundraising);

    Fundraising updatedDisaster = this.fundraisingService.update(fundraisingId, fundraising);

//    UpdateDisasterResponse updateDisasterResponse = UpdateDisasterResponse.builder().build();
//    BeanUtils.copyProperties(updatedDisaster, updateDisasterResponse);
//
//    ReporterResponse reporterResponse = ReporterResponse.builder().build();
//    BeanUtils.copyProperties(updatedDisaster.getReporter(), reporterResponse);
//    updateDisasterResponse.setReporter(reporterResponse);

    return ResponseEntity.ok(new SingleBaseResponse<>(
            null,
            null,
            true,
            null,
            null
//            updateDisasterResponse
    ));
  }
}