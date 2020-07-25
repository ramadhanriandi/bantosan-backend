package com.blibli.demo.company.model.web;

import com.blibli.demo.company.entity.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFundraisingResponse {
  private String id;
  private String title;
  private String description;
  private Integer day;
  private Double target;
  private String status;
  private Date startDate;
  private Date endDate;
  private Bank[] banks;
  private Date createdAt;
  private Date updatedAt;
}
