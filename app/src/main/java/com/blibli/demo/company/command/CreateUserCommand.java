package com.blibli.demo.company.command;

import com.blibli.demo.company.model.command.CreateUserRequest;
import com.blibli.demo.company.model.web.CreateUserResponse;
import com.blibli.demo.company.repository.UserRepository;
import com.blibli.oss.command.Command;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.blibli.demo.company.entity.User;

@Service
public class CreateUserCommand implements Command<CreateUserRequest, CreateUserResponse> {

  @Autowired
  UserRepository userRepository;

  @Override
  public Mono<CreateUserResponse> execute(CreateUserRequest request) {
    return Mono.fromCallable(() -> createUser(request))
            .flatMap(user -> userRepository.save(user))
            .map(user -> createUserResponse(user));
  }

  private User createUser(CreateUserRequest request) {
    User user = User.builder()
            .userId(1)
            .build();
    BeanUtils.copyProperties(request, user);
    return user;
  }

  private CreateUserResponse createUserResponse(User user) {
    CreateUserResponse response = new CreateUserResponse();
    BeanUtils.copyProperties(user, response);
    return response;
  }
}
