package com.blibli.demo.company.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.blibli.demo.base.BaseResponse;
import com.blibli.demo.base.ListBaseResponse;
import com.blibli.demo.base.SingleBaseResponse;
import com.blibli.demo.company.constant.UserStatus;
import com.blibli.demo.company.entity.User;
import com.blibli.demo.company.entity.ERole;
import com.blibli.demo.company.entity.Role;
import com.blibli.demo.company.model.command.CreateUserRequest;
import com.blibli.demo.company.model.command.LoginRequest;
import com.blibli.demo.company.model.command.UpdateUserRequest;
import com.blibli.demo.company.model.web.GetAllUsersResponse;
import com.blibli.demo.company.model.web.JwtResponse;
import com.blibli.demo.company.model.web.UpdateUserResponse;
import com.blibli.demo.company.repository.RoleRepository;
import com.blibli.demo.company.repository.UserRepository;
import com.blibli.demo.company.security.jwt.JwtUtils;
import com.blibli.demo.company.security.service.UserDetailsImpl;
import com.blibli.demo.company.security.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = UserControllerPath.BASE_PATH)
public class UserController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  private UserService userService;

  @RequestMapping(
          method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          value = UserControllerPath.LOGIN
  )
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    return ResponseEntity.ok(new SingleBaseResponse<>(null, null, true, null,
            new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles)));
  }

  @RequestMapping(
          method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          value = UserControllerPath.REGISTER
  )
  public ResponseEntity<?> registerUser(@Valid @RequestBody CreateUserRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new BaseResponse("Error: Username is already taken!", "409", false, null));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new BaseResponse("Error: Email is already in use!", "409", false, null));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            UserStatus.UNVERIFIED);

    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            user.setStatus(UserStatus.VERIFIED);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new BaseResponse(null, null, true, null));
  }

  @RequestMapping(
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity getAllUser() throws Exception {
    List<User> users = this.userService.find();
    List<GetAllUsersResponse> usersResponses = new ArrayList<>();

    for (User user : users) {
      GetAllUsersResponse usersResponse = GetAllUsersResponse.builder().build();
      BeanUtils.copyProperties(user, usersResponse);
      usersResponses.add(usersResponse);
    }

    return ResponseEntity.ok(
            new ListBaseResponse<>(
                    null,
                    null,
                    true,
                    null,
                    usersResponses,
                    null
            )
    );
  }

  @RequestMapping(
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          value = UserControllerPath.UPDATE_BY_ID
  )
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserRequest updateUserRequest) throws Exception {
    User user = User.builder().build();
    BeanUtils.copyProperties(updateUserRequest, user);

    User updatedUser = this.userService.update(userId, user);

    return ResponseEntity.ok(new SingleBaseResponse<>(
            null,
            null,
            true,
            null,
            new UpdateUserResponse(
                    updatedUser.getId(),
                    updatedUser.getUsername(),
                    updatedUser.getEmail(),
                    updatedUser.getFullname(),
                    updatedUser.getPhone(),
                    updatedUser.getStatus()
            )
    ));
  }

  @RequestMapping(
          method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE,
          value = UserControllerPath.LOGOUT
  )
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity logoutUser() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(null);

    return ResponseEntity.ok(new BaseResponse(null, null, true, null));
  }
}