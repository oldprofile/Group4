package com.exadel.training.repository.impl;

import com.exadel.training.model.EntityFile;
import com.exadel.training.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Клим on 09.08.2015.
 */
public interface FileRepository extends JpaRepository<EntityFile, Long> {
    @Query("select f from EntityFile as f where f.training = ?1")
    List<EntityFile> findFilesByTraining(Training training);

    @Query("select f from EntityFile as f inner join f.training as tr where f.name = ?1 and tr.name = ?2")
    EntityFile findFilesByNameAndTrainingName(String fileName, String trainingName);

    @Modifying
    @Query(value = "delete from files where id = ?1", nativeQuery = true)
    void deleteFileById(int id);
}
