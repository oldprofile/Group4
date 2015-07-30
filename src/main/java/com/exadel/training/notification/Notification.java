package com.exadel.training.notification;

import com.twilio.sdk.TwilioRestException;

import javax.mail.MessagingException;

/**
 * Created by HP on 28.07.2015.
 */
public interface Notification {
    void send (String to, String text) throws TwilioRestException, MessagingException;
}
