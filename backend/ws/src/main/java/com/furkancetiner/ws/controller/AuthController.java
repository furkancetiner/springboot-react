package com.furkancetiner.ws.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.furkancetiner.ws.error.ApiError;
import com.furkancetiner.ws.model.User;
import com.furkancetiner.ws.repository.UserRepository;
import com.furkancetiner.ws.shared.Views;

@RestController
public class AuthController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/api/1.0/auth")
	@JsonView(Views.Base.class)
	public ResponseEntity<?> handleAuthentication(@RequestHeader(name = "Authorization",required = false) String authorization) {
		
		if(authorization==null) {
			ApiError error = new ApiError(401,"UNAUTHORIZED request","/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		
		String base64encoded = authorization.split("Basic ")[1];
		
		String decoded= new String(Base64.getDecoder().decode(base64encoded));// 
		
		String[] userDetail = decoded.split(":");
		String username=userDetail[0];
		String password=userDetail[1];
		
		User inDB = userRepository.findByUsername(username);
		
		if(inDB==null) {
			ApiError error = new ApiError(401,"UNAUTHORIZED request","/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		
		String hashedPassword= inDB.getPassword();
		if(!passwordEncoder.matches(password, hashedPassword)) {
			ApiError error = new ApiError(401,"UNAUTHORIZED request","/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		
		//userName, displayName,image
		Map<String,String>  responseBody= new HashMap<String, String>();
		
		responseBody.put("username", inDB.getUsername());
		responseBody.put("displayName", inDB.getDisplayName());
		responseBody.put("image", inDB.getImage());
		//log.info(authorization);
		return ResponseEntity.ok(inDB);
	}

	
	
	
	

	
}
