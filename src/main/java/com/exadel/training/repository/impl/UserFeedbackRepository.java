package com.exadel.training.repository.impl;

import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */
public interface UserFeedbackRepository extends JpaRepository <UserFeedback, Long> {
    List<UserFeedback> findFeedbackByUserOrderByDateAsc(User user);
    @Query ("select uf from UserFeedback as uf where uf.user.login = ?1 and uf.feedbacker.login = ?2 and uf.date = ?3")
    UserFeedback findFeedbackByUserAndDateAndFeedbacker(String user, String feedbacker, Date date);
}
