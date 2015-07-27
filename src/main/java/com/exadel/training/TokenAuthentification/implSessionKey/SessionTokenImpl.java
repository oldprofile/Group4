package com.exadel.training.tokenAuthentification.implSessionKey;

import com.exadel.training.tokenAuthentification.SessionToken;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 27.07.2015.
 */
@Singleton
@Service
public class SessionTokenImpl implements SessionToken {

    private Map<String,String> tokens;

    public SessionTokenImpl() {
        tokens = new ConcurrentHashMap<>();
    }

    public Map<String, String> getTokens() {
        return tokens;
    }

    public void setKeys(Map<String, String> tokens) {
        this.tokens = tokens;
    }

    public String getToken(String login) {
       return tokens.get(login);
    }

    public void addToken(String login, String key) {
        tokens.put(login, key);
    }

    public Boolean containsToken(String token) {
       return tokens.containsValue(token);
    }

    @Override
    public void deleteToken(String login, String token) {
        tokens.remove(login, token);
    }
}
