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
public class GetAllDisastersResponse {
  private String id;
  private String name;
  private String category;
  private String status;
  private Location location;
  private String display;
  private Date createdAt;
  private Date updatedAt;
  private ReporterResponse reporter;
}
