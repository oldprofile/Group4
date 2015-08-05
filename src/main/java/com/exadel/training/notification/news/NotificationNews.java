package com.exadel.training.notification.news;

import com.exadel.training.model.News;
import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.User;

/**
 * Created by HP on 28.07.2015.
 */
public interface NotificationNews {

    void sendNews(News news) throws NoSuchFieldException;
    void sendNews(String action, String with, String who) throws NoSuchFieldException;
    void sendNews(String action, User user, Training training) throws NoSuchFieldException;
    void sendNews(String action, User user, TrainingFeedback trainingFeedback) throws NoSuchFieldException;
}
