package com.exadel.training.service.impl;

import com.exadel.training.controller.model.News.NewsPage;
import com.exadel.training.model.News;
import com.exadel.training.repository.impl.NewsRepository;
import com.exadel.training.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 23.07.2015.
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private static final int PAGE_SIZE = 2;
    private Map<DeferredResult, Long> newsRequests = new ConcurrentHashMap<DeferredResult, Long>();

   @Autowired
    private NewsRepository newsRepository;

    @Override
    public void insertNews(News news) {
          newsRepository.save(news);
          this.addToDeferredResult();
    }

    @Override
    public Page<News> getNewsPage(Integer pageNumber) {
        PageRequest request =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        return newsRepository.findAll(request);
    }

    @Override
    public List<News> getLatestNews(Long id) {
        return newsRepository.getLatestNews(id);
    }

    @Override
    public int getCountOFNews() {
        return newsRepository.getCountOfPages();
    }

    public  DeferredResult<List<NewsPage>> getDefferdResult(/*@RequestParam(required = false) Long timestamp*/) throws NoSuchFieldException {

        final DeferredResult<List<NewsPage>> result = new DeferredResult<List<NewsPage>>(null, Collections.emptyList());
        this.newsRequests.put(result,12L);

        result.onCompletion(new Runnable() {
            public void run() {
                newsRequests.remove(result);
            }
        });


        List<NewsPage> list = new ArrayList<>();
        for(News news : this.getLatestNews(12l)) {
            list.add(NewsPage.parseNewsPage(news));
        }

        if (!list.isEmpty()) {
            result.setResult(list);
        }

        return result;
    }

    public void addToDeferredResult() {

        for (Map.Entry<DeferredResult, Long> entry : this.newsRequests.entrySet()) {
            List<News> news = this.getLatestNews(entry.getValue());

            entry.getKey().setResult(news);
        }
    }
}
