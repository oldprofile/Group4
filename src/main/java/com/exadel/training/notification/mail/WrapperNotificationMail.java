package com.exadel.training.notification.mail;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by HP on 16.07.2015.
 */
public class WrapperNotificationMail {
    public WrapperNotificationMail() {


    }
    public void sendMessage(String to) throws MessagingException {
        final String username = "mrartem6695@gmail.com";
        final String password = "jordan23!";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.debug", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mrartem6695@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("mrartem6695@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

    }
}
