package com.exadel.training.controller.model.News;

import com.exadel.training.controller.model.User.AllTrainingUserShort;
import com.exadel.training.model.News;

/**
 * Created by HP on 23.07.2015.
 */
public class NewsPage {

    private static final Object EMPTY = null;

    private Long id;
    private String name;
    private String description;
    private AllTrainingUserShort training;
    private Boolean isRead;


    public NewsPage(){
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


    public static NewsPage parseNewsPage(News news) throws NoSuchFieldException {
        NewsPage newsPage = new NewsPage();

         newsPage.setName(news.getUser().getName());
         newsPage.setDescription(news.getAction());

        if(news.getTraining() != EMPTY) {
         newsPage.setTraining(AllTrainingUserShort.parseAllTrainingUserShort(news.getTraining()));
        }
        newsPage.setIsRead(news.isRead());
        newsPage.setId(news.getId());

        return newsPage;
    }
}
