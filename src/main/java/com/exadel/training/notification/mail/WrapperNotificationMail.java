package com.exadel.training.notification.mail;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by HP on 16.07.2015.
 */
public class WrapperNotificationMail {

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
    public void sendMessage(String to, String text, String topic) throws MessagingException {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(topic);
            message.setText(text);

            Transport.send(message);
            System.out.println("Done");
    }
}
