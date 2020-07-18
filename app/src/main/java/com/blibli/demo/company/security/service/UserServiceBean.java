package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.User;
import com.blibli.demo.company.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceBean implements UserService {

//	public final int THREAD_NUMBER = 5;

	@Autowired
	private UserRepository userRepository;

//	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	@Autowired
	PasswordEncoder encoder;

	@Override
	public void create(User user) {
		userRepository.save(user);
	}

	@Override
	public User update(String userId, User user) throws Exception {
		User updatedUser = userRepository.findFirstById(userId);

		if (user.getUsername() != null) updatedUser.setUsername(user.getUsername());
		if (user.getEmail() != null) updatedUser.setEmail(user.getEmail());
		if (user.getPassword() != null) updatedUser.setPassword(encoder.encode(user.getPassword()));
		if (user.getFullname() != null) updatedUser.setFullname(user.getFullname());
		if (user.getPhone() != null) updatedUser.setPhone(user.getPhone());
		if (user.getStatus() != null) updatedUser.setStatus(user.getStatus());

		userRepository.save(updatedUser);

		return updatedUser;
	}

//	@Override
//	public Flux<User> bulkInsert(int total) {
//		return createFlux(total)
//						.map(integer -> newUser())
//						.flatMap(user -> userRepository.save(user))
//						.doOnNext(user -> log.debug("Success insert user id = {}", user.getUserId()))
//						.doOnComplete(() -> log.debug("Success insert total {} users", total));
//	}

//	private Flux<Integer> createFlux(int total) {
//		return Flux.range(1, total);
//	}
//
//	private User newUser() {
//		return User.builder()
//								.userId(1)
//								.name("User")
//								.address("Address")
//								.birthdate(1)
//								.gender("M")
//								.build();
//	}
}
