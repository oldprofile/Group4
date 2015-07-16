package com.exadel.training.notification.mail;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by HP on 16.07.2015.
 */
public class WrapperNotificationMail {
    private MimeMessage mimeMessage;
    private String from = "artem6695@mail.ru";
    private String host = "localhost";
    private Properties properties = System.getProperties();
    private Session session;

    public WrapperNotificationMail() {
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true"); //enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        final String password = "jrcfyf"; // correct password for gmail id
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getInstance(properties,auth);

    }
    public void sendMessage(String to) throws MessagingException {
        mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));
        mimeMessage.setSubject("This is the Subject Line!");
        Transport.send(mimeMessage);
    }
}
