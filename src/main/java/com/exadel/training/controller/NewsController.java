package com.exadel.training.controller;

import com.exadel.training.model.News;
import com.exadel.training.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by HP on 23.07.2015.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService userNewsService;

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public String getRunbookPage(@PathVariable("pageNumber")Integer pageNumber) {
        Page<News> page = userNewsService.getNewsPage(pageNumber);

       
        return "deploymentLog";
    }
}
