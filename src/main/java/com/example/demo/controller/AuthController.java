package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.JwtUtil;
//this is auth controller.
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final JwtUtil jwtUtil;

	public AuthController(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	
	@PostMapping("/login")
	
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		if(!"password".equals(loginRequest.password)) {
			return ResponseEntity.status(401).body("Invalid credentials");
			
		}
		String token=jwtUtil.generateToken(loginRequest.username());
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	public record LoginRequest(
	        String username,
	        String password
	) {
	}
	
	public record JwtResponse(String token) {
	}

}
