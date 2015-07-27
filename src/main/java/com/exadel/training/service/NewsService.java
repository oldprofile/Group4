package com.exadel.training.service;

import com.exadel.training.model.News;
import org.springframework.data.domain.Page;

/**
 * Created by HP on 23.07.2015.
 */
public interface NewsService {
    Page<News> getNewsPage(Integer page);
    int getCountOFPages();
}
