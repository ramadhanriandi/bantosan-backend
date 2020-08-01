package com.blibli.demo.company.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllDonationsResponse {
  private String id;
  private Double nominal;
  private String status;
  private Date createdAt;
  private DonaturResponse donatur;
  private FundraisingResponse fundraising;
  private String proof;
}
