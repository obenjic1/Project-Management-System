package com.oben.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oben.Model.PlanType;
import com.oben.Model.Subscription;
import com.oben.Model.User;
import com.oben.Service.Subscriptionservice;
import com.oben.Service.UserService;

@RestController
@RequestMapping("/api/subcription")
public class SubscriptionController {
	
	@Autowired
	private Subscriptionservice subscriptionService;
	
	@Autowired
	private UserService userService; 
	
	
	@GetMapping("/user")
	public ResponseEntity<Subscription> getUserSubscription () throws Exception{
		String fullName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findUserByFullName(fullName);
		Subscription subscription = subscriptionService.getUserBySubcsription(user.getId());
		return new ResponseEntity<Subscription>(subscription,HttpStatus.OK);

	}
	@PatchMapping("/upgrade")
	public ResponseEntity<Subscription> upgradeUserSubscription ( @RequestParam PlanType planType) throws Exception{
		String fullName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findUserByFullName(fullName);
		Subscription subscription = subscriptionService.upgradteSubscription(user.getId(), planType);
		return new ResponseEntity<Subscription>(subscription,HttpStatus.OK);

	}

}
