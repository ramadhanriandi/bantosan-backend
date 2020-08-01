package com.blibli.demo.company.model.command;

import com.blibli.demo.company.entity.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFundraisingRequest {
  @NotBlank
  private String title;

  @NotBlank
  private String description;

  @NotNull
  private Integer day;

  @NotNull
  private Double target;

  @NotEmpty
  private Bank[] banks;

  @NotBlank
  private String organizer;

  @NotBlank
  private String image;
}
