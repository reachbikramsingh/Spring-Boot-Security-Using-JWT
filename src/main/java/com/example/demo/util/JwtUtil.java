package com.example.demo.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Component
//"Create one object of this class and manage it."
//Spring creates it automatically when the application starts.
public class JwtUtil {
	private static final String SECRET_KEY = "my-super-secure-secret-key-for-jwt-signing-12345";

	private static final long EXPIRATION_MS = 15 * 60 * 1000;

	
	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	//old  style
	
//	public String generateToken(String userName) {
//		return Jwts.builder().setSubject(userName).setIssuedAt(new Date())
//				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
//				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256).compact();
//	}
	
//new style
		public String generateToken(String userName) {
			 return Jwts.builder()
			            .subject(userName)
			            .issuedAt(new Date())
			            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
			            .signWith(key)
			            .compact();
		}

	public String extractUsername(String token) {
		 Claims claims = Jwts.parser()
	                .verifyWith((javax.crypto.SecretKey) key)
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();

	        return claims.getSubject();
	    }

}
