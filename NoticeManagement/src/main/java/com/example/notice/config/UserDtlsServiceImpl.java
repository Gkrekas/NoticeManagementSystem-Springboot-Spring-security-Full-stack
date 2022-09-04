package com.example.notice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.notice.entity.UserDtls;
import com.example.notice.repository.UserRepository;

public class UserDtlsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDtls user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Can not found user with email: " + username);
		} else {
			CustomUserDtls 	customUserDtls = new CustomUserDtls(user);
			return customUserDtls;
		}
	}

}
