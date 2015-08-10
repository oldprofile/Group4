package com.exadel.training.service.impl;

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

    private static final int PAGE_SIZE = 10;
    private Map<DeferredResult, Long> newsRequests = new ConcurrentHashMap<DeferredResult, Long>();

   @Autowired
    private NewsRepository newsRepository;

    @Override
    public void insertNews(News news) throws NoSuchFieldException {
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
    public void updateAllUnreadToReadNews() throws NoSuchFieldException {
        List<News> newsList = newsRepository.updateAllUnreadToReadNews();

        for(News news : newsList) {
            news.setRead(true);
        }

        this.addToDeferredResult();
    }

    @Override
    public int getCountOFNews() {
        return newsRepository.getCountOfNews();
    }

    @Override
    public long getCountOfUnreadNews() {
        return newsRepository.getCountOfUnreadNews();
    }

    public  DeferredResult<Long> getDefferdResult(Long state) throws NoSuchFieldException {
        final DeferredResult<Long> deferredResult = new DeferredResult<Long>(null, Collections.emptyList());
        newsRequests.put(deferredResult, state);

        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                newsRequests.remove(deferredResult);
            }
        });

        Long newNews = this.newsRepository.getCountOfUnreadNews();

        if (state != newNews) {
            deferredResult.setResult(newNews);
        }

        return deferredResult;
        }

    public void addToDeferredResult() throws NoSuchFieldException {
        for (Map.Entry<DeferredResult, Long> entry : this.newsRequests.entrySet()) {
            Long news = this.newsRepository.getCountOfUnreadNews();

                entry.getKey().setResult(news);
        }

    }

    @Override

    public void changeUnread(long id) throws NoSuchFieldException {
        News news = newsRepository.findOne(id);

        news.setRead(true);
        this.addToDeferredResult();
    }
}
