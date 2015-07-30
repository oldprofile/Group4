package com.exadel.training.repository.impl;

import com.exadel.training.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by HP on 23.07.2015.
 */
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select count(n) from News as n")
    int getCountOfPages();
}
