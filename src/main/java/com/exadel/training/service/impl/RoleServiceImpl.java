package com.exadel.training.service.impl;

import com.exadel.training.model.Role;
import com.exadel.training.repository.impl.RoleRepository;
import com.exadel.training.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HP on 09.07.2015.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getRoleByID(long id) {
        Role role = roleRepository.findOne(id);
        return role;
    }
}
