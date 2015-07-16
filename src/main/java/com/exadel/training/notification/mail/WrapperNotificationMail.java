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
    private String password = "jrcfyf";
    private String host = "localhost";
    private Properties properties = System.getProperties();
    private Session session;

    String mailSmtpHost = "localhost";

    String mailTo = "artem6695@mail.ru";
    String mailCc = "artem6695@mail.ru";
    String mailFrom = "artem6695@mail.ru";
    String mailSubject = "Email from Java";
    String mailText = "This is an email from Java";

    public WrapperNotificationMail() {


    }
    public void sendMessage(String to) throws MessagingException {
        final String username = "mrartem6695@gmail.com";
        final String password = "jordan23!";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");

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
