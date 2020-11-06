package com.qa.sec.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.sec.persistence.domain.User;
import com.qa.sec.services.UserService;

@RestController
@CrossOrigin
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerNewUser(@RequestBody User user) {
		return new ResponseEntity<String>(this.service.registerNewUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/user")
	public String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		return "Hello, " + currentPrincipalName + "!";
	}

}
