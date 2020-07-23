package com.blibli.demo.company.model.command;

import com.blibli.demo.company.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDisasterRequest {
  private String name;
  private String category;
  private String status;
  private String description;
  private Location location;
  private String display;
  private String evidence;
}
