package com.blibli.demo.company.controller;

import com.blibli.demo.base.BaseResponse;
import com.blibli.demo.base.ListBaseResponse;
import com.blibli.demo.base.SingleBaseResponse;
import com.blibli.demo.company.entity.Disaster;
import com.blibli.demo.company.model.command.CreateDisasterRequest;
import com.blibli.demo.company.model.command.UpdateDisasterRequest;
import com.blibli.demo.company.model.web.GetAllDisastersResponse;
import com.blibli.demo.company.model.web.GetDisasterByIdResponse;
import com.blibli.demo.company.model.web.ReporterResponse;
import com.blibli.demo.company.model.web.UpdateDisasterResponse;
import com.blibli.demo.company.security.service.DisasterService;
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
@RequestMapping(value = DisasterControllerPath.BASE_PATH)
public class DisasterController {
  @Autowired
  private DisasterService disasterService;

  @RequestMapping(
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity getAllDisaster(
          @RequestParam(value = "display", required = false) String display,
          @RequestParam(value = "userId", required = false) String userId
  ) throws Exception {
    List<Disaster> disasters = this.disasterService.find(display, userId);
    List<GetAllDisastersResponse> disastersResponses = new ArrayList<>();

    for (Disaster disaster : disasters) {
      GetAllDisastersResponse disastersResponse = GetAllDisastersResponse.builder().build();
      BeanUtils.copyProperties(disaster, disastersResponse);

      ReporterResponse reporterResponse = ReporterResponse.builder().build();
      BeanUtils.copyProperties(disaster.getReporter(), reporterResponse);
      disastersResponse.setReporter(reporterResponse);

      disastersResponses.add(disastersResponse);
    }

    return ResponseEntity.ok(
            new ListBaseResponse<>(
                    null,
                    null,
                    true,
                    null,
                    disastersResponses,
                    null
            )
    );
  }

  @RequestMapping(
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE,
          value = DisasterControllerPath.GET_BY_ID
  )
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity getDisasterById(@PathVariable String disasterId) throws Exception {
    Disaster disaster = this.disasterService.findByDisasterId(disasterId);
    GetDisasterByIdResponse disasterResponse = GetDisasterByIdResponse.builder().build();
    BeanUtils.copyProperties(disaster, disasterResponse);

    return ResponseEntity.ok(new SingleBaseResponse<>(
            null,
            null,
            true,
            null,
            disasterResponse
    ));
  }

  @RequestMapping(
          method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> createDisaster(@Valid @RequestBody CreateDisasterRequest createDisasterRequest) {
    Disaster disaster = Disaster.builder().build();
    BeanUtils.copyProperties(createDisasterRequest, disaster);
    this.disasterService.create(disaster, createDisasterRequest.getReporter());

    return ResponseEntity.ok(new BaseResponse(null, null, true, null));
  }

  @RequestMapping(
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          value = DisasterControllerPath.UPDATE_BY_ID
  )
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> updateDisaster(@PathVariable String disasterId, @Valid @RequestBody UpdateDisasterRequest updateDisasterRequest) {
    Disaster disaster = Disaster.builder().build();
    BeanUtils.copyProperties(updateDisasterRequest, disaster);

    Disaster updatedDisaster = this.disasterService.update(disasterId, disaster);

    UpdateDisasterResponse updateDisasterResponse = UpdateDisasterResponse.builder().build();
    BeanUtils.copyProperties(updatedDisaster, updateDisasterResponse);

    ReporterResponse reporterResponse = ReporterResponse.builder().build();
    BeanUtils.copyProperties(updatedDisaster.getReporter(), reporterResponse);
    updateDisasterResponse.setReporter(reporterResponse);

    return ResponseEntity.ok(new SingleBaseResponse<>(
            null,
            null,
            true,
            null,
            updateDisasterResponse
    ));
  }
}