package com.exadel.training.repository.impl;

import com.exadel.training.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by HP on 23.07.2015.
 */
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select count(n) from News as n")
    int getCountOfPages();

    @Query("select n from News as n where n.id > ?1")
    List<News> getLatestNews(Long id);
}
