package com.furkancetiner.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.furkancetiner.ws.model.User;
import com.furkancetiner.ws.repository.UserRepository;

@Service
public class UserService {


	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void createUser(User user) {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		 userRepository.save(user);
	}
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public Page<User> getPageUsers(Pageable page){
		return userRepository.findAll(page);
	}
	

	
}
