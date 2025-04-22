package com.oben.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oben.Model.User;
import com.oben.Repository.UserRepository;
import com.oben.Response.AuthResponse;
import com.oben.Response.LoginRequest;
import com.oben.Service.CustomUserDetailsImpl;
import com.oben.Service.Subscriptionservice;
import com.oben.config.JwtGenerator;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsImpl customUserDetailsImpl;
	
	@Autowired
	private Subscriptionservice  subscriptionService;

	
	
	
	@PostMapping("/signUp")
	public ResponseEntity<AuthResponse> createUserHandler (@RequestBody User user) throws Exception{
		
		
		if(userRepository.findByEmail(user.getEmail())!=null) {
			
			throw new Exception("this email is already taken  " + user.getEmail());
		}
		
		User createdUser = new User ();
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
		createdUser.setEmail(user.getEmail());
		createdUser.setFullName(user.getFullName());
		userRepository.save(createdUser);
		subscriptionService.createSubscription(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(createdUser.getEmail(), createdUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = JwtGenerator.generateToken(authentication);

		
		AuthResponse res = new AuthResponse();
		res.setJwt(jwt);
		res.setMessage("signUp is Successfull");
		
		return new ResponseEntity<AuthResponse>(res,HttpStatus.CREATED);
		
		
		
	}
	
	 @PostMapping("/signIn") 
	public ResponseEntity<AuthResponse> signInHandler (@RequestBody LoginRequest loginRequest){
		
		 String username = loginRequest.getEmail();
		 String password = loginRequest.getPassword();
		
			Authentication authentication = authenticate(username, password);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			String jwt = JwtGenerator.generateToken(authentication);
			
			
			
			AuthResponse res = new AuthResponse();
			res.setJwt(jwt);
			res.setMessage("welcome to your home page");
			return new ResponseEntity<AuthResponse>(res,HttpStatus.CREATED);
		
	}

	private Authentication authenticate  (String username, String password) {
			
		UserDetails userDetails = customUserDetailsImpl.loadUserByUsername(username);
		if(userDetails==null) {
			throw new BadCredentialsException("invalid user name ");
			
		} if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid passord ");

		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	}


}
