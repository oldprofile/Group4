package com.exadel.training.interceptors.access.impl;

import com.exadel.training.interceptors.access.Access;
import com.exadel.training.interceptors.access.AccessRole;
import com.exadel.training.model.Role;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 06.08.2015.
 */
@Singleton
@Service
public class AccessRoleImpl implements AccessRole {

    @Autowired
    private Access access;
    @Autowired
    private UserService userService;
    private Map<String, PriorityQueue<Long>> priorityQueueMap;

    public AccessRoleImpl() {
        priorityQueueMap = new ConcurrentHashMap<>();
    }

    public boolean allowMethod(String login, String uri) {
        PriorityQueue<Long> roles = priorityQueueMap.get(login);
        Long role = roles.peek();

        return  access.isAllowMethod(role, uri);
    }

    public void addUser(String login) {
        List<Role> roles = userService.findRolesOfUser(login);
        PriorityQueue<Long> longPriorityQueue = new PriorityQueue<>();

        for(Role role : roles) {
            longPriorityQueue.add(role.getId());
        }

        priorityQueueMap.put(login, longPriorityQueue);
    }

    @Override
    public void deleteUser(String login) {
        priorityQueueMap.remove(login);
    }

    public Long getRole(String login) {
        return priorityQueueMap.remove(login).peek();
    }
}
