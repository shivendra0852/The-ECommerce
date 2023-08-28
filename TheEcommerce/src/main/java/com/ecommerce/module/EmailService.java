package com.ecommerce.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String verificationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Email Verification Link");
        message.setText("Click the link to verify your email: http://yourdomain.com/verify?token=" + verificationToken);
        mailSender.send(message);
    }
}
