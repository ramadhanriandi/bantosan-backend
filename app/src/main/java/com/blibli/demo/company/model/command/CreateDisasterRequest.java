package com.blibli.demo.company.model.command;

import com.blibli.demo.company.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDisasterRequest {
  @NotBlank
  private String name;

  @NotBlank
  private String category;

  @NotBlank
  private String description;

  @NotNull
  private Location location;

  @NotBlank
  private String evidence;

  @NotBlank
  private String reporter;
}
