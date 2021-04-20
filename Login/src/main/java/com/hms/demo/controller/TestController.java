package com.hms.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.demo.model.MailRequest;
import com.hms.demo.model.MailResponse;
import com.hms.demo.model.User;
import com.hms.demo.repository.UserRepository;
import com.hms.demo.service.EmailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	private EmailService service;

	@PostMapping("/sendingEmail")
	public MailResponse sendEmail(@RequestBody MailRequest request) {
		Map<String, Object> model = new HashMap<>();
		model.put("Name", request.getName());
		model.put("location", "Bangalore,India");
		return service.sendEmail(request, model);

	}
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('RECEPTIONIST') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MANAGER')")
	public String moderatorAccess() {
		return "MANAGER Board.";
	}
	
	@GetMapping("/rec")
	@PreAuthorize("hasRole('RECEPTIONIST')")
	public String receptionistAccess() {
		return "RECEPTIONIST Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@GetMapping("/users/count")
	@PreAuthorize("hasRole('ADMIN')")
	public int countUsers() {
		List<User> userList =  userRepository.findAll();
		int count =0;
		for(User usr:userList) {
			count++;
		}
		System.out.println(count);
		return count;
	}
	
}

