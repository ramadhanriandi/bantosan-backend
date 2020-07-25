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
public class GetAllFundraisingsResponse {
  private String id;
  private String title;
  private Integer day;
  private Double target;
  private String status;
  private Date endDate;
  private Date createdAt;
  private OrganizerResponse organizer;
  private Double totalDonation;
}
