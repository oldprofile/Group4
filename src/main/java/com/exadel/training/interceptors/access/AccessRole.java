package com.exadel.training.interceptors.access;

/**
 * Created by HP on 06.08.2015.
 */
public interface AccessRole {
    public boolean allowMethod(String login, String uri);
    public void addUser(String login);
    public void deleteUser(String login);
    public Long getRole(String login);
}
