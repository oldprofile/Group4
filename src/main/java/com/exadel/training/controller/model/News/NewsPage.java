package com.exadel.training.controller.model.News;

import com.exadel.training.controller.model.Feedback.CoachFeedbackGETModel;
import com.exadel.training.controller.model.Feedback.TrainingFeedbackGETModel;
import com.exadel.training.controller.model.Feedback.UserFeedbackGETModel;
import com.exadel.training.controller.model.User.AllTrainingUserShort;
import com.exadel.training.model.News;

/**
 * Created by HP on 23.07.2015.
 */
public class NewsPage {

    private static final Object EMPTY = null;

    private Long id;
    private String name;
    private String login;
    private String description;
    private int type;
    private AllTrainingUserShort training;
    private TrainingFeedbackGETModel trainingFeedbackGETModel;
    private CoachFeedbackGETModel coachFeedbackGETModel;
    private UserFeedbackGETModel userFeedbackGETModel;
    private Boolean isRead;

    public NewsPage(){
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AllTrainingUserShort getTraining() {
        return training;
    }

    public void setTraining(AllTrainingUserShort training) {
        this.training = training;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public TrainingFeedbackGETModel getTrainingFeedbackGETModel() {
        return trainingFeedbackGETModel;
    }

    public void setTrainingFeedbackGETModel(TrainingFeedbackGETModel trainingFeedbackGETModel) {
        this.trainingFeedbackGETModel = trainingFeedbackGETModel;
    }

    public UserFeedbackGETModel getUserFeedbackGETModel() {
        return userFeedbackGETModel;
    }

    public void setUserFeedbackGETModel(UserFeedbackGETModel userFeedbackGETModel) {
        this.userFeedbackGETModel = userFeedbackGETModel;
    }

    public CoachFeedbackGETModel getCoachFeedbackGETModel() {
        return coachFeedbackGETModel;
    }

    public void setCoachFeedbackGETModel(CoachFeedbackGETModel coachFeedbackGETModel) {
        this.coachFeedbackGETModel = coachFeedbackGETModel;
    }



    public static NewsPage parseNewsPage(News news) throws NoSuchFieldException {
        NewsPage newsPage = new NewsPage();

         newsPage.setName(news.getUser().getName());
         newsPage.setDescription(news.getAction());

        if(news.getTraining() != EMPTY) {
         newsPage.setTraining(AllTrainingUserShort.parseAllTrainingUserShort(news.getTraining()));
        }
        if(news.getTrainingFeedback() != EMPTY) {
            newsPage.setTrainingFeedbackGETModel(TrainingFeedbackGETModel.parseTrainingFeedback(news.getTrainingFeedback()));
        }
        if(news.getCoachFeedback() != EMPTY) {
            newsPage.setCoachFeedbackGETModel(CoachFeedbackGETModel.parseCoachFeedback(news.getCoachFeedback()));
        }
        if(news.getCoachFeedback() != EMPTY) {
            newsPage.setUserFeedbackGETModel(UserFeedbackGETModel.parseToUserFeedbackModel(news.getUserFeedback()));
        }

        newsPage.setType(news.getType());
        newsPage.setIsRead(news.isRead());
        newsPage.setLogin(news.getUser().getLogin());
        newsPage.setId(news.getId());


        return newsPage;
    }
}
