package com.oben.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oben.Model.User;
import com.oben.Service.UserService;

@RestController
@RequestMapping("api/Users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@GetMapping("profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwt(jwt);
		
	//	String fullName = SecurityContextHolder.getContext().getAuthentication().getName();
	//	System.out.println(jwt.substring(7));
	//	User user = userService.findUserByEmail(fullName);	
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
		
		
	}

}
