package com.exadel.training.service.impl;

import com.exadel.training.model.User;
import com.exadel.training.repository.impl.UserRepository;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HP on 08.07.2015.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByID(long id) {
        User user = userRepository.getOne(id);
        return user;
    }
}
