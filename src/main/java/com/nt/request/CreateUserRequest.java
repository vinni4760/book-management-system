package com.nt.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CreateUserRequest {

	 private String email;
	 private String password;
}
