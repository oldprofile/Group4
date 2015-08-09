package com.exadel.training.repository.impl;

import com.exadel.training.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Клим on 09.08.2015.
 */
public interface FileRepository extends JpaRepository<File, Long> {
}
