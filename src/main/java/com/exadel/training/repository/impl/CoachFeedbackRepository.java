package com.exadel.training.repository.impl;

import com.exadel.training.model.CoachFeedback;
import com.exadel.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */
public interface CoachFeedbackRepository extends JpaRepository<CoachFeedback, Long> {
    List<CoachFeedback> findFeedbackByCoachOrderByDateAsc(User coach);
}
