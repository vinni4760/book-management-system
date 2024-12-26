package com.nt.service;

import java.util.List;
import com.nt.dto.UserDto;
import com.nt.entity.User;
import com.nt.request.CreateUserRequest;

public interface IUserService {
   
	 public User addUser(CreateUserRequest user);

	 public List<UserDto> getallusers();
}
