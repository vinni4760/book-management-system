package com.nt.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.dto.UserDto;
import com.nt.entity.Roles;
import com.nt.entity.User;
import com.nt.repository.IRoleRepository;
import com.nt.repository.IUserRepository;
import com.nt.request.CreateUserRequest;


@Service
public class UserServiceimpl implements IUserService {

@Autowired
private IRoleRepository roleRepository;

@Autowired
private IUserRepository  userRepository;

@Autowired
private PasswordEncoder encoder;

@Autowired
private ModelMapper mapper;


 Logger logger = LoggerFactory.getLogger(UserServiceimpl.class);

	@Override
	public User addUser(CreateUserRequest request) {

		logger.info("Inside addUser:{}",request.getEmail());

	Roles roles  = 	roleRepository.findByRole("ROLE_USER");
	      Set<Roles> userroles = new HashSet<Roles>();
	      userroles.add(roles);
	      User user = new User();
	      user.setEmail(request.getEmail());
	      user.setPassword(encoder.encode(request.getPassword()));
	      user.setRoles(userroles);
	       return userRepository.save(user);
	}
	@Override
	public List<UserDto> getallusers() {
		return getconverted(userRepository.findAll());
	}
	
	public UserDto convertodto(User user) {
		return mapper.map(user, UserDto.class);
	}
	
	public List<UserDto> getconverted(List<User> users){
		return users.stream().map(this::convertodto).toList();
	}

	 
}
