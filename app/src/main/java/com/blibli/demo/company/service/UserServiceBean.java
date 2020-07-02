package com.blibli.demo.company.service;

import com.blibli.demo.company.entity.User;
import com.blibli.demo.company.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class UserServiceBean implements UserService {

//	public final int THREAD_NUMBER = 5;

	@Autowired
	private UserRepository userRepository;

//	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	@Override
	public void create(User user) {
		userRepository.save(user);
	}

	@Override
	public Flux<User> bulkInsert(int total) {
		return createFlux(total)
						.map(integer -> newUser())
						.flatMap(user -> userRepository.save(user))
						.doOnNext(user -> log.debug("Success insert user id = {}", user.getUserId()))
						.doOnComplete(() -> log.debug("Success insert total {} users", total));
	}

	private Flux<Integer> createFlux(int total) {
		return Flux.range(1, total);
	}

	private User newUser() {
		return User.builder()
								.userId(1)
								.name("User")
								.address("Address")
								.birthdate(1)
								.gender("M")
								.build();
	}
}
