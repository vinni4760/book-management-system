package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Roles;
import com.nt.repository.IRoleRepository;

@Service
public class RoleServiceimpl implements IRoleService {

	@Autowired
	private IRoleRepository iRoleRepository;

	@Override
	public void addRole(Roles role) {
		iRoleRepository.save(role);
	}

}
