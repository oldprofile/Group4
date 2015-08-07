package com.exadel.training.notification.news.impl;

import com.exadel.training.model.*;
import com.exadel.training.notification.news.NotificationNews;
import com.exadel.training.notification.news.util.NotificationNewsUtil;
import com.exadel.training.service.NewsService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HP on 28.07.2015.
 */
@Service
public class NotificationNewsImpl implements NotificationNews {

    private final static int TRAINING_TYPE = 0;
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;
    @Autowired
    TrainingService trainingService;

    public void sendNews(String action, String with, String who) throws NoSuchFieldException {
        News news = new News();
        User user = userService.findUserByLogin(who);
        Training training = trainingService.getTrainingByName(with);

        news.setTraining(training);
        news.setUser(user);
        news.setAction(action);

        newsService.insertNews(news);
    }

    @Override
    public void sendNews(String action, User user, Training training) throws NoSuchFieldException {
        News news = new News();

        news.setTraining(training);
        news.setUser(user);
        news.setAction(action);
        news.setType(TRAINING_TYPE);

        newsService.insertNews(news);
    }

    @Override
    public void sendNews(String action, User user, TrainingFeedback trainingFeedback) throws NoSuchFieldException {
        News news = new News();

        news.setTrainingFeedback(trainingFeedback);
        news.setUser(user);
        news.setAction(action);
        news.setType(NotificationNewsUtil.TRAINING_FEEDBACK);

        newsService.insertNews(news);
    }

    @Override
    public void sendNews(String action, User user, CoachFeedback coachFeedback) throws NoSuchFieldException {
        News news = new News();

        news.setCoachFeedback(coachFeedback);
        news.setUser(user);
        news.setAction(action);
        news.setType(NotificationNewsUtil.COACH_FEEDBACK);

        newsService.insertNews(news);
    }

    @Override
    public void sendNews(String action, User user, UserFeedback userFeedback) throws NoSuchFieldException {
        News news = new News();

        news.setUserFeedback(userFeedback);
        news.setUser(user);
        news.setAction(action);
        news.setType(NotificationNewsUtil.USER_FEEDBACK);

        newsService.insertNews(news);
    }

    public void sendNews(News news) throws NoSuchFieldException {
        newsService.insertNews(news);
    }
}
