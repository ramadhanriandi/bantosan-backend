package com.blibli.demo.company.security.service;

import com.blibli.demo.company.entity.ERole;
import com.blibli.demo.company.entity.User;
import com.blibli.demo.company.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceBean implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public void create(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> find() {
		List<User> users = userRepository.findAllBy();
		List<User> filteredUsers = new ArrayList<>();

		for (User user : users) {
			if (!user.getRoles().stream().anyMatch(role -> role.toString() == ERole.ROLE_ADMIN.toString())) {
				User filteredUser = User.builder().build();
				BeanUtils.copyProperties(user, filteredUser);
				filteredUsers.add(filteredUser);
			}
		}

		return filteredUsers;
	}

	@Override
	public User findByUserId(String userId) { return userRepository.findFirstById(userId); };

	@Override
	public User update(String userId, User user) throws Exception {
		User updatedUser = userRepository.findFirstById(userId);

		if (user.getUsername() != null) updatedUser.setUsername(user.getUsername());
		if (user.getEmail() != null) updatedUser.setEmail(user.getEmail());
		if (user.getPassword() != null) updatedUser.setPassword(encoder.encode(user.getPassword()));
		if (user.getFullname() != null) updatedUser.setFullname(user.getFullname());
		if (user.getPhone() != null) updatedUser.setPhone(user.getPhone());
		if (user.getStatus() != null) updatedUser.setStatus(user.getStatus());
		if (user.getAvatar() != null) updatedUser.setAvatar(user.getAvatar());
		if (user.getIdentity() != null) updatedUser.setIdentity(user.getIdentity());

		userRepository.save(updatedUser);

		return updatedUser;
	}
}
