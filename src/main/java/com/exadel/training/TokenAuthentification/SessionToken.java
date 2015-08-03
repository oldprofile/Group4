package com.exadel.training.tokenAuthentification;

/**
 * Created by HP on 27.07.2015.
 */
public interface SessionToken {
    void addToken(String login, String key);
    void deleteToken(String login,String token);

    String getToken(String login);

    Boolean containsToken(String token);
    Boolean isEmpty();

}
