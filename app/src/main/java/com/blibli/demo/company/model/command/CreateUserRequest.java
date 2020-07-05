package com.blibli.demo.company.model.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
  @NotBlank
  private String username;

  @NotBlank
  private String email;

  @NotBlank
  private String password;

//  @Length(max = 100, message = "Too long name")
//  private String fullname;
}
