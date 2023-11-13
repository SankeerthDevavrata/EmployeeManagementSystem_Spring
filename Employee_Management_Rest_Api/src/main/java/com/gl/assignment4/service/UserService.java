package com.gl.assignment4.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gl.assignment4.DTO.UserRegistrationDto;
import com.gl.assignment4.entity.User;

public interface UserService extends UserDetailsService{
	
	User save(UserRegistrationDto registrationDto);
}
