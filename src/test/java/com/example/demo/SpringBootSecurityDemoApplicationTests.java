package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.util.JwtUtil;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class SpringBootSecurityDemoApplicationTests {



	    private final JwtUtil jwtUtil = new JwtUtil();

	    @Test
	    void testGenerateToken() {

	        String username = "bikram";

	        String token = jwtUtil.generateToken(username);

	        assertNotNull(token);
	        assertFalse(token.isEmpty());
	        System.out.println(token);
	    }
	}


