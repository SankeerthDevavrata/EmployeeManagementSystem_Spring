package com.gl.assignment4.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.assignment4.DTO.UserRegistrationDto;
import com.gl.assignment4.entity.Role;
import com.gl.assignment4.entity.User;
import com.gl.assignment4.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


//	private final BCryptPasswordEncoder passwordEncoder;

//	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//		this.userRepository = userRepository;
////		this.passwordEncoder = passwordEncoder;
//	}

	public User save(UserRegistrationDto registrationDto) {

		User user = new User(registrationDto.getUsername(), passwordEncoder.encode(registrationDto.getPassword()),
				Arrays.asList(new Role("USER")));

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid Credentials Please Try Again ");
		}
//		Collection<? extends GrantedAuthority> authorities = user.getRoles().stream()
//									.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
