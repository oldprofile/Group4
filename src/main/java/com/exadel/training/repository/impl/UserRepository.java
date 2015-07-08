package com.exadel.training.repository.impl;

import com.exadel.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HP on 08.07.2015.
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
