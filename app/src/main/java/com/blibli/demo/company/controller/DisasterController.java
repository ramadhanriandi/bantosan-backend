package com.blibli.demo.company.controller;

import com.blibli.demo.base.ListBaseResponse;
import com.blibli.demo.company.entity.Disaster;
import com.blibli.demo.company.model.web.GetAllDisastersResponse;
import com.blibli.demo.company.security.service.DisasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity getAllDisaster(@RequestParam String display, @RequestParam String userId) throws Exception {
    List<Disaster> disasters = this.disasterService.find(display, userId);
    List<GetAllDisastersResponse> disastersResponses = new ArrayList<>();

    for (Disaster disaster : disasters) {
      GetAllDisastersResponse disastersResponse = GetAllDisastersResponse.builder().build();
      BeanUtils.copyProperties(disaster, disastersResponse);
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
}