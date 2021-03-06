package com.furkancetiner.ws.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.furkancetiner.ws.error.ApiError;
import com.furkancetiner.ws.model.User;
import com.furkancetiner.ws.repository.UserRepository;
import com.furkancetiner.ws.service.UserService;
import com.furkancetiner.ws.shared.GenericResponse;
import com.furkancetiner.ws.shared.Views;

@RestController
public class UserController {

	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@PostMapping("/api/1.0/users")
	public GenericResponse createUser(@Valid @RequestBody User user) {
		
		userService.createUser(user);
		return new GenericResponse("user created");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationException(MethodArgumentNotValidException exception) {
		ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		Map<String,String> validationErrors = new HashMap<>();
		for(FieldError e : exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(e.getField(), e.getDefaultMessage());
		}
		error.setValidationErrors(validationErrors);
		return error;
	}
	
	@GetMapping("/api/1.0/users")
	@JsonView(Views.Base.class)
	public List<User> getUsers() {
		return userService.getUsers();
	}
	

	
	@GetMapping("/api/1.0/users/page")
	@JsonView(Views.Base.class)
	public Page<User> getPageUsers(Pageable page/*currentPage&pageSize in application.yaml<--@RequestParam int currentPage,@RequestParam(required = false, defaultValue = "5") int pageSize*/) {	
		return userService.getPageUsers(page);
	}
	
}
