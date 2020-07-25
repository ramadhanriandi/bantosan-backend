package com.blibli.demo.company.model.web;

import com.blibli.demo.company.entity.Bank;
import com.blibli.demo.company.entity.DonationBank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFundraisingByIdResponse {
  private String id;
  private String title;
  private String description;
  private Integer day;
  private Double target;
  private Date endDate;
  private OrganizerResponse organizer;
  private Date updatedAt;
  private Bank[] banks;
  private Double totalDonation;
  private DonationBank[] donationByBank;
  private Integer donaturs;
}
