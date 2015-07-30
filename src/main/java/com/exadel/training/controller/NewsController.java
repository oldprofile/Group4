package com.exadel.training.controller;

import com.exadel.training.controller.model.News.NewsPage;
import com.exadel.training.model.News;
import com.exadel.training.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 23.07.2015.
 */
@Controller
@RequestMapping("/news_controller")
public class NewsController {

    private Map<DeferredResult, Long> newsRequests = new ConcurrentHashMap<DeferredResult, Long>();
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
    public DeferredResult<List<News>> quotes(/*@RequestParam(required = false) Long timestamp*/) {

        final DeferredResult<List<News>> result = new DeferredResult<List<News>>(null, Collections.emptyList());
        this.newsRequests.put(result,1L);

        result.onCompletion(new Runnable() {
            public void run() {
                newsRequests.remove(result);
            }
        });

        List<News> list = getLatestNews(1L);
        if (!list.isEmpty()) {
            result.setResult(list);
        }

        return result;
    }

    public void postMessage(@RequestParam String message) {


        // Update all chat requests as part of the POST request
        // See Redis branch for a more sophisticated, non-blocking approach

        for (Map.Entry<DeferredResult<List<News>>, Integer> entry : this.newsRequests.entrySet()) {
            List<String> messages = this.chatRepository.getMessages(entry.getValue());

            entry.getKey().setResult(messages);
        }
    }
    private List<News> getLatestNews(Long timestamp) {
        List<News> list = new ArrayList<>();
        News news = new News();
        news.setAction("as");
        list.add(news);

        return list;
    }
}
