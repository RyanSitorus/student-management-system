
package com.example.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sms.entity.UserModel;
import com.example.sms.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepository.findByuserName(username);

		if (user != null) {
//			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//			if (requestAttributes != null) {
//				HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//				CharSequence password = request.getParameter("password");
//				if (passwordEncoder.matches(password, user.getPassword())) {
			return User.builder().username(user.getUserName()).password(user.getPassword()).roles(user.getRole().name())
					.build();
//				}
//
//			}
		}
		throw new UsernameNotFoundException("Username not found");
//		return null;

	}
}
