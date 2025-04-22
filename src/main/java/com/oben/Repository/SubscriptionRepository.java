package com.oben.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oben.Model.Subscription;
import com.oben.Model.User;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	Subscription  findByUser(User user); 

}
