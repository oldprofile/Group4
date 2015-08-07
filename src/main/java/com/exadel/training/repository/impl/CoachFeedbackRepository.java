package com.exadel.training.repository.impl;

import com.exadel.training.model.CoachFeedback;
import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */
public interface CoachFeedbackRepository extends JpaRepository<CoachFeedback, Long> {
    List<CoachFeedback> findFeedbackByCoachOrderByDateAsc(User coach);
    @Query("select cf from CoachFeedback as cf where cf.coach.login = ?1 and cf.feedbacker.login = ?2 and cf.date = ?3")
    CoachFeedback findFeedbackByCoachAndDateAndFeedbacker(String coach, String feedbacker, Date date);
}
