package com.exadel.training.service.impl;

import com.exadel.training.model.News;
import com.exadel.training.repository.impl.NewsRepository;
import com.exadel.training.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by HP on 23.07.2015.
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private static final int PAGE_SIZE = 2;

   @Autowired
    private NewsRepository newsRepository;

    @Override
    public void insertNews(News news) {
          newsRepository.save(news);
    }

    @Override
    public Page<News> getNewsPage(Integer pageNumber) {
        PageRequest request =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        return newsRepository.findAll(request);
    }

    @Override
    public int getCountOFPages() {
        return newsRepository.getCountOfPages();
    }
}
