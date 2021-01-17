package com.crs.microservices.netflixzuulapigatewayserver.service;

import java.util.Arrays;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final String PWD = "password";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.equals("Hotel"))
			return new User("Hotel", PWD, Arrays.asList(() -> "ROLE_HOTEL"));
		else if (username.equals("Guest"))
			return new User("Guest", PWD, Arrays.asList(() -> "ROLE_GUEST"));
		else
			return new User("username", PWD, Arrays.asList(() -> "ROLE_ADMIN"));
	}
}
