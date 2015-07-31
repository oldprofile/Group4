package com.exadel.training.notification.twitter.impl;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HP on 31.07.2015.
 */
public class NotificationTwitterImpl {

    public void doTwitter(HttpServletRequest request) throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        RequestToken requestToken = twitter.getOAuthRequestToken();
        request.getSession().setAttribute("rt", requestToken);
        String authUrl = requestToken.getAuthenticationURL();
        request.setAttribute("so_daria", authUrl);
    }
}
