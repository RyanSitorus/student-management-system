package com.example.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sms.entity.UserModel;
import com.example.sms.entity.UserRole;
import com.example.sms.repository.UserRepository;
import com.example.sms.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

//	private static List<UserModel> userModels = new ArrayList<>();
	private final PasswordEncoder passwordEncoder;

//	@PostConstruct
//	public void postConstruct() {
//		UserModel user = new UserModel();
//		user.setRole(UserRole.ADMIN);
//		user.setUserName("admin");
//		user.setPassword(passwordEncoder.encode("abc"));
//		userModels.add(user);
//	}

	public UserModel register(UserModel userModel) {
		userModel.setRole(UserRole.USER);
		userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
		return userRepository.save(userModel);
	}

}
