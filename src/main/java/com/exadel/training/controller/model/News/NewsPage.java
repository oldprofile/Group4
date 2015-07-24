package com.exadel.training.controller.model.News;

import com.exadel.training.model.News;
import org.springframework.data.domain.Page;

/**
 * Created by HP on 23.07.2015.
 */
public class NewsPage {

    private Page<News> userNewses;

    public NewsPage(){
    }

    public Page<News> getUserNewses() {
        return userNewses;
    }

    public void setUserNewses(Page<News> userNewses) {
        this.userNewses = userNewses;
    }
}
