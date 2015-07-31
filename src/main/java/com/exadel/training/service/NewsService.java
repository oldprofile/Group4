package com.exadel.training.service;

import com.exadel.training.model.News;
import org.springframework.data.domain.Page;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

/**
 * Created by HP on 23.07.2015.
 */
public interface NewsService {

    void insertNews(News news) throws NoSuchFieldException;
    void addToDeferredResult(long newNews) throws NoSuchFieldException;

    Page<News> getNewsPage(Integer page);
    List<News> getLatestNews(Long id);
    DeferredResult<Long> getDefferdResult(/*@RequestParam(required = false) Long timestamp*/) throws NoSuchFieldException;

    int getCountOFNews();
    long getCountOfUnreadNews();
}
