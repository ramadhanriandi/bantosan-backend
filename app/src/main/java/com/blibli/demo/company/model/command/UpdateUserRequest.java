package com.blibli.demo.company.model.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
  @Size(min = 3, max = 20)
  private String username;

  @Size(max = 50)
  @Email
  private String email;

  @Size(min = 6, max = 40)
  private String password;

  private String fullname;

  private String phone;

  private String status;
}
