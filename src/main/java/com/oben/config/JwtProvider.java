package com.oben.config;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
	
	
	 
	static	SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRETE_kEY.getBytes());
	
	
	
	public static String generateToken(Authentication auth) {
	//	Collection<?extends GrantedAuthority> authorities = auth.getAuthorities();
		
		String jwt = Jwts.builder().setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+ 86400000))
				.claim("email", auth.getName())
				.signWith(key)
				.compact();
		
		return jwt;
	}
	
	
	public static String getEmailFromToken(String jwt) {
		jwt= jwt.substring(7);

		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String email = String.valueOf(claims.get("email"));
		return email;

	}

}
