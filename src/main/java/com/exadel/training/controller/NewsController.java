package com.exadel.training.controller;

import com.exadel.training.controller.model.News.NewsPage;
import com.exadel.training.model.News;
import com.exadel.training.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 23.07.2015.
 */
@Controller
@RequestMapping("/news_controller")
public class NewsController {

    @Autowired
    private NewsService userNewsService;

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<NewsPage> getNewsPage(@PathVariable("pageNumber") String pageNumber) {
        Page<News> page = userNewsService.getNewsPage(Integer.parseInt(pageNumber));
        List<News> newsList = page.getContent();
        List<NewsPage> newses = new ArrayList<>();
        for(News news : newsList) {
            newses.add(NewsPage.parseNewsPage(news));
        }

        return  newses;
    }
}
