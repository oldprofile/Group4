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
import org.springframework.web.context.request.async.DeferredResult;

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
    private Integer i = 0;

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<NewsPage> getNewsPage(@PathVariable("pageNumber") String pageNumber) throws NoSuchFieldException {
        Page<News> page = userNewsService.getNewsPage(Integer.parseInt(pageNumber));
        List<News> newsList = page.getContent();
        List<NewsPage> newsPageList = new ArrayList<>();
        for(News news : newsList) {
            newsPageList.add(NewsPage.parseNewsPage(news));
        }

        return  newsPageList;
    }

    @RequestMapping(value = "/count_of_news", method = RequestMethod.GET)
    public @ResponseBody Integer getCountOfNews() {
        return userNewsService.getCountOFNews();
    }

    @RequestMapping("/quotes")
    @ResponseBody
    public DeferredResult<Long> quotes(/*@RequestParam(required = false) Long timestamp*/) throws NoSuchFieldException {
        return userNewsService.getDefferdResult();
    }


    private List<News> getLatestNews(Long timestamp) {
        List<News> list = new ArrayList<>();
        News news = new News();
        news.setAction("as");
        list.add(news);

        return list;
    }
}
