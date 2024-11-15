package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.User;

public interface IUserRepository  extends JpaRepository<User,Integer>{
       public User findByEmail(String username);
}
