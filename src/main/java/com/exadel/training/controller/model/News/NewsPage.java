package com.exadel.training.controller.model.News;

import com.exadel.training.controller.model.User.AllTrainingUserShort;
import com.exadel.training.model.News;

/**
 * Created by HP on 23.07.2015.
 */
public class NewsPage {

    private static final Object EMPTY = null;

    private String name;
    private String description;
    private AllTrainingUserShort training;
    private Boolean isRead;


    public NewsPage(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return description;
    }

    public void setDesrciption(String description) {
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


    public static NewsPage parseNewsPage(News news) throws NoSuchFieldException {
        NewsPage newsPage = new NewsPage();
         newsPage.setName(news.getUser().getName());
         newsPage.setDesrciption(news.getAction());
        if(news.getTraining() != EMPTY) {
         newsPage.setTraining(AllTrainingUserShort.parseAllTrainingUserShort(news.getTraining()));
        }
        newsPage.setIsRead(news.isRead());

        return newsPage;
    }
}
