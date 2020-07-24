package com.blibli.demo.company.controller;

import com.blibli.demo.base.BaseResponse;
import com.blibli.demo.company.entity.Fundraising;
import com.blibli.demo.company.model.command.CreateFundraisingRequest;
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
}