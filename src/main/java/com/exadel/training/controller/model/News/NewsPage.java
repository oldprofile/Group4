package com.exadel.training.controller.model.News;

import com.exadel.training.model.News;

/**
 * Created by HP on 23.07.2015.
 */
public class NewsPage {

    private String name;
    private String desciption;

    public NewsPage(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public static NewsPage parseNewsPage(News news) {
        NewsPage newsPage = new NewsPage();
         newsPage.setName(news.getUser().getName());
         newsPage.setDesciption(news.getAction());

        return newsPage;
    }
}
