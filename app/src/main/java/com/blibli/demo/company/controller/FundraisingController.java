package com.blibli.demo.company.controller;

import com.blibli.demo.base.BaseResponse;
import com.blibli.demo.base.SingleBaseResponse;
import com.blibli.demo.company.entity.Fundraising;
import com.blibli.demo.company.model.command.CreateFundraisingRequest;
import com.blibli.demo.company.model.command.UpdateFundraisingRequest;
import com.blibli.demo.company.model.web.UpdateFundraisingResponse;
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

    Fundraising updatedFundraising = this.fundraisingService.update(fundraisingId, fundraising);

    UpdateFundraisingResponse updateFundraisingResponse = UpdateFundraisingResponse.builder().build();
    BeanUtils.copyProperties(updatedFundraising, updateFundraisingResponse);

    return ResponseEntity.ok(new SingleBaseResponse<>(
            null,
            null,
            true,
            null,
            updateFundraisingResponse
    ));
  }

  @RequestMapping(
          method = RequestMethod.DELETE,
          produces = MediaType.APPLICATION_JSON_VALUE,
          value = FundraisingControllerPath.DELETE_BY_ID
  )
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> deleteFundraising(@PathVariable String fundraisingId) {
    boolean isDeleteSuccess = this.fundraisingService.delete(fundraisingId);

    if (!isDeleteSuccess) return ResponseEntity.ok(
            new BaseResponse(
                    "Fundraising is not found",
                    "404",
                    false,
                    null
            )
    );

    return ResponseEntity.ok(new BaseResponse(null, null, true, null));
  }
}