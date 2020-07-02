package com.blibli.demo.company.controller;

import com.blibli.demo.company.command.CreateUserCommand;
import com.blibli.demo.company.model.Person;
import com.blibli.demo.company.model.command.CreateUserRequest;
import com.blibli.demo.company.model.web.CreateUserResponse;
import com.blibli.demo.company.service.UserService;
import com.blibli.demo.company.entity.User;
import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = UserControllerPath.BASE_PATH)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CommandExecutor commandExecutor;

	@RequestMapping(method = RequestMethod.POST)
	public Flux<User> bulkInsert() {
		return userService.bulkInsert(1000)
						.subscribeOn(Schedulers.elastic());
	}

	@RequestMapping(
					value = UserControllerPath.INSERT,
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public Mono<Response<CreateUserResponse>> insert(@RequestBody CreateUserRequest request, Person person) {

    log.info("Request from {}", person.getName());

	  return commandExecutor.execute(CreateUserCommand.class, request)
						.map(response -> ResponseHelper.ok(response))
						.subscribeOn(Schedulers.elastic());
	}

}
