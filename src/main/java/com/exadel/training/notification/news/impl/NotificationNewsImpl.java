package com.exadel.training.notification.news.impl;

import com.exadel.training.model.News;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.notification.news.NotificationNews;
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

        newsService.insertNews(news);
    }

    public void sendNews(News news) throws NoSuchFieldException {
        newsService.insertNews(news);
    }
}
