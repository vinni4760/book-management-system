package com.nt.dto;

import java.util.Collection;

import com.nt.entity.Roles;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

	 private Integer id;
	 private String email;
	 private Collection<Roles> roles;
}
