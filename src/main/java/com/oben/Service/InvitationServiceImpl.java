package com.oben.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oben.Model.Invitation;
import com.oben.Repository.InvitationRepositoy;

import jakarta.mail.MessagingException;

@Service
public class InvitationServiceImpl implements InvitationService{

	@Autowired
	private InvitationRepositoy  invitationRepositoy;
	
	@Autowired
	private EmailService emailService;
	@Override
	public void sendInvitation(String email, Long projectId) throws MessagingException {
		String invitationToken = UUID.randomUUID().toString();
		Invitation invitation = new Invitation();
		invitation.setEmail(email);
		invitation.setProjectId(projectId);
		invitation.setToken(invitationToken);
		
		invitationRepositoy.save(invitation);
		String invitationLink = "http://localhost:5171/accept_invitation?token="+invitationToken;
		
		emailService.sendEmailWithToken(email, invitationLink);
	}

	@Override
	public Invitation acceptInvitation(String token) throws Exception {
		Invitation invitation = invitationRepositoy.findByToken(token);
		if(invitation ==null) {
			throw new Exception("invalid Invitation token");
			
		}
				return invitation;
	}

	@Override
	public String getTokenByUserMail(String usermail) throws Exception {
		Invitation invitation = invitationRepositoy.findByEmail(usermail);
		
				return invitation.getToken();
	}

	@Override
	public void deleteToken(String token) {
		invitationRepositoy.delete(invitationRepositoy.findByToken(token));
		
	}

}
