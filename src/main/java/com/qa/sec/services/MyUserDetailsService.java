package com.qa.sec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.qa.sec.config.MyUserDetails;
import com.qa.sec.persistence.domain.User;
import com.qa.sec.persistence.repo.UserRepo;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User found = this.repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No user exists with name: " + username));
		return new MyUserDetails(found);

	}

}
