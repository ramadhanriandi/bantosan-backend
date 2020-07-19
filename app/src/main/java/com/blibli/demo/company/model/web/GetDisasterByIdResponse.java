package com.blibli.demo.company.model.web;

import com.blibli.demo.company.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDisasterByIdResponse {
  private String id;
  private String name;
  private String category;
  private String description;
  private String evidence;
  private Location location;
  private Date updatedAt;
}
