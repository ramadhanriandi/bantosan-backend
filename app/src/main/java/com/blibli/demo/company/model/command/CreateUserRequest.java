package com.blibli.demo.company.model.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
  private Integer userId;

  @Length(max = 100, message = "Too long name")
  private String name;

  private long birthdate;

  @NotBlank
  private String address;

  @NotBlank
  private String gender;
}
