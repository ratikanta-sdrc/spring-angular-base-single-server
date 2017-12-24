package org.sdrc.service;

import java.util.Optional;

import org.sdrc.domain.CustomUserDetails;
import org.sdrc.domain.User;
import org.sdrc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Optional<User> optionalUser = userRepository.findByUserName(username);
		
		optionalUser.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
				
		return optionalUser
				.map(CustomUserDetails::new)
				.get();
	}

}
