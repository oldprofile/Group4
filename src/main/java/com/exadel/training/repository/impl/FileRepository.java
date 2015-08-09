package com.exadel.training.repository.impl;

import com.exadel.training.model.File;
import com.exadel.training.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Клим on 09.08.2015.
 */
public interface FileRepository extends JpaRepository<File, Long> {
    @Query("select f from File as f where f.training = ?1")
    List<File> findFilesByTraining(Training training);

    @Query("select f from File as f where f.name = ?1")
    File findFilesByName(String name);

    @Modifying
    @Query(value = "delete from files where name = ?1", nativeQuery = true)
    void deleteFileByName(String fileName);
}
