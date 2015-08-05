package com.exadel.training.repository.impl;

import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */
public interface TrainingFeedbackRepository extends JpaRepository<TrainingFeedback, Long> {
    List<TrainingFeedback> findFeedbackByTrainingOrderByDateAsc(Training training);

    @Query("select case when (count(t)>0) then true else false end from TrainingFeedback as t inner join  t.feedbacker as tf inner join t.training as tt where tf.login = ?1 and tt.name = ?2")
    Boolean checkFeedbackByLoginAndName(String login, String trainingName);

    @Query ("select tf from TrainingFeedback as tf where tf.training.name = ?1 and tf.feedbacker.login = ?2 and tf.date = ?3")
    TrainingFeedback findFeedbackByTrainingrAndDateAndFeedbacker(String trainingName, String feedbacker, Date date);
}
