package com.exadel.training.service.impl;

import com.exadel.training.common.RoleType;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.UserRepository;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByID(long id) {
        User user = userRepository.getOne(id);
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login,long password) {
        return userRepository.findUserByLoginAndPassword(login,password);
    }

    @Override
    public List<User> findUserByRole(RoleType type) throws NoSuchFieldException {
       return userRepository.findUsersByRole(RoleType.parseRoleTypeToInt(type));
    }

    @Override
    public List<Training> selectAllTraining(String login) {
        return userRepository.selectAllTraining(login);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
