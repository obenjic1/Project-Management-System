package com.oben.Service;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public interface EmailService {
	
	void sendEmailWithToken(String userEmail, String link) throws MessagingException;
	

}
