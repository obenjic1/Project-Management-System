package com.oben.Service;

import com.oben.Model.PlanType;
import com.oben.Model.Subscription;
import com.oben.Model.User;

public interface Subscriptionservice {
	
	Subscription createSubscription (User user);
	Subscription getUserBySubcsription  (Long userId)throws Exception;
	Subscription upgradteSubscription (Long userId,PlanType planType) throws Exception;
	boolean isValid(Long subscriptionId)throws Exception;

	
	

}
