
package com.oben.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oben.Model.PlanType;
import com.oben.Model.Subscription;
import com.oben.Model.User;
import com.oben.Repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements Subscriptionservice {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Override
	public Subscription createSubscription(User user) {
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setIsvalid(true);
		subscription.setSubscriptionStartDate(LocalDateTime.now());
		subscription.setSubscriptionEndDate(LocalDateTime.now().plusMonths(12));
		subscription.setPlanType(PlanType.FREE);
		Subscription saveSubscription = subscriptionRepository.save(subscription);
		return saveSubscription ;
	}

	@Override
	public Subscription getUserBySubcsription(Long userId) throws Exception {
	
		return subscriptionRepository.findByUser(userService.findUserById(userId));
	}

	@Override
	public Subscription upgradteSubscription(Long userId, PlanType planType) throws Exception {
		Subscription subscription = subscriptionRepository.findByUser(userService.findUserById(userId));
		subscription.setPlanType(planType);
		subscription.setSubscriptionStartDate(LocalDateTime.now());
		
		switch (planType) {
		case MONTHLY  :{
			subscription.setSubscriptionEndDate(LocalDateTime.now().plusMonths(1));			
			
			break;
		}case ANNUALLY : {
			
			subscription.setSubscriptionEndDate(LocalDateTime.now().plusMonths(12));			

			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + planType);
			
		}

		return subscriptionRepository.save(subscription);
	}

	@Override
	public boolean isValid(Long subscriptionId) throws Exception {
		Subscription subscription = subscriptionRepository.findById(subscriptionId).get();
		if(subscription.getPlanType().equals(PlanType.FREE)||subscription.getSubscriptionEndDate().isAfter(LocalDateTime.now()) || subscription.getSubscriptionEndDate().isEqual(LocalDateTime.now()) ) return true;
		else return false;
	}
}
