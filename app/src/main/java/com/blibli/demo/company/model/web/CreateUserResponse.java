package com.blibli.demo.company.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
  private Integer userId;

  private String name;

  private long birthdate;

  private String address;

  private String gender;
}
