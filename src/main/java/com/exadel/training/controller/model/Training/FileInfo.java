package com.exadel.training.controller.model.Training;

import com.exadel.training.model.File;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Клим on 09.08.2015.
 */
public class FileInfo {
    private String name;
    private String link;
    private String trainingName;

    public FileInfo() {
    }

    public FileInfo(File file) {
        name = file.getName();
        link = file.getLink();
    }

    public FileInfo(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public static List<FileInfo> parseToList(List<File> files) {
        List<FileInfo> fileInfos = new ArrayList<>();
        for(File file: files)
            fileInfos.add(new FileInfo(file));
        return fileInfos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }
}
