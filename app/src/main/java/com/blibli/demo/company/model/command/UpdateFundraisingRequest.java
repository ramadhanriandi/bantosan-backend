package com.blibli.demo.company.model.command;

import com.blibli.demo.company.entity.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFundraisingRequest {
  private String title;
  private String description;
  private Integer day;
  private Double target;
  private String status;
  private Bank[] banks;
}
