package com.blibli.demo.company.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserByIdResponse {
  private String id;
  private String username;
  private String email;
  private String fullname;
  private String phone;
  private String status;
}