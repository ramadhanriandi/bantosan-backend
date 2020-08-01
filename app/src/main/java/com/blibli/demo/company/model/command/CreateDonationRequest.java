package com.blibli.demo.company.model.command;

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
public class CreateDonationRequest {
  @NotNull
  private Double nominal;

  @NotBlank
  private String donatur;

  @NotNull
  private Integer bank;

  @NotBlank
  private String fundraising;

  @NotBlank
  private String proof;
}
