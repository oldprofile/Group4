package com.exadel.training.notification.mail;


import com.exadel.training.notification.Notification;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by HP on 16.07.2015.
 */
@Service
public class WrapperNotificationMail implements Notification{

    private final String username = "mrartem6695@gmail.com";
    private final String password = "jordan23!";
    private Properties properties;
    private Session session;

    public WrapperNotificationMail() {
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.debug", "true");

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }
    public void send(String to, String text) throws MessagingException {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Exadel");
            message.setText(text);

            Transport.send(message);
            System.out.println("Done");
    }
}
