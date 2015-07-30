package com.exadel.training.service;

import com.exadel.training.model.Training;
import com.exadel.training.model.User;

import java.util.List;

/**
 * Created by HP on 29.07.2015.
 */
public interface SearchService {
    List<User> searchUsers(String searchWord);
    List<Training> searchTrainings(String searchWord);
}
