package com.oben.Service;

import org.springframework.stereotype.Service;

import com.oben.Model.Invitation;

import jakarta.mail.MessagingException;

@Service
public interface InvitationService {
	
	public void sendInvitation(String email,Long projectId) throws MessagingException;
	public Invitation  acceptInvitation(String token) throws Exception;
	public String getTokenByUserMail(String usermail)throws Exception;
	void deleteToken(String token);


}
