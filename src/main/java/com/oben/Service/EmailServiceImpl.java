package com.oben.Service;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmailWithToken(String userEmail, String link) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
		
		String subject = "Join project team invitation Link";
		String text ="Click on link bellow to join the team " + link;
		
		helper.setSubject(subject);
		helper.setText(text, true);
		helper.setTo(userEmail);
		
		try {
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new MailSendException("failed to send message "); 
		}
	}

}
