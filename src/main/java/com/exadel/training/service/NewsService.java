package com.exadel.training.service;

import com.exadel.training.model.News;
import org.springframework.data.domain.Page;

/**
 * Created by HP on 23.07.2015.
 */
public interface NewsService {

    void insertNews(News news);

    Page<News> getNewsPage(Integer page);

    int getCountOFNews();
}
