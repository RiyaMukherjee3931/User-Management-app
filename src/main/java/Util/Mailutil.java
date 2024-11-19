package Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class Mailutil {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject, String body, String to) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {

        
            MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage,true);

            mailMessage.setTo(to);
            mailMessage.setText(body);
            mailMessage.setSubject(subject);

            mailSender.send(mailMessage.getMimeMessage());
            return true;
            
        }

        catch (Exception e) {
            e.printStackTrace();
        }
		
		return false;
    }


	}

