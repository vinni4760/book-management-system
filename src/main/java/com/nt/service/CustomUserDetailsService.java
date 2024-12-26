package com.nt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.nt.entity.User;
import com.nt.repository.IUserRepository;
import jakarta.annotation.PostConstruct;

@Service
public class CustomUserDetailsService implements UserDetailsService{
@Autowired
private IUserRepository iUserRepository;

 Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

@PostConstruct
public void init() {
    System.out.println("CustomUserDetailsService loaded successfully");
}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info("inside loadUSerByName:{}",username);

	  User user = 	iUserRepository.findByEmail(username);
	  if(user==null)
		  throw new UsernameNotFoundException("User Not Found !");
		return new CustomUserDetails(user);
	}

}
